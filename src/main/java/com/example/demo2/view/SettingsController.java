package com.example.demo2.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingsController implements Initializable{
	
	private DashboardController mainApp;
	private Stage dialogStage;
	

	@FXML
	private ToggleButton toggleOnline;
	@FXML
	private ToggleButton toggleChat;
	@FXML
	private ToggleButton toggleHistory;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	public void Upload() {

	}
	
    public void setMainApp(DashboardController mainApp) {
        this.mainApp = mainApp;
    }
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
}
