package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.Revenue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class RevenueControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void getCustomerRevenue() throws Exception {
        String uri = "/revenue/customer/1?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "{\"name\":\"Diago Trade\",\"startDate\":\"2021-05-24\",\"endDate\":\"2021-05-25\",\"revenue\":236.66}";
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, content);
    }

    @Test(expected = Exception.class)
    public void getCustomerRevenueNoCustomer() throws Exception {
        String uri = "/revenue/customer/6?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getStaffRevenue() throws Exception {
        String uri = "/revenue/staff/1?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String expected = "";
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"name\":\"Jane\",\"startDate\":\"2021-05-24\",\"endDate\":\"2021-05-25\",\"revenue\":236.66}", content);
    }
    @Test(expected = Exception.class)
    public void getStaffRevenueNoStaff() throws Exception {
        String uri = "/revenue/staff/6?start=2021-05-24&end=2021-05-25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}