package com.web.product.app.product.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.web.product.app.product.Product;
import com.web.product.app.product.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductTests {

	@Autowired
	private ProductRepository repo;
	
	@Test
	@Rollback(false)
	@Disabled
	public void testCreateProduct() {
		Product product = new Product("yup5", 789);
		Product savedProduct = repo.save(product);
		assertNotNull(savedProduct);
	}
	
	@Test
	@Rollback(false)
	//@Disabled
	public void testFindProductByNameExist() {
		String name = "yup1";
		Product product = repo.findByName(name);
		assertThat(product.getName()).isEqualTo(name);
	}
	
	@Test
	@Rollback(false)
	//@Disabled
	public void testFindProductByNameNotExist() {
		String name = "iPhone 12";
		Product product = repo.findByName(name);
		assertNull(product);
	}
	
	@Test
	@Rollback(false)
	//@Disabled
	public void testUpdateProduct() {
		String productName = "Kindle Reader18";
		Product product = new Product(productName, 199);
		product.setId(34);
		
		repo.save(product);
		
		Product updatedProduct = repo.findByName(productName);
		
		assertThat(updatedProduct.getName()).isEqualTo(product.getName());
	}
	
	@Test
	//@Disabled
	public void testListProducts() {
		List<Product> products = (List<Product>) repo.findAll();
		
		for(Product product : products) {
			System.out.println(product);
		}
		
		assertThat(products).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	//@Disabled
	public void testDeleteProduct() {
		Integer id = 22;
		
		boolean isExistBeforeDelete = repo.findById(id).isPresent();
		
		repo.deleteById(id);
		
		boolean isExistAfterDelete = repo.findById(id).isPresent();
		
		assertTrue(isExistBeforeDelete);
		assertFalse(isExistAfterDelete);
	}
	
}

