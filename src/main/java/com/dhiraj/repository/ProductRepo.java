package com.dhiraj.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.Product;

@Repository("ProductRepo")
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("select p from Product p where p.id=:id")
	public Product getProducts(@Param("id") int id);


//	public List<Product> findByDepartment(int d);

	@Query("select p from Product p inner join Department d on p.department=d.id inner join Category c on p.category=c.id where (p.title LIKE %:searchdata% or d.departmentname LIKE %:searchdata% or c.categoryname LIKE %:searchdata%) and p.status=1")
	public Page<Product> getProductsBySearchList(@Param("searchdata") String searchdata,  Pageable pageable);

	@Query("select p from Product p inner join ProductAndMainPageSectionJoin pm on p.id=pm.product where pm.mainpagesections=:sid and p.status=1 order by pm.id desc limit :lmt")
	public List<Product> getAllProductForIndexPage(@Param("sid") int sectionid,@Param("lmt") int limit);

	@Query("select p from Product p where p.department=:id and p.status=1 order by p.id desc limit 4")
	public List<Product> findByDepartmentLimit(@Param("id") int id);

	@Query("select count(p) from Product p")
	public int getProductCount();

	@Transactional
	@Modifying
	@Query("update Product p set p.status=:status where id=:id")
	public void disableProductById(@Param("status") int status, @Param("id") int id);

	@Query("select p from Product p where p.status=1 order by p.id desc limit :lmt")
	public List<Product> getLatestProductForIndexPage(@Param("lmt") int limit);

	@Query("select p from Product p where p.department=:d")
	public Page<Product>  findDepartment(@Param("d") int d, Pageable pageable);

}
