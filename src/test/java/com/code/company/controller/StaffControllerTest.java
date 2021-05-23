package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;

public class StaffControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void getById() throws Exception {
        String uri = "/staff/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals("jane@hotmail.com", staff.getEmail());
    }
    @Test
    public void add() throws Exception {
        String uri = "/staff";
        Staff s2 = new Staff("Hugo", "23 Hung Vuong","Hugo@edu.com", "092713");s2.setId(Long.parseLong("3"));
        //s2.setId(Long.parseLong("3"));
        String inputJson = super.mapToJson(s2);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        uri = "/staff/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals("Hugo@edu.com", staff.getEmail());
    }

    //Update name
    @Test
    public void updateName() throws Exception {
        String uri = "/staff/2?name=Lemon";
        Staff staff2 = new Staff();
        staff2.setName("Lemon");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals(staff2.getName(), staff.getName());

    }
    //Update address
    @Test
    public void updateAddress() throws Exception {
        String uri = "/staff/2?address=42 Green Waverley";
        Staff staff2 = new Staff();
        staff2.setAddress("42 Green Waverley");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals(staff2.getAddress(), staff.getAddress());

    }
    //Update email
    @Test
    public void updateEmail() throws Exception {
        String uri = "/staff/2?email=jackws@gmail.com";
        Staff staff2 = new Staff();
        staff2.setEmail("jackws@gmail.com");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals(staff2.getEmail(), staff.getEmail());

    }
    //Update phone
    @Test
    public void updatePhone() throws Exception {
        String uri = "/staff/2?phone=0937421321";
        Staff staff2 = new Staff();
        staff2.setPhone("0937421321");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals(staff2.getPhone(), staff.getPhone());
    }
    //Update all
    @Test
    public void updateAll() throws Exception {
        String uri = "/staff/2?name=Lemon&address=42 Green Waverley&email=Lemon@abc.vom&phone=0937421321";
        Staff staff2 = new Staff("Lemon","42 Green Waverley","Lemon@abc.vom","0937421321");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals(staff2.getName(), staff.getName());
        assertEquals(staff2.getAddress(), staff.getAddress());
        assertEquals(staff2.getEmail(), staff.getEmail());
        assertEquals(staff2.getPhone(), staff.getPhone());
    }

}