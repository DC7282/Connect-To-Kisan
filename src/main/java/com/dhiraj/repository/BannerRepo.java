package com.dhiraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dhiraj.entity.Banner;

@Repository("bannerRepo")
public interface BannerRepo extends JpaRepository<Banner, Integer>{

	@Query("select b from Banner b where b.type=:bannerid order by id desc limit 1")
	public Banner findBannerById(@Param("bannerid") int bannerid);
}
