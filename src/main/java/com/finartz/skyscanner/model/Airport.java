package com.finartz.skyscanner.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Airport")
public class Airport extends NamedEntity {
    public Airport() {
    }

    @Override
    public String toString() {
        return "Airport [id=" + super.getId() + ", airportName=" + super.getName() + "]";
    }
}
