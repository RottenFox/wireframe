/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.otherclasses;

import com.example.Entity.AddItem;
import javafx.util.StringConverter;

/**
 *
 * @author HP
 */
public class AddItemConverter extends StringConverter<AddItem> {

    @Override
    public String toString(AddItem additem) {
        if (additem == null) {
            return null;
        } else {
            // Assuming getProductname(), getStock(), and getSize() are methods in AddItem class
            return String.format("%s - Stock: %f, Size: %s", additem.getProductname(), additem.getItem_price(), additem.getItem_size());
        }
       
    }

    @Override
    public AddItem fromString(String string) {
        return null; 
    }
    
    
}
