/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author HP
 */
@Controller
public class Add_SaleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Autowired
    private DashboardController dashboardController;
    
        @FXML
    private Button clear;

    @FXML
    void clear(ActionEvent event) {
        try{
     dashboardController.setOverlayPaneInvisible();
//     dashboardController.loadContent("/fxml/ShowItem.fxml");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
