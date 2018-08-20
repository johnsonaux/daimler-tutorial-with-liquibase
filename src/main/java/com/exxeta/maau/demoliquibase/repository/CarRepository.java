package com.exxeta.maau.demoliquibase.repository;

import com.exxeta.maau.demoliquibase.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByDailyProductionId(Long dailyProductionId);
}
