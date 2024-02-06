package com.dhiraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.OrderPlaced;

@Repository("orderPlacedRepo")
public interface OrderPlacedRepo extends JpaRepository<OrderPlaced, Integer>{

	@Query("select o from OrderPlaced o where o.billingid=:id")
	List<OrderPlaced> findbyBillingId(@Param("id") int id);

	@Transactional
	@Modifying
	@Query("update OrderPlaced o set o.deliverystatus=:status, o.timeStamp=:timeStamp where o.id=:orderid")
	int UpdateDeliveryStatus(@Param("orderid") int orderid, @Param("status") int status, @Param("timeStamp") String timeStamp);

	List<OrderPlaced> findAllByUserid(int userid);

	@Query("select count(o) from OrderPlaced o")
	public int getOrdersCount();

}
