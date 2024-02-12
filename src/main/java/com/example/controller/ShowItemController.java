
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TableColumn<?,?> name;

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
    private Tab categoryTab,unitsTab,itemsTab;

    @FXML
    private Button categoryTabB,unitTabB,itemsTabB;
    
    @FXML
    private TabPane showItemTabPane;
    
    @FXML
    private TextField searchbar;
    
    private ObservableList<categoryData> categoryDataList;
    
    @FXML
    private TableView<categoryData> stocktable11;
    
    @FXML
    private TableColumn<categoryData, String> name11;
    
    @FXML
    private TableColumn<categoryData, Integer> quantity11;
    
    private ObservableList<items> itemsList;
    
    @FXML
    private TableView<items> invoicetable11;
    
    @FXML
    private TableColumn<items, Integer> type11;
    
    @FXML
    private TableColumn<items, String> invoice11,itemname11, sp11;
    
    @FXML
    private void showItems(MouseEvent event){
        showItem();
    }
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

    @FXML
    void switchCategory(ActionEvent event) {
        showItemTabPane.getSelectionModel().select(categoryTab);
        categoryTabB.setStyle("-fx-border-color: red");
        unitTabB.setStyle("-fx-border-color: transparent");
        itemsTabB.setStyle("-fx-border-color: transparent");
    }

    @FXML
    void switchItems(ActionEvent event) {
        firstSwitch();
    }

    @FXML
    void switchUnits(ActionEvent event) {
        showItemTabPane.getSelectionModel().select(unitsTab);
        categoryTabB.setStyle("-fx-border-color: transparent");
        unitTabB.setStyle("-fx-border-color: red");
        itemsTabB.setStyle("-fx-border-color: transparent");
    }
    
    void firstSwitch(){
        showItemTabPane.getSelectionModel().select(itemsTab);
        categoryTabB.setStyle("-fx-border-color: transparent");
        unitTabB.setStyle("-fx-border-color: transparent");
        itemsTabB.setStyle("-fx-border-color: red");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showmember();
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


       firstSwitch();
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

public void showmember(){

        name11.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        quantity11.setCellValueFactory(cellData -> cellData.getValue().categoryQuantityProperty().asObject());

        // Initialize the data
        categoryDataList = FXCollections.observableArrayList();

        // Add dummy data
        categoryDataList.add(new categoryData("Menu 1", 5));
        categoryDataList.add(new categoryData("Menu 2", 8));
        categoryDataList.add(new categoryData("Menu 3", 3));

        // Set the data to the TableView
        stocktable11.setItems(categoryDataList);
}

public void showItem(){
    type11.setCellValueFactory(cellData -> cellData.getValue().itemCodeProperty().asObject());
    invoice11.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
    itemname11.setCellValueFactory(cellData -> cellData.getValue().itemCompanyProperty());
    sp11.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());
    
    itemsList= FXCollections.observableArrayList();
    
    itemsList.add(new items(011,"Pratha", "Sunridge","50"));
    itemsList.add(new items(025,"Egg Omlette", "Sb eggs","40"));
    itemsList.add(new items(024,"Mix tea", "Lipton","60"));
    itemsList.add(new items(017,"Water bottle 500ml", "Nestle","40"));
    
    invoicetable11.setItems(itemsList);
}

}

class categoryData {
    private final SimpleStringProperty categoryName;
    private final SimpleIntegerProperty categoryQuantity;

    public categoryData(String categoryName, int categoryQuantity) {
        this.categoryName = new SimpleStringProperty(categoryName);
        this.categoryQuantity = new SimpleIntegerProperty(categoryQuantity);
    }

    public String getcategoryName() {
        return categoryName.get();
    }

    public void setcategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public int getcategoryQuantity() {
        return categoryQuantity.get();
    }

    public void setcategoryQuantity(int categoryQuantity) {
        this.categoryQuantity.set(categoryQuantity);
    }

    public SimpleIntegerProperty categoryQuantityProperty() {
        return categoryQuantity;
    }
    
}


class items{
    private final SimpleIntegerProperty itemCode;
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemCompany;
    private final SimpleStringProperty itemPrice;
    
    public items(int itemCode, String itemName, String itemCompany, String itemPrice) {
        this.itemCode = new SimpleIntegerProperty(itemCode);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemCompany = new SimpleStringProperty(itemCompany);
        this.itemPrice = new SimpleStringProperty(itemPrice);
    }
    
     // Getter methods
    public int getItemCode() {
        return itemCode.get();
    }

    public String getItemName() {
        return itemName.get();
    }

    public String getItemCompany() {
        return itemCompany.get();
    }

    public String getItemPrice() {
        return itemPrice.get();
    }

    // Setter methods
    public void setItemCode(int code) {
        itemCode.set(code);
    }

    public void setItemName(String name) {
        itemName.set(name);
    }

    public void setItemCompany(String company) {
        itemCompany.set(company);
    }

    public void setItemPrice(String price) {
        itemPrice.set(price);
    }
    
    public SimpleIntegerProperty itemCodeProperty() {
        return itemCode;
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public SimpleStringProperty itemCompanyProperty() {
        return itemCompany;
    }

    public SimpleStringProperty itemPriceProperty() {
        return itemPrice;
    }
    
}
