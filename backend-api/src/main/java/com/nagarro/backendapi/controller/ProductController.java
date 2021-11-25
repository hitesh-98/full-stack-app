package com.nagarro.backendapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.backendapi.models.Product;
import com.nagarro.backendapi.repo.ProductRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/getResults")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(this.productRepository.findAll());
	}

}
