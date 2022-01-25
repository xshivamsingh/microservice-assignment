package com.impact.productms.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impact.productms.exception.ProductNotFoundException;
import com.impact.productms.model.Product;
import com.impact.productms.repo.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	//ProductService productService;
	
	@PostMapping("/add-product")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		if(product.getName()!=null&&product.getPrice()!=null) {
			if(product.getCategory()==null) product.setCategory("General");
			if(product.getDetails()!=null) {
				Map<String, String> details = product.getDetails();
				for(Entry<String,String> i:details.entrySet()) {
					if(i.getValue()==null) {
						return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Product details: "+i.getKey()+" cannot have null value");
					}
			}
			Product myProduct=repository.save(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(myProduct);
		}}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Product Name and Price is required!");
	}
	
	@PutMapping("/update-product")
	public ResponseEntity<String> updateProduct(@RequestBody Product product){
		Optional<Product> myProduct = repository.findById(product.getProductId());
		if(myProduct.isPresent()) {
			if(product.getName()==null||product.getPrice()==null) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Product Name and Price is required!");
			}
			repository.save(product);
			return ResponseEntity.status(HttpStatus.OK).body("Product updated");
		}
		throw new ProductNotFoundException(product.getProductId());
	}
	
	@PatchMapping("/update-product-details")
	public ResponseEntity<String> updateProductDetails(@RequestParam Long id,@RequestBody Map<String, String> details) {
		Optional<Product> myProduct = repository.findById(id);
		if(myProduct.isPresent()) {
			Map<String, String> existingDetails = myProduct.get().getDetails();
			for(Entry<String,String> i:details.entrySet()) {
				if(i.getValue()==null) {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Product details cannot have null value");
				}
				existingDetails.put(i.getKey(), i.getValue());
			}
			myProduct.get().setDetails(existingDetails);
			repository.save(myProduct.get());
			return ResponseEntity.status(HttpStatus.OK).body("Product details updated");
		}
		
		throw new ProductNotFoundException(id);
	}
	@DeleteMapping("/product-detail-remove")
	public ResponseEntity<String> deleteProductDetail(@RequestParam Long productId,
			@RequestParam String detail_key){
		Optional<Product> myProduct = repository.findById(productId);
		if(myProduct.isPresent()) {
			Product existingProduct = myProduct.get();
			if (existingProduct.getDetails().get(detail_key)==null) {
				return ResponseEntity.status(HttpStatus.OK).body("Product detail not Found!");	
			}
			existingProduct.removeDetails(detail_key);
			repository.save(existingProduct);
			return ResponseEntity.status(HttpStatus.OK).body("Product detail removed");	
		}
		throw new ProductNotFoundException(productId);
	}
	
	/*
	 * @DeleteMapping("/product-detail-remove") public ResponseEntity<String>
	 * deleteProductDetail(@RequestParam Long productId,
	 * 
	 * @RequestParam String detail_key, @RequestParam String detail_value){
	 * Optional<Product> myProduct = repository.findById(productId);
	 * if(myProduct.isPresent()) { repository.deleteProductDetail(productId,
	 * detail_key); return
	 * ResponseEntity.status(HttpStatus.OK).body("Product detail removed"); } throw
	 * new ProductNotFoundException(productId); }
	 */

	/*
	 * @PatchMapping("/update-product-details") public ResponseEntity<String>
	 * updateProductDetails(@RequestParam Long id,@RequestBody Map<String, String>
	 * details) { Optional<Product> myProduct = repository.findById(id);
	 * if(myProduct.isPresent()) { Map<String, String> existingDetails =
	 * myProduct.get().getDetails(); for(Entry<String,String> i:details.entrySet())
	 * { existingDetails.put(i.getKey(), i.getValue()); }
	 * myProduct.get().setDetails(existingDetails);
	 * repository.save(myProduct.get()); return
	 * ResponseEntity.status(HttpStatus.OK).body("Product details updated"); }
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!!"); }
	 */
	@PutMapping("/add-product-details")
	public ResponseEntity<String> addProductDetails(@RequestParam Long id,@RequestParam String deatil_name,
			@RequestParam String detail_value) {
		Optional<Product> myProduct = repository.findById(id);
		if(myProduct.isPresent()) {
			myProduct.get().addDetails(deatil_name, detail_value);
			repository.save(myProduct.get());
			return ResponseEntity.status(HttpStatus.OK).body("Product details added");
		}
		
		throw new ProductNotFoundException(id);
		/*
		 * return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!!");
		 */
	}
	
	@GetMapping("/product-by-id")
	public ResponseEntity<Product> getProductById(@RequestParam Long id) {
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()) return ResponseEntity.status(HttpStatus.FOUND).body(product.get());
		throw new ProductNotFoundException(id);
	}
	
	@GetMapping("/product-by-category")
	public ResponseEntity<Object> getProductByCategory(@RequestParam String category) {
		List<Product> products = repository.findByCategory(category);
		if(products.isEmpty()) return ResponseEntity.status(HttpStatus.OK).body("No product found under category: "+category);
		return ResponseEntity.ok(products);	
	}
	
	@GetMapping("/product-price")
	public ResponseEntity<Integer> getProductPrice(@RequestParam Long productId) {
		Optional<Product> myProduct= repository.findById(productId);
		if(myProduct.isPresent()) {Integer price= myProduct.get().getPrice();
		return ResponseEntity.ok(price);}
		throw new ProductNotFoundException(productId);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@DeleteMapping("/delete-product")
	public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
		  Optional<Product> myProduct = repository.findById(id);
		  if(myProduct.isPresent()) { 
			  repository.deleteById(id);
			  return ResponseEntity.ok("product deleted!");}
		  throw new ProductNotFoundException(id);
	}
	
}
