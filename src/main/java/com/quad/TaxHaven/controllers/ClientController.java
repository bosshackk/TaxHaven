package com.quad.TaxHaven.controllers;

import com.quad.TaxHaven.domain.user.Client;
import com.quad.TaxHaven.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Client client){
        client = clientService.save(client);
//        client = clientService.findById(client.getId());
//        List<Plan> planList =
//        client.setPlanList();
        return new ResponseEntity<>(clientService.findById(client.getId()), HttpStatus.OK);
//        return new ResponseEntity<>("redirect:/{id}", HttpStatus.OK);
//        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> fetch(@PathVariable Integer id){
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Client client){
        return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        clientService.deleteById(id);
        return new ResponseEntity<>("client deleted successfully with id-"+id, HttpStatus.OK);
    }

}
