package com.finartz.skyscanner.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Company")
public class Company extends NamedEntity {
    public Company() {
    }

    @Override
    public String toString() {
        return "Company [id=" + super.getId() + ", companyName=" + super.getName() + "]";
    }
}
