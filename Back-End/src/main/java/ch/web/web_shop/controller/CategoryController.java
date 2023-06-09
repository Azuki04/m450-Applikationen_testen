package ch.web.web_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.web.web_shop.model.Category;
import ch.web.web_shop.service.CategoryService;

/**
 * Category controller class.
 * This class is used to manage categories.
 * The @RestController annotation combines the @Controller and @ResponseBody annotations,
 * simplifying the code and eliminating the need for individual @ResponseBody annotations.
 * The @RequestMapping("/api/category") annotation informs that this controller
 * will process requests whose URI begins with "/api/category".
 * Handles HTTP methods such as GET, POST, etc. using appropriate mapping annotations.
 * Manages and provides access to Category objects.
 * Uses CategoryService to retrieve and manipulate data.
 *
 * Note: Make sure to inject the CategoryService dependency in this controller.
 *
 * Example:
 *
 * @Autowired
 * private CategoryService categoryService;
 *
 * And use categoryService.getAllCategories() to retrieve categories.
 *
 * Alternatively, you can use constructor injection instead of field injection.
 *
 * Example:
 *
 * private final CategoryService categoryService;
 *
 * @Autowired
 * public CategoryController(CategoryService categoryService) {
 *     this.categoryService = categoryService;
 * }
 *
 * This approach is recommended for better testability and maintainability.
 *
 * Update the code accordingly based on your preference.
 *
 * @version 1.0
 * @see Category
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * Retrieves all categories.
	 *
	 * @return ResponseEntity with a list of categories if successful,
	 *         or an error response if an exception occurs.
	 */
	@GetMapping("")
	public ResponseEntity<Iterable<Category>> getAllCategories() {
		try {
			Iterable<Category> categories = categoryService.getAllCategories();
			return ResponseEntity.ok(categories);
		} catch (Exception ex) {
			// Handle other generic exceptions
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
