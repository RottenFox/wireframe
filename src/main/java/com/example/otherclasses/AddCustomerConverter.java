/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.otherclasses;

/**
 *
 * @author HP
 */
import com.example.Entity.AddCustomer;
import javafx.util.StringConverter;

public class AddCustomerConverter extends StringConverter<AddCustomer> {

    @Override
    public String toString(AddCustomer addCustomer) {
        // Return the display string for each AddCustomer object.
        return (addCustomer == null) ? "" : addCustomer.getCustomerName();
    }

    @Override
    public AddCustomer fromString(String string) {
        // This method is not necessary for a ComboBox that doesn't accept new input to create objects.
        return null; 
    }
}
