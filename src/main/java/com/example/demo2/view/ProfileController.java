package com.example.demo2.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo2.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfileController implements Initializable{
	
	private DashboardController mainApp;
	private Stage dialogStage;
	
	private User user = new User();
	
	@FXML
	private Label lblFullName;
	@FXML
	private Label lblEmail;
	@FXML
	private Button btnUpload;
	@FXML
	private ImageView imgProfile;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		btnUpload.setOnAction(eve->{Upload();});
	}
	public void Upload() {
		chooseFile();
	}
	
	  public void chooseFile() {
			  FileChooser fileChooser = new FileChooser();
			  File file = fileChooser.showOpenDialog(btnUpload.getScene().getWindow());
			  try {
				  FileInputStream fileInputStream = new FileInputStream(file);
				  mainApp.showWaitingDialog("Show");
				  mainApp.uploadUploadProfile(fileInputStream.readAllBytes());
				  loadImage();  
				  mainApp.showWaitingDialog("Hide");
			  }catch(IOException e) {
				  System.out.println(e.getMessage());
			  }
			  
		  }
	  
	  public void loadImage() {
		  Image image = new Image(new ByteArrayInputStream(user.getProfilepic()));
		  imgProfile.setImage(image);
	  }

	
    public void setMainApp(DashboardController mainApp) {
        this.mainApp = mainApp;
    }
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	 
	 public void setUser(User user) {
		 this.user=user;
//		 System.out.println("Bytearray:"+this.user.getProfilepic().length);
		 lblFullName.setText(user.getFullName());
		 lblEmail.setText(user.getEmail());
		 loadImage();
	 }
}
