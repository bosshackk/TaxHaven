package com.quad.TaxHaven.controllers;

import com.quad.TaxHaven.domain.plan.Plan;
import com.quad.TaxHaven.domain.user.Client;
import com.quad.TaxHaven.repositories.ClientRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/saveClient")
    public ResponseEntity<Object> saveClient(@RequestBody Client client){
        client = clientRepository.save(client);
//        client = clientRepository.findById(client.getId());
//        List<Plan> planList =
//        client.setPlanList();
        return new ResponseEntity<>(clientRepository.findById(client.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> fetchClient(@PathVariable Integer id){
        return new ResponseEntity<>(clientRepository.findById(id), HttpStatus.OK);
    }


}
