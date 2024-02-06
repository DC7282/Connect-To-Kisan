package com.dhiraj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.ProductAndMainPageSectionJoin;

@Repository("ProductAndMainPageSectionJoinRepo")
public interface ProductAndMainPageSectionJoinRepo extends JpaRepository<ProductAndMainPageSectionJoin, Integer>{

	ProductAndMainPageSectionJoin getByProduct(int id);

	Optional findByProduct(int id);

}
