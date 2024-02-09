/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "customer")
public class AddCustomer {

    
   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
   
private Integer customerId;

   @Version
private Long version;
   
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;
   
@Column(name = "Customer_Name",unique = true, nullable = false)
private String customerName;

@Column(name = "Customer_Code")
private String customercode;
 
 @Column(name = "Phonenumber")
    private String phoneumber;
 
 
 @Column(name = "Email")
    private String email;
 
 @Column(name = "ShippingAddress")
    private String shippingAddress;
 
 @Column(name = "Address")
    private String address;
 
 @Column(name = "Father_name")
    private String fathername;
 
 @Column(name = "CNIC")
    private String cnic;
 
 @Column(name = "Nationality")
    private String nationality;
 
 @Column(name = "Additionafieldname1")
    private String aditionalnamefield1;
 
 @Column(name = "Additionafieldname2")
    private String aditionalnamefield2;
 
 @Column(name = "Additionafieldname3")
    private String aditionalnamefield3;
 

 
 @Column(name = "Additionafieldname4")
    private String aditionalnamefield4;
 
 
 @Column(name = "Aditionalfield1")
    private String aditionalfield1;
 
 @Column(name = "Aditionalfield2")
    private String aditionalfield2;
 
 @Column(name = "Aditionalfield3")
    private String aditionalfield3;
 
 @Column(name = "Aditionalfield4")
    private LocalDate  date;
 
 @Column(name = "Openbalance")
    private Float openbalance;

  @Column(name = "Open_Balancedate")
    private LocalDate  openbalancedate;
  
  @Column(name = "Credit_Limit")
    private Float creditlimit;
    @Column(name = "Customer_Balance")
    private Float customerBalance;
    
    @Column(name = "purchase_Balance")
    private Float purchaseBalance;
    
    @Column(name = "Description")
    private Float description;
    
    
    @Transient
    private final SimpleFloatProperty  Balance = new SimpleFloatProperty();;
   
     {
        Balance.bind(Bindings.createFloatBinding(() ->
                        Optional.ofNullable(getCustomerBalance() - (getPurchaseBalance() + getOpenbalance())).orElse(0.0f)));
    }
    public void setBalance(Float Balance) {
        this.Balance.set(Balance);
        
    }
    

    public Float getBalance() {
         return Optional.ofNullable(customerBalance -(purchaseBalance + openbalance)).orElse(0f);
    }
     public SimpleFloatProperty getBalanceProperty() {
        return Balance;
    }
    public Float getPurchaseBalance() {
       return Optional.ofNullable(purchaseBalance).orElse(0f);
    }

    public void setPurchaseBalance(Float purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setCustomerBalance(Float customerBalance) {
        this.customerBalance = customerBalance;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public Float getCustomerBalance() {
        
        return Optional.ofNullable(customerBalance).orElse(0f);
    }
    
    
    public void setOpenbalance(Float openbalance) {
        this.openbalance = openbalance;
    }

    public void setOpenbalancedate(LocalDate openbalancedate) {
        this.openbalancedate = openbalancedate;
    }

    public void setCreditlimit(Float creditlimit) {
        this.creditlimit = creditlimit;
    }

    public Float getOpenbalance() {
        return Optional.ofNullable(openbalance).orElse(0f);
    }

    public LocalDate getOpenbalancedate() {
        return openbalancedate;
    }

    public Float getCreditlimit() {
        return Optional.ofNullable(creditlimit).orElse(0f);
    }
  
  
    public Integer getCustomerId() {
        return customerId;
    }

    

    public String getCustomerName() {
        return Optional.ofNullable(customerName).orElse("");
    }

    public String getCustomercode() {
        return Optional.ofNullable(customercode).orElse("");
    }

    public String getPhoneumber() {
        return Optional.ofNullable(phoneumber).orElse("");
    }

    public String getEmail() {
        return Optional.ofNullable(email).orElse("");
    }

    public String getShippingAddress() {
        return Optional.ofNullable(shippingAddress).orElse("");
    }

    public String getAddress() {
        return Optional.ofNullable(address).orElse("");
    }

    public String getFathername() {
        return Optional.ofNullable(fathername).orElse("");
    }

    public String getCnic() {
        return Optional.ofNullable(cnic).orElse("");
    }

    public String getNationality() {
        return Optional.ofNullable(nationality).orElse("");
    }

    public String getAditionalnamefield1() {
        return Optional.ofNullable(aditionalnamefield1).orElse("");
    }

    public String getAditionalnamefield2() {
        
        return Optional.ofNullable(aditionalnamefield2).orElse("");
    }

    public String getAditionalnamefield3() {
        
        return Optional.ofNullable(aditionalnamefield3).orElse("");
    }

    public String getAditionalnamefield4() {
        
        return Optional.ofNullable(aditionalnamefield4).orElse("");
    }

    public String getAditionalfield1() {
       
        return Optional.ofNullable(aditionalfield1).orElse("");
    }

    public String getAditionalfield2() {
        
        return Optional.ofNullable(aditionalfield2).orElse("");
    }

    public String getAditionalfield3() {
        
        return Optional.ofNullable(aditionalfield3).orElse("");
    }

    public LocalDate getDate() {
        return date;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customername) {
        this.customerName = customername;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public void setPhoneumber(String phoneumber) {
        this.phoneumber = phoneumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAditionalnamefield1(String aditionalnamefield1) {
        this.aditionalnamefield1 = aditionalnamefield1;
    }

    public void setAditionalnamefield2(String aditionalnamefield2) {
        this.aditionalnamefield2 = aditionalnamefield2;
    }

    public void setAditionalnamefield3(String aditionalnamefield3) {
        this.aditionalnamefield3 = aditionalnamefield3;
    }

    public void setAditionalnamefield4(String aditionalnamefield4) {
        this.aditionalnamefield4 = aditionalnamefield4;
    }

    public void setAditionalfield1(String aditionalfield1) {
        this.aditionalfield1 = aditionalfield1;
    }

    public void setAditionalfield2(String aditionalfield2) {
        this.aditionalfield2 = aditionalfield2;
    }

    public void setAditionalfield3(String aditionalfield3) {
        this.aditionalfield3 = aditionalfield3;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
 
 

}
