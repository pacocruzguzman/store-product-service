package com.store.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.entity.Category;
import com.store.product.entity.Product;
import com.store.product.service.IProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path = "${path-service}")
public class ProductController {

	@Autowired
	private IProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> listProducts(
			@RequestParam(name = "categoryId", required = false) Long categoryId) {

		List<Product> listProducts = null;

		if (categoryId != null) {

			Category category = new Category();
			category.setId(categoryId);

			log.info(categoryId);

			listProducts = service.findByCategory(category);

			if (listProducts.isEmpty())
				return ResponseEntity.noContent().build();
		} else {

			listProducts = service.listAllProduct();
		}
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {

		Product p = service.getProduct(id);

		if (p != null)
			return new ResponseEntity<>(p, HttpStatus.OK);
		else
			return ResponseEntity.noContent().build();

	}

	@PostMapping
	public Product creatProduct(@RequestBody Product product) {

		log.error(product);

		return service.creatdProduct(product);
	}

}
