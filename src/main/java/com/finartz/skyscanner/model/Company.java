package com.finartz.skyscanner.model;

import javax.persistence.*;

@Entity
@Table(name = "Company")
public class Company extends NamedEntity {
    public Company() {
    }

    public Company(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Company [id=" + super.getId() + ", companyName=" + super.getName() + "]";
    }
}
