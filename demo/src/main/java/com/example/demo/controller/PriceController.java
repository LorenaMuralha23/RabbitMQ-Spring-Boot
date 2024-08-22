package com.example.demo.controller;

import com.example.demo.constants.RabbitMQConstants;
import com.example.demo.dtos.InventoryDTO;
import com.example.demo.dtos.PriceDTO;
import com.example.demo.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "price")
public class PriceController {
    
    @Autowired
    private RabbitMQService service;
    
    @PutMapping
    private ResponseEntity changePrice(@RequestBody PriceDTO priceDTO){
        System.out.println(priceDTO.productCode);
        this.service.sendMessage(RabbitMQConstants.QUEUE_PRICE, priceDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
