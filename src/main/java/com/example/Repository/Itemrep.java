/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Repository;

import com.example.Entity.AddItem;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface Itemrep extends JpaRepository<AddItem, Integer>{

    Optional<AddItem> findByItemCode(String itemCode);
    
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AddItem> findByProductname(String productname);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM AddItem i WHERE i.productname = :productname")
    Optional<AddItem> findByProductnameWithLock( String productname);
     List<AddItem> findByProductnameContainingIgnoreCaseOrItemCodeContainingIgnoreCase(String productName, String itemCode);


}

