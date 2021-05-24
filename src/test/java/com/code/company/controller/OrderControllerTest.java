package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.Assert.*;

public class OrderControllerTest extends AbstractTest {
    String allOrders= "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"provider\":{\"id\":1,\"name\":\"Lee Sin\",\"address\":\"12 Blair Rd\",\"email\":\"lee@gmail.com\",\"phone\":\"0192384214\",\"fax\":\"1233555\",\"contactPerson\":\"Lie\"},\"packageDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]},{\"id\":2,\"date\":\"2021-05-24\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"provider\":{\"id\":2,\"name\":\"Victor Supplies\",\"address\":\"20 Nguyen Van Linh\",\"email\":\"vsup@gmail.com\",\"phone\":\"02937741\",\"fax\":\"123456\",\"contactPerson\":\"Vicitor\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}],\"totalPages\":1,\"totalElement\":2,\"pageSize\":20}";
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

    }
    @Test
    public void getAll() throws Exception {
        String uri = "/order";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allOrders, content);
    }

    @Test
    public void getById() throws Exception {
        String uri = "/order/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String expected = "{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"provider\":{\"id\":1,\"name\":\"Lee Sin\",\"address\":\"12 Blair Rd\",\"email\":\"lee@gmail.com\",\"phone\":\"0192384214\",\"fax\":\"1233555\",\"contactPerson\":\"Lie\"},\"packageDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void add() throws Exception {
        String uri = "/order";
        String inputJson = "{\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"provider\":{\"id\":1,\"name\":\"Lee Sin\",\"address\":\"12 Blair Rd\",\"email\":\"lee@gmail.com\",\"phone\":\"0192384214\",\"fax\":\"1233555\",\"contactPerson\":\"Lie\"},\"packageDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        uri = "/order/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String expected = "{\"id\":3,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"provider\":{\"id\":1,\"name\":\"Lee Sin\",\"address\":\"12 Blair Rd\",\"email\":\"lee@gmail.com\",\"phone\":\"0192384214\",\"fax\":\"1233555\",\"contactPerson\":\"Lie\"},\"packageDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void update() throws Exception {
        String uri = "/order/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String oldOrder = mvcResult.getResponse().getContentAsString();

        uri = "/order/1";
        //Change date
        String inputJson = "{\"date\":\"2021-05-25\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"provider\":{\"id\":1,\"name\":\"Lee Sin\",\"address\":\"12 Blair Rd\",\"email\":\"lee@gmail.com\",\"phone\":\"0192384214\",\"fax\":\"1233555\",\"contactPerson\":\"Lie\"},\"packageDetails\":[{\"product\":{\"id\":1,\"name\":\"Vitamin E\",\"model\":\"12HAC\",\"brand\":\"Daily's\",\"company\":\"DonyJ Corp\",\"category\":{\"id\":1,\"name\":\"Medicine\"},\"description\":\"Vitamin E for daily use\",\"price\":10.99},\"quantity\":4,\"price\":10.99},{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String modifiedOrder = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldOrder,modifiedOrder);
    }

    @Test(expected = Exception.class)
    public void delete() throws Exception {
        String uri = "/order/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/order/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
    }
    @Test(expected = Exception.class)
    public void deleteNoIdFound() throws Exception {
        String uri = "/order/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void find() throws Exception {
        String uri = "/order/find?startDate=2021-05-24&endDate=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allOrders,content);

    }
}