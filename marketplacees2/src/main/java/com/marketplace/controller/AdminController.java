package com.marketplace.controller;

import com.marketplace.model.Admin;
import com.marketplace.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admins")
public class AdminController {
    @Autowired
    private AdminService useAdminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return useAdminService.saveAdmin(admin);
    }

    @GetMapping
    public List<Admin> listAdmin() {
        return useAdminService.listAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = useAdminService.getAdminById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin updatedAdmin = useAdminService.updateAdmin(id, admin);
        return (updatedAdmin != null) ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        useAdminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
