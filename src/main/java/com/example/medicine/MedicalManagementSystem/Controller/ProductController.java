package com.example.medicine.MedicalManagementSystem.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicine.MedicalManagementSystem.Entity.Product;
import com.example.medicine.MedicalManagementSystem.Exception.APIException;
import com.example.medicine.MedicalManagementSystem.Services.ProductServiceImpl;


@CrossOrigin("*")
@RestController
public class ProductController {
	static Logger LOGGER = LoggerFactory.getLogger(ProductController.class); 

	@Autowired
	ProductServiceImpl productService;

	/**
	 * This method is used to add products.
	 * 
	 * @param product First parameter for the method. Accepts Products.
	 * @return product added successfully or not.
	 * @throws Exception if details are not entered correctly.
	 */

	@PostMapping("/Admin/ProductAdd")
	private String saveProduct(@Valid @RequestBody final Product product) throws Exception {
		if (product.getProductId() > 0) {
			if (product.getCategory() != "" && product.getProductName() != "" && product.getCategory() != null
					&& product.getProductName() != null && product.getPrice() > 0 && product.getQuantity() > 0) {
				LOGGER.info("Product Added");
				return product.getProductId() + " " + productService.saveOrUpdate(product);
			} else {
				LOGGER.error("Exception Occured!!! PRODUCT field has incoreect data");
				throw new APIException(" Exception Occured!!!INVALID PRODUCT DETAILS!!!Please Check Product Details.");
			}
		} else {
			LOGGER.error("Exception Occured!!! PRODUCT field has incorrect data");
			throw new APIException(" Exception Occured!!!INVALID PRODUCT ID!!!Please check Product ID");
		}

	}

	/**
	 * This method is used to view All products.
	 * 
	 * @return list of all Products with their details.
	 */

	@GetMapping("/getProducts")
	public List<Product> getAllProducts() {
		LOGGER.info("All products Fetched");
		return productService.getAllProducts();
	}

	/**
	 * This method is used to view product by name.
	 * 
	 * @param productname First parameter for the method. Accepts product name.
	 * @return product with same name.
	 * @throws Exception if product with same name is not available.
	 */

	@RequestMapping("/getProductByName")
	private List<Product> getProductByName(@RequestParam("productname") String productname) throws Exception {
		if (productname != null && productname != "") {
			LOGGER.info("products Fetched by name");
			return productService.getProductByName(productname);
		} else {
			LOGGER.error("Exception Occured!!! PRODUCT Name has incorrect data");
			throw new APIException("INVALID Product Name");
		}
	}

	/**
	 * This method is used to view product by category.
	 * 
	 * @param category First parameter for the method. Accepts product category.
	 * @return products with same category.
	 * @throws Exception if products of this category is not available.
	 */

	@RequestMapping("/getProductsByCategory")
	public List<Product> getProductByCategory(@RequestParam("category") String category) throws Exception {
		if (category != null && category != "") {
			LOGGER.info("products Fetched by name");
			return productService.getProductByCategory(category);
		} else {
			LOGGER.error("Exception Occured!!! PRODUCT Category has incorrect data");
			throw new APIException("INVALID Product Category");
		}
	}

	/**
	 * This method is used to update products.
	 * 
	 * @param product First parameter for the method. Accepts product.
	 * @return product updated or not.
	 */

	@PostMapping("/Admin/updateProduct")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody final Product product) {
		Product updateProduct = productService.updateProduct(product);
		if (product.getCategory() != "" || product.getProductName() != "" || product.getCategory() != null
				|| product.getProductName() != null || product.getPrice() > 0 || product.getQuantity() > 0) {
			LOGGER.info("Products Updated");
			return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);

		} else {
			LOGGER.error("Products are not Updated");

			return new ResponseEntity("Product not Updated.", HttpStatus.NOT_MODIFIED);
		}

	}
}
