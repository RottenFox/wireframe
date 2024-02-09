/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Sharedclasses;

import com.example.Entity.AddCustomer;
import com.example.Entity.AddItem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */

@Service
public class SharedItemService {
    
private final ObjectProperty<Object> selectedEntity = new SimpleObjectProperty<>();

public <T> ObjectProperty<T> selectedEntityProperty(Class<T> entityType) {
    // Warning: This cast can cause a ClassCastException if misused
    return (ObjectProperty<T>) selectedEntity;
}

public <T> T getSelectedEntity(Class<T> entityType) {
    Object value = selectedEntity.get();
    if (entityType.isInstance(value)) {
        return entityType.cast(value);
    } else {
        return null; // or throw an exception
    }
}





    public void clearSelectedEntity() {
        this.selectedEntity.set(null);
    }
}
