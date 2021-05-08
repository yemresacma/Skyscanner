package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Company;
import com.finartz.skyscanner.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<Company>();
        companyRepository.findAll().forEach(company -> companies.add(company));
        return companies;
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name).get(0);
    }

    public void saveOrUpdate(Company company) {
        companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
