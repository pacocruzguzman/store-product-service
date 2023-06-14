package com.store.product.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.product.entity.Category;
import com.store.product.entity.Product;
import com.store.product.repository.IProductRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public List<Product> listProducts() {

		List<Product> productosDisponibles = productRepository
				.findAll()
				.stream()
				.filter(x -> x.getStatus().equals("DISPONIBLE"))
				.toList();

		return productosDisponibles;
	}

	@Override
	public Product findProduct(String sku) {

		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARM");
		log.error("ERROR");
		
		
		return productRepository.findById(sku).orElse(null);
	}
	
	@Override
	public Product registerProduct(Product product) {

		Product productBD = findProduct(product.getSku());

		if (productBD != null) {

			return productBD;
		}

		product.setStatus("DISPONIBLE");
		product.setCreateAt(LocalDateTime.now());
		
		return productRepository.save(product);

	}

	@Override
	public Product updateProduct(Product product) {

		Product productBD = findProduct(product.getSku());

		if (productBD != null) {
			if (product.getStock() < 1)
				product.setStatus("NO DISPONOBLE");
			else
				product.setStatus("DISPONIBLE");

			product.setCreateAt(LocalDateTime.now());

			return productRepository.save(product);
		}

		return null;
	}

	@Override
	public void deleteProduct(String sku) {

		// productRepository.deleteById(sku);

		Product productDB = findProduct(sku);

		if (productDB != null) {
			productDB.setStatus("NO DISPONIBLE");
			productDB.setCreateAt(LocalDateTime.now());
			productRepository.save(productDB);
		}
	}

	@Override
	public List<Product> findByCategory(Category cagetory) {
		
		return productRepository.findByCategory(cagetory);
	}

	@Override
	public List<Product> buscarProductos(String name) {
		
		return productRepository.buscarProdutosByProduct(name);
	}

}
