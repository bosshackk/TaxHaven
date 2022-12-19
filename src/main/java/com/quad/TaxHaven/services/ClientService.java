package com.quad.TaxHaven.services;

import com.quad.TaxHaven.domain.user.Client;
import com.quad.TaxHaven.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SendEmailService sendEmailService;

    public Client save(Client client){
        Client savedClient = clientRepository.save(client);
        if(client.getId()==(null)){
            sendOtpOnMail(client.getEmail(), client.getFirstName());
        }
        return savedClient;
    }
    public Optional<Client> findById(Integer id){
        return clientRepository.findById(id);
    }
    public void deleteById(Integer id){
        clientRepository.deleteById(id);
    }

    public void sendOtpOnMail(String clientMailId, String clientName){
        sendEmailService.sendOTPOnMail(clientMailId,
                clientName, "for verfication via otp");
    }

}
