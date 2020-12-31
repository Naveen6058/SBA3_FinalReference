package com.wf.training.bootapprestfulcrud.dto;

public class PortfolioDto {
	
	private String stockName;
	private double avgStockPrice;
	private double currentStockPrice;
	private String companyCommodity;
	private double earning;
	private int stockQuantity;
	private double investedAmount;
	private double currentAmount;
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getAvgStockPrice() {
		return avgStockPrice;
	}
	public void setAvgStockPrice(double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
	public double getCurrentStockPrice() {
		return currentStockPrice;
	}
	public void setCurrentStockPrice(double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}
	public String getCompanyCommodity() {
		return companyCommodity;
	}
	public void setCompanyCommodity(String companyCommodity) {
		this.companyCommodity = companyCommodity;
	}
	public double getEarning() {
		return earning;
	}
	public void setEarning(double earning) {
		this.earning = earning;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public double getInvestedAmount() {
		return investedAmount;
	}
	public void setInvestedAmount(double investedAmount) {
		this.investedAmount = investedAmount;
	}
	public double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
}
