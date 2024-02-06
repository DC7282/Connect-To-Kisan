package com.dhiraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.Wishlist;

@Repository("wishlistRepo")
public interface WishlistRepo extends JpaRepository<Wishlist, Integer>{

	@Query("select w from Wishlist w where w.userid=:userid and w.productid=:productid")
	Wishlist getPerticularProductForUserFromWishlist(@Param("userid") int userid, @Param("productid") int productid);

	List<Wishlist> findAllByUserid(int userid);

	@Query("select count(w) from Wishlist w where w.userid=:userid")
	int getWishlistCountForPerticularUser(int userid);

	@Transactional
	@Modifying
	@Query("delete Wishlist where productid=:id")
	void deleteByProductid(int id);

	List<Wishlist> getByUserid(int userid);

	@Query("select count(w) from Wishlist w")
	int getWishCount();

}
