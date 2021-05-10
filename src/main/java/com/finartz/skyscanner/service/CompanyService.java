package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Company;
import com.finartz.skyscanner.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No company found with the given id"));
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("No company found with the given name"));
    }

    public void saveOrUpdate(Company company) {
        try {
            companyRepository.save(company);
        } catch (DataIntegrityViolationException e) {
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
