package com.finartz.skyscanner.controller;

import com.finartz.skyscanner.model.Company;
import com.finartz.skyscanner.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * This method is used to retrive all companies
     * @return returns all companies in the database
     */
    @GetMapping("/company")
    private List<Company> getAllCompany()
    {
        return companyService.getAllCompanies();
    }

    /**
     * This method is used to retrive company object
     * that matches with the id in the url
     * @param id company id
     * @return company object
     */
    @GetMapping("/company/id={id}")
    private Company getCompany(@PathVariable("id") Long id)
    {
        return companyService.getCompanyById(id);
    }

    /**
     * This method is used to retrive company object
     * that matches with the name in the url
     * @param name company name
     * @return company object
     */
    @GetMapping("/company/name={companyName}")
    private Company getCompany(@PathVariable("companyName") String name)
    {
        return companyService.getCompanyByName(name);
    }

    /**
     * This method is used to delete company object
     * that matches with the id in the url
     * @param id company id
     */
    @DeleteMapping("/company/id={id}")
    private void deleteCompany(@PathVariable("id") Long id)
    {
        companyService.deleteById(id);
    }

    /**
     * This method is used to save company object to
     * the database
     * @param company company object
     * @return company id
     */
    @PostMapping("/company")
    private Long saveCompany(@RequestBody Company company)
    {
        companyService.saveOrUpdate(company);
        return company.getId();
    }
}
