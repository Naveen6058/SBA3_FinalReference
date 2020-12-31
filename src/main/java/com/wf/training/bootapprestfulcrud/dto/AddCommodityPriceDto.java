package com.wf.training.bootapprestfulcrud.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AddCommodityPriceDto {
	
	private String commodityName;

	@DecimalMin("0.1")
	@NotNull(message="Commodity price should not be blank")
	private Float commodityPrice;
	
	private LocalDateTime dateTime;

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Float getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(Float commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	

}
