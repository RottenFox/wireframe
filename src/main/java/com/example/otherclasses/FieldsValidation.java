/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.otherclasses;

import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author HP
 */
public class FieldsValidation {
    
    
   public void validatenumber(KeyEvent event){
        
       TextField sourceField = (TextField) event.getSource(); // Get the source TextField
       

        sourceField.getLayoutX();
        sourceField.getLayoutY();
        System.out.println(sourceField.getLayoutY());
        System.out.println(sourceField.getLayoutX());
UnaryOperator<TextFormatter.Change> numericFilter = change -> {
    String newText = change.getControlNewText();
    if (newText.isEmpty() || newText.matches("(((0|[1-9][0-9]*)|0)?(\\.[0-9]*)?)?")) {
        // Regex to allow floating point numbers or an empty string
        return change;
    }
    return null;
};
TextFormatter<String> numericTextFormatter = new TextFormatter<>(numericFilter);
sourceField.setTextFormatter(numericTextFormatter);
}
    
}
