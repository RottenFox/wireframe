/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Sharedclasses;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import org.springframework.stereotype.Component;

@Component
public class SharedDataModel {
    private final FloatProperty sharedQtyProperty = new SimpleFloatProperty();
    private final FloatProperty sharedAmountProperty = new SimpleFloatProperty();
    public FloatProperty sharedQtyProperty() {
        return sharedQtyProperty;
    }
    public FloatProperty sharedAmountProperty() {
        return sharedAmountProperty;
    }
    // Other methods...
    
}
