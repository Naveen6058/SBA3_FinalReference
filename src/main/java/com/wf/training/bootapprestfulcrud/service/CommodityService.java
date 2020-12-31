package com.wf.training.bootapprestfulcrud.service;

import java.util.List;

import com.wf.training.bootapprestfulcrud.dto.AddCommodityPriceDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityHistoricalDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCommodityDto;


public interface CommodityService {


	public CommodityDto addCommodity(CommodityDto dto);

	public CommodityDto fetchSingleCommodityByName(SearchCommodityDto searchCommodityDto);

	public CommodityDto fetchSingleCommodityByName(String commodityName);
	public CommodityDto modifyCommodity(CommodityDto commodityNewOutputDto);

	List<CommodityHistoricalDto> fetchAllHistoricalDataByCommodityId(int commodityId);

	List<CommodityDto> fetchAllCommodities();

	List<String> fetchAllCommodityNames();

	boolean addCommodityPrice(AddCommodityPriceDto addCommodityDto);
	
}
