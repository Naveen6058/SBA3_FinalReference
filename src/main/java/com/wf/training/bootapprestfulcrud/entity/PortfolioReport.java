package com.wf.training.bootapprestfulcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PortfolioReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;
	@Column(unique = true)
	private String date;
	private double portfolioValue;
	private double investedAmount;
	private Long walletId;
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
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
	public Long getWalletId() {
		return walletId;
	}
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

}
