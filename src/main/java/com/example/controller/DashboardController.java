/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.BasicApplication;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
    private AddItemController addItemController;
    
    @Autowired
    private CustomerAddController customercontroller;
    
     @FXML
    private Pane bizContainer;

    @FXML
    private Pane bizContainer1;

    @FXML
    private Pane bizContainer2;

    @FXML
    private Pane bizContainer3;

    @FXML
    private Pane bizContainer4;

    @FXML
    private Pane bizContainer5;

    @FXML
    private Pane bizContainer6;

    @FXML
    private Pane bizContainer7;

    @FXML
    private VBox leftSidePanel,salepane,purchasepane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Pane quickAccess;

    @FXML
    private StackPane stackerPane;
    
    @FXML
    private Button addCustomerButon;
    
    
    @FXML
    private HBox statusBar;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    
    @FXML
    private Button addsale,paymentin;
    
    @FXML
    public StackPane overlayPane,rootpanr;
    

    @FXML
    void OpenExpensesHome(MouseEvent event) {

    }

    @FXML
    void openHome(MouseEvent event) {

    }

    @FXML
    void openItemHome(MouseEvent event) {
        loadContent("/fxml/ShowItem.fxml");
    }

    @FXML
    void openPOS(MouseEvent event) {
            loadOverlayContent("/fxml/quickBilling.fxml");
    }

    @FXML
    void openPurchaseHome(MouseEvent event) {
        if (purchasepane.getPrefHeight() ==0 && purchasepane.getPrefWidth()==0) {
            purchasepane.setPrefSize(288, 200);
           
        }else{
            purchasepane.setPrefSize(0, 0);
           
        }
        loadContent("/fxml/Showpurchase.fxml");
    }

    @FXML
    void openReportsHome(MouseEvent event) {

    }
    
    void setOverlayPaneVisible(){
        overlayPane.setVisible(true);
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
    void setOverlayPaneInvisiblewithoutcheck() {
        if (!overlayPane.getChildren().isEmpty()) {
        overlayPane.getChildren().remove(0);
    }
    overlayPane.setVisible(overlayPane.getChildren().size() > 0);
    }
    void setOverlayPaneInvisible() {
        if (showConfirmationDialog("Confirm Action", "Are you sure you want to close this form")) {
            if (!overlayPane.getChildren().isEmpty()) {
        overlayPane.getChildren().remove(0);
    }
    overlayPane.setVisible(overlayPane.getChildren().size() > 0);
        }// Set visible only if there are children
}

    @FXML
    void openSaleHome(MouseEvent event) {
        if (salepane.getPrefHeight() ==0 && salepane.getPrefWidth()==0) {
            salepane.setPrefSize(288, 250);
//            
        }else{
            salepane.setPrefSize(0, 0);
//           
        }
    }
    
   @FXML
    void openCustomerOL(ActionEvent event) {
        loadOverlayContent("/fxml/customerAdd.fxml");
    }
    
    @FXML
    void openadditem(MouseEvent event) {
        
        loadContent("/fxml/addItem.fxml");
    }

    @FXML
    void openSettings(MouseEvent event) {

    }
    @FXML
    void openShowCustomer(MouseEvent event) {
        loadContent("/fxml/ShowCustomer.fxml");
    }
    
    @FXML
    void opensale(MouseEvent event) {
        loadContent("/fxml/Add_Sale.fxml");
    }
    
    @FXML
    void openpurchase(MouseEvent event) {
        loadOverlayContent("/fxml/Adpurchase.fxml");
    }
    
      public void loadContent(String fxmlFile) {
    try {
        ConfigurableApplicationContext context = BasicApplication.getApplicationContext();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        // Set the controller factory so that Spring manages the controllers
        loader.setControllerFactory(context::getBean);

        Node content = loader.load();
        stackerPane.getChildren().clear();
        stackerPane.getChildren().add(content);
    } catch (IOException e) {
        e.printStackTrace();
        // Optionally, you can show an error alert here if the FXML file fails to load
        showAlert("Error", "Could not load the view: " + fxmlFile, Alert.AlertType.ERROR);
    }
}
      public void loadOverlayContent(String fxmlFile) {
    try {
        ConfigurableApplicationContext context = BasicApplication.getApplicationContext();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        // Set the controller factory so that Spring manages the controllers
        loader.setControllerFactory(context::getBean);

        Node content = loader.load();
        overlayPane.getChildren().clear();
        overlayPane.getChildren().add(content);
        setOverlayPaneVisible();
    } catch (IOException e) {
        e.printStackTrace();
        // Optionally, you can show an error alert here if the FXML file fails to load
        showAlert("Error", "Could not load the view: " + fxmlFile, Alert.AlertType.ERROR);
    }
}

      private void showAlert(String title, String content, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        rootpanr.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//    // Check if the event target is not one of the excluded elements
//    if (!isEventTargetExcluded(event)) {
//       setOverlayPaneInvisible();
//    }
//    });
                }
    

}
