/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.AddItem;
import com.example.Repository.Itemrep;
import com.example.Sharedclasses.SharedItemService;
import com.example.otherclasses.FieldsValidation;
import com.example.otherclasses.bindings;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author wwwsa
 */
@Controller
public class AddItemController implements Initializable {

    @Autowired
    private DashboardController dashboardController;
    
    @Autowired
    private SharedItemService sharedItemService;
    
    @Autowired
    Itemrep item;
    
    @FXML
    private ComboBox<?> ItemCategory;

    @FXML
    private TextField Itemname,atPrice,itemcode,location,minStockToMaintain,openingQty,
            purchasePrice,salePrice,wholeSalePrice,wholesaleQty,itemsize,maxstock;

    @FXML
    private Button addImageButton,priceTab,saveandnew,save,selectUnit,stockTab,Update,delete;
    
    @FXML
    private Label ID,additemS;
    
    @FXML
    private Label promptLabel1,promptLabel2,promptLabel3,promptLabel4,promptLabel5,promptLabel6,promptLabel7,promptLabel8,promptLabel9,promptLabel10,promptLabel11,promptLabel12,promptLabel13, promptLabelCat;
    @FXML
    private Pane itemSelection;
    @FXML
    private VBox pricing;
    @FXML
    private HBox stackSwitch;
    @FXML
    private StackPane stockPane;
    @FXML
    private Tab stock,price;    
    @FXML
    private TabPane itempane;
    @FXML
    private DatePicker datepicker;
    
    public void clear(){
        Itemname.clear();atPrice.clear();itemcode.clear();location.clear();minStockToMaintain.clear();openingQty.clear();
            purchasePrice.clear();salePrice.clear();wholeSalePrice.clear();wholesaleQty.clear();itemsize.clear();maxstock.clear();
    }
    @FXML
    void saveandnewitem(ActionEvent event) {
        if (save()) {  // Check if save() returns true
        // Code to execute if save is successful
        clear();
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
    }
    }
    public boolean  save(){
        if (Itemname.getText().isEmpty()) {
            showAlert("Input is empty", "Please enter Item Name");
            return false;
        }else{
            
        try{
        AddItem a = new AddItem();
    a.setItemCode(Optional.ofNullable(itemcode.getText()).orElse("DefaultCode"));
    a.setProductname(Optional.ofNullable(Itemname.getText()).orElse("DefaultName"));
    a.setItem_size(tostring(itemsize.getText()));
    if (ItemCategory.getValue() != null) {
        a.setItem_Category(ItemCategory.getValue().toString());
    } else {
        a.setItem_Category("DefaultCategory"); // Or handle it as you see fit
    }

    a.setItem_price(tostring(salePrice.getText()));
    a.setPurchase_price(tostring(purchasePrice.getText()));
    a.setWholesale_price(tostring(wholeSalePrice.getText()));
    a.setWholesale_qty(tostring(wholesaleQty.getText()));
    a.setLocation(Optional.ofNullable(location.getText()).orElse("DefaultLocation"));
    a.setMinstock(tostring(minStockToMaintain.getText()));
    a.setMaxstock( tostring(maxstock.getText()));
    a.setItem_openstock(tostring(openingQty.getText()));
    a.setItem_stockAmount(tostring(atPrice.getText()));
             System.out.println(datepicker.getValue());
    if (datepicker.getValue() == null) {
    a.setDate(LocalDate.now()); // Set to current date if DatePicker is empty
} else {
    a.setDate(datepicker.getValue()); // Set to the selected date
}
            item.save(a);
            
            return true; 
        } catch (DataIntegrityViolationException e) {
        showAlert("Duplicate Entry", "An item with the same code or name already exists.");
            System.out.println(e);
        return false;
    } catch (Exception e) {
        System.out.println(e);
        showAlert("Error", "An error occurred while saving the item.");
        return false;
    }
    }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     @FXML
    void fetchitem(MouseEvent event) {
        
      String productName = Itemname.getText(); // Assuming Itemname is a TextField for the product name input
Optional<AddItem> items = item.findByProductname(productName);

if (items.isPresent() && save.isVisible()) {
    // Assuming product names are unique and you only expect one result
    if (showConfirmationDialog("Confirm Action", "Are you sure you want to Show The Item Having Same Name?")) {
    AddItem firstItem = items.get();

                itemcode.setText(firstItem.getItemCode());
                itemsize.setText(firstItem.getItem_size() +"");
//                ItemCategory.setValue(firstItem.getItem_code());
                salePrice.setText(firstItem.getItem_price()+"");
                purchasePrice.setText(firstItem.getPurchase_price() +"");
                wholeSalePrice.setText(firstItem.getWholesale_price() +"");
                wholesaleQty.setText(firstItem.getWholesale_qty()+"");
                openingQty.setText(firstItem.getItem_openstock()+"");
                atPrice.setText(firstItem.getItem_stockAmount()+"");
                maxstock.setText(firstItem.getMaxstock()+"");
                minStockToMaintain.setText(firstItem.getMinstock()+"");
                location.setText(firstItem.getLocation());
                datepicker.setValue(firstItem.getDate());
                ID.setText(firstItem.getStoreId()+"");
    Update.setVisible(true);
                Update.setPrefWidth(124);
                 delete.setVisible(true);
                delete.setPrefWidth(124);
                save.setVisible(false);
                saveandnew.setVisible(false);
                additemS.setText("EDIT ITEM");
    }
}
    else if(items.isEmpty() && Update.isVisible()) {
        
    if (showConfirmationDialog("Confirm Action", "Are you sure you want Add New Item?")) {
     // Replace 'getItemCode' with your actual getter method for item_code
    // Set the item code in the TextField
    itemcode.setText("");
    Update.setVisible(false);
                Update.setPrefWidth(0);
                delete.setVisible(false);
                delete.setPrefWidth(0);
                save.setVisible(true);
                saveandnew.setVisible(true);
                additemS.setText("ADD ITEM");
    }
}
                

    }
    
    public boolean showConfirmationDialog(String title, String content) {
    // Create a confirmation alert with custom buttons
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);

    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");

    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

    Optional<ButtonType> result = alert.showAndWait();

    return result.isPresent() && result.get() == buttonTypeYes;
}
     @FXML
    void checkduplicate(MouseEvent event) {
        String itemCode = itemcode.getText();
         // Assuming Itemname is a TextField for the product name input
         Optional<AddItem> items = null;
         if (!itemCode.isEmpty()) {
           items  = item.findByItemCode(itemCode);
         }
    

    if (items != null && items.isPresent() && save.isVisible()) {
        // Assuming product names are unique and you only expect one result
        showAlert("Duplicate Item Code", "This Item Code Already Exist");
    }
    
    }
    
    @FXML
    void isselected(MouseEvent event) {
        TextField sourceField = (TextField) event.getSource();
        sourceField.selectAll();
    }
    
     @FXML
    void saveitem(ActionEvent event) {
         if (save()) {  // Check if save() returns true
        // Code to execute if save is successful
        if (dashboardController != null) {
            dashboardController.loadContent("/fxml/ShowItem.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
    }
        }
    
    public Float tostring(String value){
       
        float v = 0;
        try{
        if (!value.equals("")) {
            v= Float.parseFloat(value);
            
        }else{
            v=0;
        }
        }catch(Exception e){
            System.out.println(e);
        }
    return v;
        
    }
     @FXML
    void switchToPrice(ActionEvent event) {
           itempane.getSelectionModel().select(price);
        priceTab.setStyle("-fx-border-color: red");
        stockTab.setStyle("-fx-border-color: transparent");
    }

    @FXML
    void switchToStock(ActionEvent event) {
        
        itempane.getSelectionModel().select(stock);
           priceTab.setStyle("-fx-border-color: transparent");
        stockTab.setStyle("-fx-border-color: red");
    }

    @FXML
    void deleteItem(MouseEvent event) {
        if (showConfirmationDialog("Confirm Action", "Are you sure you want to Delete that Item ?")) {
        item.deleteById(Integer.valueOf(ID.getText()));
        if (dashboardController != null) {
            dashboardController.loadContent("/fxml/ShowItem.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Delete operation failed.");
    }
        
    }
     public boolean  update(){
        
        try{
            Integer productName = Integer.valueOf(ID.getText()); // Assuming Itemname is a TextField for the product name input
          Optional<AddItem> items = item.findById(productName);

        if (items.isPresent() && Update.isVisible()) {
        AddItem a = items.get();
        
        a.setStoreId(Integer.valueOf(ID.getText()));
    a.setItemCode(Optional.ofNullable(itemcode.getText()).orElse("DefaultCode"));
    a.setProductname(Optional.ofNullable(Itemname.getText()).orElse("DefaultName"));
    a.setItem_size(tostring(itemsize.getText()));
    if (ItemCategory.getValue() != null) {
        a.setItem_Category(ItemCategory.getValue().toString());
    } else {
        a.setItem_Category("DefaultCategory"); // Or handle it as you see fit
    }

    a.setItem_price(tostring(salePrice.getText()));
    a.setPurchase_price(tostring(purchasePrice.getText()));
    a.setWholesale_price(tostring(wholeSalePrice.getText()));
    a.setWholesale_qty(tostring(wholesaleQty.getText()));
    a.setLocation(Optional.ofNullable(location.getText()).orElse("DefaultLocation"));
    a.setMinstock(tostring(minStockToMaintain.getText()));
    a.setMaxstock( tostring(maxstock.getText()));
    a.setItem_openstock(tostring(openingQty.getText()));
    a.setItem_stockAmount(tostring(atPrice.getText()));
             System.out.println(datepicker.getValue());
    if (datepicker.getValue() == null) {
    a.setDate(LocalDate.now()); // Set to current date if DatePicker is empty
} else {
    a.setDate(datepicker.getValue()); // Set to the selected date
}
            item.save(a);
        }
        return true; 
        }  catch (Exception e) {
        System.out.println(e);
        showAlert("Error", "An error occurred while saving the item.");
        return false;
    }
    
    }
      @FXML
    void updateitem(MouseEvent event) {
       if( update()){
           if (dashboardController != null) {
            dashboardController.loadContent("/fxml/ShowItem.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
        
    }
       
    }
    @FXML
    void validatenumberinput(KeyEvent event){
        
       FieldsValidation v = new FieldsValidation();
       v.validatenumber(event);

// No need for the listener anymore as the filter should directly handle the cases


    }
     @FXML
    void validateintinput(KeyEvent event) {
        if (!(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE)) {
         TextField sourceField = (TextField) event.getSource(); // Get the source TextField
   
        if (!event.getCharacter().matches("[0-9]")){
            event.consume();
        }
 
            
            UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || newText.matches("^[0-9]*$")) { 
                return change;
                
            }
            return null;
        };

        TextFormatter<String> numericTextFormatter = new TextFormatter<>(numericFilter);
        sourceField.setTextFormatter(numericTextFormatter);
            System.out.println(numericFilter);
            System.out.println(numericTextFormatter);
        }
    }
    @FXML
    void selectUnit(){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectUnitdBox.fxml"));
            try{
            Parent root = loader.load();
                System.out.println("test");
                //root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
            // In your calling code              
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(new Scene(root));

            // Show the dialog
            stage.showAndWait();
            }catch(Exception e){
                System.out.println(e);
            }
        }
public void bindLabels(){
    try {
        
   
    bindings b= new bindings();
    b.setupPromptLabelBindings(Itemname, promptLabel1, "Item Name*");
    b.setupPromptLabelBindings(itemcode, promptLabel2, "Item Code*");
    b.setupPromptLabelBindings(itemsize, promptLabel3, "Item Size*");
    b.setupPromptLabelBindings(purchasePrice, promptLabel4, "Purchase Price*");
    b.setupPromptLabelBindings(salePrice, promptLabel5, "Sale Price*");
    b.setupPromptLabelBindings(wholeSalePrice, promptLabel6, "Wholesale Price*");
    b.setupPromptLabelBindings(wholesaleQty, promptLabel7, "Wholesale Qty*");
    b.setupPromptLabelBindings(openingQty, promptLabel8, "Opening Qty*");
    b.setupPromptLabelBindings(atPrice, promptLabel9, "At Price*");
    b.setupPromptLabelBindings(minStockToMaintain, promptLabel11, minStockToMaintain.getPromptText());
    b.setupPromptLabelBindings(maxstock, promptLabel12,maxstock.getPromptText());
    b.setupPromptLabelBindings(location, promptLabel13, location.getPromptText());
    b.setupPromptLabelBindings(datepicker, promptLabel10, "Select Date");
    b.setupPromptLabelBindings(ItemCategory, promptLabelCat, "Category");
     } catch (Exception e) {
         System.out.println(e);
    }

}
public void showitem(){
    sharedItemService.selectedEntityProperty(AddItem.class).addListener((obs, oldEntity, newEntity) -> {
            if (newEntity != null) {
                // Update text fields based on the selected entity
                additemS.setText("EDIT ITEM");
                Itemname.setText(newEntity.getProductname());
                itemcode.setText(newEntity.getItemCode());
                itemsize.setText(newEntity.getItem_size() +"");
               // ItemCategory.setText(newEntity.getItem_code());
                salePrice.setText(newEntity.getItem_price()+"");
                purchasePrice.setText(newEntity.getPurchase_price() +"");
                wholeSalePrice.setText(newEntity.getWholesale_price() +"");
                wholesaleQty.setText(newEntity.getWholesale_qty()+"");
                openingQty.setText(newEntity.getItem_openstock()+"");
                atPrice.setText(newEntity.getItem_stockAmount()+"");
                maxstock.setText(newEntity.getMaxstock()+"");
                minStockToMaintain.setText(newEntity.getMinstock()+"");
                location.setText(newEntity.getLocation());
                datepicker.setValue(newEntity.getDate());
                ID.setText(newEntity.getStoreId()+"");
                System.out.println(newEntity.getStoreId());
                Update.setVisible(true);
                Update.setPrefWidth(124);
                 delete.setVisible(true);
                delete.setPrefWidth(124);
                save.setVisible(false);
                saveandnew.setVisible(false);
                // Handle other fields similarly
            }
        });
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bindLabels();
        showitem();
        priceTab.setStyle("-fx-border-color: red");
        stockTab.setStyle("-fx-border-color: transparent");
        System.out.println("work");
        
        
    }    
    
}
