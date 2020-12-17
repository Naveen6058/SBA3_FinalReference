package com.wf.training.bootapprestfulcrud.service;

import java.util.List;


import com.wf.training.bootapprestfulcrud.dto.CommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityHistoricalDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCommodityDto;


public interface CommodityService {

//	public List<CompanyDto> fetchAllCompanies();
//	public CompanyDto fetchSingleCompany(Long id);
//	public CompanyDto addCompany(CompanyDto addCompanyInputDto);
//	public CompanyDto editCompany(Long id, SearchCompanyDto employeeInputDto);
//	public CompanyDto deleteCompany(Long id);
//	public CompanyDto fetchSingleCompanyByName(SearchCompanyDto searchCompanyDto);
//	CompanyDto modifyCompany(CompanyDto companyOutputDto);
	public CommodityDto addCommodity(CommodityDto dto);

	public CommodityDto fetchSingleCommodityByName(SearchCommodityDto searchCommodityDto);

	public CommodityDto fetchSingleCommodityByName(String commodityName);
	public CommodityDto modifyCommodity(CommodityDto commodityNewOutputDto);

	List<CommodityHistoricalDto> fetchAllHistoricalDataByCommodityId(int commodityId);
	
}
