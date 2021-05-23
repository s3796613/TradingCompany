package com.code.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Staff")
@Table(name = "staff")
public class Staff{
    @Id
    @SequenceGenerator(
            name = "staff_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staff_sequence"
    )
    @Column(name = "id")
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Staff() {
    }

    public Staff(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
