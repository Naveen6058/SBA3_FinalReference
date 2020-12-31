package com.wf.training.bootapprestfulcrud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ShareTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shareTransactionId;
	private String transactionType;
	private String stockName;
	private double stockPrice;
	private Long walletId;
	private int transactionShareCount;
	private String dateTime;
	private double transactionAmount;
	private double commission;
	private String companyCommodity;
	private double earning;
	
	public Long getShareTransactionId() {
		return shareTransactionId;
	}
	public void setShareTransactionId(Long shareTransactionId) {
		this.shareTransactionId = shareTransactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getTransactionShareCount() {
		return transactionShareCount;
	}
	public void setTransactionShareCount(int transactionShareCount) {
		this.transactionShareCount = transactionShareCount;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Long getWalletId() {
		return walletId;
	}
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	public String getCompanyCommodity() {
		return companyCommodity;
	}
	public void setCompanyCommodity(String companyCommodity) {
		this.companyCommodity = companyCommodity;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public double getEarning() {
		return earning;
	}
	public void setEarning(double earning) {
		this.earning = earning;
	}
	
}
