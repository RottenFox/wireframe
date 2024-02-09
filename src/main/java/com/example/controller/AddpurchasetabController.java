package com.example.controller;

import com.example.BasicApplication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * Controller for handling dynamic content loading into a TabPane.
 */
@Controller
public class AddpurchasetabController implements Initializable {

    @FXML
    private TabPane showpurchase;

    /**
     * Load content from the specified FXML file into a new Tab and add it to the TabPane.
     *
     * @param contentFXML The FXML file path for the content to load.
     */
    public void newTabCreation() {
    try {
        // Get the Spring application context
        ConfigurableApplicationContext context = BasicApplication.getApplicationContext();

        // Load the FXML file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Adpurchase.fxml"));

        // Set the controller factory so that Spring manages the controllers
        loader.setControllerFactory(context::getBean);

        // Load the content from the FXML file
        Node content = loader.load();

        // Create a new tab and set its content
        Tab newTab = new Tab("ADD PURCHASE");
        newTab.setContent(content);

        // Add the new tab to the parentTabPane
        showpurchase.getTabs().add(newTab);

        // Select the newly added tab
        showpurchase.getSelectionModel().select(newTab);
    } catch (IOException e) {
        // Handle the exception (e.g., log it or show an error dialog)
        e.printStackTrace();
    }
}
    
    @FXML
    void addtab(ActionEvent event) {
       newTabCreation();
    }
    
    /**
     * Show an error message if there's an issue loading content.
     *
     * @param exception The exception that occurred.
     */
    private void showLoadContentError(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Content Loading Error");
        alert.setContentText("An error occurred while loading content. Please try again.");

        alert.showAndWait();
        exception.printStackTrace();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic, if needed

        newTabCreation();
    }
}
