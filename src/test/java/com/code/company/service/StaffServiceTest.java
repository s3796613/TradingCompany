package com.code.company.service;

import com.code.company.CompanyApplication;
import com.code.company.JPA.StaffRepository;
import com.code.company.entity.SaleInvoice;
import com.code.company.entity.Staff;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = CompanyApplication.class)
public class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    StaffService staffService;

    @Before
    public void before() throws Exception {
        staffService = new StaffService(staffRepository);
    }

    // Test get staff by Id
    @Test
    public void getById() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        Mockito.when(staffRepository.findStaffById(id)).thenReturn(s1);
        assertEquals("Jane",staffService.getById(id).getName());
        Mockito.verify(staffRepository, Mockito.times(1)).findStaffById(id);
    }
    // Test get staff by Id
    @Test
    public void getByIdNull() {
        Long id = Long.parseLong("2");
        Mockito.when(staffRepository.findStaffById(id)).thenReturn(null);
        assertNull(staffService.getById(id));
        Mockito.verify(staffRepository, Mockito.times(1)).findStaffById(id);
    }

    @Test
    public void deleteById() {
        Long id = Long.parseLong("1");
        staffService.deleteById(id);
        Mockito.verify(staffRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void add() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        Mockito.when(staffRepository.save(s1)).thenReturn(s1);
        staffService.add(s1);
        Mockito.verify(staffRepository, Mockito.times(1)).save(s1);

    }

    @Test(expected = Exception.class)
    public void update() throws Exception {
        // Staff id does not exist
        Long id = Long.parseLong("1");
        staffService.update(id,"Hugo","123 abc", "abc@sml.com","12345");
        Mockito.verify(staffRepository, Mockito.times(1)).findStaffById(id);
    }

    @Test
    public void findbyName() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        List<Staff> staffs = new ArrayList<>();
        staffs.add(s1);
        Pageable pageable= PageRequest.of(0,5);
        Page<Staff> result = new PageImpl<>(staffs,pageable,1);

        Mockito.when(staffRepository.findByNameContains("Jane",pageable)).thenReturn(result);

        staffService.find(Optional.of("Jane"),Optional.empty(),Optional.empty(),pageable);
        Mockito.verify(staffRepository, Mockito.times(1)).findByNameContains("Jane",pageable);
    }
    @Test
    public void findbyAddress() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        List<Staff> staffs = new ArrayList<>();
        staffs.add(s1);
        Pageable pageable= PageRequest.of(0,5);
        Page<Staff> result = new PageImpl<>(staffs,pageable,1);

        Mockito.when(staffRepository.findByAddressContains("Distric 7",pageable)).thenReturn(result);

        staffService.find(Optional.empty(),Optional.of("Distric 7"),Optional.empty(),pageable);
        Mockito.verify(staffRepository, Mockito.times(1)).findByAddressContains("Distric 7",pageable);
    }
    @Test
    public void findbyPhone() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        List<Staff> staffs = new ArrayList<>();
        staffs.add(s1);
        Pageable pageable= PageRequest.of(0,5);
        Page<Staff> result = new PageImpl<>(staffs,pageable,1);

        Mockito.when(staffRepository.findByPhone("09283617",pageable)).thenReturn(result);

        staffService.find(Optional.empty(),Optional.empty(),Optional.of("09283617"),pageable);
        Mockito.verify(staffRepository, Mockito.times(1)).findByPhone("09283617",pageable);
    }
    @Test
    public void findbyNothing() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);
        List<Staff> staffs = new ArrayList<>();
        staffs.add(s1);
        Pageable pageable= PageRequest.of(0,5);
        Page<Staff> result = new PageImpl<>(staffs,pageable,1);

        Mockito.when(staffRepository.findAll(pageable)).thenReturn(result);

        staffService.find(Optional.empty(),Optional.empty(),Optional.empty(),pageable);
        Mockito.verify(staffRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    public void getStaffSale() {
        Long id = Long.parseLong("1");
        Staff s1 = new Staff("Jane","Distric 7","jane@hotmail.com","0928361");
        s1.setId(id);

        List<SaleInvoice> invoices = new ArrayList<>();
        Pageable pageable= PageRequest.of(0,5);
        Page<SaleInvoice> result = new PageImpl<>(invoices,pageable,1);

        Mockito.when(staffRepository.getStaffSaleInvoice(id)).thenReturn(invoices);
        staffService.getStaffSale(id,"2021-01-01","2021-02-02");
        Mockito.verify(staffRepository, Mockito.times(1)).getStaffSaleInvoice(id);
    }
}