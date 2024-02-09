/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Repository;

import com.example.Entity.Purchase;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface purchaserep extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByBillNo(String billNo);
    Optional<Purchase> findByBillNoAndItem_StoreId(String billNo, Integer storeId);
}

