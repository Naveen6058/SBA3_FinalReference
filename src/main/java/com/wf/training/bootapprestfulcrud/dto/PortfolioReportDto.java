package com.wf.training.bootapprestfulcrud.dto;

public class PortfolioReportDto {
	
	private String date;
	private double portfolioValue;
	private double investedAmount;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPortfolioValue() {
		return portfolioValue;
	}
	public void setPortfolioValue(double portfolioValue) {
		this.portfolioValue = portfolioValue;
	}
	public double getInvestedAmount() {
		return investedAmount;
	}
	public void setInvestedAmount(double investedAmount) {
		this.investedAmount = investedAmount;
	}
}
