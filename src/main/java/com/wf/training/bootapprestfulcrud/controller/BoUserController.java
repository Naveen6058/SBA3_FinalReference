package com.wf.training.bootapprestfulcrud.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wf.training.bootapprestfulcrud.dto.AddCommodityPriceDto;
import com.wf.training.bootapprestfulcrud.dto.AddStockPriceDto;
import com.wf.training.bootapprestfulcrud.dto.BackOfficeLoginDto;
import com.wf.training.bootapprestfulcrud.dto.CommodityDto;
import com.wf.training.bootapprestfulcrud.dto.CompanyDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCommodityDto;
import com.wf.training.bootapprestfulcrud.dto.SearchCompanyDto;
import com.wf.training.bootapprestfulcrud.dto.SelectMonthDto;
import com.wf.training.bootapprestfulcrud.dto.SelectPeriodDto;
import com.wf.training.bootapprestfulcrud.dto.SelectYearDto;
import com.wf.training.bootapprestfulcrud.dto.SuperUserLoginDto;
import com.wf.training.bootapprestfulcrud.service.CommodityService;
import com.wf.training.bootapprestfulcrud.service.CompanyService;
import com.wf.training.bootapprestfulcrud.service.InvestorService;

@Controller
@RequestMapping("/bouser")
public class BoUserController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private InvestorService investorService;
	
	@RequestMapping("/home")
	public String returnHome() {
		
		// respond back with a view page name
		return "BackOfficeUserHomePage";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		BackOfficeLoginDto backofficeuser=new BackOfficeLoginDto();
		model.addAttribute("backofficeuser", backofficeuser);
		
		model.addAttribute("Message", "Logged out successfully");
		return "BackOfficeUserLogin";
	}

	@RequestMapping("/validate")
	public String loginValidate() {
		
		// respond back with a view page name
		return "index";
	}
	
	@RequestMapping("/returnAddCompany")
	public String returnAddCompany(@ModelAttribute("createCompany") CompanyDto createCompany) {
		
		// respond back with a view page name
		return "CreateCompany";
	}
	
	@PostMapping("/createCompany")
	public String addCompany(@Valid @ModelAttribute("createCompany") CompanyDto createCompany,BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "CreateCompany";
		}
		
		CompanyDto addCompanyOutputDto = this.companyService.addCompany(createCompany);
		
		model.addAttribute("CompanyOutput", addCompanyOutputDto);
		return "SavedCompany";
	}
	
	@RequestMapping("/selectModifyCompany")
	public String selectModifyCompany(@ModelAttribute("selectCompany") SearchCompanyDto searchCompanyDto) {
		return "SelectModifyCompany";
	}
	
	@RequestMapping("/returnModifyCompany")
	public String returnModifyCompany(@Valid @ModelAttribute("selectCompany") SearchCompanyDto searchCompanyDto, BindingResult result, @ModelAttribute("companyNewOutputDto") CompanyDto companyNewOutputDto, Model model) {
		if (result.hasErrors()) {
			return "SelectModifyCompany";
		}
		CompanyDto companyOutputDto = new CompanyDto();
		
		companyOutputDto = this.companyService.fetchSingleCompanyByName(searchCompanyDto);
		model.addAttribute("searchCompanyDto", companyOutputDto);
		// respond back with a view page name
		return "ModifyCompany";
	}
	
	@RequestMapping("/modifyCompany")
	public String modifyCompany(@Valid @ModelAttribute("companyNewOutputDto") CompanyDto companyNewOutputDto, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "ModifyCompany";
		}
		
		CompanyDto companyOutputDto =this.companyService.modifyCompany(companyNewOutputDto);
		model .addAttribute("CompanyOutput", companyOutputDto);
		
		// respond back with a view page name
		return "SavedCompany";
	}
	
	
	@RequestMapping("/returnCreateCommodity")
	public String returnAddCommodity(Model model ) {
		CommodityDto commodityDto=new CommodityDto();
		commodityDto.setDateTime(LocalDateTime.now().toString());
		model.addAttribute("commodity", commodityDto);
		
		return "CreateCommodity";
	}
	
	@PostMapping("/createCommodity")
	public String addCommodity(@Valid @ModelAttribute("commodity") CommodityDto dto,BindingResult result,Model model) {
		if (result.hasErrors()) {
			return "CreateCommodity";
		}
		CommodityDto output=this.commodityService.addCommodity(dto);
		model.addAttribute("CommodityOutput", output);
		return "SavedCommodity";
	}
	
	@RequestMapping("/selectModifyCommodity")
	public String selectModifyCommodity(@ModelAttribute("selectCommodity") SearchCommodityDto searchCommodityDto) {
		return "SelectModifyCommodity";
	}
	
	@RequestMapping("/returnModifyCommodity")
	public String returnModifyCommodity(@Valid @ModelAttribute("selectCommodity") SearchCommodityDto searchCommodityDto, BindingResult result, @ModelAttribute("comDto") CommodityDto comDto, Model model) {
		if (result.hasErrors()) {
			return "SelectModifyCommodity";
		}
		CommodityDto output = new CommodityDto();
		
		output = this.commodityService.fetchSingleCommodityByName(searchCommodityDto);
		comDto.setDateTime(LocalDateTime.now().toString());
		model.addAttribute("searchCommodityDto", output);
		// respond back with a view page name
		return "ModifyCommodity";
	}
	
	@RequestMapping("/modifyCommodity")
	public String modifyCommodity(@Valid @ModelAttribute("commodityNewOutputDto") CommodityDto commodityNewOutputDto, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "ModifyCommodity";
		}
		
		CommodityDto commodityOutputDto =this.commodityService.modifyCommodity(commodityNewOutputDto);
		model .addAttribute("CommodityOutput", commodityOutputDto);
		
		return "SavedCommodity";
	}
	
	@RequestMapping("/addCompanyStockPrice")
	public String addCompanyStockPrice(@ModelAttribute("addstockprice") AddStockPriceDto addStockDto,Model model) {
		List<String> companyNames=this.companyService.fetchAllCompanyNames();
		model.addAttribute("companyNames",companyNames);
		return "BoAddCompanyStockPrice";
	}
	
	@PostMapping("/newStockPrice")
	public String newStockPrice(@Valid @ModelAttribute("addstockprice") AddStockPriceDto addStockDto,BindingResult result,Model model) {
		List<String> companyNames=this.companyService.fetchAllCompanyNames();
		model.addAttribute("companyNames",companyNames);
		if (result.hasErrors()) {
			
			return "BoAddCompanyStockPrice";
		}
		if(this.companyService.addStockPrice(addStockDto))
		{
			model.addAttribute("Message", "Stock added successfully");
			return "BoAddCompanyStockPrice";
		}
		return "BoAddCompanyStockPrice";
	}
	
	@RequestMapping("/addCommodityPrice")
	public String addCommodityPrice(@ModelAttribute("addcommodityprice") AddCommodityPriceDto addCommodityDto,Model model) {
		List<String> commodityNames=this.commodityService.fetchAllCommodityNames();
		model.addAttribute("commodityNames",commodityNames);
		return "BoAddCommodityPrice";
	}
	
	@PostMapping("/newCommodityPrice")
	public String newCommodityPrice(@Valid @ModelAttribute("addcommodityprice") AddCommodityPriceDto addCommodityDto,BindingResult result,Model model) {
		List<String> commodityNames=this.commodityService.fetchAllCommodityNames();
		model.addAttribute("commodityNames",commodityNames);
		if (result.hasErrors()) {
			
			return "BoAddCommodityPrice";
		}
		if(this.commodityService.addCommodityPrice(addCommodityDto))
		{
			model.addAttribute("Message", "Commodity added successfully");
			return "BoAddCommodityPrice";
		}
		return "BoAddCommodityPrice";
	}
	
	@RequestMapping("/generateAnnualReport")
	public String annualReport(@ModelAttribute("annualreport") SelectYearDto year,Model model) {
		model.addAttribute("year", year);
		return "BoGenerateAnnualReport";
	}
	
	@RequestMapping("/generateMonthlyReport")
	public String monthlyReport(@ModelAttribute("monthlyreport") SelectMonthDto month,Model model) {
		//String[] months= {"January","February","March","April","May","June","July","August","September","October","November","December"};
		String[] months= {"-01-","-02-","-03-","-04-","-05-","-06-","-07-","-08-","-09-","-10-","-11-","-12-"};
		model.addAttribute("months", months);
		return "BoGenerateMonthlyReport";
	}
	
	@RequestMapping("/generatePeriodicReport")
	public String periodicReport(@ModelAttribute("periodicreport") SelectPeriodDto period,Model model) {
		return "BoGeneratePeriodicReport";
	}
	
	@PostMapping("/returnAnnualReport")
	public String returnAnnualReport(@ModelAttribute("annualreport") SelectYearDto year,Model model) {
			model.addAttribute("transactions", this.investorService.findAllShareTransaction());
		return "BoViewAnnualReport";
	}
	
	@PostMapping("/returnMonthlyReport")
	public String returnMonthlyReport(@ModelAttribute("monthlyreport") SelectMonthDto month,Model model) {
			model.addAttribute("transactions", this.investorService.findAllShareTransaction());
		return "BoViewMonthlyReport";
	}
	
	@PostMapping("/returnPeriodicReport")
	public String returnPeriodicReport(@ModelAttribute("periodicreport") SelectPeriodDto period,Model model) {
			model.addAttribute("transactions", this.investorService.findAllShareTransactionBetweenDates(period.getStartDate(),period.getEndDate()));
		return "BoViewPeriodicReport";
	}
	
}
