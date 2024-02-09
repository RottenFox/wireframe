package com.example.controller;

import com.example.Entity.AddItem;
import com.example.Sharedclasses.ChangeType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.springframework.stereotype.Controller;

@Controller
public class ChangeController implements Initializable {

    @FXML
    private Label ITEMNAME;

    @FXML
    private TextField VALUE;

    @FXML
    public Label changelabel;

    @FXML
    public Label valuelabel;
    private FloatProperty sharedProperty;
    
    private AddItem selectedItem;
    private ChangeType changeType;
    @FXML
    private Button ok;
    
    
   
    public void setSharedProperty(FloatProperty sharedProperty, ChangeType changeType) {
        if (this.sharedProperty == null) {
            this.sharedProperty = sharedProperty;
            this.changeType = changeType;

            // Bind the TextField text property bidirectionally to the sharedProperty
//            Bindings.bindBidirectional(VALUE.textProperty(), sharedProperty, new NumberStringConverter());
        VALUE.textProperty().bindBidirectional(sharedProperty , new NumberStringConverter());
        }
    }

    public void setSelectedItem(AddItem selectedItem) {
        this.selectedItem = selectedItem;

        // Check if sharedProperty is not null before accessing it
        if (sharedProperty != null) {
            ITEMNAME.setText(selectedItem.getProductname());

            // Adjust behavior based on the changeType
            switch (changeType) {
                case PRICE:
                    sharedProperty.set(selectedItem.getPurchase_price());
                    break;
                case QUANTITY:
                    sharedProperty.set(selectedItem.getQty());
                    break;
                case AMOUNT:
                    sharedProperty.set(selectedItem.getAmount());
                    break;
            }
        }
    }
     @FXML
    void closeonkeypressed(KeyEvent event) {
         if (event.getCode() == KeyCode.ENTER) {
             ok.fire();
         }
    }
    @FXML
    void applyChanges(ActionEvent event) {
        try {
            // Update the corresponding property of the selected item when the dialog is closed
            if (selectedItem != null && sharedProperty != null) {
                switch (changeType) {
                    case PRICE:
                        selectedItem.setPurchase_price(sharedProperty.get());
                        selectedItem.setAmount(sharedProperty.get() * selectedItem.getQty());
                        break;
                    case QUANTITY:
                        
                        selectedItem.setQty(sharedProperty.get());
                        selectedItem.setAmount(sharedProperty.get() * selectedItem.getPurchase_price());
                        break;
                    case AMOUNT:
                        
                        selectedItem.setAmount(sharedProperty.get());
                        selectedItem.setPurchase_price(sharedProperty.get() / selectedItem.getQty());
                        break;
                }
            }

            // Close the dialog
            Stage stage = (Stage) VALUE.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            // Handle the case where the user enters an invalid number
            // You can show an error message or provide feedback to the user
            e.printStackTrace(); // For now, print the stack trace
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
