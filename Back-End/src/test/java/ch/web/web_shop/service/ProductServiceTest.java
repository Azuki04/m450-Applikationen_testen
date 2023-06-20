package ch.web.web_shop.service;

import ch.web.web_shop.dto.ProductDTO;
import ch.web.web_shop.exception.*;
import ch.web.web_shop.model.*;
import ch.web.web_shop.repository.ProductRepository;
import ch.web.web_shop.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts_NoTitle() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts(null);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllProducts_WithTitle() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findByTitleContaining("test")).thenReturn(productList);

        List<Product> result = productService.getAllProducts("test");
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllProducts_Exception() {
        when(productRepository.findAll()).thenThrow(RuntimeException.class);

        assertThrows(ProductLoadException.class, () -> {
            productService.getAllProducts(null);
        });
    }

    @Test
    void testGetProductById_Success() {
        long productId = 1;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);
        assertEquals(product, result);
    }

    @Test
    void testGetProductById_NotFound() {
        long productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(productId);
        });
    }

    @Test
    void testCreateProduct_Success() {
        ProductDTO productDTO = new ProductDTO.Builder()
                .withTitle("Test Product")
                .withDescription("Test Description")
                .withPrice(10)
                .withStock(5)
                .withPublished(false)
                .withCategory(new Category())
                .withUser(new User())
                .withContent("Cool Product")
                .build();

        Product product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10);
        product.setStock(5);
        product.setPublished(false);
        product.setCategory(new Category());
        product.setUser(new User());
        product.setContent("Cool Product");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(productDTO);
        assertEquals(product, result);
    }


    @Test
    void testCreateProduct_Exception() {
        ProductDTO productDTO = new ProductDTO.Builder()
                .withTitle("Test Product")
                .withDescription("Test Description")
                .withPrice(10)
                .withStock(5)
                .withPublished(false)
                .withCategory(new Category())
                .withUser(new User())
                .build();

        when(productRepository.save(any(Product.class))).thenThrow(RuntimeException.class);

        assertThrows(ProductCouldNotBeSavedException.class, () -> {
            productService.createProduct(productDTO);
        });
    }

    @Test
    void testUpdateProduct_Success() {
        long productId = 1;
        ProductDTO productDTO = new ProductDTO.Builder()
                .withTitle("Updated Product")
                .withDescription("Updated Description")
                .withPrice(20)
                .withStock(10)
                .withPublished(true)
                .withCategory(new Category())
                .withUser(new User())
                .build();

        Product existingProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product result = productService.updateProduct(productId, productDTO);

        assertEquals(existingProduct, result);
        assertEquals("Updated Product", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(20, result.getPrice());
        assertEquals(10, result.getStock());
        assertTrue(result.getPublished());
        assertNotNull(result.getCategory());
        assertNotNull(result.getUser());
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void testUpdateProduct_NotFound() {
        long productId = 1;
        ProductDTO productDTO = new ProductDTO();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct(productId, productDTO);
        });
    }

    @Test
    void testDeleteProduct_Success() {
        long productId = 1;
        doNothing().when(productRepository).deleteById(productId);

        assertDoesNotThrow(() -> {
            productService.deleteProduct(productId);
        });

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteProduct_NotFound() {
        long productId = 1;
        doThrow(ProductNotFoundException.class).when(productRepository).deleteById(productId);

        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });
    }

    @Test
    void testDeleteAllProducts_Success() {
        doNothing().when(productRepository).deleteAll();

        assertDoesNotThrow(() -> {
            productService.deleteAllProducts();
        });

        verify(productRepository, times(1)).deleteAll();
    }

    @Test
    void testDeleteAllProducts_Exception() {
        doThrow(RuntimeException.class).when(productRepository).deleteAll();

        assertThrows(ProductDeleteException.class, () -> {
            productService.deleteAllProducts();
        });
    }

    @Test
    void testGetPublishedProducts_Success() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findByPublished(true)).thenReturn(productList);

        List<Product> result = productService.getPublishedProducts();
        assertEquals(2, result.size());
    }

    @Test
    void testGetPublishedProducts_Exception() {
        when(productRepository.findByPublished(true)).thenThrow(RuntimeException.class);

        assertThrows(ProductLoadException.class, () -> {
            productService.getPublishedProducts();
        });
    }
}
