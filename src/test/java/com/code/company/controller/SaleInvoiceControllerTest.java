package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class SaleInvoiceControllerTest extends AbstractTest {
    String allSales = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99,\"totalValue\":43.96},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]},{\"id\":2,\"date\":\"2021-05-24\",\"staffID\":2,\"deliveryID\":2,\"customerID\":2,\"staffName\":\"Jack\",\"customerName\":\"Jack's Whole Sale\",\"saleDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}],\"totalPages\":1,\"totalElement\":2,\"pageSize\":20}";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getAll() throws Exception {
        String uri = "/sale";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allSales, content);

    }

    @Test
    public void getById() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String expected = "{\"id\":1,\"date\":\"2021-05-24\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99,\"totalValue\":43.96},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void getByIdNotFound() throws Exception {
        String uri = "/sale/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"status\":\"Bad request\",\"error\":\"Sale invoice id not found!\",\"statusCode\":404}",content);

    }

    @Test
    public void add() throws Exception {
        String uri = "/sale";
        String inputJson = "{\"date\":\"2021-04-30\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        uri = "/sale/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String expected = "{\"id\":3,\"date\":\"2021-04-30\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void updateStaff() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String oldInvoice = mvcResult.getResponse().getContentAsString();

        uri = "/sale/1?staffID=2";
                 mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String newInvoice= mvcResult.getResponse().getContentAsString();
        assertNotEquals(oldInvoice, newInvoice);

    }
    @Test
    public void updateDelivery() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String oldInvoice = mvcResult.getResponse().getContentAsString();

        uri = "/sale/1?deliveryID=2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String newInvoice= mvcResult.getResponse().getContentAsString();
        assertNotEquals(oldInvoice, newInvoice);
    }

    @Test
    public void updateCustomer() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String oldInvoice = mvcResult.getResponse().getContentAsString();

        uri = "/sale/1?customerID=2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String newInvoice= mvcResult.getResponse().getContentAsString();
        assertNotEquals(oldInvoice, newInvoice);
    }
    @Test
    public void updateDate() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String oldInvoice = mvcResult.getResponse().getContentAsString();

        uri = "/sale/1?date=2021-04-20";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String newInvoice= mvcResult.getResponse().getContentAsString();
        assertNotEquals(oldInvoice, newInvoice);
    }

    @Test
    public void update() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String oldInvoice = mvcResult.getResponse().getContentAsString();

        uri = "/sale/1?staffID=2&deliveryID=2&customerID=2&date=2021-04-20";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String newInvoice= mvcResult.getResponse().getContentAsString();
        assertNotEquals(oldInvoice, newInvoice);
    }

    @Test
    public void delete() throws Exception {
        String uri = "/sale/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/sale/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"status\":\"Bad request\",\"error\":\"Sale invoice id not found!\",\"statusCode\":404}",content);
    }
    @Test(expected = Exception.class)
    public void deleteNoIdFound() throws Exception {
        String uri = "/sale/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void find() throws Exception {
        String uri = "/sale/find?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allSales,content);
    }

    @Test
    public void findwithCustomerIDandBetweenDate() throws Exception {
        String uri = "/sale/find?customerID=1&start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99,\"totalValue\":43.96},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }
    @Test
    public void findwithStaffIDandBetweenDate() throws Exception {
        String uri = "/sale/find?staffID=1&start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99,\"totalValue\":43.96},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }
    @Test
    public void findwithStaffIDandCustomerIDandBetweenDate() throws Exception {
        String uri = "/sale/find?staffID=1&customerID=1&start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staffID\":1,\"deliveryID\":1,\"customerID\":1,\"staffName\":\"Jane\",\"customerName\":\"Diago Trade\",\"saleDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99,\"totalValue\":43.96},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35,\"totalValue\":192.7}]}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }
}