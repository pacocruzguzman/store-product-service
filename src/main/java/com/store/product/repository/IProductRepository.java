package com.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.product.entity.Category;
import com.store.product.entity.Product;

public interface IProductRepository extends JpaRepository<Product, String>{

	List<Product> findByCategory(Category category);
	
	//Con JPQL
	/*@Query("FROM Product WHERE name LIKE %:product%")
	List<Product> buscarProdutosByProduct(@Param("product") String product);*/
	
	//Con SQL nativo
	@Query(value = "SELECT * FROM products WHERE name LIKE %:product%", nativeQuery = true)
	List<Product> buscarProdutosByProduct(@Param("product") String product);
}
