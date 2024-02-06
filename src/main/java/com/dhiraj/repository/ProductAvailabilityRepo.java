package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.ProductAvailability;

@Repository("productAvailabilityRepo")
public interface ProductAvailabilityRepo extends JpaRepository<ProductAvailability, Integer>{

	ProductAvailability findById(int availability);

}
