package com.marketplace.service;

import com.marketplace.model.Buyer;
import com.marketplace.repository.BuyerRepository;
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

public class BuyerServiceTest {

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private BuyerService buyerService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    // Teste de criação de Buyer
    @Test
    void testSaveBuyer() {
        Buyer buyer = new Buyer();
        buyer.setNome("João");
        buyer.setEmail("joao@email.com");

        when(buyerRepository.save(any(Buyer.class))).thenReturn(buyer);

        Buyer savedBuyer = buyerService.saveBuyer(buyer);

        assertEquals("João", savedBuyer.getNome());
        assertEquals("joao@email.com", savedBuyer.getEmail());
    }

    // Teste de listagem de Buyers
    @Test
    void testListBuyers() {
        Buyer buyer1 = new Buyer();
        buyer1.setNome("Ana");
        buyer1.setEmail("ana@email.com");

        Buyer buyer2 = new Buyer();
        buyer2.setNome("Carlos");
        buyer2.setEmail("carlos@email.com");

        when(buyerRepository.findAll()).thenReturn(Arrays.asList(buyer1, buyer2));

        List<Buyer> buyers = buyerService.listBuyers();

        assertEquals(2, buyers.size());
        assertEquals("Ana", buyers.get(0).getNome());
    }

    // Teste de buscar Buyer por ID
    @Test
    void testGetBuyerById() {
        Buyer buyer = new Buyer();
        buyer.setId(1L);
        buyer.setNome("Lucas");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(buyer));

        Optional<Buyer> foundBuyer = buyerService.getBuyerById(1L);

        assertTrue(foundBuyer.isPresent());
        assertEquals("Lucas", foundBuyer.get().getNome());
    }

    // Teste de atualização de Buyer
    @Test
    void testUpdateBuyer() {
        Buyer existingBuyer = new Buyer();
        existingBuyer.setId(1L);
        existingBuyer.setNome("Joana");
        existingBuyer.setEmail("joana@email.com");

        Buyer updatedBuyerData = new Buyer();
        updatedBuyerData.setNome("Joana Silva");
        updatedBuyerData.setEmail("joana.silva@email.com");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(existingBuyer));
        when(buyerRepository.save(any(Buyer.class))).thenReturn(updatedBuyerData);

        Buyer updatedBuyer = buyerService.updateBuyer(1L, updatedBuyerData);

        assertNotNull(updatedBuyer);
        assertEquals("Joana Silva", updatedBuyer.getNome());
        assertEquals("joana.silva@email.com", updatedBuyer.getEmail());
    }

    // Teste de atualização com ID inexistente
    @Test
    void testUpdateBuyer_NotFound() {
        Buyer updatedBuyerData = new Buyer();
        updatedBuyerData.setNome("Maria");

        when(buyerRepository.findById(99L)).thenReturn(Optional.empty());

        Buyer result = buyerService.updateBuyer(99L, updatedBuyerData);

        assertNull(result);
    }

    // Teste de deleção de Buyer
    @Test
    void testDeleteBuyer() {
        doNothing().when(buyerRepository).deleteById(1L);

        buyerService.deleteBuyer(1L);

        verify(buyerRepository, times(1)).deleteById(1L);
    }
}
