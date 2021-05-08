package com.finartz.skyscanner.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Company")
public class Company extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String companyName;

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return "Company [id=" + super.getId() + ", companyName=" + companyName + "]";
    }
}
