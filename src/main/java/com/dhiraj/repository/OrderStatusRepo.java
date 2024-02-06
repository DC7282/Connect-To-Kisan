package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.OrderStatus;

@Repository("orderStatusRepo")
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer>{

}
