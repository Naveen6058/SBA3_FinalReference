package com.wf.training.bootapprestfulcrud.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wf.training.bootapprestfulcrud.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	Optional<Company> findBycompanyTitle(String companyTitle);
	Optional<List<Company>> findAllBySector(String sector);
	
	@Transactional
	@Modifying
    @Query("UPDATE Company c SET c.sharePrice = :sharePrice WHERE c.companyTitle = :companyTitle")
    void updateSharePrice(@Param("companyTitle") String companytitle, @Param("sharePrice") Float shareprice);
}


