package com.example.controller;

import com.example.Entity.AddItem;
import com.example.Repository.Itemrep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShowitemDetailsController implements Initializable {

    @Autowired
    Itemrep item;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns
//        availablestock.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        itemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
//        itemcode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
//        itemname.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        itemprice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        itemsize.setCellValueFactory(new PropertyValueFactory<>("size"));
//
//        // Fetch data from the repository or database
//        ObservableList<AddItem> masterData = FXCollections.observableArrayList(item.findAll());
//        
//        // Create a root item for the TreeTableView
//        TreeItem<AddItem> rootItem = new TreeItem<>();
//        rootItem.setExpanded(true); // Optional: Set the root item to be expanded
//
//        // Add the fetched data to the root item
//        rootItem.getChildren().addAll(masterData);
//
//        // Set the root item to the TreeTableView
//        itemtable.setRoot(rootItem);
//
//        // Optionally, set the columns to be sortable
//        itemtable.getSortOrder().add(availablestock);
//        itemtable.getSortOrder().add(itemCategory);
//        itemtable.getSortOrder().add(itemcode);
//        itemtable.getSortOrder().add(itemname);
//        itemtable.getSortOrder().add(itemprice);
//        itemtable.getSortOrder().add(itemsize);
//
//        // Set the table's items to the root item
//        itemtable.setShowRoot(false); // Optional: Hide the root item in the TreeTableView
    }
}
