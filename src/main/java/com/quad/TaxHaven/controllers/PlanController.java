package com.quad.TaxHaven.controllers;

import com.quad.TaxHaven.domain.plan.Plan;
import com.quad.TaxHaven.services.PlanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    PlanService planService;
    @PersistenceContext
    EntityManager em;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Plan plan){
        plan = planService.save(plan);
        return new ResponseEntity<>("plan created successfully with id-"+plan.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> fetch(@PathVariable Integer id){
        return new ResponseEntity<>(planService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Plan plan){
        return new ResponseEntity<>(planService.save(plan), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        planService.deleteById(id);
        return new ResponseEntity<>("plan deleted successfully with id-"+id, HttpStatus.OK);
    }
}