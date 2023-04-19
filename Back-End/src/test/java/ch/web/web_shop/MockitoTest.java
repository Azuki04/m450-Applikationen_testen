package ch.web.web_shop;

import ch.web.web_shop.controller.ProductController;
import ch.web.web_shop.model.Product;
import ch.web.web_shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MockitoTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @Spy
    private List<Product> products = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllProducts() {
        // Arrange
        Product product1 = new Product("Title1", "Description1", "Content1", 10.0, 100, "src1", false, "Category1");
        Product product2 = new Product("Title2", "Description2", "Content2", 20.0, 200, "src2", false, "Category2");
        products.add(product1);
        products.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts(null);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()).contains(product1, product2);

        Mockito.verify(productRepository).findAll();
    }

}
