/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Repository;

import com.example.Entity.AddCustomer;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface Cusrep extends JpaRepository<AddCustomer, Integer> {
    
    Optional<AddCustomer> findByCustomerName(String customerName);
    Optional<AddCustomer> findByCustomercode(String customercode);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM AddCustomer i WHERE i.customerName = :customerName")
    Optional<AddCustomer> findByCustomerNameWithLock(String customerName);
}
