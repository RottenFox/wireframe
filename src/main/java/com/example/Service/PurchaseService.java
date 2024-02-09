/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Service;

import com.example.Entity.AddCustomer;
import com.example.Entity.AddItem;
import com.example.Repository.Cusrep;
import com.example.Repository.Itemrep;
import com.example.Repository.purchaserep;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    private Itemrep itemRepository;
    
    
    
    
////     @Transactional
//    public void purchasebalance(String customername, Float quantity) {
//        AddCustomer items = cus.findByCustomerNameWithLock(customername)
//                .orElseThrow(() -> new RuntimeException("Customer  not found"));
//
//        // Perform stock decrement logic
//        items.setPurchaseBalance(items.getPurchaseBalance() + quantity);
//        cus.save(items);
//    }
    @Transactional
    public void incrementStock(String itemname, Float quantity , Float Price , String check) {
        AddItem items = itemRepository.findByProductnameWithLock(itemname)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Perform stock decrement logic
        float Stock =  Optional.ofNullable(items.getPurchaseqty() + items.getItem_openstock() - (items.getSaleqty())).orElse(0.0f);
        float totalStock = Stock + quantity;
        float averagecost = Stock * items.getAverageCost();
        float newprice = quantity * Price;
            items.setPurchaseqty(items.getPurchaseqty() + quantity);
        
            items.setAverageCost((averagecost + newprice) / totalStock );
            
            
            System.out.println(averagecost);
            System.out.println(newprice);
            System.out.println(totalStock);
            
//            if (check.equals("CHANAGE")) {
//            items.setAverageCost(((items.getAverageCost() * Stock) - (quantity * Price)  + (quantity * Price)) /2 );
//        }
        
        itemRepository.save(items);
    }
}
