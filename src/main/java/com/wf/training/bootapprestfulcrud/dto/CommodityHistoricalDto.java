package com.wf.training.bootapprestfulcrud.dto;

public class CommodityHistoricalDto {
	
	private Long commodityHistoricalDataId;
	private int commodityId;
	private float commodityPrice; 
	private String dateTime;
	private String currency;
	
	public Long getCommodityHistoricalDataId() {
		return commodityHistoricalDataId;
	}
	public void setCommodityHistoricalDataId(Long commodityHistoricalDataId) {
		this.commodityHistoricalDataId = commodityHistoricalDataId;
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}
	public float getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(float commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
