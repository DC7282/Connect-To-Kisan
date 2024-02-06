package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Setting;

@Repository("SettingRepo")
public interface SettingRepo extends JpaRepository<Setting, Integer>{
	public Setting findById(int i);
}
