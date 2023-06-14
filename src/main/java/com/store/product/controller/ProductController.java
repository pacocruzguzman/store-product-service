package com.store.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.entity.Category;
import com.store.product.entity.Product;
import com.store.product.service.IProductService;

@RestController
@RequestMapping(path = "/api/store/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	ResponseEntity<List<Product>> listProducts(@RequestParam(
			name = "idCategory", 
			required = false) Long idCategory) {
		
		if(idCategory !=null) {
			Category category = new Category();
			category.setId(idCategory);
			
			List<Product> listProducsByCategory = productService.findByCategory(category);
			
			return ResponseEntity.ok(listProducsByCategory);
		}
		List<Product> listProducts = productService.listProducts();

		if (!listProducts.isEmpty())
			return ResponseEntity.ok(listProducts);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	Product registerProduct(@Valid @RequestBody Product product) {

		return productService.registerProduct(product);
	}

	@GetMapping("/{sku}")
	Product findProduct(@PathVariable("sku") String sku) {

		return productService.findProduct(sku);
	}

	@PutMapping("/{sku}")
	Product updateProduct(@PathVariable("sku") String sku, @RequestBody Product product) {

		product.setSku(sku);

		return productService.updateProduct(product);
	}

	@DeleteMapping("/{sku}")
	void deleteProduct(@PathVariable("sku") String sku) {

		productService.deleteProduct(sku);
	}

	/*@GetMapping(path = "findByCategory")
	List<Product> findByCategory(@RequestParam("idCategory") Long idCategory) {
		
		Category category = new Category();
		category.setId(idCategory);
		
		return productService.findByCategory(category);
	}*/
	
	@GetMapping("/buscarPorNombre")
	List<Product> buscarPorNombre(@RequestParam("name")String name){
		
		return productService.buscarProductos(name);
	}

}
