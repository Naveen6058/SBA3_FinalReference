package com.wf.training.bootapprestfulcrud.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistoricalRecordCommodity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI
	private Long commodityHistoricalDataId;
	private int commodityId;
	private float CommodityPrice; 
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
		return CommodityPrice;
	}
	public void setCommodityPrice(float commodityPrice) {
		CommodityPrice = commodityPrice;
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
