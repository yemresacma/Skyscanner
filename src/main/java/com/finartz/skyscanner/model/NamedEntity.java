package com.finartz.skyscanner.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 *  * Simple JavaBean domain object with an id property. Used as a base class for objects
 *  * needing this property.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity implements Serializable  {
    @Column(unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}