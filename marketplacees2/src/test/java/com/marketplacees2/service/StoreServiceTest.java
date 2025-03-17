package com.marketplacees2.service;

import com.marketplace.model.Store;
import com.marketplace.repository.StoreRepository;
import com.marketplace.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
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

    private Store createStore(String name, String email, String password, String cnpj, String address) {
        return new Store(name, email, password, cnpj, address);
    }

    @Test
    void testAddStore() {
        Store store = createStore("Loja Tech", "tech@loja.com", "senha123", "12.345.678/0001-90", "Rua Tech, 123");

        ArgumentCaptor<Store> storeCaptor = ArgumentCaptor.forClass(Store.class);
        doNothing().when(storeRepository).addStore(storeCaptor.capture());

        storeService.addStore(store);

        verify(storeRepository, times(1)).addStore(storeCaptor.capture());
        assertEquals(store.getName(), storeCaptor.getValue().getName());
        assertEquals(store.getEmail(), storeCaptor.getValue().getEmail());
    }

    @Test
    void testListStores() {
        Store store1 = createStore("Loja A", "a@loja.com", "senhaA", "11.111.111/0001-11", "Rua A, 111");
        Store store2 = createStore("Loja B", "b@loja.com", "senhaB", "22.222.222/0001-22", "Rua B, 222");

        when(storeRepository.getAllStores()).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.listStores();

        assertEquals(2, stores.size());
        assertEquals("Loja A", stores.get(0).getName());
        assertEquals("a@loja.com", stores.get(0).getEmail());
        assertEquals("22.222.222/0001-22", stores.get(1).getCnpj());
    }

    @Test
    void testListStoresEmpty() {
        when(storeRepository.getAllStores()).thenReturn(Arrays.asList());

        List<Store> stores = storeService.listStores();

        assertTrue(stores.isEmpty(), "A lista de lojas deve estar vazia.");
    }

    @Test
    void testUpdateStore_Success() {
        Store storeToUpdate = createStore("Loja Atualizada", "nova@loja.com", "novaSenha", "99.999.999/0001-99", "Rua Nova, 999");
        storeToUpdate.setId(1); // Adicionando o ID para simular loja existente

        when(storeRepository.updateStore(storeToUpdate)).thenReturn(true);

        boolean result = storeService.updateStore(storeToUpdate);

        assertTrue(result);
        verify(storeRepository, times(1)).updateStore(storeToUpdate);
    }

    @Test
    void testUpdateStore_NotFound() {
        Store storeToUpdate = createStore("Inexistente", "no@loja.com", "senhaNo", "00.000.000/0001-00", "Rua X, 0");
        storeToUpdate.setId(99); // ID fictício

        when(storeRepository.updateStore(storeToUpdate)).thenReturn(false);

        boolean result = storeService.updateStore(storeToUpdate);

        assertFalse(result, "A loja não foi encontrada, não deveria ter sido atualizada.");
        verify(storeRepository, times(1)).updateStore(storeToUpdate);
    }

    @Test
    void testRemoveStore_Success() {
        int storeId = 1;

        when(storeRepository.removeStore(storeId)).thenReturn(true);

        boolean result = storeService.removeStore(storeId);

        assertTrue(result);
        verify(storeRepository, times(1)).removeStore(storeId);
    }

    @Test
    void testRemoveStore_NotFound() {
        int storeId = 99;

        when(storeRepository.removeStore(storeId)).thenReturn(false);

        boolean result = storeService.removeStore(storeId);

        assertFalse(result, "A loja não foi encontrada, não deveria ter sido removida.");
        verify(storeRepository, times(1)).removeStore(storeId);
    }

    @Test
    void testAddStoreThrowsException() {
        Store store = createStore("Loja Tech", "tech@loja.com", "senha123", "12.345.678/0001-90", "Rua Tech, 123");

        // Simula exceção no repositório
        doThrow(new RuntimeException("Erro ao adicionar loja")).when(storeRepository).addStore(store);

        assertThrows(RuntimeException.class, () -> {
            storeService.addStore(store);
        }, "Esperado que uma exceção seja lançada ao adicionar loja.");
    }

    @Test
    void testUpdateStoreThrowsException() {
        Store storeToUpdate = createStore("Loja Atualizada", "nova@loja.com", "novaSenha", "99.999.999/0001-99", "Rua Nova, 999");

        // Simula exceção no repositório
        when(storeRepository.updateStore(storeToUpdate)).thenThrow(new RuntimeException("Erro ao atualizar loja"));

        assertThrows(RuntimeException.class, () -> {
            storeService.updateStore(storeToUpdate);
        }, "Esperado que uma exceção seja lançada ao atualizar loja.");
    }
}

