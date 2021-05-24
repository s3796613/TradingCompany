package com.code.company.controller;

import com.code.company.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class CustomerControllerTest extends AbstractTest {
    String allCustomers = "{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},{\"id\":3,\"name\":\"Alysia Scopham\",\"address\":\"56168 7th Street\",\"email\":\"ascopham6@plala.or.jp\",\"phone\":\"128 581 2103\"},{\"id\":4,\"name\":\"Cornie Kalf\",\"address\":\"01 Michigan Crossing\",\"email\":\"ckalf7@nytimes.com\",\"phone\":\"134 899 3532\"},{\"id\":5,\"name\":\"Pauletta Como\",\"address\":\"67778 Del Sol Plaza\",\"email\":\"pcomo8@so-net.ne.jp\",\"phone\":\"958 169 1256\"}],\"totalPages\":1,\"totalElement\":5,\"pageSize\":20}";
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getAll() throws Exception {
        String uri = "/customer";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print(content);
        //assertEquals(allStaff, content);
    }

    @Test
    public void getById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void find() {
    }

    @Test
    public void findSaleInvoice() {
    }
}