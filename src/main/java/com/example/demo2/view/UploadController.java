package com.example.demo2.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UploadController implements Initializable{
	
	private DashboardController mainApp;
	private Stage dialogStage;
	
	
	@FXML
	private Button btnUpload;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		btnUpload.setOnAction(eve->{Upload();});
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
