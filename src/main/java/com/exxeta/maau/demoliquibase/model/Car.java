package com.exxeta.maau.demoliquibase.model;

import com.exxeta.maau.demoliquibase.model.enumeration.CarTypes;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private CarTypes type;

    @NotNull
    private String vehicleClass;

    @NotNull
    private String vehicleModel;

    @ManyToOne
    @JoinColumn(name = "dailyProd_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DailyProduction dailyProduction;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dealer dealer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarTypes getType() {
        return type;
    }

    public void setType(CarTypes type) {
        this.type = type;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public DailyProduction getDailyProduction() {
        return dailyProduction;
    }

    public void setDailyProduction(DailyProduction dailyProduction) {
        this.dailyProduction = dailyProduction;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
