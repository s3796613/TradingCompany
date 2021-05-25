package com.code.company.controller;

import com.code.company.AbstractTest;
import com.code.company.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.checkerframework.checker.units.qual.C;
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
    public void getById() throws Exception {
        String uri = "/customer/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Customer customer = super.mapFromJson(content, Customer.class);
        assertEquals("Diago Trade", customer.getName());
        assertEquals("diagotrade@gmail.com", customer.getEmail());
    }

    @Test
    public void deleteById() throws Exception {
        String uri = "/customer/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/3";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"status\":\"Bad request\",\"error\":\"Did not find any customer with the id: 3\",\"statusCode\":404}",content);
    }
    @Test
    public void deleteCustomerWithDependencies() throws Exception {
        String uri = "/customer/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/1";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Customer customer = super.mapFromJson(content, Customer.class);
        assertEquals("Diago Trade",customer.getName());
    }

    @Test
    public void add() throws Exception {
        String uri = "/customer";
        Customer c6 = new Customer("Hershein Berg", "10 Toban Circle", "hbff2@fastcompany.com", "123 773 0766", "5468382983", "Hershine");

        String inputJson = super.mapToJson(c6);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/6";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer customer = super.mapFromJson(content, Customer.class);
        assertEquals("Hershein Berg", customer.getName());
        assertEquals("hbff2@fastcompany.com", customer.getEmail());
    }

    //All
    @Test
    public void updateAll() throws Exception {
        String uri = "/customer/2?name=Lemon&address=42 Green Waverley&email=jackws@gmail.com&phone=0937421321&fax=0937421321&contactPerson=Lemon";
        Customer customer = new Customer("Lemon","42 Green Waverley","jackws@gmail.com","0937421321","0937421321","Lemon");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(customer.getName(), c2.getName());
        assertEquals(customer.getAddress(), c2.getAddress());
        assertEquals(customer.getEmail(), c2.getEmail());
        assertEquals(customer.getPhone(), c2.getPhone());
        assertEquals(customer.getFax(), c2.getFax());
        assertEquals(customer.getContactPerson(), c2.getContactPerson());

    }

    //Update name
    @Test
    public void updateName() throws Exception {
        String uri = "/customer/2?name=Lemon";
        Customer customer = new Customer();
        customer.setName("Lemon");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(customer.getName(), c2.getName());

    }
    //Update address
    @Test
    public void updateAddress() throws Exception {
        String uri = "/customer/2?address=42 Green Waverley";
        Customer c1 = new Customer();
        c1.setAddress("42 Green Waverley");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(c1.getAddress(), c2.getAddress());

    }
    //Update email
    @Test
    public void updateEmail() throws Exception {
        String uri = "/customer/2?email=jackws@gmail.com";
        Customer c1 = new Customer();
        c1.setEmail("jackws@gmail.com");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(c1.getEmail(), c2.getEmail());

    }
    //Update phone
    @Test
    public void updatePhone() throws Exception {
        String uri = "/customer/2?phone=0937421321";
        Customer c1 = new Customer();
        c1.setPhone("0937421321");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(c1.getPhone(), c2.getPhone());
    }
    //Update fax
    @Test
    public void updateFax() throws Exception {
        String uri = "/customer/2?fax=0937421321";
        Customer c1 = new Customer();
        c1.setFax("0937421321");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(c1.getFax(), c2.getFax());
    }
    //Update contact person
    @Test
    public void updateCP() throws Exception {
        String uri = "/customer/2?contactPerson=Lemon";
        Customer c1 = new Customer();
        c1.setContactPerson("Lemon");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        uri = "/customer/2";
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Customer c2 = super.mapFromJson(content, Customer.class);
        assertEquals(c1.getContactPerson(), c2.getContactPerson());
    }

    @Test
    public void findSaleInvoice() throws Exception {
        String uri = "/customer/2?contactPerson=Lemon";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}