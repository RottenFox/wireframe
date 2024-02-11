package com.example.controller;

import com.example.Entity.AddItem;
import com.example.Repository.Itemrep;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Controller;

@Controller
public class ShowitemDetailsController implements Initializable {

   

    @FXML
    private TreeTableColumn<AddItem, String> availablestock;

    @FXML
    private TreeTableColumn<AddItem, String> itemCategory;

    @FXML
    private TreeTableColumn<AddItem, String> itemcode;

    @FXML
    private TreeTableColumn<AddItem, String> itemname;

    @FXML
    private TreeTableColumn<AddItem, String> itemprice;

    @FXML
    private TreeTableColumn<AddItem, String> itemsize;

    @FXML
    private TreeTableView<AddItem> itemtable;

    private FilteredList<AddItem> filteredData;

    public void loaddata(Itemrep item){
        availablestock.setCellValueFactory(param -> param.getValue().getValue().stockProperty());
//        itemCategory.setCellValueFactory(param -> param.getValue().getValue().categoryProperty());
//        itemcode.setCellValueFactory(param -> param.getValue().getValue().itemCodeProperty());
        itemname.setCellValueFactory(param -> param.getValue().getValue().productnameProperty());
        itemprice.setCellValueFactory(param -> param.getValue().getValue().itemPriceProperty());
//        itemsize.setCellValueFactory(param -> param.getValue().getValue().sizeProperty());

        // Fetch data from the repository or database
        ObservableList<AddItem> masterData = FXCollections.observableArrayList(item.findAll());

        // Create a root item for the TreeTableView
        TreeItem<AddItem> rootItem = new TreeItem<>();
        rootItem.setExpanded(true); // Optional: Set the root item to be expanded

        // Add the fetched data to the root item
        for (AddItem items : masterData) {
            TreeItem<AddItem> itemTreeItem = new TreeItem<>(items);
            rootItem.getChildren().add(itemTreeItem);
        }

        // Set the root item to the TreeTableView
        itemtable.setRoot(rootItem);

        // Optionally, set the columns to be sortable
        

        // Set the table's items to the root item
        itemtable.setShowRoot(false); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns
        // Optional: Hide the root item in the TreeTableView
    }
}
