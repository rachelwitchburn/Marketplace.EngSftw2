import com.marketplace.model.Admin;
import com.marketplace.service.AdminService;
import com.marketplace.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setName("Admin Name");

        when(adminRepository.save(admin)).thenReturn(admin);

        Admin savedAdmin = adminService.saveAdmin(admin);

        assertNotNull(savedAdmin);
        assertEquals(1L, savedAdmin.getId());
        assertEquals("Admin Name", savedAdmin.getName());

        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void testListAdmins() {
        Admin admin1 = new Admin();
        admin1.setId(1L);
        admin1.setName("Admin 1");

        Admin admin2 = new Admin();
        admin2.setId(2L);
        admin2.setName("Admin 2");

        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        List<Admin> admins = adminService.listAdmins();

        assertEquals(2, admins.size());
        assertEquals("Admin 1", admins.get(0).getName());
        assertEquals("Admin 2", admins.get(1).getName());

        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void testGetAdminByIdFound() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setName("Admin");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Optional<Admin> foundAdmin = adminService.getAdminById(1L);

        assertTrue(foundAdmin.isPresent());
        assertEquals("Admin", foundAdmin.get().getName());

        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAdminByIdNotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Admin> foundAdmin = adminService.getAdminById(1L);

        assertFalse(foundAdmin.isPresent());

        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAdminWhenExists() {
        Admin existingAdmin = new Admin();
        existingAdmin.setId(1L);
        existingAdmin.setName("Old Admin");

        Admin updatedAdmin = new Admin();
        updatedAdmin.setName("Updated Admin");

        when(adminRepository.existsById(1L)).thenReturn(true);
        when(adminRepository.save(any(Admin.class))).thenReturn(updatedAdmin);

        Admin result = adminService.updateAdmin(1L, updatedAdmin);

        assertNotNull(result);
        assertEquals("Updated Admin", result.getName());
        assertEquals(1L, updatedAdmin.getId()); // ID atualizado antes de salvar

        verify(adminRepository, times(1)).existsById(1L);
        verify(adminRepository, times(1)).save(updatedAdmin);
    }

    @Test
    void testUpdateAdminWhenNotExists() {
        Admin updatedAdmin = new Admin();
        updatedAdmin.setName("Updated Admin");

        when(adminRepository.existsById(1L)).thenReturn(false);

        Admin result = adminService.updateAdmin(1L, updatedAdmin);

        assertNull(result);

        verify(adminRepository, times(1)).existsById(1L);
        verify(adminRepository, never()).save(any(Admin.class));
    }

    @Test
    void testDeleteAdmin() {
        doNothing().when(adminRepository).deleteById(1L);

        adminService.deleteAdmin(1L);

        verify(adminRepository, times(1)).deleteById(1L);
    }
}
