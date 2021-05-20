package com.code.company.controller;

import com.code.company.entity.Staff;
import com.code.company.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    //CRUD api

    @GetMapping
    public Page<Staff> getAll(@RequestParam Optional<Integer> page) {
        return staffService.getAll(page);
    }

    @GetMapping(path = "{id}")
    public Staff getById(@PathVariable("id") Long id) {
        return staffService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id) {
        staffService.deleteById(id);
    }

    @PostMapping
    public void add(@RequestBody Staff staff) {
        staffService.add(staff);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody Staff newStaff) throws Exception {
        staffService.update(id,newStaff);
    }

    //Search api
    @GetMapping(path = "find")
    public Page<Staff> find(@RequestParam(required = false) Optional<String> name,
                            @RequestParam(required = false) Optional<String> address,
                            @RequestParam(required = false) Optional<String> phone,
                            Pageable pageable) {
        return staffService.find(name,address,phone,pageable);
    }

}
