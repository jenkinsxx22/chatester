package com.example.demo2.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo2.ChatesterApplicationMain;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class waitingbarController implements Initializable{
	
	private ChatesterApplicationMain mainApp;
	private Stage dialogStage;
	@FXML
	private ProgressBar pbr;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pbr.setProgress(0);
		
	}
	
    public void setMainApp(ChatesterApplicationMain mainApp) {
        this.mainApp = mainApp;
    }
    
    public void runProgressBar() {
    	pbr.setMinWidth(200);
    	pbr.setMaxWidth(Double.MAX_VALUE);
           IntegerProperty seconds = new SimpleIntegerProperty();
           pbr.progressProperty().bind(seconds.divide(30.0));
           Timeline timeline = new Timeline(
               new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
               new KeyFrame(Duration.minutes(1), e-> {
                   // do anything you need here on completion...
                 //  System.out.println("Minute over");
               }, new KeyValue(seconds, 60))   
           );
           timeline.setCycleCount(Animation.INDEFINITE);
           timeline.play();
    }
    
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	 public void hideDialog() {
		 dialogStage.close();
	 }
}
