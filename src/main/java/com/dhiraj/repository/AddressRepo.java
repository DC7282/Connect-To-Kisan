package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Address;

@Repository("addressRepo")
public interface AddressRepo  extends JpaRepository<Address, Integer> {

	Address findByUserid(int userid);

}
