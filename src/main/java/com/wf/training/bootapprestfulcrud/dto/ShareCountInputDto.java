package com.wf.training.bootapprestfulcrud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ShareCountInputDto {
	@NotNull
	@Min(value = 1)
	private int shareCount;

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

}
