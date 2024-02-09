
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.AddItem;
import com.example.Repository.Itemrep;
import com.example.Sharedclasses.SharedItemService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class ShowItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
    private DashboardController dashboardController;
    
    @Autowired
    private SharedItemService sharedItemService;
    
    @Autowired
   Itemrep item;
    @FXML
    private SplitMenuButton AddItem;

    @FXML
    private Button Adjustitem;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> invoice;

    @FXML
    private Label itemn;

    @FXML
    private TableColumn<?, ?> itemname;

    @FXML
    private TableColumn<?, ?> itemquantity;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private Label purchaseprice;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private Label saleprice;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private Label stockquantity;

    @FXML
    private TableView<AddItem> stocktable,invoicetable;

    @FXML
    private Label stockvalue;

    @FXML
    private TableColumn<?, ?> type,code;
    
    @FXML
    private VBox rootpane;
   
    private FilteredList<AddItem> filteredData;
    
    
    @FXML
    private TextField searchbar;

    @FXML
    void filterdata(KeyEvent event) {
        String filter = searchbar.getText(); 
        if (filter == null || filter.isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            String lowerCaseFilter = filter.toLowerCase();

            filteredData.setPredicate(addItem -> {
                // Check if item name or code matches the filter
                if (addItem.getProductname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return addItem.getItemCode().toLowerCase().contains(lowerCaseFilter);
            });
        }
    }
    
   @FXML
void showbill(MouseEvent event) {
    searchbar.setVisible(false);
    AddItem.setVisible(true);

    if (event.getClickCount() == 2) {
        // Get the selected item
        AddItem selectedItem = stocktable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Selected: " + selectedItem.getProductname());
            
            
            if (dashboardController != null) {
            dashboardController.loadContent("/fxml/addItem.fxml");
            sharedItemService.clearSelectedEntity();
            ObjectProperty<AddItem> selectedProperty = sharedItemService.selectedEntityProperty(AddItem.class);
                selectedProperty.set(selectedItem);

//            System.out.println(selectedItem);
        }
        }
    }
}

    
    @FXML
    void openAddItem(ActionEvent event) {
        
    }
    
    @FXML
    void additem(MouseEvent event) {
        if (dashboardController != null) {
            dashboardController.loadContent("/fxml/addItem.fxml");
        }
        
    }
    @FXML
    void showsearchbar(MouseEvent event) {
        searchbar.setVisible(true);
            AddItem.setVisible(false);
    }
    
    @FXML
    void selectinvoice(MouseEvent event) {
        searchbar.setVisible(false);
            AddItem.setVisible(true);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        code.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
//        List<AddItem> products = item.findAll();
//        stocktable.setItems(FXCollections.observableArrayList(products));
        
       ObservableList<AddItem> masterData = FXCollections.observableArrayList(item.findAll());
        filteredData = new FilteredList<>(masterData, p -> true);

        // Set the table's items to the filtered list
        stocktable.setItems(filteredData);
        
        rootpane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
    // Check if the event target is not one of the excluded elements
    if (!isEventTargetExcluded(event)) {
        searchbar.setVisible(false);
        AddItem.setVisible(true);
    }
});
       stocktable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    // Bind the textProperty of itemn TextField to the Productname property of the selected item
                    Bindings.bindBidirectional(itemn.textProperty(), newValue.productnameProperty());
                    // Bind the textProperty of saleprice TextField to the Item_price property of the selected item
                    Bindings.bindBidirectional(saleprice.textProperty(), newValue.itemPriceProperty());
                    Bindings.bindBidirectional(purchaseprice.textProperty(), newValue.purchasePriceProperty());
                    Bindings.bindBidirectional(stockquantity.textProperty(), newValue.stockProperty());
                    Bindings.bindBidirectional(stockvalue.textProperty(), newValue.StockvalueProperty());
                } else {
                    // Unbind the textProperty if the selected item is null
                    itemn.textProperty().unbind();
                    saleprice.textProperty().unbind();
                }
            } catch (Exception e) {
                // Handle exceptions, e.g., log or display an error message
                e.printStackTrace();
            }
        });


 
    }  
    
    private boolean isEventTargetExcluded(MouseEvent event) {
    // Check if the target of the event is one of the excluded components
     Node target = (Node) event.getTarget();
     return isChild(target, searchbar) || 
           isChild(target, AddItem);
}

private boolean isChild(Node node, Parent parent) {
    // Utility method to check if 'node' is a child of 'parent'
    while (node != null) {
        if (node.equals(parent)) {
            return true;
        }
        
        node = node.getParent();
    }
    return false;
}
    
}
