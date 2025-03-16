import com.marketplace.model.Product;
import com.marketplace.service.ProductService;
import com.marketplace.repository.ProductRepository;
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

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
    }

    // Teste de criação de Produto
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Smartphone");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals("Smartphone", savedProduct.getName());
    }

    // Teste de listagem de Produtos
    @Test
    void testListProducts() {
        Product product1 = new Product();
        product1.setName("Camiseta");

        Product product2 = new Product();
        product2.setName("Livro de Java");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.listProducts();

        assertEquals(2, products.size());
        assertEquals("Camiseta", products.get(0).getName());
    }

    // Teste de buscar Produto por ID
    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Notebook");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Notebook", foundProduct.get().getName());
    }

    // Teste de atualização de Produto
    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Geladeira");

        Product updatedProductData = new Product();
        updatedProductData.setName("Geladeira Frost Free");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProductData);

        Product updatedProduct = productService.updateProduct(1L, updatedProductData);

        assertNotNull(updatedProduct);
        assertEquals("Geladeira Frost Free", updatedProduct.getName());
    }

    // Teste de atualização com ID inexistente
    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProductData = new Product();
        updatedProductData.setName("Fone de Ouvido");

        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        Product result = productService.updateProduct(99L, updatedProductData);

        assertNull(result);
    }

    // Teste de deleção de Produto
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
