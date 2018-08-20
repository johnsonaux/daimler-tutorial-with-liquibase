package com.exxeta.maau.demoliquibase.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Dealer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "dealer")
    @JsonIgnore
    private Set<Car> carSet;

    //[OneToMany] NO additional "join" table (id/id) will be created - table customer gets column dealer_id
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id")
    @JsonIgnore
    private List<Customer> customerList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
