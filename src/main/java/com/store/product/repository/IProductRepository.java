package com.store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.product.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{

}
