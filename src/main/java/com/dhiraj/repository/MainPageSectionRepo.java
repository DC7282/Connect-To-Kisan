package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.MainPageSections;

@Repository("mainPageSectionRepo")
public interface MainPageSectionRepo extends JpaRepository<MainPageSections, Integer>{

}
