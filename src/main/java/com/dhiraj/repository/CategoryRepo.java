package com.dhiraj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Category;

@Repository("CategoryRepo")
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query("select d.categoryname,d.id from Category d where d.departmentid=:did")
	List<Optional> findByProductCategory(@Param("did") int did);

	@Query("select c from Category c where c.id=:id")
	Category findBySpacificCategory(@Param("id") int id);

	List<Category> findByDepartmentid(int department);
}
