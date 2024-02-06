package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.AdminLogin;

@Repository("adminLoginRepo")
public interface AdminLoginRepo extends JpaRepository<AdminLogin, Integer>{

	@Override
	public long count();

	@Transactional
	@Modifying
	@Query(value="insert into adminlogin(username,password) values(:username,:password)", nativeQuery = true)
	public void byDefaultCredantials(@Param("username") String username, @Param("password") String password);

	@Query(value="select count(*) from adminlogin where username=:username and password=:password", nativeQuery = true)
	public int checkAdminAuth(@Param("username") String username, @Param("password") String password);

	@Query("select a from AdminLogin a where a.id=:id")
	public AdminLogin getAdminLoginCredential(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value="update adminlogin set username=:nuser, password=:npass  where id=1", nativeQuery = true)
	public void updateAdminCredential(@Param("nuser") String nuser, @Param("npass") String npass);
}
