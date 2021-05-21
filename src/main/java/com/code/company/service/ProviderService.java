package com.code.company.service;

import com.code.company.JPA.ProviderRepository;
import com.code.company.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Page<Provider> findAll(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    public Provider findById(Long id) {
        return providerRepository.findProviderById(id);
    }

    public void add(Provider provider) {
        providerRepository.save(provider);
    }

    public void update(Long id, Provider newProvider) throws Exception {
        providerRepository.findById(id).map(provider -> {
            provider.setName(newProvider.getName());
            provider.setAddress(newProvider.getAddress());
            provider.setEmail(newProvider.getEmail());
            provider.setPhone(newProvider.getPhone());
            provider.setFax(newProvider.getFax());
            provider.setContactPerson(newProvider.getContactPerson());
            return providerRepository.save(provider);
        }).orElseThrow(() -> new Exception("Provider not found!"));
    }

    public void delete(Long id) {
        providerRepository.deleteById(id);
    }

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
