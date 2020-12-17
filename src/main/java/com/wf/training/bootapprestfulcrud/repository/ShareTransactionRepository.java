package com.wf.training.bootapprestfulcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.training.bootapprestfulcrud.entity.ShareTransaction;

public interface ShareTransactionRepository extends JpaRepository<ShareTransaction, Long>{

	List<ShareTransaction> findAllByShareTransactionId(Long shareTransactionId);
	
	ShareTransaction findByShareTransactionId(Long shareTransactionId);

}
