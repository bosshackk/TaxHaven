package com.quad.TaxHaven.repositories;

import com.quad.TaxHaven.domain.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
