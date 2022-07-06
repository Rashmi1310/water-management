package com.rubicon.watermanagement.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubicon.watermanagement.entity.FarmerEntity;

@Repository
	
public interface FarmerRepository extends JpaRepository<FarmerEntity, Long>{
    public Optional<FarmerEntity> findByFarmid(int  farmid);
	
}

