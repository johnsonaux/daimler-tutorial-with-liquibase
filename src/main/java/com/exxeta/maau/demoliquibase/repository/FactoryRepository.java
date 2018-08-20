package com.exxeta.maau.demoliquibase.repository;

import com.exxeta.maau.demoliquibase.model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {

}
