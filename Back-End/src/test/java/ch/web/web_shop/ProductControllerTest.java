
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ch.web.web_shop.controller.ProductController;
import ch.web.web_shop.repository.CategoryRepository;
import ch.web.web_shop.repository.NewslatterRepository;
import ch.web.web_shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

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
		assertThat(productController).isNotNull();
	}

	@Test
	public void whenGetRequestToGetAllProduicts_thenCorrectResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void whenPostRequestToProductAndInValidProduct_thenCorrectResponse() throws Exception {
		String product = "{\"products\": null }";
		mockMvc.perform(MockMvcRequestBuilders.post("/products/1")
				.content(product)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.product").value("Product is mandatory"))
				.andExpect(MockMvcResultMatchers.content()
						.contentType(MediaType.APPLICATION_JSON));
	}



	//heute
//Test für delete Komponent
	@Test
	public void testDeleteAllProducts() {
		productRepository.deleteAll();

		assertEquals(0, productRepository.count());
	}

	@Test
	public void testDeleteAllProductsException() {
		doThrow(new RuntimeException()).when(productRepository).deleteAll();
		ResponseEntity<HttpStatus> response = productController.deleteAllProducts();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}


// checken ob neue newslatter erstellt
	@Test
	public void testCreateNewslatter() {
		Newslatter newslatter = new Newslatter();
		newslatter.setEmailId("test@test.com");
		when(newslatterRepository.save(newslatter)).thenReturn(newslatter);
		assertEquals(newslatter, newslatterController.createNewslatter(newslatter));
	}



	//Publish
	@Test
	public void testFindByPublished() {
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setTitle("Test Product");
		product.setDescription("Test Description");
		product.setPublished(true);
		products.add(product);
		when(productRepository.findByPublished(true)).thenReturn(products);
		ResponseEntity<List<Product>> responseEntity = productController.findByPublished();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(products, responseEntity.getBody());
	}



}
