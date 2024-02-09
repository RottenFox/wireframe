/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Service;

import com.example.Entity.InvoiceSequence;
import com.example.Repository.Invoicerep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP
 */
@Service
public class InvoiceSequenceService {

    @Autowired
    private Invoicerep sequenceRepository;

    @Transactional
    public Long getNextInvoiceNumber() {
        InvoiceSequence sequence = sequenceRepository.findById(1L).orElseGet(() -> {
            InvoiceSequence newSequence = new InvoiceSequence();
            newSequence.setNextValue(1L);
            return sequenceRepository.save(newSequence);
        });

        Long nextValue = sequence.getNextValue();
        sequence.setNextValue(nextValue + 1);

        return sequenceRepository.save(sequence).getNextValue();
    }
}

