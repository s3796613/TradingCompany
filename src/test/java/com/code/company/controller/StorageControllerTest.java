package com.code.company.controller;

import com.code.company.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class StorageControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

    }
    @Test
    public void getInventory() throws Exception {
        String uri = "/storage";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"startDate\":null,\"endDate\":null,\"inventoryList\":[{\"productID\":1,\"productName\":\"Vitamin E\",\"received\":4,\"delivery\":0,\"balance\":4},{\"productID\":2,\"productName\":\"Longos - Chicken Wings\",\"received\":4,\"delivery\":2,\"balance\":2},{\"productID\":3,\"productName\":\"Spinach - Frozen\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":4,\"productName\":\"Tea - English Breakfast\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":5,\"productName\":\"Peach - Halves\",\"received\":0,\"delivery\":0,\"balance\":0}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, content);
    }

    @Test
    public void getInventoryByDate() throws Exception {
        String uri = "/storage/find?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"startDate\":\"2021-05-24\",\"endDate\":\"2021-05-25\",\"inventoryList\":[{\"productID\":1,\"productName\":\"Vitamin E\",\"received\":4,\"delivery\":0,\"balance\":4},{\"productID\":2,\"productName\":\"Longos - Chicken Wings\",\"received\":4,\"delivery\":2,\"balance\":2},{\"productID\":3,\"productName\":\"Spinach - Frozen\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":4,\"productName\":\"Tea - English Breakfast\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":5,\"productName\":\"Peach - Halves\",\"received\":0,\"delivery\":0,\"balance\":0}]}";      String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, content);
    }
    @Test
    public void getInventoryByDateNoEndDate() throws Exception {
        String uri = "/storage/find?start=2021-05-24";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"startDate\":\"2021-05-24\",\"endDate\":\"2021-05-25\",\"inventoryList\":[{\"productID\":1,\"productName\":\"Vitamin E\",\"received\":4,\"delivery\":0,\"balance\":4},{\"productID\":2,\"productName\":\"Longos - Chicken Wings\",\"received\":4,\"delivery\":2,\"balance\":2},{\"productID\":3,\"productName\":\"Spinach - Frozen\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":4,\"productName\":\"Tea - English Breakfast\",\"received\":0,\"delivery\":0,\"balance\":0},{\"productID\":5,\"productName\":\"Peach - Halves\",\"received\":0,\"delivery\":0,\"balance\":0}]}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, content);
    }
}