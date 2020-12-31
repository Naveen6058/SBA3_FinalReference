package com.wf.training.bootapprestfulcrud.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.bootapprestfulcrud.dto.AddCommodityPriceDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityHistoricalDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCommodityDto;
import com.wf.training.bootapprestfulcrud.entity.Commodity;
import com.wf.training.bootapprestfulcrud.entity.HistoricalRecordCommodity;
import com.wf.training.bootapprestfulcrud.repository.CommodityHistoricalDataRepository;
import com.wf.training.bootapprestfulcrud.repository.CommodityRepository;
import com.wf.training.bootapprestfulcrud.service.CommodityService;

@Service
public class CommodityServiceImp implements CommodityService {
	
	@Autowired
	private CommodityRepository commodityRepository;
	
	@Autowired
	private CommodityHistoricalDataRepository commodityHisDataRepository;
	
	private Commodity convertInputAddCommodityToEntity(CommodityDto dto) {
		Commodity com = new Commodity();
		 
		com.setCommodityName(dto.getCommodityName());
		com.setCurrency(dto.getCurrency());
		com.setPrice(dto.getPrice());
		com.setDateTime(dto.getDateTime());
		 
		 return com;
	}
	
	private CommodityDto convertCommodityEntityToOutputDto(Commodity newCom) {
		CommodityDto outputDto = new CommodityDto();
		
		outputDto.setCommodityId(newCom.getCommodityId());
		outputDto.setCommodityName(newCom.getCommodityName());
		outputDto.setCurrency(newCom.getCurrency());
		outputDto.setPrice(newCom.getPrice());
		outputDto.setBoUserId(newCom.getBoUserId());
		 
		return outputDto;
	}

	
	@Override
	public List<CommodityDto> fetchAllCommodities() {
		List<Commodity> commodity=this.commodityRepository.findAll();
		List<CommodityDto> dto=new ArrayList<CommodityDto>();
		for(Commodity c:commodity)
			dto.add(this.convertCommodityEntityToOutputDto(c));
		return dto;
	}

	@Override
	public List<String> fetchAllCommodityNames() {
		List<CommodityDto> commodityList=this.fetchAllCommodities();
		List<String> commodityNames=new ArrayList<String>();
		for(CommodityDto c:commodityList)
			commodityNames.add(c.getCommodityName());
		
		return commodityNames;
	}

	@Override
	public CommodityDto addCommodity(CommodityDto dto) {
				
		Commodity com =  this.convertInputAddCommodityToEntity(dto);
		Commodity newCom = this.commodityRepository.save(com);
		
		return this.convertCommodityEntityToOutputDto(newCom);
	}

	@Override
	public CommodityDto fetchSingleCommodityByName(SearchCommodityDto searchCommodityDto) {
		Commodity commodity = this.convertSearchCommodityDtoToEntity(searchCommodityDto);
		
		Commodity newCommodity = this.commodityRepository.findByCommodityName(commodity.getCommodityName());
		if(newCommodity==null) {
			return null;
		}
		CommodityDto commodityOutputDto =  this.convertCommodityEntityToOutputDto(newCommodity);
		return commodityOutputDto;
	}

	private Commodity convertSearchCommodityDtoToEntity(SearchCommodityDto searchCommodityDto) {
		Commodity commodity = new Commodity();
		
		commodity.setCommodityName(searchCommodityDto.getCommodityName());
		
		return commodity;
	}

	@Override
	public CommodityDto modifyCommodity(CommodityDto commodityNewOutputDto) {
		Commodity commodity = this.convertCommodityOutputToEntity(commodityNewOutputDto);
		Commodity modifyCommodity = this.commodityRepository.save(commodity);
		
		CommodityDto dto = this.convertCommodityEntityToOutputDto(modifyCommodity);
	
		return dto;
	}

	private Commodity convertCommodityOutputToEntity(CommodityDto commodityNewOutputDto) {
		Commodity commodity = new Commodity();
		
		commodity.setCommodityId(commodityNewOutputDto.getCommodityId());
		commodity.setCommodityName(commodityNewOutputDto.getCommodityName());
		commodity.setCurrency(commodityNewOutputDto.getCurrency());
		commodity.setDateTime(commodityNewOutputDto.getDateTime());
		commodity.setPrice(commodityNewOutputDto.getPrice());
		return commodity;
	}

	@Override
	public CommodityDto fetchSingleCommodityByName(String commodityName) {
		
		Commodity commodity = this.commodityRepository.findByCommodityName(commodityName);
		CommodityDto commodityDto = this.convertCommodityEntityToOutputDto(commodity);
		return commodityDto;
	}
	
	@Override
	public List<CommodityHistoricalDto> fetchAllHistoricalDataByCommodityId(int commodityId) {
		List<HistoricalRecordCommodity> historicalPriceData = this.commodityHisDataRepository.findAllByCommodityId(commodityId).orElse(null);
		
		if(historicalPriceData==null) {
			return null;
		}
		
		List<CommodityHistoricalDto> commodityHistoricalDto = historicalPriceData.stream()
				.map(this::convertCommodityHistoricalEntityToDto).collect(Collectors.toList());
		
		return commodityHistoricalDto;
	}
	
	public CommodityHistoricalDto convertCommodityHistoricalEntityToDto(HistoricalRecordCommodity historicalRecordCommodity){
		
		CommodityHistoricalDto commodityHistoricalDto = new CommodityHistoricalDto();
		
		commodityHistoricalDto.setCommodityHistoricalDataId(historicalRecordCommodity.getCommodityHistoricalDataId());
		commodityHistoricalDto.setCommodityId(historicalRecordCommodity.getCommodityId());
		commodityHistoricalDto.setCommodityPrice(historicalRecordCommodity.getCommodityPrice());
		commodityHistoricalDto.setCurrency(historicalRecordCommodity.getCurrency());
		commodityHistoricalDto.setDateTime(historicalRecordCommodity.getDateTime());
		
		return commodityHistoricalDto;
	}

	@Override
	public boolean addCommodityPrice(AddCommodityPriceDto addCommodityDto) {
		Commodity commodity=this.commodityRepository.findByCommodityName(addCommodityDto.getCommodityName());
		if(commodity!=null) {
		HistoricalRecordCommodity entity=new HistoricalRecordCommodity();
		entity.setCommodityId(commodity.getCommodityId());
		entity.setCurrency(commodity.getCurrency());
		entity.setCommodityPrice(addCommodityDto.getCommodityPrice());
		entity.setDateTime(LocalDateTime.now().toString());
		this.commodityHisDataRepository.save(entity);
		this.commodityRepository.updateCommodityPrice(commodity.getCommodityName(), addCommodityDto.getCommodityPrice());
		return true;
		}
		
		return false;
	}
	
}
