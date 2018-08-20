package com.exxeta.maau.demoliquibase.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class DailyProduction {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Date localDate;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Factory factory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
