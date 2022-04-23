package com.store.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.product.entity.Product;
import com.store.product.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository repo;

	@Override
	public List<Product> listAllProduct() {

		return repo.findAll();
	}

	@Override
	public Product getProduct(Long id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	public Product creatdProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreateAt(new Date());

		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {

		if (getProduct(product.getId()) != null) {

			product.setStatus("UPDATED");

			return repo.save(product);
		}

		return null;
	}

	@Override
	public Product deleteProduct(Long id) {

		Product p = getProduct(id);

		if (p != null) {
			p.setStatus("DELETED");

			return repo.save(p);
		}

		return null;
	}

}
