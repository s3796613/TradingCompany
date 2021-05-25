package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.Staff;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StaffControllerTest extends AbstractTest {
    String allStaff = "{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"},{\"id\":2,\"name\":\"Jack\",\"address\":\"23 Hung Vuong\",\"email\":\"jack@edu.com\",\"phone\":\"092713\"},{\"id\":3,\"name\":\"Alysia Scopham\",\"address\":\"56168 7th Street\",\"email\":\"ascopham6@plala.or.jp\",\"phone\":\"128 581 2103\"},{\"id\":4,\"name\":\"Cornie Kalf\",\"address\":\"01 Michigan Crossing\",\"email\":\"ckalf7@nytimes.com\",\"phone\":\"134 899 3532\"},{\"id\":5,\"name\":\"Pauletta Como\",\"address\":\"67778 Del Sol Plaza\",\"email\":\"pcomo8@so-net.ne.jp\",\"phone\":\"958 169 1256\"}],\"totalPages\":1,\"totalElement\":5,\"pageSize\":20}";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void getAll() throws Exception {
        String uri = "/staff";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(allStaff, content);
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
    public void deleteById() throws Exception {
        String uri = "/staff/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
    }
    @Test
    public void deleteStaffWithDependencies() throws Exception {
        String uri = "/staff/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/staff/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Staff staff = super.mapFromJson(content, Staff.class);
        assertEquals("Jack",staff.getName());
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

    @Test
    public void findByName() throws Exception {
        String uri = "/staff/find?name=Jane";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}", content);
    }
    @Test
    public void findByAddress() throws Exception {
        String uri = "/staff/find?address=Distric 7";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}", content);
    }
    @Test
    public void findByEmail() throws Exception {
        String uri = "/staff/find?email=jane@hotmail.com";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}", content);
    }
    @Test
    public void findByPhone() throws Exception {
        String uri = "/staff/find?phone=0928361";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}", content);
    }
    @Test
    public void find() throws Exception {
        String uri = "/staff/find?name=Jane&address=Distric 7&email=jane@hotmail.com&phone=0928361";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals("{\"content\":[{\"id\":1,\"name\":\"Jane\",\"address\":\"Distric 7\",\"email\":\"jane@hotmail.com\",\"phone\":\"0928361\"}],\"totalPages\":1,\"totalElement\":1,\"pageSize\":20}", content);
    }
    @Test
    public void findNoParam() throws Exception {
        String uri = "/staff/find";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(allStaff, content);
    }

    @Test
    public void getStaffSaleNoParam() throws Exception {
        String uri = "/staff/1/sale?start";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);

        String content = mvcResult.getResponse().getContentAsString();
    }
}