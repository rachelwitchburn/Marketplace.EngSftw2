package service;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;
import com.marketplace.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product("Notebook Acer","2500,00", ProductType.ELETRÔNICO,"Acer","Descrição");

        // Simula o comportamento de método void
        doNothing().when(productRepository).addProduct(product);

        productService.addProduct(product);

        // Verifica se o método foi chamado uma vez
        verify(productRepository, times(1)).addProduct(product);
    }

    @Test
    void testListProducts() {
        Product product1 = new Product("Notebook Acer","2500,00", ProductType.ELETRÔNICO,"Acer","Descrição");
        Product product2 = new Product("Notebook Multilaser","2000,00", ProductType.ELETRÔNICO,"Multilaser","Descrição");

        when(productRepository.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.listProducts();

        assertEquals(2, products.size());
        assertEquals("Notebook Acer", products.get(0).getName());
        assertEquals("Notebook Multilaser", products.get(1).getName());
    }

    @Test
    void testUpdateProduct_Success() {
        Product productToUpdate = new Product("Monitor","3000,00", ProductType.ELETRÔNICO,"Acer","Descrição");

        when(productRepository.updateProduct(productToUpdate)).thenReturn(true);

        boolean result = productService.updateProduct(productToUpdate);

        assertTrue(result);
        verify(productRepository, times(1)).updateProduct(productToUpdate);
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product productToUpdate = new Product("Mouse","300,00", ProductType.ELETRÔNICO,"Logitech","Descrição");

        when(productRepository.updateProduct(productToUpdate)).thenReturn(false);

        boolean result = productService.updateProduct(productToUpdate);

        assertFalse(result);
        verify(productRepository, times(1)).updateProduct(productToUpdate);
    }

    @Test
    void testRemoveProduct_Success() {
        when(productRepository.removeProduct(1)).thenReturn(true);

        boolean result = productService.removeProduto(1);

        assertTrue(result);
        verify(productRepository, times(1)).removeProduct(1);
    }

    @Test
    void testRemoveProduct_NotFound() {
        when(productRepository.removeProduct(99)).thenReturn(false);

        boolean result = productService.removeProduto(99);

        assertFalse(result);
        verify(productRepository, times(1)).removeProduct(99);
    }
}
