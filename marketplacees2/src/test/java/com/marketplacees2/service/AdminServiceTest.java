package com.marketplacees2.service;

import com.marketplace.model.Admin;
import com.marketplace.repository.AdminRepository;
import com.marketplace.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
        adminRepository = mock(AdminRepository.class);

        // Injeta o mock no serviço
        adminService = new AdminService(adminRepository);
    }

    private Admin createAdmin(String name, String email) {
        return new Admin(name, email, "12345678", "123456789-02", "Rua exemplo");
    }

    @Test
    void testAddAdmin() {
        Admin admin = createAdmin("Admin One", "admin1@example.com");

        // Captura o argumento passado para o método
        ArgumentCaptor<Admin> adminCaptor = ArgumentCaptor.forClass(Admin.class);
        doNothing().when(adminRepository).addAdmin(adminCaptor.capture());

        adminService.addAdmin(admin);

        verify(adminRepository, times(1)).addAdmin(adminCaptor.capture());

        // Verifica se o admin capturado é igual ao que foi passado
        assertEquals(admin.getName(), adminCaptor.getValue().getName());
        assertEquals(admin.getEmail(), adminCaptor.getValue().getEmail());
    }

    @Test
    void testListAdmins() {
        List<Admin> admins = Arrays.asList(
                createAdmin("Admin One", "admin1@example.com"),
                createAdmin("Admin Two", "admin2@example.com")
        );

        when(adminRepository.getAllAdmins()).thenReturn(admins);

        List<Admin> result = adminService.listAdmins();

        assertEquals(2, result.size());
        assertEquals("Admin One", result.get(0).getName());
        assertEquals("Admin Two", result.get(1).getName());
    }

    @Test
    void testListAdminsEmpty() {
        // Configuração do mock para retornar uma lista vazia
        when(adminRepository.getAllAdmins()).thenReturn(Arrays.asList());

        List<Admin> result = adminService.listAdmins();

        assertTrue(result.isEmpty(), "A lista de admins deve estar vazia.");
    }

    @Test
    void testUpdateAdmin() {
        Admin admin = createAdmin("Admin Updated", "adminUpdated@example.com");

        when(adminRepository.updateAdmin(admin)).thenReturn(true);

        boolean result = adminService.updateAdmin(admin);

        assertTrue(result);
        verify(adminRepository, times(1)).updateAdmin(admin);
    }

    @Test
    void testUpdateAdminNotFound() {
        Admin admin = createAdmin("Admin Updated", "adminUpdated@example.com");

        // Quando o repositório não encontrar o admin, ele deve retornar false
        when(adminRepository.updateAdmin(admin)).thenReturn(false);

        boolean result = adminService.updateAdmin(admin);

        assertFalse(result, "O admin não deveria ter sido atualizado, pois não foi encontrado.");
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

    @Test
    void testRemoveAdminNotFound() {
        int id = 1;

        // Quando o repositório não encontrar o admin para remover
        when(adminRepository.removeAdmin(id)).thenReturn(false);

        boolean result = adminService.removeAdmin(id);

        assertFalse(result, "O admin não deveria ter sido removido, pois não foi encontrado.");
        verify(adminRepository, times(1)).removeAdmin(id);
    }

    @Test
    void testAddAdminThrowsException() {
        Admin admin = createAdmin("Admin One", "admin1@example.com");

        // Simula exceção no repositório
        doThrow(new RuntimeException("Erro ao adicionar admin")).when(adminRepository).addAdmin(admin);

        assertThrows(RuntimeException.class, () -> {
            adminService.addAdmin(admin);
        }, "Esperado que uma exceção seja lançada ao adicionar admin.");
    }
}
