package com.wf.training.bootapprestfulcrud.service;

import java.util.List;

import com.wf.training.bootapprestfulcrud.dto.CompanyDto;
import com.wf.training.bootapprestfulcrud.dto.HomePageOutputDto;
import com.wf.training.bootapprestfulcrud.dto.InvestorDto;
import com.wf.training.bootapprestfulcrud.dto.LoginDto;
import com.wf.training.bootapprestfulcrud.dto.PortfolioDto;
import com.wf.training.bootapprestfulcrud.dto.PortfolioReportDto;
import com.wf.training.bootapprestfulcrud.dto.ReportTypeInputDto;
import com.wf.training.bootapprestfulcrud.dto.ShareTransactionDto;
import com.wf.training.bootapprestfulcrud.dto.WalletDto;
import com.wf.training.bootapprestfulcrud.dto.WalletTransactionsDto;
import com.wf.training.bootapprestfulcrud.entity.RecentlyViewedCompanies;

public interface InvestorService {

	public InvestorDto addInvestor(InvestorDto investorDto);
	boolean validateInvestor(LoginDto investorLoginDto);
	public RecentlyViewedCompanies addRecentViewCompany(LoginDto investorLoginDto, CompanyDto companyDto);
	public List<CompanyDto> getAllRecentViewCompanies(String loginKey);
	public WalletDto fetchWalletDetails(String loginKey);
	String addMoneyToWallet(String loginKey, double amount);
	String withdrawMoneyFromWallet(String loginKey, double amount);
	List<WalletTransactionsDto> fetchAllWalletTransactions(String loginKey);
	
	String buySellShares(String stockName, String loginKey, String transactionType, String companyCommodity,
			int transactionShareCount);
	
	ShareTransactionDto findShareTransactionsById(Long shareTransactionId);
	
	HomePageOutputDto fetchPortFolioDetails(String loginKey);
	List<Double> getEarningFor10Weeks(String loginKey);
	List<PortfolioDto> getPortfolio(String loginKey);
	List<PortfolioReportDto> getPortfolioReport(String loginKey, ReportTypeInputDto reportTypeInputDto);
	List<ShareTransactionDto> findAllShareTransaction();
	List<ShareTransactionDto> findAllShareTransactionBetweenDates(String startDate, String endDate);	
	
}
