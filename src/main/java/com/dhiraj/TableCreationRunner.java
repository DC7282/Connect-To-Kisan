package com.dhiraj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dhiraj.repository.AdminLoginRepo;
import com.dhiraj.repository.SettingRepo;

@Component
public class TableCreationRunner implements CommandLineRunner{

	@Autowired
	AdminLoginRepo adminLoginRepo;

	@Override
	public void run(String... args) throws Exception {
		if(adminLoginRepo.count()==0)
			adminLoginRepo.byDefaultCredantials("admin","admin");
	}

}
