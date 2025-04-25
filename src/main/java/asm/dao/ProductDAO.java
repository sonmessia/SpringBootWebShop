package asm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import asm.entity.Product;

//import com.j5.entity.Report;

public interface ProductDAO extends JpaRepository<asm.entity.Product, Integer> {

	List<asm.entity.Product> findByPriceBetween(double minPrice, double maxPrice);

	Page<asm.entity.Product> findAllByNameLike(String keywords, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	Page<Product> findByCategoryId(String category, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.id like ?1 and p.price between ?2 and ?3")
	Page<Product> findByCategoryId2(String category, Double min, Double max, Pageable pageable);
	

	@Query("SELECT p FROM Product p WHERE p.price between ?1 and ?2")
	Page<Product> findByCategoryId3(Double min, Double max, Pageable pageable);
	
}
