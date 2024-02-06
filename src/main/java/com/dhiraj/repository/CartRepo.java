package com.dhiraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.Cart;

@Repository("cartRepo")
public interface CartRepo extends JpaRepository<Cart, Integer> {

	@Query("select c from Cart c where c.userid=:userid and c.productid=:productid")
	Cart getPerticularProductForUserFromCart(@Param("userid") int userid, @Param("productid") int productid);

	@Transactional
	@Modifying
	@Query("update Cart c set c.quantity=:quantity where c.userid=:userid and c.productid=:productid")
	int updateCartProductForUser(@Param("userid") int userid, @Param("productid") int productid, @Param("quantity") int quantity);

	@Query("select count(c) from Cart c where c.userid=:userid")
	int getCartCountForPerticularUser(@Param("userid") int userid);

	List<Cart> findAllByUserid(int userid);

	@Transactional
	@Modifying
	@Query("delete Cart where productid=:id")
	void deleteByProductid(int id);

	@Query("select count(c) from Cart c")
	int getCartCount();

}
