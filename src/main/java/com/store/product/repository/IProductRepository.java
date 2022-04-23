package com.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.product.entity.Category;
import com.store.product.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByCategory(Category category);
}
