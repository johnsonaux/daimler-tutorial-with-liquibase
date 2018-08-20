package com.exxeta.maau.demoliquibase.model;

import com.exxeta.maau.demoliquibase.model.enumeration.CarTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Factory {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private CarTypes acceptedType;

    @NotNull
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarTypes getAcceptedType() {
        return acceptedType;
    }

    public void setAcceptedType(CarTypes acceptedType) {
        this.acceptedType = acceptedType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
