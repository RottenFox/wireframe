
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.otherclasses;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author wwwsa
 */
public class bindings {
    public void setupPromptLabelBindings(ComboBox<?> comboBox, Label promptLabel, String promptText) {
    promptLabel.textProperty().bind(
            Bindings.when(comboBox.focusedProperty().or(comboBox.valueProperty().isNotNull()))
                    .then(promptText)
                    .otherwise(promptText+ comboBox.getPromptText()) // Change as needed
    );
    promptLabel.styleProperty().bind(
            Bindings.when(comboBox.focusedProperty())
                    .then("-fx-text-fill: #3498db; -fx-font-size: 13px; -fx-translate-y:-22px;")
                    .otherwise("-fx-text-fill: gray")
    );   
}
    public void setupPromptLabelBindings(DatePicker datePicker, Label promptLabel, String promptText) {
    promptLabel.textProperty().bind(
            Bindings.when(datePicker.focusedProperty().or(datePicker.valueProperty().isNotNull()))
                    .then(promptText+ datePicker.getPromptText())
                    .otherwise("")
    );
    promptLabel.styleProperty().bind(
            Bindings.when(datePicker.focusedProperty())
                    .then("-fx-text-fill: #3498db")
                    .otherwise("-fx-text-fill: gray")
    );
    
    }
    public void setupPromptLabelBindings(TextField textField, Label promptLabel, String promptText) {
    promptLabel.textProperty().bind(
            Bindings.when(textField.focusedProperty().or(textField.textProperty().isNotEmpty()))
                    .then(promptText + textField.getPromptText())
                    .otherwise("")
    );
    promptLabel.styleProperty().bind(
            Bindings.when(textField.focusedProperty())
                    .then("-fx-text-fill: #3498db")
                    .otherwise("-fx-text-fill: gray")
    );
    
    promptLabel.setOnMouseClicked(event -> {
    textField.requestFocus();});
}
    public void setupPromptLabelBindings(TextArea textArea, Label promptLabel, String promptText) {
    // Bind the text property of the promptLabel
    promptLabel.textProperty().bind(
            Bindings.when(textArea.focusedProperty().or(textArea.textProperty().isNotEmpty()))
                    .then(promptText+ textArea.getPromptText())
                    .otherwise("")
    );

    // Bind the style property of the promptLabel
    promptLabel.styleProperty().bind(
            Bindings.when(textArea.focusedProperty())
                    .then("-fx-text-fill: #3498db")
                    .otherwise("-fx-text-fill: gray")
    );

    // Unbind the style property when the TextArea loses focus
    textArea.focusedProperty().addListener((obs, oldVal, newVal) -> {
        if (!newVal) {
            promptLabel.styleProperty().unbind();
            promptLabel.setStyle("-fx-text-fill: gray");
        }
    });
}
    
    public void additionalFieldBind(TextField textfield, TextField valueField, Label promptLabel){
       valueField.promptTextProperty().bind(textfield.textProperty());
       
       
       promptLabel.textProperty().bind(
            Bindings.when(valueField.focusedProperty().or(valueField.textProperty().isNotEmpty()))
                    .then("Enter Value of"+textfield.getText())
                    .otherwise("")
               
    );
       promptLabel.styleProperty().bind(
            Bindings.when(valueField.focusedProperty())
                    .then("-fx-text-fill: #3498db")
                    .otherwise("-fx-text-fill: gray")
    );
    promptLabel.setOnMouseClicked(event -> {
    valueField.requestFocus();});
    }
}
    
