package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByCompanyName(String companyName);
}
