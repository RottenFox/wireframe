/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
/**
 * FXML Controller class
 *
 * @author wwwsa
 */
public class SelectUnitdBoxController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> baseUnitBox,secondaryUnitBox;

    @FXML
    private Label baseUnitLabel,derivedUnitLabel,conLabel;

    @FXML
    private FontAwesomeIconView closeIcon;
    
    @FXML
    private DialogPane dBox;

    @FXML
    private TextField conRate;
    
    @FXML
    private HBox conBox;

    @FXML
    private RowConstraints row3,row4;

    @FXML
    private Button saveButton;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       bindRowHeights();
       String data[]={"KiloGrams","Grams","Litre","MiliLitre","Meter","CentiMeter","Boxes","Bottles"};
       setContentInCboxes(data);
       
    }    
    
    private void bindRowHeights() {
    // Create a DoubleBinding for row3 and row4 heights
    DoubleBinding row3HeightBinding = Bindings.when(
            baseUnitBox.getSelectionModel().selectedItemProperty().isEqualTo(secondaryUnitBox.getSelectionModel().selectedItemProperty())
    ).then(0.0).otherwise(35.0);

    DoubleBinding row4HeightBinding = row3HeightBinding;

    // Bind the prefHeight of row3 and row4 to the calculated heights
    row3.prefHeightProperty().bind(row3HeightBinding);
    row4.prefHeightProperty().bind(row4HeightBinding);
    conBox.visibleProperty().bind(Bindings.when(
            baseUnitBox.getSelectionModel().selectedItemProperty().isEqualTo(secondaryUnitBox.getSelectionModel().selectedItemProperty()))
            .then(false)
            .otherwise(true));
    
    conLabel.visibleProperty().bind(Bindings.when(
            baseUnitBox.getSelectionModel().selectedItemProperty().isEqualTo(secondaryUnitBox.getSelectionModel().selectedItemProperty()))
            .then(false)
            .otherwise(true));
    
    DoubleBinding dBoxBinding = Bindings.when(
            baseUnitBox.getSelectionModel().selectedItemProperty().isEqualTo(secondaryUnitBox.getSelectionModel().selectedItemProperty())
    ).then(180.0).otherwise(600.0);
    dBox.minHeightProperty().bind(dBoxBinding);
    
    }
    
    void setContentInCboxes(String[] strings){
        baseUnitBox.getItems().addAll(Arrays.asList(strings));
        secondaryUnitBox.getItems().addAll(Arrays.asList(strings));
    }
     
}
