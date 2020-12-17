package com.wf.training.bootapprestfulcrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.training.bootapprestfulcrud.entity.HistoricalRecordCommodity;

public interface CommodityHistoricalDataRepository extends JpaRepository<HistoricalRecordCommodity, Long>{
	
	Optional<List<HistoricalRecordCommodity>> findAllByCommodityId(int commodityId);

}
