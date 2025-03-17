package service;

import com.marketplace.model.Store;
import com.marketplace.repository.StoreRepository;
import com.marketplace.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testAddStore() {
        Store store = new Store("Loja Tech", "tech@loja.com", "senha123", "12.345.678/0001-90", "Rua Tech, 123");

        doNothing().when(storeRepository).addStore(store);

        storeService.addStore(store);

        verify(storeRepository, times(1)).addStore(store);
    }

    @Test
    void testListStores() {
        Store store1 = new Store("Loja A", "a@loja.com", "senhaA", "11.111.111/0001-11", "Rua A, 111");
        Store store2 = new Store("Loja B", "b@loja.com", "senhaB", "22.222.222/0001-22", "Rua B, 222");

        when(storeRepository.getAllStores()).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.listStores();

        assertEquals(2, stores.size());
        assertEquals("Loja A", stores.get(0).getName());
        assertEquals("a@loja.com", stores.get(0).getEmail());
        assertEquals("22.222.222/0001-22", stores.get(1).getCnpj());
    }

    @Test
    void testUpdateStore_Success() {
        Store storeToUpdate = new Store("Loja Atualizada", "nova@loja.com", "novaSenha", "99.999.999/0001-99", "Rua Nova, 999");
        storeToUpdate.setId(1); // Adicionando o ID para simular loja existente

        when(storeRepository.updateStore(storeToUpdate)).thenReturn(true);

        boolean result = storeService.updateStore(storeToUpdate);

        assertTrue(result);
        verify(storeRepository, times(1)).updateStore(storeToUpdate);
    }

    @Test
    void testUpdateStore_NotFound() {
        Store storeToUpdate = new Store("Inexistente", "no@loja.com", "senhaNo", "00.000.000/0001-00", "Rua X, 0");
        storeToUpdate.setId(99); // ID fictício

        when(storeRepository.updateStore(storeToUpdate)).thenReturn(false);

        boolean result = storeService.updateStore(storeToUpdate);

        assertFalse(result);
        verify(storeRepository, times(1)).updateStore(storeToUpdate);
    }

    @Test
    void testRemoveStore_Success() {
        when(storeRepository.removeStore(1)).thenReturn(true);

        boolean result = storeService.removeStore(1);

        assertTrue(result);
        verify(storeRepository, times(1)).removeStore(1);
    }

    @Test
    void testRemoveStore_NotFound() {
        when(storeRepository.removeStore(99)).thenReturn(false);

        boolean result = storeService.removeStore(99);

        assertFalse(result);
        verify(storeRepository, times(1)).removeStore(99);
    }
}
