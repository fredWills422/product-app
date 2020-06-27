package com.web.product.app.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer>{

	public Product findByName(String name);
	
}
