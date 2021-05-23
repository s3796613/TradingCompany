package com.code.company.service;

import com.code.company.JPA.ProviderRepository;
import com.code.company.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    //CRUD

    public Page<Provider> findAll(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    public Provider findById(Long id) {
        return providerRepository.findProviderById(id);
    }

    public void add(Provider provider) {
        providerRepository.save(provider);
    }


    public void delete(Long id) {
        providerRepository.deleteById(id);
    }


    @Transactional
    public void update(Long id, String name, String address, String email, String phone, String fax, String contactPerson) throws Exception {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new Exception("Provider id not found"));
        if (name != null && name.length() > 0) {
            provider.setName(name);
        }
        if (address != null && address.length() > 0) {
            provider.setAddress(address);
        }
        if (email != null && email.length() > 0) {
            provider.setEmail(email);
        }
        if (phone != null && phone.length() > 0) {
            provider.setPhone(phone);
        }
        if (fax != null && fax.length() > 0) {
            provider.setFax(fax);
        }
        if (contactPerson != null && contactPerson.length() > 0) {
            provider.setContactPerson(contactPerson);
        }
    }

    //Search api

    public Page<Provider> find(Optional<String> name, Optional<String> address, Optional<String> phone, Pageable pageable) {
        if (name.isPresent()) {
            return providerRepository.findByNameContains(name.orElse(""),pageable);
        }
        if (address.isPresent()) {
            return providerRepository.findByAddressContains(address.orElse(""),pageable);
        }
        if (phone.isPresent()) {
            return providerRepository.findByPhone(phone.orElse(""), pageable);
        }
        return providerRepository.findAll(pageable);
    }
}
