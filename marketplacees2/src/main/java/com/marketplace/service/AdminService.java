package com.marketplace.service;

import com.marketplace.model.Admin;
import com.marketplace.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository useAdminRepository;

    public Admin saveAdmin(Admin admin) {
        return useAdminRepository.save(admin);
    }
    
    public List<Admin> listAdmins() {
        return useAdminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id){
        return useAdminRepository.findById(id);
    }

    public Admin updateAdmin(Long id, Admin adminUpdated){
        if (useAdminRepository.existsById(id)) {
            adminUpdated.setId(id);
            return useAdminRepository.save(adminUpdated);
        }

        return null;
    }

    public void deleteAdmin(Long id) {
        useAdminRepository.deleteById(id);
    }
}