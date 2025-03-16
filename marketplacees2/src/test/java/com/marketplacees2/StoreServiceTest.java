import com.marketplace.model.Store;
import com.marketplace.service.StoreService;
import com.marketplace.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    // Teste de criação de Loja
    @Test
    void testSaveStore() {
        Store store = new Store();
        store.setName("Loja de Roupas");

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        Store savedStore = storeService.saveStore(store);

        assertEquals("Loja de Roupas", savedStore.getName());
    }

    // Teste de listagem de Lojas
    @Test
    void testListStores() {
        Store store1 = new Store();
        store1.setName("Loja de Eletrônicos");

        Store store2 = new Store();
        store2.setName("Loja de Móveis");

        when(storeRepository.findAll()).thenReturn(Arrays.asList(store1, store2));

        List<Store> stores = storeService.listStores();

        assertEquals(2, stores.size());
        assertEquals("Loja de Eletrônicos", stores.get(0).getName());
    }

    // Teste de buscar Loja por ID
    @Test
    void testGetStoreById() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Loja de Alimentos");

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Optional<Store> foundStore = storeService.getStoreById(1L);

        assertTrue(foundStore.isPresent());
        assertEquals("Loja de Alimentos", foundStore.get().getName());
    }

    // Teste de atualização de Loja
    @Test
    void testUpdateStore() {
        Store existingStore = new Store();
        existingStore.setId(1L);
        existingStore.setName("Loja de Roupas");

        Store updatedStoreData = new Store();
        updatedStoreData.setName("Loja de Moda");


        when(storeRepository.findById(1L)).thenReturn(Optional.of(existingStore));
        when(storeRepository.save(any(Store.class))).thenAnswer(invocation -> {
            Store storeToSave = invocation.getArgument(0);
            storeToSave.setId(existingStore.getId()); // Garantir que o ID seja mantido
            return storeToSave;
        });

        Store updatedStore = storeService.updateStore(1L, updatedStoreData);

        assertNotNull(updatedStore);
        assertEquals("Loja de Moda", updatedStore.getName());
        assertEquals(1L, updatedStore.getId());
    }

    // Teste de atualização com ID inexistente
    @Test
    void testUpdateStore_NotFound() {
        Store updatedStoreData = new Store();
        updatedStoreData.setName("Loja de Informática");

        when(storeRepository.findById(99L)).thenReturn(Optional.empty());

        Store result = storeService.updateStore(99L, updatedStoreData);

        assertNull(result);
    }

    // Teste de deleção de Loja
    @Test
    void testDeleteStore() {
        doNothing().when(storeRepository).deleteById(1L);

        storeService.deleteStore(1L);

        verify(storeRepository, times(1)).deleteById(1L);
    }
}
