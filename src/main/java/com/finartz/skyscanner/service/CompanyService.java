package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Company;
import com.finartz.skyscanner.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
        try {
            return companyRepository.findById(id).get();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    public Company getCompanyByName(String name) {
        try {
            return companyRepository.findByCompanyName(name).get(0);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    public void saveOrUpdate(Company company) {
        try {
            companyRepository.save(company);
        } catch (EntityExistsException e) {
            throw e;
        }
    }

    public void deleteById(Long id) {
        try {
            companyRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
