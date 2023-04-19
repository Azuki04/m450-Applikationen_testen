
package ch.web.web_shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import ch.web.web_shop.controller.NewslatterController;
import ch.web.web_shop.model.Newslatter;
import ch.web.web_shop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ch.web.web_shop.controller.ProductController;
import ch.web.web_shop.repository.CategoryRepository;
import ch.web.web_shop.repository.NewslatterRepository;
import ch.web.web_shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc

public class ProductControllerTest {

	@MockBean
	private NewslatterRepository newslatterRepository;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private CategoryRepository categoryRepository;

	@Autowired
	ProductController productController;

	@Autowired
	NewslatterController newslatterController;

	@Autowired
	private MockMvc mockMvc;

@Test
public void whenProductControllerInjected_thenNotNull() throws Exception {
	// Assert
	assertThat(productController).isNotNull();
}
	@Test
	public void getAllProductsTest() {
		// Arrange
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product("Title1", "Description1", "Content1", 10.0, 100, "src1", false, "Category1");
		Product product2 = new Product("Title2", "Description2", "Content2", 20.0, 200, "src2", false, "Category2");
		products.add(product1);
		products.add(product2);
		when(productRepository.findAll()).thenReturn(products);
		// Act
		ResponseEntity<List<Product>> responseEntity = productController.getAllProducts(null);
		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void getProductByIdTest() {
	    // Arrange
		long id = 1;
		Product product = new Product("Title", "Description", "Content", 10.0, 100, "src", false, "Category");
		product.setId(id);
		Optional<Product> optionalProduct = Optional.of(product);
		when(productRepository.findById(id)).thenReturn(optionalProduct);
		// Act
		ResponseEntity<Product> responseEntity = productController.getProductById(id);
		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
// createProduct
	@Test
	public void createProductTest() {
		// Arrange
		Product product = new Product("Title", "Description", "Content", 10.0, 100, "src", false, "toy");
		// Act
		ResponseEntity<Product> responseEntity = productController.createProduct(product);
		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}


//Test für delete Komponente
	@Test
	public void testDeleteAllProducts() {
		// Act
		productRepository.deleteAll();
		// Assert
		assertEquals(0, productRepository.count());
	}

	@Test
	public void testDeleteAllProductsException() {
		// Arrange
		doThrow(new RuntimeException()).when(productRepository).deleteAll();
		// Act
		ResponseEntity<HttpStatus> response = productController.deleteAllProducts();
		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	@Test
	public void testUpdateProduct() {
		// Arrange
		long productId = 1L;
		Product existingProduct = new Product("Product1", "Description1", "Content1", 10.0, 100, "src1", false, "Category1");
		Product updatedProduct = new Product("Product1_Updated", "Description1_Updated", "Content1", 20.0, 250, "src1", true, "Category1");
		Optional<Product> optionalProduct = Optional.of(existingProduct);
		when(productRepository.findById(productId)).thenReturn(optionalProduct);
		when(productRepository.save(existingProduct)).thenReturn(updatedProduct);
		// Act
		ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);
		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedProduct, response.getBody());
	}

// checken, ob neue newslatter erstellt
	@Test
	public void testCreateNewslatter() {
		// Arrange
		Newslatter newslatter = new Newslatter();
		newslatter.setEmailId("test@test.com");
		when(newslatterRepository.save(newslatter)).thenReturn(newslatter);
		// Assert
		assertEquals(newslatter, newslatterController.createNewslatter(newslatter));
	}

	//Publish
	@Test
	public void testFindByPublished() {
		// Arrange
		List<Product> products = new ArrayList<>();
		Product product = new Product("Title", "Description", "Content", 10.0, 100, "src", false, "toy");
		product.setTitle("Test Product");
		product.setDescription("Test Description");
		product.setPublished(true);
		products.add(product);
		when(productRepository.findByPublished(true)).thenReturn(products);
		// Act
		ResponseEntity<List<Product>> responseEntity = productController.findByPublished();
		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(products, responseEntity.getBody());
	}



}
