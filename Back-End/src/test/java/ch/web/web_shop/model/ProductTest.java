package ch.web.web_shop.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductCreation() {
        // Create test data
        String title = "Test Product";
        String description = "This is a test product";
        String content = "Test content";
        int price = 100;
        int stock = 10;
        boolean published = false;
        Category category = new Category("Test Category");
        User user = new User("Test User","email@email.ch",false, "password");

        // Create a new product
        Product product = new Product.Builder(title, description, price, stock, published)
                .content(content)
                .category(category)
                .user(user)
                .build();

        // Test the product attributes
        Assertions.assertEquals(title, product.getTitle());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(content, product.getContent());
        Assertions.assertEquals(price, product.getPrice());
        Assertions.assertEquals(stock, product.getStock());
        Assertions.assertEquals(published, product.getPublished());
        Assertions.assertEquals(category, product.getCategory());
        Assertions.assertEquals(user, product.getUser());
    }
}
