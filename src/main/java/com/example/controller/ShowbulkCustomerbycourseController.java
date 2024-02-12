package com.example.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.example.Entity.AddCustomer;
import com.example.Repository.Cusrep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */

@Controller
public class ShowbulkCustomerbycourseController implements Initializable {
    
//    @Autowired
//    Cusrep cus;
//    
     @FXML
    private TableColumn<?, ?> balance;

    @FXML
    private TableColumn<?, ?> code;

    @FXML
    private TableView<AddCustomer> customer;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> phone;
    
    @FXML
    private CheckBox selectall;
    ObservableList<AddCustomer> masterData;
    @FXML
    private TableColumn<AddCustomer, Boolean> select;
    
    private FilteredList<AddCustomer> filteredData;
    
    void loaddata(Cusrep cus){
        
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        balance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
        code.setCellValueFactory(new PropertyValueFactory<>("customercode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneumber"));
        select.setCellValueFactory(new PropertyValueFactory<>("selected"));
    select.setCellFactory(CheckBoxTableCell.forTableColumn(select));
    
//        List<AddItem> products = item.findAll();
//        stocktable.setItems(FXCollections.observableArrayList(products));
        
        masterData = FXCollections.observableArrayList(cus.findAll());
       
        filteredData = new FilteredList<>(masterData, p -> true);
   
        customer.setItems(filteredData);
        
        
        BooleanBinding allSelectedBinding = Bindings.createBooleanBinding(() ->
        masterData.stream().allMatch(AddCustomer::isSelected),
    masterData
);

SimpleBooleanProperty allSelectedProperty = new SimpleBooleanProperty();
selectall.selectedProperty().bindBidirectional(allSelectedProperty);

// Listen for changes in the 'selectAll' checkbox
selectall.selectedProperty().addListener((obs, oldVal, newVal) -> {
    // Update all checkboxes in the table only if 'selectAll' is checked
    if (newVal) {
        masterData.forEach(customer -> customer.setSelected(true));
    }
});

// Listen for changes in individual checkboxes in the table
masterData.forEach(customer ->
        customer.selectedProperty().addListener((obs, oldVal, newVal) -> {
            // Update 'selectAll' checkbox only if the unselected checkbox was checked before
            if (!newVal && oldVal) {
                allSelectedProperty.set(false);
            } else {
                // Update 'selectAll' checkbox when any checkbox in the table changes
                allSelectedProperty.set(masterData.stream().allMatch(AddCustomer::isSelected));
            }
        })
);

selectall.setOnMouseClicked(event -> {
        if (!selectall.isSelected()) {
            masterData.forEach(c -> c.setSelected(false));
        }
    });


    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

    }    
    
}
