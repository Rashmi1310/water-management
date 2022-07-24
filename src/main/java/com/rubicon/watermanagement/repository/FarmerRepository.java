package com.rubicon.watermanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubicon.watermanagement.entity.FarmerEntity;

import java.util.List;

@Repository
	
public interface FarmerRepository extends JpaRepository<FarmerEntity, Long>
{
	 public List<FarmerEntity> findByFarmid(String  farmid);
}

