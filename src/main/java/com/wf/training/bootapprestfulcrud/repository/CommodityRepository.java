package com.wf.training.bootapprestfulcrud.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wf.training.bootapprestfulcrud.entity.Commodity;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

	Commodity findByCommodityName(String commodityName);
	
	
	@Transactional
	@Modifying
    @Query("UPDATE Commodity c SET c.price = :price WHERE c.commodityName = :commodityName")
    void updateCommodityPrice(@Param("commodityName") String commodityName, @Param("price") Float commodityPrice);


}


