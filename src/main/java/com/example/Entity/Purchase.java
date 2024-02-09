/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Entity;

/**
 *
 * @author HP
 */
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AddCustomer customer;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private AddItem item;

    @ManyToOne
    @JoinColumn(name = "purchase_summary_id")
    private PurchaseSummary purchaseSummary;

    
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "bill_no", nullable = false)
    private String billNo;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    // Additional fields as needed

    // Constructors, getters, and setters

    public void setPurchaseSummary(PurchaseSummary purchaseSummary) {
        this.purchaseSummary = purchaseSummary;
    }

    public PurchaseSummary getPurchaseSummary() {
        return purchaseSummary;
    }
    
    
    public Long getId() {
        return id;
    }

    public AddCustomer getCustomer() {
        return customer;
    }

    public AddItem getItem() {
        return item;
    }

    public Float getQuantity() {
        return quantity;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getPrice() {
        return price;
    }

    public String getBillNo() {
        return billNo;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    

    // Getters and setters...

    // Additional methods if needed...

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(AddCustomer customer) {
        this.customer = customer;
    }

    public void setItem(AddItem item) {
        this.item = item;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

}

