package com.wf.training.bootapprestfulcrud.controller;

//import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wf.training.bootapprestfulcrud.dto.CompanyHistoricalDataOutputDto;
import com.wf.training.bootapprestfulcrud.dto.HomePageOutputDto;
import com.wf.training.bootapprestfulcrud.dto.LoginDto;
import com.wf.training.bootapprestfulcrud.dto.MoneyInputDto;
import com.wf.training.bootapprestfulcrud.dto.PortfolioDto;
import com.wf.training.bootapprestfulcrud.dto.PortfolioReportDto;
import com.wf.training.bootapprestfulcrud.dto.ReportTypeInputDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityHistoricalDto;
import com.wf.training.bootapprestfulcrud.dto.CompanyDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCompanyDto;
import com.wf.training.bootapprestfulcrud.dto.SearchSectorInputDto;
import com.wf.training.bootapprestfulcrud.dto.ShareCountInputDto;
import com.wf.training.bootapprestfulcrud.dto.ShareTransactionDto;
import com.wf.training.bootapprestfulcrud.dto.WalletDto;
import com.wf.training.bootapprestfulcrud.dto.WalletTransactionsDto;
import com.wf.training.bootapprestfulcrud.service.CommodityService;
import com.wf.training.bootapprestfulcrud.service.CompanyHistoricalDataService;
import com.wf.training.bootapprestfulcrud.service.CompanyService;
import com.wf.training.bootapprestfulcrud.service.InvestorService;

@Controller
@SessionAttributes("Investor")
@RequestMapping("/user")
public class InvestorContoller {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyHistoricalDataService historicalService;
	@Autowired
	private InvestorService investorService;
	@Autowired
	private CommodityService commodityService;

	//dashboard for user
	@RequestMapping(value= {"/home","/dashboard","/index"})
	public String home(@SessionAttribute("Investor") LoginDto investorLoginDto, Model model) {
		
		HomePageOutputDto homePageOutputDto = this.investorService.fetchPortFolioDetails(investorLoginDto.getLoginKey());
		
		model.addAttribute("Investor", investorLoginDto);
		model.addAttribute("homePageOutputDto", homePageOutputDto);
		return "invHomePage";
	}
	
	@RequestMapping()
	public String defaultResponse() {
		return "redirect:/user/home";
	}
	
	//****************************Wallet*****************************************************
	@RequestMapping("/wallet")
	public String wallet(@SessionAttribute("Investor") LoginDto investorLoginDto, Model model) {
		WalletDto walletDto = this.investorService.fetchWalletDetails(investorLoginDto.getLoginKey());
		
		model.addAttribute("walletDto", walletDto);
		
		return "invWallet";
	}
	
	//****************************Money to be Added*****************************************************
	@RequestMapping("/returnAddmoney")
	public String returnAddMoney(@ModelAttribute("moneyInputDto") MoneyInputDto moneyInputDto, 
			@SessionAttribute("Investor") LoginDto investorLoginDto,
			Model model) {
		WalletDto walletDto = this.investorService.fetchWalletDetails(investorLoginDto.getLoginKey());
		model.addAttribute("walletDto", walletDto);
		return "invAddMoneyPage";
	}
	
	//****************************Money to be Withdrawn*****************************************************
	@RequestMapping("/returnWithdrawmoney")
	public String returnWithdrawMoney(@ModelAttribute("moneyInputDto") MoneyInputDto moneyInputDto,
			@SessionAttribute("Investor") LoginDto investorLoginDto,
			Model model) {
		WalletDto walletDto = this.investorService.fetchWalletDetails(investorLoginDto.getLoginKey());
		model.addAttribute("walletDto", walletDto);
		return "invWithdrawMoneyPage";
	}
	
	//****************************Add Money*****************************************************
	@RequestMapping("/addmoney")
	public String walletAddMoney(@Valid @ModelAttribute("moneyInputDto") MoneyInputDto moneyInputDto,BindingResult result, Model model,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		
		WalletDto walletDto = this.investorService.fetchWalletDetails(investorLoginDto.getLoginKey());
		model.addAttribute("walletDto", walletDto);
		if(result.hasErrors()) {
			return "invAddMoneyPage";
		}
		
		String message = this.investorService.addMoneyToWallet(investorLoginDto.getLoginKey(), moneyInputDto.getAmount());
		
		model.addAttribute("message", message);
		
		return "invAddMoneyPage";
	}
	
	//****************************Withdraw Money*****************************************************
	@RequestMapping("/withdrawmoney")
	public String walletWithdrawMoney(@Valid @ModelAttribute("moneyInputDto") MoneyInputDto moneyInputDto,BindingResult result, Model model,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		WalletDto walletDto = this.investorService.fetchWalletDetails(investorLoginDto.getLoginKey());
		model.addAttribute("walletDto", walletDto);
		
		if(result.hasErrors()) {
			return "invWithdrawMoneyPage";
		}
		
		String message = this.investorService.withdrawMoneyFromWallet(investorLoginDto.getLoginKey(), moneyInputDto.getAmount());
		
		model.addAttribute("message", message);
		
		return "invWithdrawMoneyPage";
	}
	
	//****************************Search Company*****************************************************
	@RequestMapping("/searchCompany")
	public String company(@ModelAttribute("company") SearchCompanyDto company, Model model) {
		List<CompanyDto> companyDto= this.companyService.fetchAllCompanies();
		model.addAttribute("companyDto", companyDto);
		return "invSearchCompany";
	}
	
	//****************************Search Commodity*****************************************************
	@RequestMapping("/searchCommodity")
	public String commodity(@ModelAttribute("commodity") SearchCommodityDto commodity, Model model) {
		List<CommodityDto> commodityDto = this.commodityService.fetchAllCommodities();
		model.addAttribute("commodityDto", commodityDto);
		return "invSearchCommodity";
	}
	
	//****************************Company*****************************************************
	@RequestMapping("/searchCompanyName")
	public String searchCompanyName(@Valid @ModelAttribute("company") SearchCompanyDto company, BindingResult result, Model model,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		if(result.hasErrors()) {
			return "invSearchCompany";
		}
		CompanyDto searchCompany = this.companyService.fetchSingleCompanyByName(company);
		if (searchCompany==null) {
			model.addAttribute("message", "Company Not Found");
			List<CompanyDto> companyDto= this.companyService.fetchAllCompanies();
			model.addAttribute("companyDto", companyDto);
			return "invSearchCompany";
		}
		this.investorService.addRecentViewCompany(investorLoginDto, searchCompany);
		
		model.addAttribute("searchCompany",searchCompany);
		return "invCompanyPage";
	}
	
	//****************************Commodity*****************************************************
	@RequestMapping("/commodity")
	public String searchCommodityName(@Valid @ModelAttribute("commodity") SearchCommodityDto searchCommodityDto, BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			return "invSearchCommodity";
		}
		
		CommodityDto commodityDto = this.commodityService.fetchSingleCommodityByName(searchCommodityDto);
		
		if (commodityDto==null) {
			model.addAttribute("message", "Commodity Not Found");
			return "invSearchCommodity";
		}
		
		model.addAttribute("commodityDto",commodityDto);
		return "invCommodityPage";
	}
	
	//****************************Company Page*****************************************************
	@RequestMapping("/company/{companyTitle}")
	public String searchCompanyByName(@PathVariable("companyTitle") String companyTitle, Model model,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {

		CompanyDto searchCompany = this.companyService.fetchSingleCompanyByName(companyTitle);
		this.investorService.addRecentViewCompany(investorLoginDto, searchCompany);
		
		model.addAttribute("searchCompany",searchCompany);
		return "invCompanyPage";
	}
	
	//****************************Commodity Page*****************************************************
	@RequestMapping("/commodity/{commodityName}")
	public String searchCommodityByName(@PathVariable("commodityName") String commodityName, Model model,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {

		CommodityDto commodityDto = this.commodityService.fetchSingleCommodityByName(commodityName);
		
		model.addAttribute("commodityDto",commodityDto);
		return "invCommodityPage";
	}
	
	//****************************Buy Page*****************************************************
	@RequestMapping("/buyCompany/{stockName}")
	public String returnBuyPage(@PathVariable("stockName") String companyName, Model model,
			@ModelAttribute("shareCount") ShareCountInputDto shareCount) {
		model.addAttribute("stockName", companyName);
		model.addAttribute("commodtiyCompany", "Company");
		model.addAttribute("transactionType", "Buy");
		CompanyDto companyDto = this.companyService.fetchSingleCompanyByName(companyName);
		
		model.addAttribute("stockDto",companyDto);
		return "invBuySellPage";
	}
	
	@RequestMapping("/BuyCompanyShares/{stockName}")
	public String buyShares(@PathVariable("stockName") String companyName, Model model,
			@Valid @ModelAttribute("shareCount") ShareCountInputDto shareCount, BindingResult result,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		model.addAttribute("commodtiyCompany", "Company");
		model.addAttribute("transactionType", "Buy");
		CompanyDto companyDto = this.companyService.fetchSingleCompanyByName(companyName);
		model.addAttribute("stockDto",companyDto);
		if (result.hasErrors()) {
			return "invBuySellPage";
		}
		
		String message= this.investorService.buySellShares(companyName, investorLoginDto.getLoginKey(), "Buy", 
				"Company", shareCount.getShareCount());
		model.addAttribute("message", message);
		
		return "invBuySellPage";
	}
	
	@RequestMapping("/sellCompany/{stockName}")
	public String returnSellPage(@PathVariable("stockName") String companyName, Model model,
			@ModelAttribute("shareCount") ShareCountInputDto shareCount) {
		model.addAttribute("companyTitle", companyName);
		model.addAttribute("commodtiyCompany", "Company");
		model.addAttribute("transactionType", "Sell");
		CompanyDto companyDto = this.companyService.fetchSingleCompanyByName(companyName);
		model.addAttribute("stockDto",companyDto);
		
		return "invBuySellPage";
	}
	
	@RequestMapping("/SellCompanyShares/{stockName}")
	public String sellShares(@PathVariable("stockName") String companyName, Model model,
			@Valid @ModelAttribute("shareCount") ShareCountInputDto shareCount, BindingResult result,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		model.addAttribute("commodtiyCompany", "Company");
		model.addAttribute("transactionType", "Sell");
		CompanyDto companyDto = this.companyService.fetchSingleCompanyByName(companyName);
		model.addAttribute("stockDto",companyDto);
		if (result.hasErrors()) {
			return "invBuySellPage";
		}
		
		String message= this.investorService.buySellShares(companyName, investorLoginDto.getLoginKey(), "Sell", 
				"Company", shareCount.getShareCount());
		
		model.addAttribute("message", message);
		
		return "invBuySellPage";
	}
	
	@RequestMapping("/historicalPrices/{companyCode}")
	public String companyHistoricalPrice(@PathVariable("companyCode") Long companyCode, Model model) {
		List<CompanyHistoricalDataOutputDto> companyHistoricalDataOutputDto = 
				this.historicalService.fetchSingleByCompanyId(companyCode);
		
		model.addAttribute("companyHistoricalDataOutputDto", companyHistoricalDataOutputDto);
		return "invCompHistoricalPrices"; 
	}
	
	@RequestMapping("/{commodityTitle}/historicalPrices/{commodityCode}")
	public String commodityHistoricalPrice(@PathVariable int commodityCode, Model model,@PathVariable String commodityTitle) {
		
		List<CommodityHistoricalDto> commodityHistoricalDto = this.commodityService.fetchAllHistoricalDataByCommodityId(commodityCode);
		
		model.addAttribute("commodityHistoricalDto", commodityHistoricalDto);
		model.addAttribute("commodityTitle", commodityTitle);
		return "invCommodityHistoricalPrices";
	}
	
	@RequestMapping("/recentViewCompanies")
	public String recentViewCompanies(@SessionAttribute("Investor") LoginDto investorLoginDto, Model model) {
		
		List<CompanyDto> companyDto = this.investorService.getAllRecentViewCompanies(investorLoginDto.getLoginKey());
		
		model.addAttribute("recentViewCompanies", companyDto);
		
		return "invRecentViewCompanies";
	}

	@RequestMapping("/walletTransactionSummary")
	public String transactionSummary(@SessionAttribute("Investor") LoginDto investorLoginDto, Model model) {
		
		List<WalletTransactionsDto> walletTransactionsDto = this.investorService.fetchAllWalletTransactions(investorLoginDto.getLoginKey());
		
		model.addAttribute("walletTransactionsDto", walletTransactionsDto);
		return "invWalletTransactions";
	}
	
	@RequestMapping("/buyCommodity/{stockName}")
	public String returnBuyPageCommodity(@PathVariable("stockName") String commodityName, Model model,
			@ModelAttribute("shareCount") ShareCountInputDto shareCount) {
		model.addAttribute("stockName", commodityName);
		model.addAttribute("commodtiyCompany", "Commodity");
		model.addAttribute("transactionType", "Buy");
		CommodityDto stockDto = this.commodityService.fetchSingleCommodityByName(commodityName);
		model.addAttribute("stockDto", stockDto);
		return "invBuySellPage";
	}
	
	@RequestMapping("/BuyCommodityShares/{stockName}")
	public String buySharesCommodity(@PathVariable("stockName") String commodityName, Model model,
			@Valid @ModelAttribute("shareCount") ShareCountInputDto shareCount, BindingResult result,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		model.addAttribute("commodtiyCompany", "Commodity");
		model.addAttribute("transactionType", "Buy");
		CommodityDto stockDto = this.commodityService.fetchSingleCommodityByName(commodityName);
		model.addAttribute("stockDto", stockDto);
		
		if (result.hasErrors()) {
			return "invBuySellPage";
		}
		
		String message= this.investorService.buySellShares(commodityName, investorLoginDto.getLoginKey(), "Buy", 
				"Commodity", shareCount.getShareCount());
		model.addAttribute("message", message);
		
		return "invBuySellPage";
	}
	
	@RequestMapping("/sellCommodity/{stockName}")
	public String returnSellPageCommodity(@PathVariable("stockName") String commodityName, Model model,
			@ModelAttribute("shareCount") ShareCountInputDto shareCount) {
		model.addAttribute("commodityTitle", commodityName);
		model.addAttribute("commodtiyCompany", "Commodity");
		model.addAttribute("transactionType", "Sell");
		CommodityDto stockDto = this.commodityService.fetchSingleCommodityByName(commodityName);
		model.addAttribute("stockDto", stockDto);
		
		return "invBuySellPage";
	}
	
	@RequestMapping("/SellCommodityShares/{stockName}")
	public String sellSharesCommodity(@PathVariable("stockName") String commodityName, Model model,
			@Valid @ModelAttribute("shareCount") ShareCountInputDto shareCount, BindingResult result,
			@SessionAttribute("Investor") LoginDto investorLoginDto) {
		model.addAttribute("commodtiyCompany", "Commodity");
		model.addAttribute("transactionType", "Sell");
		CommodityDto stockDto = this.commodityService.fetchSingleCommodityByName(commodityName);
		model.addAttribute("stockDto", stockDto);
		if (result.hasErrors()) {
			return "invBuySellPage";
		}
		
		String message= this.investorService.buySellShares(commodityName, investorLoginDto.getLoginKey(), "Sell", 
				"Commodity", shareCount.getShareCount());
		
		model.addAttribute("message", message);
		
		return "invBuySellPage";
	}
	
	//************************************************************
	//Share Transactions Table
	//************************************************************
	@RequestMapping("/shareTransaction/{shareTransId}")
	public String shareTransactionTable(@PathVariable("shareTransId") Long shareTransactionId, Model model) {
		
		ShareTransactionDto shareTransactionDto = this.investorService.findShareTransactionsById(shareTransactionId);
		
		if (shareTransactionDto==null) {
			return "invWalletTransactions";
		}
		
		model.addAttribute("shareTransactionDto", shareTransactionDto);
		
		return "invShareTransactionTable";
	}
	
	//************************************************************
	//Earning trend chart
	//************************************************************
	
	@RequestMapping("/earningTrend")
	public String earningTrend(@SessionAttribute("Investor") LoginDto investorLoginDto,Model model) {
		
		List<Double> earnings = this.investorService.getEarningFor10Weeks(investorLoginDto.getLoginKey());
		
		model.addAttribute("earnings", earnings);
		
		return "invEarningTrend";
	}
	
	//************************************************************
	//Portfolio Data
	//************************************************************
	
	@RequestMapping("/portfolioReport")
	public String portfolio(@SessionAttribute("Investor") LoginDto investorLoginDto,Model model, 
			@ModelAttribute ReportTypeInputDto reportTypeInputDto) {
		List<PortfolioDto> portfolioDto = this.investorService.getPortfolio(investorLoginDto.getLoginKey());
		
		model.addAttribute("portfolioDto", portfolioDto);
		
		return "invPortfolio";
	}
	
	//************************************************************
	//Portfolio Report Data
	//************************************************************
	
	@RequestMapping("/portfolioReportData")
	public String portfolioReportData(@SessionAttribute("Investor") LoginDto investorLoginDto,Model model, 
			@Valid @ModelAttribute ReportTypeInputDto reportTypeInputDto, BindingResult result) {
		
		if (result.hasErrors()) {
			return "invPortfolio";
		}
		
		if(reportTypeInputDto.getReportType().equalsIgnoreCase("Periodic")) {
			if ((reportTypeInputDto.getStartDate().equalsIgnoreCase(""))||(reportTypeInputDto.getEndDate().equalsIgnoreCase(""))){
				List<PortfolioDto> portfolioDto = this.investorService.getPortfolio(investorLoginDto.getLoginKey());
				model.addAttribute("portfolioDto", portfolioDto);
				model.addAttribute("message", "Input Dates Error!!!");
				
				return "invPortfolio";
			}
		}
		
		List<PortfolioReportDto> portfolioReportDto = this.investorService.getPortfolioReport(investorLoginDto.getLoginKey(), 
				reportTypeInputDto);
		
		if (portfolioReportDto==null) {
			List<PortfolioDto> portfolioDto = this.investorService.getPortfolio(investorLoginDto.getLoginKey());
			model.addAttribute("portfolioDto", portfolioDto);
			model.addAttribute("message", "No Data Found!!!");
			
			return "invPortfolio";
			
		}
		
		model.addAttribute("portfolioReportDto", portfolioReportDto);
		
		return "invPortfolioReport";
	}
	
	//************************************************************
	//Search Sector Page
	//************************************************************
	
	@RequestMapping("/searchSector")
	public String returnSearchSector(@ModelAttribute SearchSectorInputDto searchSectorInputDto) {
		
		return "invSearchSector";
	}
	
	//************************************************************
	//Search Companies By Sector
	//************************************************************
	
	@RequestMapping("/searchCompaniesSector")
	public String searchCompaniesSector(@Valid @ModelAttribute SearchSectorInputDto searchSectorInputDto, BindingResult result,
			Model model) {
		
		if(result.hasErrors()) {
			return "invSearchSector";
		}
		
		List<CompanyDto> listCompanyDto = this.companyService.fetchAllCompanyBySector(searchSectorInputDto.getSector());
		
		if(listCompanyDto==null) {
			String message = "Sector not found";
			model.addAttribute("message", message);
			return "invSearchSector";
		}
		
		model.addAttribute("listCompanyDto", listCompanyDto);
		
		return "invCompaniesBySector";
	}
	
}
