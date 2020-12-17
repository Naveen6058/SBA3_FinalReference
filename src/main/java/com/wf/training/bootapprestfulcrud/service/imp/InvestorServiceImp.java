package com.wf.training.bootapprestfulcrud.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.bootapprestfulcrud.dto.CompanyDto;
import com.wf.training.bootapprestfulcrud.dto.InvestorDto;
import com.wf.training.bootapprestfulcrud.dto.LoginDto;
import com.wf.training.bootapprestfulcrud.dto.ShareTransactionDto;
import com.wf.training.bootapprestfulcrud.dto.WalletDto;
import com.wf.training.bootapprestfulcrud.dto.WalletTransactionsDto;
import com.wf.training.bootapprestfulcrud.entity.Commodity;
import com.wf.training.bootapprestfulcrud.entity.Company;
import com.wf.training.bootapprestfulcrud.entity.Investor;
import com.wf.training.bootapprestfulcrud.entity.InvestorWallet;
import com.wf.training.bootapprestfulcrud.entity.InvestorWalletTransaction;
import com.wf.training.bootapprestfulcrud.entity.RecentlyViewedCompanies;
import com.wf.training.bootapprestfulcrud.entity.ShareTransaction;
import com.wf.training.bootapprestfulcrud.repository.CommodityRepository;
import com.wf.training.bootapprestfulcrud.repository.CompanyRepository;
import com.wf.training.bootapprestfulcrud.repository.InvWalletTransactionRepository;
import com.wf.training.bootapprestfulcrud.repository.InvestorRepository;
import com.wf.training.bootapprestfulcrud.repository.InvestorWalletRepository;
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
	
	private RecentlyViewedCompanies convertRecentViewDtoToEntity(LoginDto investorLoginDto, CompanyDto companyDto) {
		RecentlyViewedCompanies recentlyViewedCompanies = new RecentlyViewedCompanies();
		
		recentlyViewedCompanies.setCompanyTitle(companyDto.getCompanyTitle());
		LocalDate date = LocalDate.now();
		recentlyViewedCompanies.setDateTime(date.toString());
		recentlyViewedCompanies.setLoginKey(investorLoginDto.getLoginKey());
		
		return recentlyViewedCompanies;
	}
	
	private CompanyDto convertRecentViewEntityToCompanyDto(RecentlyViewedCompanies recentlyViewedCompanies) {
		
		CompanyServiceImp csi = new CompanyServiceImp();
				
		Company company = this.companyRepository.findBycompanyTitle(recentlyViewedCompanies.getCompanyTitle()).orElse(null);
		
		CompanyDto companyDto = csi.convertCompanyEntityToOutputDto(company);
		
		return companyDto;
		
	}
	
	private InvestorWallet convertInvestorDtoToWalletEntity(InvestorDto investorDto) {
		InvestorWallet investorWallet = new InvestorWallet();
		
		investorWallet.setInvestorID(investorDto.getInvestorId());
		investorWallet.setDateTime(LocalDateTime.now().toString());
		
		return investorWallet;
	}
	
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

	@Override
	public List<InvestorDto> fetchAllInvestors() {
		return null;
	}

	@Override
	public InvestorDto fetchSingleInvestor(Long id) {
		return null;
	}

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

	@Override
	public InvestorDto editInvestor(Long id, InvestorDto investorDto) {
		return null;
	}

	@Override
	public InvestorDto deleteInvestor(Long id) {
		return null;
	}
	
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
	
	public WalletDto convertLoginKeyToWalletDto(String loginKey) {
		WalletDto walletDto = new WalletDto();
		
		Investor investor = this.invRepository.findByLoginKey(loginKey).orElse(null);
		String name = investor.getFirstName()+" "+investor.getLastName();
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(investor.getInvestorId()).orElse(null);
		
		List<InvestorWalletTransaction> walletTransactions = this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
				.orElse(null);
		
		double amountInvested = 0.0;
		double balance = 0.0;
		
		for(InvestorWalletTransaction transaction:walletTransactions) {
			if (transaction.getTransactionType().equalsIgnoreCase("Credit")) {
				balance = balance + transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Debit")) {
				balance = balance - transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Buy")) {
				balance = balance - transaction.getAmount();
				amountInvested = amountInvested + transaction.getAmount();
			}else if (transaction.getTransactionType().equalsIgnoreCase("Sell")) {
				balance = balance + transaction.getAmount();
				amountInvested = amountInvested - transaction.getAmount();
			}
		}
		
		walletDto.setWalletId(invWallet.getWalletId());
		walletDto.setInvestorID(investor.getInvestorId());
		walletDto.setFullName(name);
		walletDto.setAmount(amountInvested);
		walletDto.setBalance(balance);
		
		return walletDto;
	}
	
	public InvestorWalletTransaction convertLoginKeyAmountToWalletTransactionEntity(String loginKey, String transactionType, double amount,
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
	
	@Override
	public WalletDto fetchWalletDetails(String loginKey) {
		WalletDto walletDto = this.convertLoginKeyToWalletDto(loginKey);
		return walletDto;
	}
	
	
	//Add Money
	@Override
	public String addMoneyToWallet(String loginKey, double amount) {
		
		InvestorWalletTransaction walletTransaction = this.convertLoginKeyAmountToWalletTransactionEntity(loginKey, "Credit", amount, 0);
		
		InvestorWalletTransaction newWalletTransaction = this.walletTransactionRepository.save(walletTransaction);
		
		if (newWalletTransaction==null) {
			return "Error in Adding Money";
		}
		
		return "Added "+amount+" Money Successfully";
	}
	
	//WithDraw Money
	@Override
	public String withdrawMoneyFromWallet(String loginKey, double amount) {
		
		InvestorWalletTransaction walletTransaction = this.convertLoginKeyAmountToWalletTransactionEntity(loginKey, "Debit", amount, 0);
		
		InvestorWalletTransaction newWalletTransaction = this.walletTransactionRepository.save(walletTransaction);
		
		if (newWalletTransaction==null) {
			return "Error in Withdraw Money";
		}
		
		return "Withdraw "+amount+" Money Successfully";
	}
	
	//Fetch all transactions
	@Override
	public List<WalletTransactionsDto> fetchAllWalletTransactions(String loginKey) {
		
		List<WalletTransactionsDto> walletTransactionsDto = this.convertLoginKeyToWalletTransactionsDto(loginKey);
		
		return walletTransactionsDto;
	}
	
	public List<WalletTransactionsDto> convertLoginKeyToWalletTransactionsDto(String loginKey) {
		Investor inv = this.invRepository.findByLoginKey(loginKey).orElse(null);
		InvestorWallet invWallet = this.walletRepository.findByInvestorID(inv.getInvestorId()).orElse(null);
		List<InvestorWalletTransaction> investorWalletTransaction= this.walletTransactionRepository.findAllByWalletId(invWallet.getWalletId())
				.orElse(null);
		if (investorWalletTransaction==null) {
			return null;
		}
		
		List<WalletTransactionsDto> walletTransactionsDto = investorWalletTransaction.stream().map(this::convertInvestorWalletTransactionToDto).collect(Collectors.toList());
		return walletTransactionsDto;
	}
	
	public WalletTransactionsDto convertInvestorWalletTransactionToDto(InvestorWalletTransaction invWalletTransaction) {
		WalletTransactionsDto walletTransactionsDto = new WalletTransactionsDto();
		
		walletTransactionsDto.setAmount(invWalletTransaction.getAmount());
		walletTransactionsDto.setDateTime(invWalletTransaction.getDateTime());
		walletTransactionsDto.setShareTransactionId(invWalletTransaction.getShareTransactionId());
		walletTransactionsDto.setTransactionType(invWalletTransaction.getTransactionType());
		walletTransactionsDto.setWalletId(invWalletTransaction.getWalletId());
		return walletTransactionsDto;
	}
	
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
		
		message = "Transaction was successful";
		return message;
	}
	
	public ShareTransaction convertToShareTransactionEntity(String stockName, String loginKey, String transactionType,
			String companyCommodity, int transactionShareCount) {
		ShareTransaction shareTransaction = new ShareTransaction();
		double stockPrice = 0.0d;
		double transactionAmount = 0.0d;
		double commission = 0.0d;
		
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
				shareTransaction.setCompanyCommodity("Bought shares are less than the Selling shares");
				return shareTransaction;
			}
			commission = (transactionAmount*(0.02));
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
		
		return shareTransaction;
	}
	
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
	
	@Override
	public ShareTransactionDto findShareTransactionsById(Long shareTransactionId) {
		
		ShareTransaction shareTransaction = this.shareTransRepository.findByShareTransactionId(shareTransactionId);
		if (shareTransaction==null) {
			return null;
		}
		
		ShareTransactionDto shareTransactionDto = this.convertShareTransactionEntityToDto(shareTransaction);
		
		return shareTransactionDto;
	}
	
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
		
		return shareTransactionDto;
	}
	
}
