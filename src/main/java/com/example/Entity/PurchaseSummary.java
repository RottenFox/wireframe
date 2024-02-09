/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase_summary")
public class PurchaseSummary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;

    @Column(name = "total_amount", nullable = false)
    private Float totalAmount;

    @Column(name = "net_amount", nullable = false)
    private Float netAmount;
    
    @Column(name = "discount_Per", nullable = false)
    private Float discountPer;
    
    @Column(name = "discount_Rup", nullable = false)
    private Float discountRup;

    @Column(name = "received_amount", nullable = false)
    private Float receivedAmount;

    @Column(name = "previous_balance", nullable = false)
    private Float previousBalance;
    
    @Column(name = "total_balance", nullable = false)
    private Float totalBalance;
    
    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

   @OneToMany(mappedBy = "purchaseSummary")
    private List<Purchase> purchases;

    
    @ManyToOne
    @JoinColumn(name = "cutomer_id", nullable = false)
    private AddCustomer customer;

    public AddCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(AddCustomer customer) {
        this.customer = customer;
    }

    public void setDiscountPer(Float discountPer) {
        this.discountPer = discountPer;
    }

    // Getters and setters...
    public void setDiscountRup(Float discountRup) {
        this.discountRup = discountRup;
    }

    public Float getDiscountPer() {
        return discountPer;
    }

    public Float getDiscountRup() {
        return discountRup;
    }

    public Float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Float totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }

    public Float getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Float receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Float getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(Float previousBalance) {
        this.previousBalance = previousBalance;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}

