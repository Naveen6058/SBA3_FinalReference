package com.wf.training.bootapprestfulcrud.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.wf.training.bootapprestfulcrud.entity.Commodity;
import com.wf.training.bootapprestfulcrud.entity.Company;
import com.wf.training.bootapprestfulcrud.entity.Investor;
import com.wf.training.bootapprestfulcrud.entity.InvestorWallet;
import com.wf.training.bootapprestfulcrud.entity.InvestorWalletTransaction;
import com.wf.training.bootapprestfulcrud.entity.PortfolioReport;
import com.wf.training.bootapprestfulcrud.entity.RecentlyViewedCompanies;
import com.wf.training.bootapprestfulcrud.entity.ShareTransaction;
import com.wf.training.bootapprestfulcrud.repository.CommodityRepository;
import com.wf.training.bootapprestfulcrud.repository.CompanyRepository;
import com.wf.training.bootapprestfulcrud.repository.InvWalletTransactionRepository;
import com.wf.training.bootapprestfulcrud.repository.InvestorRepository;
import com.wf.training.bootapprestfulcrud.repository.InvestorWalletRepository;
import com.wf.training.bootapprestfulcrud.repository.PortfolioReportRepository;
import com.wf.training.bootapprestfulcrud.repository.RecentViewCompRepository;
import com.wf.training.bootapprestfulcrud.repository.ShareTransactionRepository;
import com.wf.training.bootapprestfulcrud.service.InvestorService;
@Service
public class InvestorServiceImp implements InvestorService {
	
	@Autowired
	private InvestorRepository invRepository;
	
	@Autowired
	private RecentViewCompRepository recentViewRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private InvestorWalletRepository walletRepository;
	
	@Autowired
	private InvWalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	private ShareTransactionRepository shareTransRepository;
	
	@Autowired
	private CommodityRepository commodityRepository;
	
	@Autowired
	private PortfolioReportRepository portfolioReportRepository;
	
	//************************************************************
	//************************************************************
	private Investor convertInvestorDtoToEntity(InvestorDto investorDto) {
		Investor investor = new Investor();
		
		investor.setFirstName(investorDto.getFirstName());
		investor.setLastName(investorDto.getLastName());
		investor.setMobileNumber(investorDto.getMobileNumber());
		investor.setPanId(investorDto.getPanId());
		investor.setGender(investorDto.getGender());
		investor.setEmailId(investorDto.getEmailId());
		investor.setLoginKey(investorDto.getLoginKey());
		investor.setPassword(investorDto.getPassword());
		
		return investor;
	}
	
	//************************************************************
	//************************************************************
	private InvestorDto convertInvestorEntityToDto(Investor investor) {
		InvestorDto investorDto = new InvestorDto();
		
		investorDto.setInvestorId(investor.getInvestorId());
		investorDto.setFirstName(investor.getFirstName());
		investorDto.setLastName(investor.getLastName());
		investorDto.setMobileNumber(investor.getMobileNumber());
		investorDto.setPanId(investor.getPanId());
		investorDto.setGender(investor.getGender());
		investorDto.setEmailId(investor.getEmailId());
		investorDto.setLoginKey(investor.getLoginKey());
		investorDto.setPassword(investor.getPassword());
		
		return investorDto;
	}
	
	//************************************************************
	//************************************************************
	private RecentlyViewedCompanies convertRecentViewDtoToEntity(LoginDto investorLoginDto, CompanyDto companyDto) {
		RecentlyViewedCompanies recentlyViewedCompanies = new RecentlyViewedCompanies();
		
		recentlyViewedCompanies.setCompanyTitle(companyDto.getCompanyTitle());
		LocalDate date = LocalDate.now();
		recentlyViewedCompanies.setDateTime(date.toString());
		recentlyViewedCompanies.setLoginKey(investorLoginDto.getLoginKey());
		
		return recentlyViewedCompanies;
	}
	
	//************************************************************
	//************************************************************
	private CompanyDto convertRecentViewEntityToCompanyDto(RecentlyViewedCompanies recentlyViewedCompanies) {
		
		CompanyServiceImp csi = new CompanyServiceImp();
				
		Company company = this.companyRepository.findBycompanyTitle(recentlyViewedCompanies.getCompanyTitle()).orElse(null);
		
		CompanyDto companyDto = csi.convertCompanyEntityToOutputDto(company);
		
		return companyDto;
		
	}
	
	//************************************************************
	//************************************************************
	private InvestorWallet convertInvestorDtoToWalletEntity(InvestorDto investorDto) {
		InvestorWallet investorWallet = new InvestorWallet();
		
		investorWallet.setInvestorID(investorDto.getInvestorId());
		investorWallet.setDateTime(LocalDateTime.now().toString());
		
		return investorWallet;
	}
	
	//************************************************************
	//************************************************************
	private InvestorWalletTransaction convertInvestorDtoToWalletTransEntity(InvestorDto investorDto, String transactionType, double amount,
			long shareTransactionId) {
		InvestorWalletTransaction investorWalletTransaction = new InvestorWalletTransaction();
		
		InvestorWallet invWallet= this.walletRepository.findByInvestorID(investorDto.getInvestorId()).orElse(null);
		
		investorWalletTransaction.setWalletId(invWallet.getWalletId());
		investorWalletTransaction.setTransactionType(transactionType);
		investorWalletTransaction.setAmount(amount);
		investorWalletTransaction.setDateTime(LocalDateTime.now().toString());
		investorWalletTransaction.setShareTransactionId(shareTransactionId);
		
		return investorWalletTransaction;
	}

	//************************************************************
	//************************************************************
	@Override
	public InvestorDto addInvestor(InvestorDto investorDto) {
		boolean status = this.validateLoginKeyPanPresent(investorDto);
		if (status == true) {
			return null;
		}
		Investor investor = this.convertInvestorDtoToEntity(investorDto);
		Investor newInvestor = this.invRepository.save(investor);
		InvestorDto outInvestorDto = this.convertInvestorEntityToDto(newInvestor);
		
		
		//Create Wallet
		this.walletRepository.save(this.convertInvestorDtoToWalletEntity(outInvestorDto));
		
		//Credit 2500 in wallet of new user
		InvestorWalletTransaction invWalletTransaction = this.convertInvestorDtoToWalletTransEntity(outInvestorDto, "Credit", 2500.0, 0);
		this.walletTransactionRepository.save(invWalletTransaction);
		
		return outInvestorDto;
	}
	
	//************************************************************
	//************************************************************
	@Override
	public RecentlyViewedCompanies addRecentViewCompany(LoginDto investorLoginDto, CompanyDto companyDto) {
		RecentlyViewedCompanies recentlyViewedCompanies = this.convertRecentViewDtoToEntity(investorLoginDto,companyDto);
		
		RecentlyViewedCompanies existingRecentlyViewed = this.recentViewRepository.findByLoginKeyAndCompanyTitle(recentlyViewedCompanies.getLoginKey(), 
				recentlyViewedCompanies.getCompanyTitle()).orElse(null);
		
		//Return Null if no values found
		if (existingRecentlyViewed!=null) {
			return null;
		}
		RecentlyViewedCompanies newRecentlyViewedCompanies = this.recentViewRepository.save(recentlyViewedCompanies);
		
		return newRecentlyViewedCompanies;
	}
	
	//************************************************************
	//************************************************************
	@Override
	public List<CompanyDto> getAllRecentViewCompanies(String loginKey) {
		List<RecentlyViewedCompanies> recentViewCompanies = this.recentViewRepository.findAllByLoginKey(loginKey).orElse(null);
		
		if (recentViewCompanies==null) {
			return null;
		}
		
		List<CompanyDto> allRecentCompanies = recentViewCompanies.stream().map(this::convertRecentViewEntityToCompanyDto)
				.collect(Collectors.toList());
		
		return allRecentCompanies;
	}

	//************************************************************
	//************************************************************
	@Override
	public boolean validateInvestor(LoginDto investorLoginDto) {
		boolean status = false;
		Investor investor = this.invRepository.findByLoginKey(investorLoginDto.getLoginKey()).orElse(null);
		
		if(investor!=null) {
			if(investor.getPassword().equalsIgnoreCase(investorLoginDto.getPassword())) {
				status = true;
			}
		}
		
		return status;
	}
	
	//************************************************************
	//************************************************************
	public boolean validateLoginKeyPanPresent(InvestorDto investorDto) {
		boolean status = false;
		Investor investor = this.invRepository.findByLoginKey(investorDto.getLoginKey()).orElse(null);
		
		if(investor!=null) {
			status = true;
		}
		
		Investor panInvestor = this.invRepository.findByPanId(investorDto.getPanId()).orElse(null);
		
		if(panInvestor!=null) {
			status = true;
		}
		
		return status;
	}
	
	//************************************************************
	//************************************************************
	public double getWalletBalance(String loginKey) {
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		
		List<InvestorWalletTransaction> walletTransactions = this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
				.orElse(null);
		
		double balance = 0.0;
		
		for(InvestorWalletTransaction transaction:walletTransactions) {
			if (transaction.getTransactionType().equalsIgnoreCase("Credit")) {
				balance = balance + transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Debit")) {
				balance = balance - transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Buy")) {
				balance = balance - transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Sell")) {
				balance = balance + transaction.getAmount();
			}
		}
		return balance;
	}
	
	//************************************************************
	//************************************************************
	public WalletDto convertLoginKeyToWalletDto(String loginKey) {
		WalletDto walletDto = new WalletDto();
		
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		String name = investor.getFirstName()+" "+investor.getLastName();
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		double balance = 0.0;
		
//		List<InvestorWalletTransaction> walletTransactions = this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
//				.orElse(null);
//		for(InvestorWalletTransaction transaction:walletTransactions) {
//			if (transaction.getTransactionType().equalsIgnoreCase("Credit")) {
//				balance = balance + transaction.getAmount();
//			}else if (transaction.getTransactionType().equalsIgnoreCase("Debit")) {
//				balance = balance - transaction.getAmount();
//			}else if (transaction.getTransactionType().equalsIgnoreCase("Buy")) {
//				balance = balance - transaction.getAmount();
//			}else if (transaction.getTransactionType().equalsIgnoreCase("Sell")) {
//				balance = balance + transaction.getAmount();
//			}
//		}
		balance = this.getWalletBalance(loginKey);
		
		walletDto.setWalletId(invWallet.getWalletId());
		walletDto.setInvestorID(investor.getInvestorId());
		walletDto.setFullName(name);
		walletDto.setBalance(balance);
		
		return walletDto;
	}
	
	//************************************************************
	//************************************************************
	public InvestorWalletTransaction convertToWalletTransactionEntity(String loginKey, String transactionType, double amount,
			long shareTransactionId) {
		InvestorWalletTransaction investorWalletTransaction = new InvestorWalletTransaction();
		Investor investor= this.invRepository.findByLoginKey(loginKey).orElse(null);
		
		InvestorWallet investorWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		
		investorWalletTransaction.setWalletId(investorWallet.getWalletId());
		investorWalletTransaction.setTransactionType(transactionType);
		investorWalletTransaction.setAmount(amount);
		investorWalletTransaction.setDateTime(LocalDateTime.now().toString());
		investorWalletTransaction.setShareTransactionId(shareTransactionId);
		
		return investorWalletTransaction;
	}
	
	//************************************************************
	//************************************************************
	@Override
	public WalletDto fetchWalletDetails(String loginKey) {
		WalletDto walletDto = this.convertLoginKeyToWalletDto(loginKey);
		return walletDto;
	}
	
	//************************************************************
	//************************************************************
	//Add Money
	@Override
	public String addMoneyToWallet(String loginKey, double amount) {
		
		InvestorWalletTransaction walletTransaction = this.convertToWalletTransactionEntity(loginKey, "Credit", amount, 0);
		
		InvestorWalletTransaction newWalletTransaction = this.walletTransactionRepository.save(walletTransaction);
		
		if (newWalletTransaction==null) {
			return "Error in Adding Money";
		}
		
		return "Added "+amount+" Money Successfully";
	}
	
	//************************************************************
	//WithDraw Money
	//************************************************************
	@Override
	public String withdrawMoneyFromWallet(String loginKey, double amount) {
		
		double existingBalance = this.getWalletBalance(loginKey);
		
		if (existingBalance < amount) {
			return "Current balance is lesser than Withdrawal amount";
		}
		
		InvestorWalletTransaction walletTransaction = this.convertToWalletTransactionEntity(loginKey, "Debit", amount, 0);
		
		InvestorWalletTransaction newWalletTransaction = this.walletTransactionRepository.save(walletTransaction);
		
		if (newWalletTransaction==null) {
			return "Error in Withdrawal of Money";
		}
		
		return "Withdrawal of "+amount+" Successfull";
	}
	
	//************************************************************
	//Fetch all transactions
	//************************************************************
	@Override
	public List<WalletTransactionsDto> fetchAllWalletTransactions(String loginKey) {
		
		List<WalletTransactionsDto> walletTransactionsDto = this.convertLoginKeyToWalletTransactionsDto(loginKey);
		
		return walletTransactionsDto;
	}
	
	//************************************************************
	//************************************************************
	public List<WalletTransactionsDto> convertLoginKeyToWalletTransactionsDto(String loginKey) {
		Investor inv = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(inv.getInvestorId()).orElse(null);
		List<InvestorWalletTransaction> investorWalletTransaction= this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
				.orElse(null);
		if (investorWalletTransaction==null) {
			return null;
		}
		
		List<WalletTransactionsDto> walletTransactionsDto = investorWalletTransaction.stream().map(this::convertInvestorWalletTransactionToDto)
				.collect(Collectors.toList());
		return walletTransactionsDto;
	}
	
	//************************************************************
	//************************************************************
	public WalletTransactionsDto convertInvestorWalletTransactionToDto(InvestorWalletTransaction invWalletTransaction) {
		WalletTransactionsDto walletTransactionsDto = new WalletTransactionsDto();
		
		walletTransactionsDto.setAmount(invWalletTransaction.getAmount());
		walletTransactionsDto.setDateTime(invWalletTransaction.getDateTime());
		walletTransactionsDto.setShareTransactionId(invWalletTransaction.getShareTransactionId());
		walletTransactionsDto.setTransactionType(invWalletTransaction.getTransactionType());
		walletTransactionsDto.setWalletId(invWalletTransaction.getWalletId());
		return walletTransactionsDto;
	}
	
	//************************************************************
	//************************************************************
	@Override
	public String buySellShares(String stockName, String loginKey, String transactionType,
			String companyCommodity, int transactionShareCount) {
		String message = "";
		ShareTransaction shareTransaction = this.convertToShareTransactionEntity(stockName, loginKey, transactionType, 
				companyCommodity, transactionShareCount);
		
		if ((!(shareTransaction.getCompanyCommodity().equalsIgnoreCase("Company")))&&
			(!(shareTransaction.getCompanyCommodity().equalsIgnoreCase("Commodity")))){
			return shareTransaction.getCompanyCommodity();
		}
		
		this.shareTransRepository.save(shareTransaction);
		InvestorWalletTransaction invWalletTrans =  this.convertShareTransactionToWalletTransactionEntity(shareTransaction);
		this.walletTransactionRepository.save(invWalletTrans);
		
		if (shareTransaction.getTransactionType().equalsIgnoreCase("Buy")) {
			message = "Bought "+transactionShareCount+" shares/units of "+shareTransaction.getStockName()+" successfully";
		}else {
			message = "Sold "+transactionShareCount+" shares/units of "+shareTransaction.getStockName()+" successfully";
		}
		return message;
	}
	
	//************************************************************
	//************************************************************
	public ShareTransaction convertToShareTransactionEntity(String stockName, String loginKey, String transactionType,
			String companyCommodity, int transactionShareCount) {
		ShareTransaction shareTransaction = new ShareTransaction();
		double stockPrice = 0.0d;
		double transactionAmount = 0.0d;
		double commission = 0.0d;
		double earning = 0.0d;
		
		Investor inv = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(inv.getInvestorId()).orElse(null);
		
		if (companyCommodity.equalsIgnoreCase("Company")) {
			Company company = this.companyRepository.findBycompanyTitle(stockName).orElse(null);
			stockPrice = company.getSharePrice();
		}else {
			Commodity commodity = this.commodityRepository.findByCommodityName(stockName);
			stockPrice = commodity.getPrice();
		}
		transactionAmount = stockPrice*transactionShareCount;

		if (transactionType.equalsIgnoreCase("Sell")) {
			int existingShares = this.getSharesBalance(loginKey, stockName);
			if (existingShares<transactionShareCount) {
				shareTransaction.setCompanyCommodity("Bought shares/units are less than the Selling shares/units");
				return shareTransaction;
			}
			
			commission = (transactionAmount*(0.02));
			
			earning = this.getEarning(invWallet, stockName, transactionShareCount, stockPrice, commission);
		}else {
			double balance = this.getWalletBalance(loginKey);
			
			if (balance < transactionAmount) {
				shareTransaction.setCompanyCommodity("Balance is insufficient");
				return shareTransaction;
			}
		}
		shareTransaction.setWalletId(invWallet.getWalletId());
		shareTransaction.setStockName(stockName);
		shareTransaction.setTransactionShareCount(transactionShareCount);
		shareTransaction.setStockPrice(stockPrice);
		shareTransaction.setCompanyCommodity(companyCommodity);
		shareTransaction.setTransactionType(transactionType);
		shareTransaction.setTransactionAmount(transactionAmount);
		shareTransaction.setCommission(commission);
		shareTransaction.setDateTime(LocalDateTime.now().toString());
		shareTransaction.setEarning(earning);
		
		return shareTransaction;
	}
	//************************************************************
	//Get Earnings
	//************************************************************
	public double getEarning(InvestorWallet invWallet, String stockName, int transactionShareCount, double curStockPrice,
			double commission) {
		Map<String, String> mapShareQuantAmount = this.getShareQuantityAmount(invWallet);
		
		//Get the previous values
		String[] mapArrayValue = mapShareQuantAmount.get(stockName).split(";");
		Integer stockQuant = Integer.parseInt(mapArrayValue[0]);
		Double stockAmount = Double.parseDouble(mapArrayValue[1]);
		
		double previousStockPrice = (stockAmount/stockQuant);
		
		double earning = (curStockPrice*transactionShareCount) - (previousStockPrice*transactionShareCount) - commission;
		
		return earning;
	}
	
	//************************************************************
	//Get Shares Balance
	//************************************************************
	public int getSharesBalance(String loginKey, String stockName) {
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		int sharesBalance = 0;
		
		List<InvestorWalletTransaction> walletTransactions = this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
				.orElse(null);
		
		for(InvestorWalletTransaction transaction:walletTransactions) {
			if(transaction.getShareTransactionId()!=0) {
				List<ShareTransaction> shareTransactions = this.shareTransRepository.findAllByShareTransactionId(transaction.getShareTransactionId());
				for(ShareTransaction trans:shareTransactions) {
					if(trans.getStockName().equalsIgnoreCase(stockName)) {
						if (trans.getTransactionType().equalsIgnoreCase("Buy")) {
							sharesBalance = sharesBalance+trans.getTransactionShareCount();
						}else if(trans.getTransactionType().equalsIgnoreCase("Sell")) {
							sharesBalance = sharesBalance-trans.getTransactionShareCount();
						}
					}
				}
			}
		}
		return  sharesBalance;
	}
	
	//************************************************************
	//Convert ShareTransaction To WalletTransactionEntity
	//************************************************************
	
	public InvestorWalletTransaction convertShareTransactionToWalletTransactionEntity(ShareTransaction shareTransaction) {
		InvestorWalletTransaction investorWalletTransaction = new InvestorWalletTransaction();
		double transactionAmount = 0.0d; 
		
		investorWalletTransaction.setDateTime(shareTransaction.getDateTime());
		investorWalletTransaction.setShareTransactionId(shareTransaction.getShareTransactionId());
		investorWalletTransaction.setTransactionType(shareTransaction.getTransactionType());
		investorWalletTransaction.setWalletId(shareTransaction.getWalletId());
		if (shareTransaction.getTransactionType().equalsIgnoreCase("Sell")) {
			transactionAmount = shareTransaction.getTransactionAmount() - shareTransaction.getCommission();
			investorWalletTransaction.setAmount(transactionAmount);
		}else {
			investorWalletTransaction.setAmount(shareTransaction.getTransactionAmount());
		}
		
		return investorWalletTransaction;
	}
	
	//************************************************************
	//To find Share Transaction By Id
	//************************************************************
	@Override
	public ShareTransactionDto findShareTransactionsById(Long shareTransactionId) {
		
		ShareTransaction shareTransaction = this.shareTransRepository.findByShareTransactionId(shareTransactionId);
		if (shareTransaction==null) {
			return null;
		}
		
		ShareTransactionDto shareTransactionDto = this.convertShareTransactionEntityToDto(shareTransaction);
		
		return shareTransactionDto;
	}
	
	//************************************************************
	//Share Transaction to Share Entity Dto
	//************************************************************
	
	public ShareTransactionDto convertShareTransactionEntityToDto(ShareTransaction shareTransaction) {
		
		ShareTransactionDto shareTransactionDto = new ShareTransactionDto();
		
		shareTransactionDto.setCommission(shareTransaction.getCommission());
		shareTransactionDto.setCompanyCommodity(shareTransaction.getCompanyCommodity());
		shareTransactionDto.setDateTime(shareTransaction.getDateTime());
		shareTransactionDto.setShareTransactionId(shareTransaction.getShareTransactionId());
		shareTransactionDto.setStockName(shareTransaction.getStockName());
		shareTransactionDto.setTransactionAmount(shareTransaction.getTransactionAmount());
		shareTransactionDto.setTransactionShareCount(shareTransaction.getTransactionShareCount());
		shareTransactionDto.setTransactionType(shareTransaction.getTransactionType());
		shareTransactionDto.setWalletId(shareTransaction.getWalletId());
		shareTransactionDto.setStockPrice(shareTransaction.getStockPrice());
		shareTransactionDto.setEarning(shareTransaction.getEarning());
		
		return shareTransactionDto;
	}
	
	//************************************************************
	//Home Page Output showing the Portfolio value, Wallet Balance 
	//************************************************************
	@Override
	public HomePageOutputDto fetchPortFolioDetails(String loginKey) {
		
		HomePageOutputDto homePageOutputDto = this.convertLoginKeyToHomePageOutputDto(loginKey);

		return homePageOutputDto;
	}
	
	//************************************************************
	//Conversion of the login key to Home page output Dto
	//************************************************************
	public HomePageOutputDto convertLoginKeyToHomePageOutputDto(String loginKey) {
		double amountEarned = 0d;
		double amountInvested = 0d;
		double balance = 0d;
		double currentPortfolioValue = 0d;
		Map<String,String> mapShareTrans = new HashMap<String,String>();
		HomePageOutputDto homePageOutputDto = new HomePageOutputDto();
		
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		
		List<InvestorWalletTransaction> invWalletTrans = this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId()).orElse(null);
		
		//Balance calculation
		for (InvestorWalletTransaction wallTran:invWalletTrans) {
			if (wallTran.getTransactionType().equalsIgnoreCase("Credit")) {
				balance = balance + wallTran.getAmount();
			} else if (wallTran.getTransactionType().equalsIgnoreCase("Debit")) {
				balance = balance - wallTran.getAmount();
			} else if (wallTran.getTransactionType().equalsIgnoreCase("Buy")) {
				balance = balance - wallTran.getAmount();
			} else if (wallTran.getTransactionType().equalsIgnoreCase("Sell")) {
				balance = balance + wallTran.getAmount();
			}
		}
		
		//Share Quantity and Amount Calculation
		mapShareTrans = this.getShareQuantityAmount(invWallet);
		
		//Calculate amountInvested and currentPortfolioValue
		for (String key:mapShareTrans.keySet()) {
			//Get the previous values
			String[] mapArrayValue = mapShareTrans.get(key).split(";");
			Integer stockQuant = Integer.parseInt(mapArrayValue[0]);
			Double stockAmount = Double.parseDouble(mapArrayValue[1]);
			double currentPrice = 0;
			
			amountInvested = amountInvested + stockAmount;
			
			if (mapArrayValue[2].equalsIgnoreCase("Company")) {
				Company company = this.companyRepository.findBycompanyTitle(key).orElse(null);
				currentPrice =  company.getSharePrice();
			} else if (mapArrayValue[2].equalsIgnoreCase("Commodity")) {
				Commodity commodity = this.commodityRepository.findByCommodityName(key);
				currentPrice =  commodity.getPrice();
			}
			currentPortfolioValue = currentPortfolioValue + (currentPrice*stockQuant);
		}
		
		//Amount Earned
		amountEarned = currentPortfolioValue - amountInvested;
		
		//Push all the values to the DTO
		homePageOutputDto.setAmountEarned(amountEarned);
		homePageOutputDto.setAmountInvested(amountInvested);
		homePageOutputDto.setBalance(balance);
		homePageOutputDto.setCurrentPortfolioValue(currentPortfolioValue);
		
		return homePageOutputDto;
	}
	
	//************************************************************
	//Get Shares Quantity and Amount
	//************************************************************
	public Map<String, String> getShareQuantityAmount(InvestorWallet invWallet){
		Map<String,String> mapShareTrans = new HashMap<String,String>();
		List<ShareTransaction> shareTrans= this.shareTransRepository.findAllByWalletId(invWallet.getWalletId());
		
		//Share Quantity and Amount Calculation
		for (ShareTransaction sTrans:shareTrans) {
			String mapValue = "";
			Integer quantity = sTrans.getTransactionShareCount();
			Double amount = sTrans.getTransactionAmount();
			String stockName = sTrans.getStockName();
			String companyCommodity = sTrans.getCompanyCommodity();
			
			if (sTrans.getTransactionType().equalsIgnoreCase("Buy")) {
				if (mapShareTrans.containsKey(stockName)) {
					//If the key is present then get the previous values
					String[] mapArrayValue = mapShareTrans.get(stockName).split(";");
					Integer initialQuant = Integer.parseInt(mapArrayValue[0]);
					Double initialAmount = Double.parseDouble(mapArrayValue[1]);
					
					//Add the quantity and the amount to the previous existing values
					quantity = initialQuant+quantity;
					amount = initialAmount+amount;
					
					//replace with the latest quantity and amount
					mapValue = quantity.toString()+";"+amount.toString()+";"+companyCommodity;
					mapShareTrans.replace(stockName, mapValue);
					
				} else {
					//Store in Map with key as Stock name and Value as String of quantity and amount
					mapValue = quantity.toString()+";"+amount.toString()+";"+companyCommodity;
					mapShareTrans.put(stockName, mapValue);
				}
			} else if (sTrans.getTransactionType().equalsIgnoreCase("Sell")) {
				//Get the previous values
				String[] mapArrayValue = mapShareTrans.get(stockName).split(";");
				Integer initialQuant = Integer.parseInt(mapArrayValue[0]);
				Double initialAmount = Double.parseDouble(mapArrayValue[1]);
				
				if (initialQuant==quantity) {
					mapShareTrans.remove(stockName);
				} else {
					Double pricePerShare = initialAmount/initialQuant;
					
					//Subtract the quantity and the amount to the previous existing values
					amount = initialAmount - (pricePerShare*quantity);
					quantity = initialQuant - quantity;
					
					//replace with the latest quantity and amount
					mapValue = quantity.toString()+";"+amount.toString()+";"+companyCommodity;
					mapShareTrans.replace(stockName, mapValue);
				}
			}
		}
		return mapShareTrans;
	}
	
	//************************************************************
	//Get Earning for 10 weeks
	//************************************************************
	@Override
	public List<Double> getEarningFor10Weeks(String loginKey){
		
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		List<ShareTransaction> listShareTrans= this.shareTransRepository.findAllByWalletIdAndTransactionType(invWallet.getWalletId(), "Sell");
		List<Double> weekEarning = new ArrayList<Double>();
		
		Collections.reverse(listShareTrans);
		
		for (int i = 1; i<11;i++) {
			double earning = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_YEAR, -i);
			LocalDate startDateTime = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();

			calendar.add(Calendar.WEEK_OF_YEAR, +1);
			LocalDate endDateTime = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
			for (ShareTransaction shareTrans: listShareTrans) {
				LocalDate sellDate = LocalDateTime.parse(shareTrans.getDateTime()).toLocalDate();
				
				if (sellDate.compareTo(endDateTime)<=0) {
					if (sellDate.compareTo(startDateTime)>0) {
						earning = earning + shareTrans.getEarning();
					}else {
						break;
					}
				}
			}
			weekEarning.add(earning);
		}
		Collections.reverse(weekEarning);
		return weekEarning;
	}
	//************************************************************
	//Convert Login Key To Portfolio Dto
	//************************************************************
	
	public List<PortfolioDto> convertLoginKeyToPortfolioDto(String loginKey){
		//Initializations and Declarations
		List<PortfolioDto> listPortfolioDto = new ArrayList<PortfolioDto>();
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		Map<String, String> shareQuantAmount = this.getShareQuantityAmount(invWallet);
		
		for (String key:shareQuantAmount.keySet()) {
			PortfolioDto portfolioDto = new PortfolioDto();
			String[] mapArrayValue = shareQuantAmount.get(key).split(";");
			Integer stockQuant = Integer.parseInt(mapArrayValue[0]);
			Double stockAmount = Double.parseDouble(mapArrayValue[1]);
			String companyCommodity = mapArrayValue[2];
			double avgStockPrice = stockAmount/stockQuant;
			double currentStockPrice = 0;
			if (companyCommodity.equalsIgnoreCase("Company")) {
				Company company= this.companyRepository.findBycompanyTitle(key).orElse(null);
				currentStockPrice = company.getSharePrice();
			}else {
				Commodity commodity = this.commodityRepository.findByCommodityName(key);
				currentStockPrice = commodity.getPrice();
			}
			
			double currentAmount = currentStockPrice*stockQuant;
			double earning = currentAmount-stockAmount;
			
			portfolioDto.setStockName(key);
			portfolioDto.setAvgStockPrice(avgStockPrice);
			portfolioDto.setCompanyCommodity(companyCommodity);
			portfolioDto.setCurrentAmount(currentAmount);
			portfolioDto.setCurrentStockPrice(currentStockPrice);
			portfolioDto.setEarning(earning);
			portfolioDto.setInvestedAmount(stockAmount);
			portfolioDto.setStockQuantity(stockQuant);
			
			listPortfolioDto.add(portfolioDto);
		}
		
		return listPortfolioDto;
	}
	
	//************************************************************
	//Get Portfolio Dto
	//************************************************************
	@Override
	public List<PortfolioDto> getPortfolio(String loginKey){
		
		List<PortfolioDto> portfolioDto= this.convertLoginKeyToPortfolioDto(loginKey);
		
		return portfolioDto;
	}
	
	//************************************************************
	//Convert Login Key To Portfolio Report Dto
	//************************************************************
	
	public List<PortfolioReportDto> convertToPortfolioReportDto(String loginKey, ReportTypeInputDto reportTypeInputDto){
		//Declarations
		Investor inv = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(inv.getInvestorId()).orElse(null);
		List<PortfolioReport> listPortfolioReport = this.portfolioReportRepository.findAllByWalletId(invWallet.getWalletId()).orElse(null);
		
		//return null if there are no records of the investor
		if (listPortfolioReport==null) {
			return null;
		}
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		Collections.reverse(listPortfolioReport);	//reverse list to search from end date
		List<PortfolioReportDto> listPortfolioReportDto = new ArrayList<PortfolioReportDto>();
		LocalDate localDate = LocalDate.now();
		
		//Get Start and end date according to the report type
		if (reportTypeInputDto.getReportType().equalsIgnoreCase("Monthly")) {
			startDate = localDate.withDayOfMonth(1);
			endDate = localDate.plusMonths(1).withDayOfMonth(1).minusDays(1);
		}else if (reportTypeInputDto.getReportType().equalsIgnoreCase("Annually")) {
			startDate = localDate.withDayOfYear(1);
			endDate = localDate.withDayOfYear(1).plusYears(1).minusDays(1);
		}else if (reportTypeInputDto.getReportType().equalsIgnoreCase("Periodic")) {
			System.out.println("Start Date is : "+reportTypeInputDto.getStartDate());
			startDate = LocalDate.parse(reportTypeInputDto.getStartDate());
			endDate = LocalDate.parse(reportTypeInputDto.getEndDate());
		}
		
		//Get values between start and end date
		for(PortfolioReport portfolioReport: listPortfolioReport) {
			LocalDate reportDate = LocalDate.parse(portfolioReport.getDate());
			PortfolioReportDto portfolioReportDto = new PortfolioReportDto();
			
			if (reportDate.compareTo(endDate)<=0) {
				if(reportDate.compareTo(startDate)>=0) {
					//get values into DTO to return
					portfolioReportDto.setDate(portfolioReport.getDate());
					portfolioReportDto.setInvestedAmount(portfolioReport.getInvestedAmount());
					portfolioReportDto.setPortfolioValue(portfolioReport.getPortfolioValue());
				}else {
					//break if the date is beyond start date
					break;
				}
			}
			listPortfolioReportDto.add(portfolioReportDto);
		}
		
		//reverse list to show in jsp from start date to end date
		Collections.reverse(listPortfolioReportDto);
		return listPortfolioReportDto;
	}
	
	//************************************************************
	//Get Portfolio Report Dto
	//************************************************************
	@Override
	public List<PortfolioReportDto> getPortfolioReport(String loginKey, ReportTypeInputDto reportTypeInputDto){
		
		List<PortfolioReportDto> portfolioReportDto = this.convertToPortfolioReportDto(loginKey, reportTypeInputDto);
		
		return portfolioReportDto;
	}
	
	
	//************************************************************
	//To find all Share Transaction
	//************************************************************
	@Override
	public List<ShareTransactionDto> findAllShareTransaction() {
		
		List<ShareTransaction> shareTransaction = this.shareTransRepository.findAll();
		List<ShareTransactionDto> dtoList=new ArrayList<ShareTransactionDto>();
		if (shareTransaction==null) {
			return null;
		}else {
			for(ShareTransaction tr:shareTransaction) {
				ShareTransactionDto shareTransactionDto = this.convertShareTransactionEntityToDto(tr);
				dtoList.add(shareTransactionDto);
			}
		}
		return dtoList;
	}
		
	//************************************************************
	//To find all Share Transaction between dates
	//************************************************************
	@Override
	public List<ShareTransactionDto> findAllShareTransactionBetweenDates(String startDate,String endDate) {
		
		List<ShareTransaction> shareTransaction = this.shareTransRepository.findAllBetweenStartDateAndEndDate(startDate,endDate);
		List<ShareTransactionDto> dtoList=new ArrayList<ShareTransactionDto>();
		if (shareTransaction==null) {
			return null;
		}else {
			for(ShareTransaction tr:shareTransaction) {
				ShareTransactionDto shareTransactionDto = this.convertShareTransactionEntityToDto(tr);
				dtoList.add(shareTransactionDto);
			}
		}
		return dtoList;
	}
}
