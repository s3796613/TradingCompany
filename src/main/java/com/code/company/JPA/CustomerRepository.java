package com.code.company.JPA;

import com.code.company.entity.Customer;
import com.code.company.entity.SaleInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select i from SaleInvoice i where i.customerID =?1")
    List<SaleInvoice> getSaleInvoiceByID(Long id);

    Customer findCustomerById(Long id);
    Page<Customer> findByNameContains(String name, Pageable pageable);
    Page<Customer> findByAddressContains(String address, Pageable pageable);
    Page<Customer> findByPhone(String phone, Pageable pageable);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByPhone(String phone);
    Optional<Customer> findCustomerByFax(String fax);

    @Query("select i from SaleInvoice i where i.customerID=?1")
    List<SaleInvoice> getSaleInvoice(Long customerID);
}
