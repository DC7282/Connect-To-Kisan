package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Contact;

@Repository("contactRepo")
public interface ContactRepo extends JpaRepository<Contact, Integer>{

	@Query("select count(c) from Contact c")
	int getMessagesCount();

}
