package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findByName(String companyName);
}
