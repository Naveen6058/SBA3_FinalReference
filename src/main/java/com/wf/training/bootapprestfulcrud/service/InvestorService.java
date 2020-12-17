package com.wf.training.bootapprestfulcrud.service;

import java.util.List;

import com.wf.training.bootapprestfulcrud.dto.CompanyDto;
import com.wf.training.bootapprestfulcrud.dto.InvestorDto;
import com.wf.training.bootapprestfulcrud.dto.LoginDto;
import com.wf.training.bootapprestfulcrud.dto.ShareTransactionDto;
import com.wf.training.bootapprestfulcrud.dto.WalletDto;
import com.wf.training.bootapprestfulcrud.dto.WalletTransactionsDto;
import com.wf.training.bootapprestfulcrud.entity.RecentlyViewedCompanies;

public interface InvestorService {

	public List<InvestorDto> fetchAllInvestors();
	public InvestorDto fetchSingleInvestor(Long id);
	public InvestorDto addInvestor(InvestorDto investorDto);
	public InvestorDto editInvestor(Long id, InvestorDto investorDto);
	public InvestorDto deleteInvestor(Long id);
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
	
}
