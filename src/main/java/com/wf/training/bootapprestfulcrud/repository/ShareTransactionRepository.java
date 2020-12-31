package com.wf.training.bootapprestfulcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wf.training.bootapprestfulcrud.entity.ShareTransaction;

public interface ShareTransactionRepository extends JpaRepository<ShareTransaction, Long>{

	List<ShareTransaction> findAllByShareTransactionId(Long shareTransactionId);
	
	ShareTransaction findByShareTransactionId(Long shareTransactionId);
	
	List<ShareTransaction> findAllByWalletId(Long walletId);
	
	List<ShareTransaction> findAllByWalletIdAndTransactionType(Long walletId, String transactionType);
	
	@Query(value = "from ShareTransaction t where dateTime BETWEEN :startDate AND :endDate")
	List<ShareTransaction> findAllBetweenStartDateAndEndDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
