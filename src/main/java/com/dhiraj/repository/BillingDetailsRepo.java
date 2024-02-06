package com.dhiraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.BillingDetails;

@Repository("billingDetailsRepo")
public interface BillingDetailsRepo extends JpaRepository<BillingDetails, Integer>{

	@Query("select b from BillingDetails b where b.deliverystatus<=3 order by b.id desc")
	public List<BillingDetails> getProcessingDelivery();

	@Query("select b from BillingDetails b where b.id=:id")
	public BillingDetails findById(@Param("id") int id);

	@Transactional
	@Modifying
	@Query("update BillingDetails b set b.deliverystatus=:status_count, b.timeStamp=:timeStamp where b.id=:id")
	public void UpdateBillingDeliveryStatus(@Param("id") int id, @Param("status_count") int status_count, @Param("timeStamp") String timeStamp);

	@Query("select b from BillingDetails b where b.deliverystatus>3 order by b.id desc")
	public List<BillingDetails> getDeliveredOrders();

	@Query("select b from BillingDetails b where b.userid=:userid order by b.id desc")
	public List<BillingDetails> getBillingDetailsByUserId(@Param("userid") int userid);

	@Transactional
	@Modifying
	@Query("update BillingDetails b set b.totalbillingamount=:total where b.id=:id")
	public void updateBillingTotalAmount(@Param("total") float total, @Param("id") int id);

}
