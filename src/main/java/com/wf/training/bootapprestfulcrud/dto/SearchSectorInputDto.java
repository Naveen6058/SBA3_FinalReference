package com.wf.training.bootapprestfulcrud.dto;

import javax.validation.constraints.NotBlank;

public class SearchSectorInputDto {
	@NotBlank
	private String sector;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	

}
