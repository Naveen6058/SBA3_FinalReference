package com.wf.training.bootapprestfulcrud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MoneyInputDto {
	@NotNull
	@Min(value = 1)
	private Long amount;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
