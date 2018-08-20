package com.exxeta.maau.demoliquibase.repository;

import com.exxeta.maau.demoliquibase.model.DailyProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyProductionRepository extends JpaRepository<DailyProduction, Long> {
    List<DailyProduction> findByFactoryId (Long factoryId);
}
