package com.store.product.service;

import java.util.List;

import com.store.product.entity.Product;

public interface IProductService {

	public List<Product> listAllProduct();

	public Product getProduct(Long id);

	public Product creatdProduct(Product product);

	public Product updateProduct(Product product);

	public Product deleteProduct(Long id);

}
