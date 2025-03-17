package service;

import com.marketplace.model.Buyer;
import com.marketplace.repository.BuyerRepository;
import com.marketplace.service.BuyerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuyerServiceTest {

    private BuyerRepository buyerRepository;
    private BuyerService buyerService;

    @BeforeEach
    void setUp() {
        buyerRepository = Mockito.mock(BuyerRepository.class);
        buyerService = new BuyerService(buyerRepository);
    }

    @Test
    void testAddBuyer() {
        Buyer buyer = new Buyer("John Doe", "john@example.com","12345678","12345689-00","Rua exemplo");
        doNothing().when(buyerRepository).addBuyer(buyer);

        buyerService.addBuyer(buyer);

        verify(buyerRepository, times(1)).addBuyer(buyer);
    }

    @Test
    void testListBuyers() {
        List<Buyer> buyers = Arrays.asList(
                new Buyer("John Doe", "john@example.com","12345678","12345689-00","Rua exemplo"),
                new Buyer("Jane Doe", "jane@example.com","87654321","12345689-01","Rua exemplo")
        );

        when(buyerRepository.getAllBuyers()).thenReturn(buyers);

        List<Buyer> result = buyerService.listBuyers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void testUpdateBuyer() {
        Buyer buyer = new Buyer("John Updated", "johnUpdated@example.com","12345678","12345689-00","Rua exemplo");

        when(buyerRepository.updateBuyer(buyer)).thenReturn(true);

        boolean result = buyerService.updateBuyer(buyer);

        assertTrue(result);
        verify(buyerRepository, times(1)).updateBuyer(buyer);
    }

    @Test
    void testRemoveBuyer() {
        int id = 1;

        when(buyerRepository.removeBuyer(id)).thenReturn(true);

        boolean result = buyerService.removeBuyer(id);

        assertTrue(result);
        verify(buyerRepository, times(1)).removeBuyer(id);
    }
}
