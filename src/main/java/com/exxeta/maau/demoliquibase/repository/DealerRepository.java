package com.exxeta.maau.demoliquibase.repository;

import com.exxeta.maau.demoliquibase.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
