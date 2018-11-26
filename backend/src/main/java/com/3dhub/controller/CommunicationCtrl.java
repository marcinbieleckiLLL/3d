package com.greencrane.controller;

import java.util.Map;

import com.greencrane.consts.LogType;
import com.greencrane.entity.*;
import com.greencrane.repository.CommunicationRepository;
import com.greencrane.repository.FileRepository;
import com.greencrane.repository.OfferRepository;
import com.greencrane.service.CommunicationService;
import com.greencrane.service.CustomerService;
import com.greencrane.service.file.FileService;
import com.greencrane.service.file.ZipFileCreator;
import com.greencrane.utils.LoggingUtils;
import com.greencrane.utils.mail.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommunicationCtrl {

    @Autowired
    CustomerService customerService;
    @Autowired @Qualifier("mailCustomerUtils")
    MailUtils mailCustomerUtils;
    @Autowired @Qualifier("mailToCustomerUtils")
    MailUtils mailToCustomerUtils;
    @Autowired
    FileService fileService;
    @Autowired
    ZipFileCreator zipFileCreator;
    @Autowired @Qualifier("communicationService")
    CommunicationService communicationService;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    CommunicationRepository communicationRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    LoggingUtils loggingUtils;

    @PostMapping(path = "/communicate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, String>> communicate(@RequestBody Customer customer, HttpServletRequest request) {
        logEvent(request);

        Offer offer = getOfferById(customer);
        customer = saveCustomer(customer, offer);
        saveOffer(offer, customer);
        File file = createFile(customer);

        saveCommunication(file);
        saveFile(file);
        sendEmails(customer, file);
        updateCommunication(file);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void logEvent(HttpServletRequest request) {
        loggingUtils.logEvent(request, LogType.CONTACT);
    }

    private Offer getOfferById(Customer customer) {
        return offerRepository.findById(customer.getOfferId()).orElse(null);
    }

    private Customer saveCustomer(Customer customer, Offer offer) {
        if(offer != null) customer.addOffer(offer);
        return customerService.save(customer);
    }

    private void saveOffer(Offer offer, Customer customer) {
        if(offer != null) {
            offer.addCustomer(customer);
            offerRepository.save(offer);
        }
    }

    private File createFile(Customer customer) {
        return fileService.createFile(zipFileCreator, customer);
    }

    private void saveCommunication(File file) {
        Communication communication = file.getCommunication();
        communication.setModDate(file.getModDate());
        communication.setFile(file);
        communicationRepository.save(communication);
    }

    private void saveFile(File file) {
        fileRepository.save(file);
    }

    private void sendEmails(Customer customer, File file) {
        mailCustomerUtils.sendCustomerData(customer, file);
        mailToCustomerUtils.sendEmailToCustomer(customer);
    }

    private void updateCommunication(File file) {
        Communication communication = file.getCommunication();
        communication.setMailSended(Boolean.TRUE);
        communicationService.update(communication);
    }
}
