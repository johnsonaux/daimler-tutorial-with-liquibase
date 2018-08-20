package com.exxeta.maau.demoliquibase.model;

import com.exxeta.maau.demoliquibase.model.enumeration.CarTypes;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Factory {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private CarTypes acceptedType;

    @NotNull
    private String location;


    //[OneToMany] additional "join" table (id/id) will be created when Car class does not know Factory Class
    @OneToMany (cascade = CascadeType.ALL)
    private List<Car> carsInFactory;

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

    public List<Car> getCarsInFactory() {
        return carsInFactory;
    }

    public void setCarsInFactory(List<Car> carsInFactory) {
        this.carsInFactory = carsInFactory;
    }
}
