package com.wf.training.bootapprestfulcrud.dto;

public class WalletTransactionsDto {
	
	private Long walletId;
	private String transactionType;
	private double amount;
	private String dateTime;
	private long shareTransactionId;
	
	public Long getWalletId() {
		return walletId;
	}
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public long getShareTransactionId() {
		return shareTransactionId;
	}
	public void setShareTransactionId(long shareTransactionId) {
		this.shareTransactionId = shareTransactionId;
	}

}
