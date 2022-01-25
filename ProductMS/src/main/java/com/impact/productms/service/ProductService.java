package com.impact.productms.service;

/*
 * import java.util.List; import java.util.Map; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.impact.productms.model.Product; import
 * com.impact.productms.repo.ProductRepository;
 * 
 * 
 * @Service public class ProductService {
 * 
 * @Autowired private Product product; private ProductRepository repository;
 * 
 * public Product addProduct(Product product) { return repository.save(product);
 * }
 * 
 * public String updateProductDetails(Long id, Map<String, Object> details) {
 * Optional<Product> myProduct = repository.findById(id);
 * if(myProduct.isPresent()) { myProduct.get().setDetails(details);
 * repository.save(myProduct.get()); return "Product details updated"; }
 * 
 * return "Product not found!"; }
 * 
 * public Product getProductById(Long id) { Optional<Product> product =
 * repository.findById(id); if(product.isPresent()) return product.get(); return
 * null; }
 * 
 * public List<Product> getProductByCategory(String category) { return
 * repository.findByCategory(category);
 * 
 * }
 * 
 * public List<Product> getAllProducts() { return repository.findAll(); }
 * 
 * public void deleteProduct(Long id) { repository.deleteById(id); } }
 */