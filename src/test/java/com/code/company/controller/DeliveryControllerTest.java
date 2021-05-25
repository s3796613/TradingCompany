package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.DeliveryNote;
import com.code.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class DeliveryControllerTest  extends AbstractTest {
    String allDelis = "{\"content\":[{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]},{\"id\":2,\"date\":\"2021-05-24\",\"staff\":{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},\"packageDetails\":[]}],\"totalPages\":1,\"totalElement\":2,\"pageSize\":20}";
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getAll() throws Exception {
        String uri = "/delivery";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(allDelis, content);
    }

    @Test
    public void getById() throws Exception {
        String uri = "/delivery/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"id\":1,\"date\":\"2021-05-24\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(expected, content);
    }
    @Test
    public void getByIdNotFound() throws Exception {
        String uri = "/delivery/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void add() throws Exception {
        String uri = "/delivery";
        String inputJson = "{\"date\":\"2021-05-30\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        uri = "/delivery/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String expected = "{\"id\":3,\"date\":\"2021-05-30\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }

    @Test
    public void save() throws Exception {
        String uri = "/delivery/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String oldOrder = mvcResult.getResponse().getContentAsString();

        uri = "/delivery/1";
        //Change date
        String inputJson = "{\"date\":\"2021-04-20\",\"staff\":{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},\"packageDetails\":[{\"product\":{\"id\":2,\"name\":\"Longos - Chicken Wings\",\"model\":\"YWO2\",\"brand\":\"Ropinirole Hydrochloride\",\"company\":\"Hauck Inc\",\"category\":{\"id\":2,\"name\":\"Food\"},\"description\":\"In congue. Etiam justo. Etiam pretium iaculis justo.\",\"price\":96.35},\"quantity\":2,\"price\":96.35}]}";
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String modifiedOrder = mvcResult.getResponse().getContentAsString();

        assertNotEquals(oldOrder,modifiedOrder);
    }

    @Test
    public void delete() throws Exception {
        String uri = "/delivery/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/delivery/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
    }
    @Test(expected = Exception.class)
    public void deleteNoIdFound() throws Exception {
        String uri = "/delivery/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void find() throws Exception {
        String uri = "/delivery/find?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allDelis,content);
    }
}