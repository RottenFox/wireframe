/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.AddCustomer;
import com.example.Repository.Cusrep;
import com.example.Sharedclasses.SharedItemService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class ShowCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
    private DashboardController dashboardController;
    
    @Autowired
    private SharedItemService sharedItemService;
    
    @Autowired
   Cusrep cus;
    
    @FXML
    private Label addAddress;
    
    @FXML
    private Tab courses,members;
    
    @FXML
    private TabPane customerTabPane;
    
    @FXML
    private SplitMenuButton addCustomer, addCourseButton;

    @FXML
    private Label addEmail;

    @FXML
    private Label addPhoneNumber;

    @FXML
    private TableColumn<?, ?> amount;

    @FXML
    private TableColumn<?, ?> balance;

    @FXML
    private Label currency;

    @FXML
    private TableColumn<?, ?> customerName;

    @FXML
    private TableView<AddCustomer> customerTable;
    
    @FXML
    private TableView<TankData> course;
    
    @FXML
private TableColumn<TankData, String> coursename;

    
    @FXML
    private TableColumn<TankData, Integer> coursemember;
    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private FontAwesomeIconView emailGlyph;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private Label nameC;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    private FontAwesomeIconView phoneGlyph;

    @FXML
    private FontAwesomeIconView pinGlyph;

    @FXML
    private TextField searchCustomerPurchase;

    @FXML
    private TextField searchbar;

    @FXML
    private Label setCreditLimit;

     @FXML
    private VBox rootpane;
     
    @FXML
    private TableColumn<?, ?> type,code;
    
    @FXML
    private Button coursesTab,membersTab;
    
    private FilteredList<AddCustomer> filteredData;
    
    private ObservableList<TankData> tankDataList;
    
    @FXML
    void switchToCourses(ActionEvent event) {
        firstSwitch();    
    }
    
    @FXML
    void switchToMembers(ActionEvent event) {
        
        customerTabPane.getSelectionModel().select(members);
        coursesTab.setStyle("-fx-border-color: transparent");
        membersTab.setStyle("-fx-border-color: red");
    }
    
    void firstSwitch(){
        customerTabPane.getSelectionModel().select(courses);
        coursesTab.setStyle("-fx-border-color: red");
        membersTab.setStyle("-fx-border-color: transparent");    
    }
    @FXML
    void addCustomer(MouseEvent event) {
    {
        if (dashboardController != null) {
            dashboardController.loadOverlayContent("/fxml/CustomerAdd.fxml");
        }
    }
    }
    @FXML
    void addCourse(MouseEvent event) {
    {
        if (dashboardController != null) {
            dashboardController.loadOverlayContent("/fxml/addCourse.fxml");
        }
    }
    }

    @FXML
    void openAddAddress(MouseEvent event) {

    }

    @FXML
    void openAddEmail(MouseEvent event) {

    }

    @FXML
    void openAddPhoneNumber(MouseEvent event) {

    }

    @FXML
    void selectinvoice(MouseEvent event) {
        searchbar.setVisible(false);
        addCustomer.setVisible(true);
    }

    @FXML
    void setCreditLimit(MouseEvent event) {
            
    }

    @FXML
    void showbill(MouseEvent event) {
        searchbar.setVisible(false);
        addCustomer.setVisible(true);
        if (event.getClickCount() == 2) {
        // Get the selected item
        AddCustomer selectedItem = customerTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (dashboardController != null) {
                dashboardController.loadOverlayContent("/fxml/CustomerAdd.fxml");
                sharedItemService.clearSelectedEntity();

                // Use the generic method to handle the selected item
                ObjectProperty<AddCustomer> selectedProperty = sharedItemService.selectedEntityProperty(AddCustomer.class);
                selectedProperty.set(selectedItem);
            }
        }

    }
    }

    @FXML
    void showsearchbar(MouseEvent event) {
        searchbar.setVisible(true);
        addCustomer.setVisible(false);
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
                if (addItem.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return addItem.getCustomercode().toLowerCase().contains(lowerCaseFilter);
            });
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            firstSwitch();
            
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Balance"));
        code.setCellValueFactory(new PropertyValueFactory<>("customercode"));
//        List<AddItem> products = item.findAll();
//        stocktable.setItems(FXCollections.observableArrayList(products));
        
       ObservableList<AddCustomer> masterData = FXCollections.observableArrayList(cus.findAll());
       
        filteredData = new FilteredList<>(masterData, p -> true);
   
        customerTable.setItems(filteredData);
        
        
        rootpane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
    // Check if the event target is not one of the excluded elements
    if (!isEventTargetExcluded(event)) {
        searchbar.setVisible(false);
        addCustomer.setVisible(true);
    }
});
        showmember();
    }  
    
    private boolean isEventTargetExcluded(MouseEvent event) {
    // Check if the target of the event is one of the excluded components
     Node target = (Node) event.getTarget();
     return isChild(target, searchbar) || 
           isChild(target, addCustomer);
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

    coursename.setCellValueFactory(cellData -> cellData.getValue().tankNameProperty());
        coursemember.setCellValueFactory(cellData -> cellData.getValue().tankQuantityProperty().asObject());

        // Initialize the data
        tankDataList = FXCollections.observableArrayList();

        // Add dummy data
        tankDataList.add(new TankData("Tank1", 5));
        tankDataList.add(new TankData("Tank2", 8));
        tankDataList.add(new TankData("Tank3", 3));

        // Set the data to the TableView
        course.setItems(tankDataList);
}    
}
class TankData {
     private final SimpleStringProperty tankName;
    private final SimpleIntegerProperty tankQuantity;

    public TankData(String tankName, int tankQuantity) {
        this.tankName = new SimpleStringProperty(tankName);
        this.tankQuantity = new SimpleIntegerProperty(tankQuantity);
    }

    public String getTankName() {
        return tankName.get();
    }

    public void setTankName(String tankName) {
        this.tankName.set(tankName);
    }

    public SimpleStringProperty tankNameProperty() {
        return tankName;
    }

    public int getTankQuantity() {
        return tankQuantity.get();
    }

    public void setTankQuantity(int tankQuantity) {
        this.tankQuantity.set(tankQuantity);
    }

    public SimpleIntegerProperty tankQuantityProperty() {
        return tankQuantity;
    }
    
}