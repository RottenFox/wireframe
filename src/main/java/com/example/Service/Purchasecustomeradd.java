/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Service;

import com.example.Entity.AddCustomer;
import com.example.Repository.Cusrep;
import com.example.Repository.purchaserep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP
 */
@Service
public class Purchasecustomeradd {
    
    @Autowired
    private Cusrep cus;

    
    
    
     @Transactional
    public void purchasebalance(String customername, Float quantity) {
        AddCustomer items = cus.findByCustomerNameWithLock(customername)
                .orElseThrow(() -> new RuntimeException("Customer  not found"));

        // Perform stock decrement logic
        items.setPurchaseBalance(items.getPurchaseBalance() + quantity);
        cus.save(items);
    }
}
