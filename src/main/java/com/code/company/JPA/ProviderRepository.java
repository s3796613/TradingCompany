package com.code.company.JPA;

import com.code.company.entity.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findProviderById(Long id);
    Page<Provider> findByNameContains(String name, Pageable pageable);
    Page<Provider> findByAddressContains(String address, Pageable pageable);
    Page<Provider> findByPhone(String phone, Pageable pageable);
}
