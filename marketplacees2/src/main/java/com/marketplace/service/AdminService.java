package com.marketplace.service;

import com.marketplace.model.Admin;
import com.marketplace.repository.AdminRepository;
import java.util.List;

public class AdminService {
    private AdminRepository repository = new AdminRepository();

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }
    
    public void addAdmin(Admin admin) {
        repository.addAdmin(admin);
    }
    
    public List<Admin> listAdmins() {
        return repository.getAllAdmins();
    }
    
    public boolean updateAdmin(Admin admin) {
        return repository.updateAdmin(admin);
    }
    
    public boolean removeAdmin(int id) {
        return repository.removeAdmin(id);
    }
}
