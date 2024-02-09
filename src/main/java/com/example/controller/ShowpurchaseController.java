/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.PurchaseSummary;
import com.example.Repository.purchasesummaryrep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class ShowpurchaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
   purchasesummaryrep purchase;
    @FXML
    private TableColumn<?, ?> Amount;

    @FXML
    private TableColumn<?, ?> Balance_Due;

    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> Invoice_Number;

    @FXML
    private TableColumn<PurchaseSummary, String> Party_Name;

    @FXML
    private TableColumn<?, ?> Payment_Type;
    
    private FilteredList<PurchaseSummary> filteredData;
    @FXML
    private TableView<PurchaseSummary> showpurchase;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Date.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        Invoice_Number.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        Party_Name.setCellValueFactory(cellData -> {
    PurchaseSummary purchaseSummary = cellData.getValue();
    if (purchaseSummary != null && purchaseSummary.getCustomer() != null) {
        String partyName = purchaseSummary.getCustomer().getCustomerName();
        return new ReadOnlyStringWrapper(partyName);
    } else {
        return new ReadOnlyStringWrapper("");
    }
});
        Payment_Type.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        Balance_Due.setCellValueFactory(new PropertyValueFactory<>("totalBalance"));
       ObservableList<PurchaseSummary> masterData = FXCollections.observableArrayList(purchase.findAll());
        filteredData = new FilteredList<>(masterData, p -> true);
        // Set the table's items to the filtered list
        showpurchase.setItems(filteredData);
    }    
    
}
