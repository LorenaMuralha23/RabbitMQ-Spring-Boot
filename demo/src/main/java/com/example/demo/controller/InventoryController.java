package com.example.demo.controller;

import com.example.demo.constants.RabbitMQConstants;
import com.example.demo.dtos.InventoryDTO;
import com.example.demo.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "inventory")
public class InventoryController {
    
    @Autowired
    private RabbitMQService service;
    
    @PutMapping
    private ResponseEntity changeInventory(@RequestBody InventoryDTO inventoryDTO){
        System.out.println(inventoryDTO.productCode);
        this.service.sendMessage(RabbitMQConstants.QUEUE_INVENTORY, inventoryDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
