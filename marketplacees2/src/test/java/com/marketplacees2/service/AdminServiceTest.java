package service;

import com.marketplace.model.Admin;
import com.marketplace.repository.AdminRepository;
import com.marketplace.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private AdminRepository adminRepository;
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        // Cria o mock do repositório
        adminRepository = Mockito.mock(AdminRepository.class);

        // Injeta o mock no serviço
        adminService = new AdminService(adminRepository);
    }

    @Test
    void testAddAdmin() {
        Admin admin = new Admin("Admin One", "admin1@example.com","12345678","123456789-02","Rua exemplo");
        doNothing().when(adminRepository).addAdmin(admin);

        adminService.addAdmin(admin);

        verify(adminRepository, times(1)).addAdmin(admin);
    }

    @Test
    void testListAdmins() {
        List<Admin> admins = Arrays.asList(
                new Admin("Admin One", "admin1@example.com","12345678","123456789-02","Rua exemplo"),
                new Admin("Admin Two", "admin2@example.com","12345678","123456789-03","Rua exemplo")
        );

        when(adminRepository.getAllAdmins()).thenReturn(admins);

        List<Admin> result = adminService.listAdmins();

        assertEquals(2, result.size());
        assertEquals("Admin One", result.get(0).getName());
        assertEquals("Admin Two", result.get(1).getName());
    }

    @Test
    void testUpdateAdmin() {
        Admin admin = new Admin("Admin Updated", "adminUpdated@example.com","12345678","123456789-02","Rua exemplo");

        when(adminRepository.updateAdmin(admin)).thenReturn(true);

        boolean result = adminService.updateAdmin(admin);

        assertTrue(result);
        verify(adminRepository, times(1)).updateAdmin(admin);
    }

    @Test
    void testRemoveAdmin() {
        int id = 1;

        when(adminRepository.removeAdmin(id)).thenReturn(true);

        boolean result = adminService.removeAdmin(id);

        assertTrue(result);
        verify(adminRepository, times(1)).removeAdmin(id);
    }
}
