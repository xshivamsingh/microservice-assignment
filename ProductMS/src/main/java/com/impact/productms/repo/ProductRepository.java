package com.impact.productms.repo;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.impact.productms.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategory(String category);
	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("delete from product_details_mapping d where d.product_id=?1 and d.detail_name=?2"
	 * ) public void deleteProductDetail(Long id, String detail_key);
	 */
}
