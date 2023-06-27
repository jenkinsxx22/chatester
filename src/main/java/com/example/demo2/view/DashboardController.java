package com.example.demo2.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo2.ChatesterApplicationMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardController implements Initializable{
	
	private ChatesterApplicationMain mainApp;
	private Stage dialogStage;
	@FXML
	private BorderPane mainPane;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnProfile;
	@FXML
	private Button btnSettings;
	@FXML
	private Button btnNotifications;
	
	@FXML
	private Button btnMinimize;
	@FXML
	private Button btnMaximize;
	@FXML
	private Button btnClose;

	private String selectedStyle="	-fx-background-color: #f8eff2;" + 
			"	-fx-background-radius: 0px;" + 
			"	-fx-border-color: #0000ff;" + 
			"	-fx-border-width: 0px 4px 0px 0px;" + 
			"	-fx-border-style: solid;"; 

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btnHome.setOnAction(event->{goHome();});
		btnProfile.setOnAction(event->{goProfile();});
		btnSettings.setOnAction(event->{goSettings();});
		btnNotifications.setOnAction(event->{goNotifications();});

		btnMinimize.setOnAction(event->{goMinimize();});
		btnMaximize.setOnAction(event->{goMaximize();});
		btnClose.setOnAction(event->{goClose();});
		//btnMaximize.setDisable(true);

	}
	
	public void goHome() {
		btnHome.setStyle(selectedStyle);
		btnProfile.setStyle("");
		btnSettings.setStyle("");
		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Home.fxml"));			
		Parent fxml = (Parent) loader.load();
		mainPane.setCenter(fxml);
		HomeController controller = (HomeController) loader.getController();
		controller.setMainApp(this);
		controller.setUserName(mainApp.getUserName());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goProfile() {
		btnProfile.setStyle(selectedStyle);
		btnHome.setStyle("");
		btnSettings.setStyle("");
		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Profile.fxml"));			
		Parent fxml = (Parent) loader.load();
		mainPane.setCenter(fxml);
		ProfileController controller = (ProfileController) loader.getController();
		controller.setMainApp(this);		
		controller.setUser(mainApp.getUser());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void goSettings() {
		btnSettings.setStyle(selectedStyle);
		btnHome.setStyle("");
		btnProfile.setStyle("");
		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Settings.fxml"));			
		Parent fxml = (Parent) loader.load();
		mainPane.setCenter(fxml);
		SettingsController controller = (SettingsController) loader.getController();
		controller.setMainApp(this);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void goNotifications() {
		
	}
	
	public void goMinimize() {
		dialogStage.setIconified(true);
	}
	public void goMaximize() {
		if(dialogStage.isMaximized()) 
		{ dialogStage.setMaximized(false);
		}
		else {		
			dialogStage.setMaximized(true);
		}
		

	}
	public void goClose() {
		System.exit(0);
	}
	
	public void uploadUploadProfile(byte[] imgBytes) {
		mainApp.uploadProfile(imgBytes);
	}
	
    public void setMainApp(ChatesterApplicationMain mainApp) {
        this.mainApp = mainApp;
        mainApp.checkConnection();
    }
    
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	 public void showWaitingDialog(String cmdStr) {
		 mainApp.showWaitingBar(cmdStr);
	 }
}