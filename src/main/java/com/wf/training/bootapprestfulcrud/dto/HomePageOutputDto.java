package com.wf.training.bootapprestfulcrud.dto;

public class HomePageOutputDto {
	
	private double currentPortfolioValue;
	private double balance;
	private double amountInvested;
	private double amountEarned;
	
	public double getCurrentPortfolioValue() {
		return currentPortfolioValue;
	}
	public void setCurrentPortfolioValue(double currentPortfolioValue) {
		this.currentPortfolioValue = currentPortfolioValue;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAmountInvested() {
		return amountInvested;
	}
	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}
	public double getAmountEarned() {
		return amountEarned;
	}
	public void setAmountEarned(double amountEarned) {
		this.amountEarned = amountEarned;
	}

}
