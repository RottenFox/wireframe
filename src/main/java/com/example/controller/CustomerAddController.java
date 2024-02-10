 
/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.Entity.AddCustomer;
import com.example.Repository.Cusrep;
import com.example.Sharedclasses.SharedItemService;
import com.example.otherclasses.bindings;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ButtonType;
import org.springframework.transaction.annotation.Transactional;

/**
 * FXML Controller class
 *
 * @author wwwsa
 */
@Controller
public class CustomerAddController implements Initializable {

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
    private Tab personal,address,credit,extra;
    
    @FXML
    private TextField customerCode,ad1,ad2,ad3,ad4,partyName,phoneNumber,openingBalance,emailId,creditLimit
            ,val1,val2,val3,cnic,nationality,f_hname;
    @FXML
    private Button personalTab,addressTab,creditTab,extraTab,tglB1,tglB2,tglB3,switchShipAdd,addButton,saveandnew,save,delete,update;
    @FXML
    private ComboBox<?> ad41;
    @FXML
    private CheckBox chkBox1,chkBox2,chkBox3,chkBox4;
    @FXML
    private Label closeButton, settingButton,ID,addcustomer;
    @FXML
    private Label promptLabel1,promptLabel2,
            promptLabel11,promptLabel12,promptLabel13,promptLabel14,
            promptLabelf_hname,promptLabelnationality,promptLabelcnic,
            promptLabelOB,promptLabelAD,promptLabelCrL,
            promptLabelad1,promptLabelad2,promptLabelad3,promptLabelad4,promptLabelad1a,promptLabelad2a,promptLabelad3a,promptLabelad4a;
    @FXML
    private TabPane fieldTabs;
    @FXML
    private DatePicker asOfDate,datePicker4;
    @FXML
    private Pane bar;
    @FXML
    private TextArea billAddress,shipAddress;
    @FXML
    private GridPane addCustomerPane;
    
    @FXML
    void closeOverlay(MouseEvent event) {
        dashboardController.setOverlayPaneInvisible();
    }

    @FXML
    void openSetting(MouseEvent event) {

    }

    void firstSwitch(){
            fieldTabs.getSelectionModel().select(address);
            addressTab.setStyle("-fx-border-color: blue; -fx-text-fill: blue;");
            creditTab.setStyle("-fx-border-color: transparent;  -fx-text-fill: #bdbdbd;");
            extraTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;" );
            personalTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            
            bar.setVisible(false);
            shipAddress.setVisible(false);
            addButton.setVisible(false);
            switchShipAdd.setText("+ Enable Shipping Address");
            switchShipAdd.setStyle(" -fx-text-fill: #3498db;");

            
            
    }
    @FXML
    void switchAddress(ActionEvent event) {
            //switch code here
            firstSwitch();
            
    }
    
    @FXML
    void switchPersonal(ActionEvent event) {
        fieldTabs.getSelectionModel().select(personal);
            personalTab.setStyle("-fx-border-color: blue; -fx-text-fill: blue;");
            addressTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            extraTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            creditTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
    }

    @FXML
    void switchCredit(ActionEvent event) {
            //switch code here
            fieldTabs.getSelectionModel().select(credit);
            creditTab.setStyle("-fx-border-color: blue; -fx-text-fill: blue;");
            addressTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            extraTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            personalTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
             
    }

    @FXML
    void switchExtra(ActionEvent event) {
           //switch code here
           fieldTabs.getSelectionModel().select(extra);
            extraTab.setStyle("-fx-border-color: blue; -fx-text-fill: blue;");
            addressTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            creditTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
            personalTab.setStyle("-fx-border-color: transparent; -fx-text-fill: #bdbdbd;");
    }
    
    @FXML
    void switchTextArea(ActionEvent event) {
           if(bar.isVisible()){
               bar.setVisible(false);
               shipAddress.setVisible(false);
               addButton.setVisible(false);
               switchShipAdd.setText("+ Enable Shipping Address");
               switchShipAdd.setStyle(" -fx-text-fill: #3498db;");
           }else{
               bar.setVisible(true);
               shipAddress.setVisible(true);
               addButton.setVisible(true);
               switchShipAdd.setText("+ Disable Shipping Address");
               switchShipAdd.setStyle(" -fx-text-fill: #bdbdbd;");
           }
    }
    @FXML
    void toggleLimit(ActionEvent event) {
        if(creditLimit.isVisible()){
            tglB1.setStyle("-fx-text-fill: #3498db;");
            tglB3.setStyle("-fx-text-fill: #bdbdbd;");
            tglB2.setAlignment(Pos.BASELINE_LEFT);
            creditLimit.setVisible(false);
        }else{
            tglB1.setStyle("-fx-text-fill: #bdbdbd;");
            tglB3.setStyle("-fx-text-fill: #3498db;");
            tglB2.setAlignment(Pos.BASELINE_RIGHT);
            creditLimit.setVisible(true);
        }
    }
    
        @FXML
    void enable1(ActionEvent event) {
        ad1.setEditable(!ad1.isEditable());
        val1.setVisible(!val1.isVisible());
    }

    @FXML
    void enable2(ActionEvent event) {
        ad2.setEditable(!ad2.isEditable());
        val2.setVisible(!val2.isVisible());
    }

    @FXML
    void enable3(ActionEvent event) {
        ad3.setEditable(!ad3.isEditable());
        val3.setVisible(!val3.isVisible());
    }

    @FXML
    void enable4(ActionEvent event) {
        ad4.setEditable(!ad4.isEditable());
        datePicker4.setVisible(!datePicker4.isVisible());
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
//    @Transactional(rollbackFor = Exception.class)
    public boolean  Save(){
        String customerName = partyName.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> cust = cus.findByCustomerName(customerName);
        
        String customercode = partyName.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> custcode = cus.findByCustomercode(customercode);
        
        if(!phone() && !phoneNumber.getText().isEmpty()){
            showAlert("Invalid Phone Number", "Please enter valid Phone Number!!");
            phoneNumber.requestFocus();
            return false;
        }
        else if (!isValidEmail(emailId.getText()) && !emailId.getText().isEmpty()){
            showAlert("Invalid Email Address", "Please enter valid Email Address!!");
            emailId.requestFocus();
            return false;
        }
        else if (partyName.getText().isEmpty()) {
            showAlert("Input is empty", "Please enter Item Name");
            partyName.requestFocus();
            return false;
        }else if(cust.isPresent() && !partyName.getText().isEmpty()){
            showAlert("Already Exist", "The Customer Name Already Exist");
            partyName.requestFocus();
            return false;
        }
        else if(custcode.isPresent() && !customerCode.getText().isEmpty()){
            showAlert("Already Exist", "The Customer Code Already Exist");
            customerCode.requestFocus();
            return false;
        }else{
        try{
            
        AddCustomer a = new AddCustomer();
            
            if (update.isVisible() && !ID.getText().isEmpty()) {
            // Update existing customer
            int customerId = Integer.parseInt(ID.getText());
            //a.setCustomerId(Integer.valueOf(ID.getText()));
            a = cus.findById(customerId)
                          .orElseThrow(() -> new Exception("Customer not found"));
        } else {
            // Create new customer
            a = new AddCustomer();
        }
    a.setCustomercode(Optional.ofNullable(customerCode.getText()).orElse("DefaultCode"));
    a.setCustomerName(Optional.ofNullable(partyName.getText()).orElse("DefaultName"));
    a.setEmail(Optional.ofNullable(emailId.getText()).orElse("DefaultName"));
    a.setOpenbalance(tostring(openingBalance.getText()));
    a.setCreditlimit(tostring(creditLimit.getText()));
    a.setCustomerBalance(0.0f);
    a.setAddress(Optional.ofNullable(billAddress.getText()).orElse("DefaultName"));
    a.setShippingAddress(Optional.ofNullable(shipAddress.getText()).orElse("DefaultName"));
    a.setAditionalnamefield1(Optional.ofNullable(ad1.getText()).orElse("DefaultName"));
    a.setAditionalfield1(Optional.ofNullable(val1.getText()).orElse("DefaultName"));
    a.setAditionalnamefield2(Optional.ofNullable(ad2.getText()).orElse("DefaultName"));
    a.setAditionalfield2(Optional.ofNullable(val2.getText()).orElse("DefaultName"));
    a.setAditionalnamefield3(Optional.ofNullable(ad3.getText()).orElse("DefaultName"));
    a.setAditionalfield3(Optional.ofNullable(val3.getText()).orElse("DefaultName"));
    a.setAditionalnamefield4(Optional.ofNullable(ad4.getText()).orElse("DefaultName"));
    a.setFathername(Optional.ofNullable(f_hname.getText()).orElse("DefaultName"));
    a.setCnic(Optional.ofNullable(cnic.getText()).orElse("DefaultName"));
    a.setNationality(Optional.ofNullable(nationality.getText()).orElse("DefaultName"));
    if (datePicker4.getValue() == null) {
    a.setDate(LocalDate.now()); // Set to current date if DatePicker is empty
} else {
    a.setDate(datePicker4.getValue()); // Set to the selected date
}
    if (asOfDate.getValue() == null) {
    a.setOpenbalancedate(LocalDate.now()); // Set to current date if DatePicker is empty
} else {
    a.setDate(asOfDate.getValue()); // Set to the selected date
}
            cus.save(a);
            
            return true; 
        } catch (DataIntegrityViolationException e) {
        showAlert("Duplicate Entry", "An item with the same code or name already exists.");
        return false;
    } catch (Exception e) {
        System.out.println(e);
        showAlert("Error", "An error occurred while saving the item.");
        return false;
        
    }
    }
    }
    void clear(){
        customerCode.clear();ad1.clear();ad2.clear();ad3.clear();ad4.clear();partyName.clear();phoneNumber.clear();openingBalance.clear();emailId.clear();
        creditLimit.clear();val1.clear();val2.clear();val3.clear();
    }
    @FXML
    void save(ActionEvent event) {
        
         if (Save()) {  // Check if save() returns true
        // Code to execute if save is successful
        
        if (dashboardController != null) {
            dashboardController.setOverlayPaneInvisiblewithoutcheck();
            dashboardController.loadContent("/fxml/ShowCustomer.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
    }
    }

    @FXML
    void saveandnew(ActionEvent event) {
        if (Save()) {  // Check if save() returns true
        // Code to execute if save is successful
        clear();
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
    }
    }
    
    
    private boolean isEventTargetExcluded(MouseEvent event) {
    // Check if the target of the event is one of the excluded components
     Node target = (Node) event.getTarget();
     return isChild(target, addCustomerPane);
}
    
    @FXML
    void validatenumberinput(KeyEvent event){
        
       TextField sourceField = (TextField) event.getSource(); // Get the source TextField
       

        sourceField.getLayoutX();
        sourceField.getLayoutY();
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.isEmpty() || newText.matches("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?")) {
        // Regex to allow floating point numbers or an empty string
        return change;
    }
    return null;
};

TextFormatter<String> numericTextFormatter = new TextFormatter<>(numericFilter);
sourceField.setTextFormatter(numericTextFormatter);
    }
    @FXML
    void validatePhoneNumberInput(KeyEvent event) {
    TextField sourceField = (TextField) event.getSource(); // Get the source TextField

    UnaryOperator<TextFormatter.Change> phoneFilter = change -> {
        String newText = change.getControlNewText();
        // Regex to match a wide range of international phone numbers
        if (newText.isEmpty() || newText.matches("^[+]?[(]?\\d{1,4}[)]?([-\\s.]?\\d{1,4})*$")) {
            return change;
        }
        return null;
    };

    TextFormatter<String> phoneTextFormatter = new TextFormatter<>(phoneFilter);
    sourceField.setTextFormatter(phoneTextFormatter);
}
    boolean phone(){
        
        String phoneNumber = this.phoneNumber.getText();
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber numberProto = phoneUtil.parse(phoneNumber, null); // No default country
            boolean isValid = phoneUtil.isValidNumber(numberProto);
            
            if (isValid) {
               
                System.out.println("Phone number is valid.");
                // Optionally, get the region code (country) for the number
                
                String regionCode = phoneUtil.getRegionCodeForNumber(numberProto);
                
                System.out.println("Country code: " + regionCode);
                this.phoneNumber.setStyle(null);
                 return true;
            } else {
                System.out.println("Phone number is invalid.");
                this.phoneNumber.setStyle("-fx-border-color: red;");
                 return false;
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
            return false;
        }
        
    }
    @FXML
    void validphonenumber(MouseEvent event) {
        if(!phone() && !phoneNumber.getText().isEmpty()){
            showAlert("Invalid Phone Number", "Please enter valid Phone Number");
        }
    }
    public boolean isValidEmail(String email) {
        // Regular expression for a basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    @FXML
    void isselected(MouseEvent event) {
        TextField sourceField = (TextField) event.getSource();
        sourceField.selectAll();
    }
    
    
    private void bindLabels(){
        bindings b= new bindings();
        b.setupPromptLabelBindings(partyName, promptLabel1,"");
        b.setupPromptLabelBindings(phoneNumber, promptLabel2,"");
        b.setupPromptLabelBindings(emailId, promptLabel11,"");
        b.setupPromptLabelBindings(customerCode, promptLabel12,"");
        b.setupPromptLabelBindings(shipAddress, promptLabel14,"");
        b.setupPromptLabelBindings(billAddress, promptLabel13,"");
        b.setupPromptLabelBindings(f_hname, promptLabelf_hname,"");
        b.setupPromptLabelBindings(nationality, promptLabelnationality,"");
        b.setupPromptLabelBindings(cnic, promptLabelcnic,"");
        b.setupPromptLabelBindings(openingBalance, promptLabelOB,"");
        b.setupPromptLabelBindings(asOfDate,promptLabelAD,"");
        b.setupPromptLabelBindings(creditLimit, promptLabelCrL,"");
        b.setupPromptLabelBindings(ad1, promptLabelad1,"");
        b.setupPromptLabelBindings(ad2, promptLabelad2,"");
        b.setupPromptLabelBindings(ad3, promptLabelad3,"");
        b.setupPromptLabelBindings(ad4, promptLabelad4,"");
        //b.setupPromptLabelBindings(val1, promptLabelad1a, "Enter Value of");
        //b.setupPromptLabelBindings(val2, promptLabelad2a, "Enter Value of");
        //b.setupPromptLabelBindings(val3, promptLabelad3a, "Enter Value of" );
        b.setupPromptLabelBindings(datePicker4, promptLabelad4a, datePicker4.getPromptText());
        b.additionalFieldBind(ad1, val1,promptLabelad1a);
        b.additionalFieldBind(ad2, val2,promptLabelad2a);
        b.additionalFieldBind(ad3, val3,promptLabelad3a);
       // b.additionalFieldBind(ad4, datePicker4);
    
    
    
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
public void showitem(){
    sharedItemService.selectedEntityProperty(AddCustomer.class).addListener((obs, oldEntity, newEntity) -> {
            if (newEntity != null) {
                partyName.setText(newEntity.getCustomerName());
                customerCode.setText(newEntity.getCustomercode()+"");
//                ItemCategory.setValue(firstItem.getItem_code());
                phoneNumber.setText(newEntity.getPhoneumber());
                emailId.setText(newEntity.getEmail()+"");
                billAddress.setText(newEntity.getAddress()+"");
                shipAddress.setText(newEntity.getShippingAddress()+"");
                openingBalance.setText(newEntity.getOpenbalance()+"");
                ad1.setText(newEntity.getAditionalnamefield1()+"");
                ad2.setText(newEntity.getAditionalnamefield2()+"");
                ad3.setText(newEntity.getAditionalnamefield3()+"");
                ad4.setText(newEntity.getAditionalnamefield4());
                val1.setText(newEntity.getAditionalfield1()+"");
                val2.setText(newEntity.getAditionalfield2()+"");
                val3.setText(newEntity.getAditionalfield3());
                f_hname.setText(newEntity.getFathername());
                cnic.setText(newEntity.getCnic());
                nationality.setText(newEntity.getNationality());
                datePicker4.setValue(newEntity.getDate());
                asOfDate.setValue(newEntity.getOpenbalancedate());
                ID.setText(newEntity.getCustomerId()+"");
    update.setVisible(true);
                update.setPrefWidth(124);
                 delete.setVisible(true);
                delete.setPrefWidth(124);
                save.setVisible(false);
                saveandnew.setVisible(false);
                addcustomer.setText("EDIT CUSTOMER");
                // Handle other fields similarly
            }
        });
}
@FXML
void deleteCustomer(MouseEvent event) {
        if (showConfirmationDialog("Confirm Action", "Are you sure you want to Delete that Customer ?")) {
        cus.deleteById(Integer.valueOf(ID.getText()));
        if (dashboardController != null) {
            dashboardController.loadContent("/fxml/ShowCustomer.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Delete operation failed.");
    }
        
    }
@FXML
void fetchCustomer(MouseEvent event) {
        
     String customerName = partyName.getText(); // Assuming Itemname is a TextField for the product name input
        Optional<AddCustomer> cust = cus.findByCustomerName(customerName);
        
if (cust.isPresent() && save.isVisible() && !partyName.getText().isEmpty()) {
    // Assuming product names are unique and you only expect one result
    if (showConfirmationDialog("Confirm Action", "Are you sure you want to Edit The Customer Having Same Name?")) {
    AddCustomer firstItem = cust.get();

                partyName.setText(firstItem.getCustomerName());
                customerCode.setText(firstItem.getCustomercode()+"");
//                ItemCategory.setValue(firstItem.getItem_code());
                phoneNumber.setText(firstItem.getPhoneumber());
                emailId.setText(firstItem.getEmail()+"");
                billAddress.setText(firstItem.getAddress()+"");
                shipAddress.setText(firstItem.getShippingAddress()+"");
                openingBalance.setText(firstItem.getOpenbalance()+"");
                ad1.setText(firstItem.getAditionalnamefield1()+"");
                ad2.setText(firstItem.getAditionalnamefield2()+"");
                ad3.setText(firstItem.getAditionalnamefield3()+"");
                ad4.setText(firstItem.getAditionalnamefield4());
                val1.setText(firstItem.getAditionalfield1()+"");
                val2.setText(firstItem.getAditionalfield2()+"");
                val3.setText(firstItem.getAditionalfield3());
                f_hname.setText(firstItem.getFathername());
                cnic.setText(firstItem.getCnic());
                nationality.setText(firstItem.getNationality());
                datePicker4.setValue(firstItem.getDate());
                asOfDate.setValue(firstItem.getOpenbalancedate());
                ID.setText(firstItem.getCustomerId()+"");
    update.setVisible(true);
                update.setPrefWidth(124);
                 delete.setVisible(true);
                delete.setPrefWidth(124);
                save.setVisible(false);
                saveandnew.setVisible(false);
                addcustomer.setText("EDIT CUSTOMER");
    }
}
    else if(cust.isEmpty() && update.isVisible() && !partyName.getText().isEmpty()) {
        
    if (showConfirmationDialog("Confirm Action", "Are you sure you want Add New Item?")) {
     // Replace 'getItemCode' with your actual getter method for item_code
    // Set the item code in the TextField
    customerCode.setText("");
    update.setVisible(false);
                update.setPrefWidth(0);
                delete.setVisible(false);
                delete.setPrefWidth(0);
                save.setVisible(true);
                saveandnew.setVisible(true);
                addcustomer.setText("ADD ITEM");
    }
}
                

    }
@FXML
void updatecustomer(MouseEvent event) {
        if (Save()) {  // Check if save() returns true
        // Code to execute if save is successful
        
        if (dashboardController != null) {
            dashboardController.setOverlayPaneInvisiblewithoutcheck();
            dashboardController.loadContent("/fxml/ShowCustomer.fxml");
        }
    } else {
        // Code to execute if save fails, if any
        System.out.println("Save operation failed.");
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bindLabels();
        firstSwitch();
        showitem();
        val1.setVisible(false);
        val2.setVisible(false);
        val3.setVisible(false);
        datePicker4.setVisible(false);
        
        
        
    dashboardController.overlayPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
    // Check if the event target is not one of the excluded elements
    if (!isEventTargetExcluded(event)) {
        
        dashboardController.setOverlayPaneInvisible();
    }
});
        
    }  
}