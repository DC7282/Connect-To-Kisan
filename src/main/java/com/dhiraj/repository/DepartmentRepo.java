package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Department;

@Repository("DepartmentRepo")
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	@Query("select d from Department d where d.id=:id")
	Department findByIdDepartment(@Param("id") int d);


}
