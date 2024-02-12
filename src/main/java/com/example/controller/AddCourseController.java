/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author wwwsa
 */
@Controller
public class AddCourseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Autowired
    private DashboardController dashboardController;
    
    @FXML
    private GridPane addCoursePane;
    
    @FXML
    private Label closeButton;

    @FXML
    private Label settingButton;

    @FXML
    void closeOverlay(MouseEvent event) {
           dashboardController.setOverlayPaneInvisible();
    }

    @FXML
    void fetchCustomer(MouseEvent event) {

    }

    @FXML
    void isselected(MouseEvent event) {

    }

    @FXML
    void openSetting(MouseEvent event) {

    }

    @FXML
    void validphonenumber(MouseEvent event) {

    }
    private boolean isEventTargetExcluded(MouseEvent event) {
    // Check if the target of the event is one of the excluded components
     Node target = (Node) event.getTarget();
     return isChild(target, addCoursePane);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dashboardController.overlayPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
    // Check if the event target is not one of the excluded elements
    if (!isEventTargetExcluded(event)) {
        
        dashboardController.setOverlayPaneInvisible();
    }});
    }    
    
}
