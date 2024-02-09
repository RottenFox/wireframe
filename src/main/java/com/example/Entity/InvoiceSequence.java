/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "invoice_sequence")
public class InvoiceSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "next_value")
    private Long nextValue;

    public Long getId() {
        return id;
    }

    public Long getNextValue() {
        return nextValue;
    }
    
    
    // Constructors, getters, setters...

    public void setId(Long id) {
        this.id = id;
    }

    public void setNextValue(Long nextValue) {
        this.nextValue = nextValue;
    }
}

