/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import com.example.BasicApplication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author wwwsa
 */

@Controller
public class QuickBillingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Tab defaultTab;

    @FXML
    private Button newTab;

    @FXML
    private TabPane parentTabPane;
    
     @FXML
    void createTab(ActionEvent event) throws IOException {
      newTabCreation();
    }
  public void newTabCreation() {
        try {
            // Get the Spring application context
            ConfigurableApplicationContext context = BasicApplication.getApplicationContext();

            // Load the FXML file using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/posTab.fxml"));

            // Set the controller factory so that Spring manages the controllers
            loader.setControllerFactory(context::getBean);

            // Load the content from the FXML file
            Node content = loader.load();

            // Get the Tab from the content's parent
            Tab newTab = new Tab();
            newTab.setContent(content);

            // Add the new tab to the parentTabPane
            parentTabPane.getTabs().add(newTab);

            // Select the newly added tab
            parentTabPane.getSelectionModel().select(newTab);
        } catch (IOException e) {
            // Handle the exception (e.g., log it or show an error dialog)
            e.printStackTrace();
        }
    }



//    public void loadContent(String fxmlFile) {
//    try {
//        ConfigurableApplicationContext context = BasicApplication.getApplicationContext();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//
//        // Set the controller factory so that Spring manages the controllers
//        loader.setControllerFactory(context::getBean);
//
//        Node content = loader.load();
//        stackerPane.getChildren().add(content);
//    } catch (IOException e) {
//        e.printStackTrace();
//        // Optionally, you can show an error alert here if the FXML file fails to load
//        showAlert("Error", "Could not load the view: " + fxmlFile, Alert.AlertType.ERROR);
//    }
//}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newTabCreation();
    }    
    
}
