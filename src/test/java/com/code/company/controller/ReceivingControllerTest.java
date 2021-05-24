package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.ReceivingNote;
import com.code.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class ReceivingControllerTest extends AbstractTest {
    String allReceives = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"orderID\":1,\"receivingDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]},{\"id\":2,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"orderID\":2,\"receivingDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}],\"totalPages\":1,\"totalElement\":2,\"pageSize\":20}";
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getAll() throws Exception {
        String uri = "/receive";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allReceives, content);
    }

    @Test
    public void getById() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected ="{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"orderID\":1,\"receivingDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }
    @Test(expected = Exception.class)
    public void getByIdNoObejctfound() throws Exception {
        String uri = "/receive/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void add() throws Exception {
        String uri = "/receive";
        String inputJson = "{\"date\":\"2021-05-25\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"orderID\":1,\"receivingDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        uri = "/receive/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String expected = "{\"id\":3,\"date\":\"2021-05-25\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"orderID\":1,\"receivingDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void updateAll() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String oldNote = mvcResult.getResponse().getContentAsString();

        uri = "/receive/1?date=2021-05-23&staffID=2&orderID=1";
        //Change All
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String newNote = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldNote,newNote);
    }

    @Test
    public void updateStaffID() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String oldNote = mvcResult.getResponse().getContentAsString();

        uri = "/receive/1?staffID=2";
        //Change staff id
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String newNote = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldNote,newNote);
    }
    @Test
    public void updateOrderID() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String oldNote = mvcResult.getResponse().getContentAsString();

        uri = "/receive/1?orderID=1";
        //Change order id
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String newNote = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldNote,newNote);
    }
    @Test
    public void updateDate() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String oldNote = mvcResult.getResponse().getContentAsString();

        uri = "/receive/1?date=2021-05-23";
        //Change date
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String newNote = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldNote,newNote);
    }

    @Test(expected = Exception.class)
    public void delete() throws Exception {
        String uri = "/receive/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/receive/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
    }
    @Test(expected = Exception.class)
    public void deleteNoIdFound() throws Exception {
        String uri = "/receive/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }


    @Test
    public void find() throws Exception {
        String uri = "/receive/find?startDate=2021-05-24&endDate=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allReceives,content);
    }
    @Test
    public void findNothing() throws Exception {
        String uri = "/receive/find?startDate=2021-05-25&endDate=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"content\":[],\"totalPages\":0,\"totalElement\":0,\"pageSize\":20}",content);
    }
}