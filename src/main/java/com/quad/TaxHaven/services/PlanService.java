package com.quad.TaxHaven.services;

import com.quad.TaxHaven.domain.plan.Plan;
import com.quad.TaxHaven.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    PlanRepository planRepository;

    public Plan save(Plan plan){
        return planRepository.save(plan);
    }
    public Optional<Plan> findById(Integer id){
        return planRepository.findById(id);
    }
    public void deleteById(Integer id){
        planRepository.deleteById(id);
    }

}
