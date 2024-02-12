/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.AddCustomer;
import com.example.Entity.AddItem;
import com.example.Entity.Purchase;
import com.example.Entity.PurchaseSummary;
import com.example.Repository.Cusrep;
import com.example.Repository.Itemrep;
import com.example.Repository.purchaserep;
import com.example.Repository.purchasesummaryrep;
import com.example.Service.InvoiceSequenceService;
import com.example.Service.PurchaseService;
import com.example.Service.Purchasecustomeradd;
import com.example.Sharedclasses.ChangeType;
import static com.example.Sharedclasses.ChangeType.AMOUNT;
import static com.example.Sharedclasses.ChangeType.PRICE;
import static com.example.Sharedclasses.ChangeType.QUANTITY;
import com.example.Sharedclasses.SharedDataModel;
import com.example.otherclasses.FieldsValidation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.F1;
import static javafx.scene.input.KeyCode.F2;
import static javafx.scene.input.KeyCode.F3;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class Add_SaleController implements Initializable {

    
    @Autowired
    private Cusrep cus;
    
    @Autowired
    private DashboardController dashboardController;
    
    @Autowired
   Itemrep item;
    @Autowired
    purchasesummaryrep sprep;
    @Autowired
    private SharedDataModel sharedDataModel;
    
    @Autowired
    private purchaserep purchaseRepository;
    
    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
    private Purchasecustomeradd padd;
    
    @FXML
    private TextField searchbox,autocompletecustomer;
    
    private FilteredList<AddCustomer> filteredData;
    private ObservableList<AddCustomer> masterData;
    
    private FilteredList<AddItem> filteredItemData;
    private ObservableList<AddItem> masterItemData;
    
    TableView<AddItem> ItemtableView;
    
    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> pno;

    @FXML
    private TableColumn<?, ?> price,amount;

    @FXML
    private TableView<AddItem> purchasetable;

    @FXML
    private TableColumn<?, ?> qty;
    @FXML
    private DatePicker billdate;
    @FXML
    private TextArea Shippingaddress,billingaddress;
    @FXML
    private HBox buttonsbox;
    @FXML
    private Button delete,save,saveandnew,update,chnageqty,showbutton;
    
     @FXML
    private TableView<AddItem> showitem;
     @FXML
    private TableView<AddCustomer> customertable;
      @FXML
    private TableColumn<?, ?> Itemname,code;

    @FXML
    private TableColumn<?, ?> Itemprice;
    @FXML
    private TableColumn<?, ?> customername;
    @FXML
    private TableColumn<?, ?> Stock;
    @FXML
    private TableColumn<AddCustomer, Float> Balance;
    @FXML
    private Label totalamount,Billno,customerbalance;

    @FXML
    private Label totalqty,totalrows;
    
    @FXML
    private TextArea Description;
    
     @FXML
    private GridPane  rootPane;
    
    @FXML
    private TextField discountinpercentage,balanceamount,Discountinrupees,tax,netamount,paidamount;
    
    ObservableList<AddItem> purchaseData = FXCollections.observableArrayList();
    
    private boolean isBoxVisible = false;
    PurchaseSummary psummary;
    
    @Autowired
    private InvoiceSequenceService sequenceService;
    
    
    @FXML      
    void deleteCustomer(MouseEvent event) {

    }
    
//    public void decrementStock(String itemname, Float quantity) {
//        
//    AddItem items = item.findByProductnameWithLock(itemname)
//        .orElseThrow(() -> new RuntimeException("Item not found"));
//
////    if (items.getStock() < quantity) {
////        System.out.println("Insufficient stock");
////    
////
////   
////    }
//     items.setSaleqty(items.getPurchaseqty() + quantity);
//    item.save(items);
//}
    public Optional<Purchase> finditemwithinvoice(AddItem items){
        String invoiceNumber = Billno.getText();
        Integer itemId = items.getStoreId();
                
    Optional<Purchase> existingSummary = purchaseRepository.findByBillNoAndItem_StoreId(invoiceNumber, itemId);
    
        return existingSummary;
        
    } 
    @FXML
    void validatenumberinput(KeyEvent event){
        
       FieldsValidation v = new FieldsValidation();
       v.validatenumber(event);

// No need for the listener anymore as the filter should directly handle the cases


    }
    public boolean updateqty(Optional<Purchase> existingSummary ,AddItem items , float qty ,String type){
         // Assuming Itemname is a TextField for the product name input
         
        
            
        if (existingSummary.isPresent() && items!=null) {
            // 2. Modify the data
            Purchase summaryToUpdate = existingSummary.get();
            
            // 3. Update the database
            if(type.equals("QUANTITY")) {
                summaryToUpdate.setQuantity(items.getQty());
                summaryToUpdate.setAmount(items.getAmount());
                purchaseService.incrementStock(items.getProductname(), qty , items.getPurchase_price() ,"");
            }else if(type.equals("PRICE")){
                summaryToUpdate.setPrice(items.getPurchase_price());
                summaryToUpdate.setAmount(items.getAmount());
                purchaseService.incrementStock(items.getProductname(), items.getQty() , qty,"CHANAGE");
            }else if(type.equals("AMOUNT")){
                summaryToUpdate.setAmount(items.getAmount());
                summaryToUpdate.setPrice(items.getPurchase_price());
            }
            
            purchaseRepository.save(summaryToUpdate);

            // Optionally, perform any additional actions after the update
            // ...
            
            System.out.println("Data updated successfully.");
            return true;
        } else {
            System.out.println("Invoice number or item ID not found in the database.");
            return false;
        }

    }
    public boolean saveitemrow(){
        try{
        String customerNames = autocompletecustomer.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> cust = cus.findByCustomerName(customerNames);
        AddCustomer firstItem = null;
        if (cust.isPresent() && save.isVisible() && !autocompletecustomer.getText().isEmpty()) {
            // Assuming product names are unique and you only expect one result
             firstItem = cust.get();   
            }
AddItem selectedItem  = purchasetable.getSelectionModel().getSelectedItem();
      
        // Create a new Purchase entity for each item
        Purchase purchase = new Purchase();
        purchase.setItem(selectedItem);
             System.out.println(firstItem);
        purchase.setCustomer(firstItem);
        purchase.setQuantity(selectedItem.getQty());
        purchase.setAmount(selectedItem.getAmount());
        purchase.setPrice(selectedItem.getItem_price());
        purchase.setBillNo(Billno.getText());
        if (billdate.getValue() == null) {
     purchase.setBillDate(LocalDate.now());
     // Set to current date if DatePicker is empty
        } else {
             purchase.setBillDate(billdate.getValue()); 
// Set to the selected date
        }
        purchase.setPurchaseSummary(psummary);
        purchaseRepository.save(purchase);
       purchaseService.incrementStock(selectedItem.getProductname(), selectedItem.getQty() , selectedItem.getPurchase_price(),"");
       return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public boolean savepurchase(){
        try{
            if (autocompletecustomer.getText().isEmpty()) {
                showAlert("Alert" , "Please Enter Customer Name");
                autocompletecustomer.requestFocus();
                return false;
            }else if(purchasetable.getItems().size() <=0){
                showAlert("Alert" , "Please Enter Items In Table");
                searchbox.requestFocus();
                
                return false;
            }{
                
    String customerNames = autocompletecustomer.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> cust = cus.findByCustomerName(customerNames);
        AddCustomer firstItem = null;
if (cust.isPresent() && save.isVisible() && !autocompletecustomer.getText().isEmpty()) {
    // Assuming product names are unique and you only expect one result
     firstItem = cust.get();   
    }

        if (firstItem !=null) {
         psummary = new PurchaseSummary();
        psummary.setInvoiceNumber(Billno.getText());
        psummary.setTotalAmount(tostring(totalamount.getText()));
        psummary.setDiscountPer(tostring(discountinpercentage.getText()));
        psummary.setDiscountRup(tostring(Discountinrupees.getText()));
        psummary.setNetAmount(tostring(netamount.getText()));
        psummary.setReceivedAmount(tostring(paidamount.getText()));
        psummary.setPreviousBalance(tostring(totalamount.getText()));
        psummary.setTotalBalance(tostring(netamount.getText()) - tostring(paidamount.getText()) );
        psummary.setCustomer(firstItem);
        if (billdate.getValue() == null) {
        psummary.setInvoiceDate(LocalDate.now());
     // Set to current date if DatePicker is empty
        } else {
            psummary.setInvoiceDate(billdate.getValue());
// Set to the selected date
        }
        
        sprep.save(psummary);
        
        
         padd.purchasebalance(firstItem.getCustomerName(), tostring(netamount.getText()) - tostring(paidamount.getText()));
        
         String invoiceNumber = Billno.getText();

// 1. Retrieve all existing purchases with the given invoice number
        List<Purchase> existingPurchases = purchaseRepository.findByBillNo(invoiceNumber);

        if (!existingPurchases.isEmpty()) {
            // 2. Modify each purchase entity
            for (Purchase existingPurchase : existingPurchases) {
                // Set the new discount percentage and other modifications
                existingPurchase.setCustomer(firstItem);
                existingPurchase.setPurchaseSummary(psummary);

                // 3. Update each entity in the database
                purchaseRepository.save(existingPurchase);
            }

            // Optionally, perform any additional actions after the update
            // ...

            System.out.println("Data updated successfully.");
        } else {
            System.out.println("Invoice number not found in the database.");
        }

         return true;
        } else{
            return false;
        }
            
            }
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void save(ActionEvent event) {
          if (savepurchase()) {  // Check if save() returns true
        // Code to execute if save is successful
        
        if (dashboardController != null) {
            dashboardController.setOverlayPaneInvisiblewithoutcheck();
            dashboardController.loadContent("/fxml/Showpurchase.fxml");
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
    void saveandnew(ActionEvent event) {

    }

    @FXML
    void updatecustomer(MouseEvent event) {

    }
    
    void Searchcustomer(){
        
        customername.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        Balance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
//        Balance.setCellValueFactory(cellData ->cellData.getValue().getBalanceProperty().asObject());

        masterData = FXCollections.observableArrayList(cus.findAll());
        filteredData = new FilteredList<>(masterData, p -> true);
        customertable.setItems(filteredData);  
    }
    
    @FXML
    void movedown(KeyEvent event) {
        if (searchbox.getText().isEmpty()) {
                Showitemform(event);
            }
       else if (event.getCode() == KeyCode.DOWN) {
        // Code to be executed when the Down arrow key is pressed
        
        showitem.requestFocus();
            if (!showitem.getItems().isEmpty()) {
    showitem.getSelectionModel().selectFirst();
                System.out.println("y");
}
    }
         
         event.consume();
        
        
    }

    @FXML
void addbysearch(ActionEvent event) {
   

        
            Optional<AddItem> code = item.findByItemCode(searchbox.getText());

            if (code.isPresent()) {
                AddItem newItem = code.get();
                itembycodeandname(newItem);
            } else {
                Optional<AddItem> items = item.findByProductname(searchbox.getText());

                if (items.isPresent()) {
                    AddItem newItem = items.get();
                    itembycodeandname(newItem);
                }
                else{
                searchbox.setText("");
            }
                 }
}
void tableccollection(){
    float totalQty = 0, amounts = 0 , netamouts = 0;

for (AddItem existingItem : purchasetable.getItems()) {
    totalQty += existingItem.getQty();
    amounts += existingItem.getAmount();
}
 int rowCount = purchasetable.getItems().size();
    totalrows.setText(rowCount+"");
    totalqty.setText(totalQty+"");
    totalamount.setText(amounts +"");
}
void calculatenetamount() {
    float amount = 0, discountinper = 0, discountinrup = 0, paidamounts = 0, netamouts = 0,previousbalance=0;

    if (!totalamount.getText().isEmpty()) {
        amount += Float.valueOf(totalamount.getText());
    }
    if (!discountinpercentage.getText().isEmpty()) {
        discountinper += Float.valueOf(discountinpercentage.getText());
        discountinper = amount * (discountinper) / 100;
    }
    if (!Discountinrupees.getText().isEmpty()) {
        discountinrup += Float.valueOf(Discountinrupees.getText());
    }
    if (!paidamount.getText().isEmpty()) {
        paidamounts += Float.valueOf(paidamount.getText());
    }

    netamount.setText(String.valueOf(amount - discountinper - discountinrup));

    if (!netamount.getText().isEmpty()) {
        netamouts += Float.valueOf(netamount.getText());
    }
    if (!customerbalance.getText().isEmpty()) {
        previousbalance += Float.valueOf(customerbalance.getText());
    }
    float balance = netamouts - paidamounts;
    balanceamount.setText(String.valueOf( previousbalance - (netamouts - paidamounts)));

}


void bind() {
  // Assuming customerbalance is a Text node
//Text customerbalance = new Text();
//
//// Set up the binding
//int a = 0;
//BooleanBinding isNegative = Bindings.createBooleanBinding(() -> {
//    try {
//        return Float.parseFloat(customerbalance.getText()) < a;
//    } catch (NumberFormatException e) {
//        // Handle the case when the text is not a valid float
//        return false; // or another default value
//    }
//}, customerbalance.textProperty());
//
//// Set up the listener
//isNegative.addListener((obs, oldValue, newValue) -> {
//    System.out.println("y");
//    Color textColor = newValue ? Color.RED : Color.GREEN;
//    customerbalance.setFill(textColor);
//});




        // Update label text and trigger the binding
        
        
        
        
    showitem.visibleProperty().bind(
        Bindings.when(
            searchbox.textProperty().isNotEmpty().or(showitem.focusedProperty())
        .and(
            searchbox.focusedProperty().or(showitem.focusedProperty())
        ))
        .then(true)
        .otherwise(false)
    );
    




    
    customertable.visibleProperty().bind(
        Bindings.when(
            autocompletecustomer.textProperty().isNotEmpty().or(customertable.focusedProperty())
        .and(
            autocompletecustomer.focusedProperty().or(customertable.focusedProperty())
        ))
        .then(true)
        .otherwise(false)
    );
    
   buttonsbox.visibleProperty().bind(
                showbutton.focusedProperty().or(buttonsbox.focusedProperty())
        );

        buttonsbox.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    // Function to call when visible
                    showBox();
                } else {
                    // Function to call when not visible
                    hideBox();
                }
            }
        });
}

   void showitemintable(){
//    Itemname.setCellValueFactory(new PropertyValueFactory<>("Productname"));
//        Itemprice.setCellValueFactory(new PropertyValueFactory<>("Item_price"));
//        Stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
//        masterItemData.clear();
//        masterItemData = FXCollections.observableArrayList(item.findAll());
//    filteredItemData = new FilteredList<>(masterItemData, p -> true);
//        showitem.setItems(filteredItemData);
        
    } 
   public void itembycodeandname(AddItem newItem){
       
       
    newItem.setQty(1.0f);
    float amounts = 1 * newItem.getItem_price();
    newItem.setAmount(amounts);
    int rowCount = purchasetable.getItems().size();
        newItem.setRownumber(rowCount);

       boolean itemExists = false;
    // Check if the item already exists in the table
    for (AddItem existingItem : purchasetable.getItems()) {
        if (existingItem.getItemCode().equals(newItem.getItemCode())
                || existingItem.getProductname().equals(newItem.getProductname())) {
            // Increment the quantity by one for the existing item
            existingItem.setQty(existingItem.getQty() + 1);
            existingItem.setAmount(existingItem.getAmount() + newItem.getAmount());
            itemExists = true;
            System.out.println(existingItem.getQty());
            purchasetable.refresh();
            int rowIndex = purchasetable.getItems().indexOf(existingItem);
        purchasetable.getSelectionModel().select(rowIndex);
        purchasetable.scrollTo(rowIndex);
            Optional<Purchase> existingSummary = finditemwithinvoice(purchasetable.getSelectionModel().getSelectedItem());
            if (updateqty(existingSummary , purchasetable.getSelectionModel().getSelectedItem(),Float.valueOf("1") ,"QUANTITY")) {
                System.out.println("Update Item");
            }else{
                System.out.println("Not Update");
            }
            break;
        }
    }

    if (!itemExists) {
        pno.setCellValueFactory(new PropertyValueFactory<>("rownumber"));
        code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        name.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price.setCellValueFactory(new PropertyValueFactory<>("Purchase_price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        if (newItem != null) {
    // Add the selected item to the table's data
    purchasetable.getItems().add(newItem);
     purchasetable.getSelectionModel().selectLast();
            purchasetable.scrollTo(purchasetable.getItems().size() - 1);
            
            if (saveitemrow()) {
                System.out.println("Data Save Sucessfully");
            }else{
                System.out.println("Selected Row has Issue"); 
            }
          
   }
    }
    tableccollection();
    calculatenetamount();
    Platform.runLater(() -> {
       
    searchbox.setText("");
    searchbox.requestFocus();
    
    
});
   }
   
   @FXML
    void showinpurchasetable(MouseEvent event) {
        AddItem selectedItem = showitem.getSelectionModel().getSelectedItem();
       itembycodeandname(selectedItem);
    }
    
    @FXML
    void showinpurchasetables(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            // Enter key logic
            AddItem selectedItem = showitem.getSelectionModel().getSelectedItem();
            itembycodeandname(selectedItem);
        }
        else if (event.getCode().isLetterKey() && event.getText().length() == 1) {
    char enteredChar = event.getText().charAt(0);
    if ((enteredChar >= 'A' && enteredChar <= 'Z') || (enteredChar >= 'a' && enteredChar <= 'z')) {
        StringBuilder currentText = new StringBuilder(searchbox.getText());
        currentText.append(enteredChar);
        searchbox.setText(currentText.toString());
    }
    }else if (event.getCode() == KeyCode.BACK_SPACE) {
    int currentLength = searchbox.getLength();
    if (currentLength > 0) {
        // Delete the last character
        searchbox.deleteText(currentLength - 1, currentLength);
        if (currentLength - 1 ==0) {
                searchbox.requestFocus();
            }
    }
    else{
        searchbox.requestFocus();
    }
    
            
}
  

    }

    @FXML
    void descriptionrowsize(MouseEvent event) {
        Description.setPrefRowCount(10);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Implement this converter
        masterItemData = FXCollections.observableArrayList();    
        Searchcustomer();
        bind();
        showAllItems();
//        showitemintable();
        autocompletecustomer.requestFocus();
        billdate.setValue(LocalDate.now());
        Long nextInvoiceNumber = sequenceService.getNextInvoiceNumber();
        System.out.println(nextInvoiceNumber);
        Billno.setText("INV-" + nextInvoiceNumber);
        
         rootPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (null != event.getCode() && !purchasetable.getItems().isEmpty()) {
                switch (event.getCode()) {
                    case F1:
                        // F1 key pressed, change quantity
                        change(ChangeType.QUANTITY);
                        event.consume(); // Consume the event to prevent further handling
                        break;
                    case F2:
                        // F2 key pressed, change price
                        change(ChangeType.PRICE);
                        event.consume(); // Consume the event to prevent further handling
                        break;
                    case F3:
                        // F3 key pressed, change amount
                        change(ChangeType.AMOUNT);
                        event.consume(); // Consume the event to prevent further handling
                        break;
                    default:
                        break;
                }
            }
        });
     
       
      
        // Set up an event filter for key events
        
//        SearchItem();
//       showitem.setVisible(false);
//        if (ItemtableView != null) {
//        tableonclick();
//    }
    }

    @FXML
     void filterCustomer(KeyEvent event) {
        
        String filter = autocompletecustomer.getText(); 
        if (filter == null || filter.isEmpty()) {
            filteredData.setPredicate(null);
        } else {
            String lowerCaseFilter = filter.toLowerCase();
            filteredData.setPredicate(addCustomer -> {
                // Check if item name or code matches the filter
                if (addCustomer.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return addCustomer.getCustomercode().toLowerCase().contains(lowerCaseFilter);
            });
        }
    }
    
    
//    @FXML
//    void filterdata(KeyEvent event) {
//        
//        String filter = searchbox.getText(); 
//        if (filter == null || filter.isEmpty()) {
//            filteredItemData.setPredicate(null);
//        } else {
//            String lowerCaseFilter = filter.toLowerCase();
//
//            filteredItemData.setPredicate(addItem -> {
//                // Check if item name or code matches the filter
//                if (addItem.getProductname().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else return addItem.getItemCode().toLowerCase().contains(lowerCaseFilter);
//            });
//        }
//    }
     @FXML
void filterdata(KeyEvent event) {
    String filter = searchbox.getText();

    if (filter == null || filter.isEmpty()) {
        // No filter, display all items
        showAllItems();
    } else if(!searchbox.getText().isEmpty() && event.getCode() != KeyCode.DOWN) {
        // Trigger real-time search based on the entered text
        updateFilteredDataFromDatabase(filter);
    }
}
private void updateFilteredDataFromDatabase(String filter) {
    // Implement the logic to query the database for items matching the filter
    // Use your item repository (itemRepository) or service for this purpose
    List<AddItem> filteredItemsFromDatabase = fetchItemsFromDatabase(filter);

    // Update the filteredItemData with the fetched items
    masterItemData.clear();
    masterItemData.addAll(filteredItemsFromDatabase);
    filteredItemData = new FilteredList<>(masterItemData, p -> true);

    // Set the filtered data to the table
    showitem.setItems(filteredItemData);
}

private List<AddItem> fetchItemsFromDatabase(String filter) {
    // Implement the logic to query the database for items based on the filter
    // Use your item repository (itemRepository) or service for this purpose
    // For example:
    return item.findByProductnameContainingIgnoreCaseOrItemCodeContainingIgnoreCase(filter, filter);
}


private void showAllItems() {
    // Display all items in the table
    Itemname.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        Itemprice.setCellValueFactory(new PropertyValueFactory<>("Purchase_price"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
    masterItemData = FXCollections.observableArrayList(item.findAll());
    filteredItemData = new FilteredList<>(masterItemData, p -> true);
    showitem.setItems(filteredItemData);
}
    // AddCustomerConverter implementation goes here
    
    void change(ChangeType changeType){
        AddItem selectedItem = purchasetable.getSelectionModel().getSelectedItem();
    if (selectedItem != null) { // Corrected the condition

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/change.fxml"));
            Parent root = loader.load();
            float previousvalue=0;
            // In your calling code
            ChangeController controller = loader.getController();
            FloatProperty sharedQtyProperty = sharedDataModel.sharedQtyProperty();
            
            controller.setSharedProperty(sharedQtyProperty, changeType);
            controller.setSelectedItem(selectedItem);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            if (null != changeType) switch (changeType) {
                case QUANTITY:
                    stage.setTitle("Change Quantity");
                    controller.changelabel.setText("CHANGE QTY");
                    controller.valuelabel.setText("Qty");
                     previousvalue = selectedItem.getQty();
                    break;
                case AMOUNT:
                    stage.setTitle("Change Amount");
                    controller.changelabel.setText("CHANGE AMOUNT");
                    controller.valuelabel.setText("Amount");
                    previousvalue = selectedItem.getAmount();
                    break;
                case PRICE:
                    stage.setTitle("Change Price");
                    controller.changelabel.setText("CHANGE PRICE");
                    controller.valuelabel.setText("Price");
                    previousvalue = selectedItem.getPurchase_price();
                    break;
                default:
                    break;
            }
            
            stage.setScene(new Scene(root));

            // Show the dialog
            stage.showAndWait();
            purchasetable.refresh();
           tableccollection();
           calculatenetamount();
           checkbuttontypeandupdate(changeType , selectedItem,previousvalue);
            searchbox.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    public void checkbuttontypeandupdate(ChangeType changeType,AddItem selectedItem, float previousvalue ){
        Optional<Purchase> existingSummary;
        if (null != changeType) switch (changeType) {
                case QUANTITY:
                  float newqty = selectedItem.getQty();  
           existingSummary = finditemwithinvoice(purchasetable.getSelectionModel().getSelectedItem());
            if (updateqty(existingSummary , selectedItem , newqty - previousvalue , "QUANTITY")) {
                System.out.println("Update");
            }else{
                System.out.println("Not Updated");
            }
                    break;
                case AMOUNT:
                    float newamount = selectedItem.getQty();  
            existingSummary = finditemwithinvoice(purchasetable.getSelectionModel().getSelectedItem());
            if (updateqty(existingSummary , selectedItem , selectedItem.getQty() , "AMOUNT")) {
                System.out.println("Update");
            }else{
                System.out.println("Not Updated");
            }
                    break;
                case PRICE:
                    float newprice = selectedItem.getPurchase_price();  
           existingSummary = finditemwithinvoice(purchasetable.getSelectionModel().getSelectedItem());
            if (updateqty(existingSummary , selectedItem , newprice - previousvalue , "PRICE")) {
                System.out.println("Update");
            }else{
                System.out.println("Not Updated");
            }
                    break;
                default:
                    break;
            }
    }
    @FXML
private void changeqty(ActionEvent event) {
        change(ChangeType.QUANTITY);
}

    
    @FXML
    void showbuttons(MouseEvent event) {
        //buttonsbox.setMinHeight(200);
        showbutton.requestFocus();
    }
    
   private void toggleBox() {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), buttonsbox);
    TranslateTransition showButtonTransition = new TranslateTransition(Duration.seconds(1), showbutton);

    if (!isBoxVisible) {
        // If box is invisible, move it downwards to appear
        translateTransition.setToY(0);
//        showButtonTransition.setToY(0);
    } else {
        // If box is visible, move it upwards to disappear
        translateTransition.setToY(-buttonsbox.getHeight());
//        showButtonTransition.setToY(-buttonsbox.getHeight());
    }

    // Toggle the visibility state for the next click
    isBoxVisible = !isBoxVisible;
//    buttonsbox.setVisible(!buttonsbox.isVisible());
    // Create a parallel transition to animate both nodes
    ParallelTransition parallelTransition = new ParallelTransition(translateTransition, showButtonTransition);

    // Start the parallel transition
    parallelTransition.play();
}

    
    private void showBox() {
        // If box is invisible, move it downwards to appear
        if (!isBoxVisible) {
            toggleBox();
        }
    }

    private void hideBox() {
        // If box is visible, move it upwards to disappear
        if (isBoxVisible) {
            toggleBox();
        }
    }
    
    @FXML
    void calculatebalance(KeyEvent event) {
        calculatenetamount();
    }
    void focusfields(TextField t){
        t.requestFocus();
        t.selectEnd();
    }
     @FXML
    void enterincustomerfield(MouseEvent event) {
         focusfields(autocompletecustomer);
    }
    
    @FXML
    void showtableitem(MouseEvent event) {
        focusfields(searchbox);
    }
    
    @FXML
    void showuser(MouseEvent event) {
        // billdate.requestFocus();
        String customerName = autocompletecustomer.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> cust = cus.findByCustomerName(customerName);
        
if (cust.isPresent() && save.isVisible() && !autocompletecustomer.getText().isEmpty()) {
    // Assuming product names are unique and you only expect one result
    AddCustomer firstItem = cust.get();
    System.out.println(firstItem.getBalanceProperty().get());
                customerbalance.setText(firstItem.getBalance()+"");
                calculatenetamount();
                searchbox.requestFocus();
    }
    } 
    
    @FXML
    void showcustomername(MouseEvent event) {
        AddCustomer selectedcustomer = customertable.getSelectionModel().getSelectedItem();
        if (selectedcustomer !=null) {
          
            autocompletecustomer.setText(selectedcustomer.getCustomerName());
            customerbalance.setText(   selectedcustomer.getBalance() +"");
            calculatenetamount();
            searchbox.requestFocus();
        }
    }    
    
     @FXML
    void Showcustomerform(KeyEvent event) {
        System.out.println("Z");
        if (autocompletecustomer.getText().equals("")) {
            System.out.println("x");
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("w");
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowbulkCustomerbycourse.fxml"));
            Parent root = loader.load();
            ShowbulkCustomerbycourseController controller = loader.getController();
            controller.loaddata(cus);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
             stage.setScene(new Scene(root));

            // Show the dialog
            stage.showAndWait();
//            purchasetable.refresh();
           
            searchbox.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
            }
        }
    }
    
    void Showitemform(KeyEvent event) {
        System.out.println("Z");
        if (searchbox.getText().equals("")) {
            System.out.println("x");
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("w");
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowitemDetails.fxml"));
            Parent root = loader.load();
            ShowitemDetailsController controller = loader.getController();
            controller.loaddata(item);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
             stage.setScene(new Scene(root));

            // Show the dialog
            stage.showAndWait();
//            purchasetable.refresh();
           
            searchbox.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
            }
        }
    }
    
}
