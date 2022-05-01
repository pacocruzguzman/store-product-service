package com.store.product.service;

import java.util.List;

import com.store.product.entity.Category;
import com.store.product.entity.Product;

public interface IProductService {

	List<Product> listAllProduct();

	Product getProduct(Long id);

	Product creatdProduct(Product product);

	Product updateProduct(Product product);

	Product deleteProduct(Long id);

	List<Product> findByCategory(Category category);

}
