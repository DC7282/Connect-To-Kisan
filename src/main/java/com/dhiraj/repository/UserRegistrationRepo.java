package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhiraj.entity.UserRegistration;

@Repository("userRegistrationRepo")
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer>{

	@Query(value="select count(*) from userregisteration where contact=:contact or email=:email", nativeQuery = true)
	int checkUserRegistration(@Param("contact") String contact,@Param("email") String email);

	@Query("select u from UserRegistration u where u.contact=:contact and u.password=:password")
	UserRegistration checkUserAuth(@Param("contact") String contact,@Param("password") String password);

	public UserRegistration findById(int id);

	@Query("select count(u) from UserRegistration u")
	int getUserCount();

	@Transactional
	@Modifying
	@Query("update UserRegistration u set u.status=:status where u.id=:id")
	void updateUserStatus(@Param("status") int status, @Param("id") int id);

	UserRegistration findByContact(String contact);

}
