package com.store.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.entity.Product;
import com.store.product.service.IProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path = "store/products")
public class ProductController {

	@Autowired
	private IProductService service;

	@GetMapping
	public List<Product> listProducts() {
		List<Product> listProducts = service.listAllProduct();

		return listProducts;
	}

	@PostMapping
	public Product creatProduct(@RequestBody Product product) {

		log.error(product);

		return service.creatdProduct(product);
	}

}
