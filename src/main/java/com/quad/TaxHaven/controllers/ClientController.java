package com.quad.TaxHaven.controllers;

import com.quad.TaxHaven.domain.user.Client;
import com.quad.TaxHaven.services.ClientService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("client")
public class ClientController {

    Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Client client){
        client = clientService.save(client);
        return new ResponseEntity<>(clientService.findById(client.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> fetch(@PathVariable Integer id){
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@RequestBody Client client){
//        Client client1 = clientService.findById(client.getId()).ifPresentOrElse(a-> new Exception("clinet not found", Throwable));
        Client client1 = clientService.findById(client.getId()).get();
        Class cls;
        try {
            cls = Class.forName ("com.quad.TaxHaven.domain.user.Client");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Field> fields = new ArrayList<>(Arrays.asList(cls.getDeclaredFields()));
        fields.addAll(Arrays.asList(cls.getSuperclass().getDeclaredFields()));
        Client finalClient = client1;
        fields.forEach(attr-> {
            attr.setAccessible(true);
            try {
                if (!Objects.isNull(attr.get(client))) {
                    LOGGER.info("attr get(client) {}", attr.get(client));
                    BeanUtils.setProperty(finalClient, attr.getName(), attr.get(client));
                    LOGGER.info("attr getName {}", attr.getName());
                    LOGGER.info("finalClient attr value {}", attr.get(finalClient));
                }
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("illegal access {}", e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("invocation target exception {}", e);
            }
        });
//        client1 = finalClient; not neccessary how, and if we remove it, finalClient is showing redundant
        return new ResponseEntity<>(clientService.save(client1), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        clientService.deleteById(id);
        return new ResponseEntity<>("client deleted successfully with id-"+id, HttpStatus.OK);
    }

}
