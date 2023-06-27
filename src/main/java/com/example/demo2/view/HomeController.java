package com.example.demo2.view;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane; 
public class HomeController implements Initializable{
	
	private DashboardController mainApp;
	
	private String userName;
	@FXML
	private AnchorPane RecActivitiesPane;
	@FXML
	private TilePane RecBodyAcitivities;
	@FXML
	private ListView<String> friendList;
	@FXML
	private Label userLabel;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnExit;

	private ObservableList<String> personData = FXCollections.observableArrayList();
	public  HomeController(){
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		personData.add("Hans");
		personData.add("Ruth");
		personData.add("Mueller");
		personData.add("Cornelia");
		personData.add("Meier");
		personData.add("Lydia");
		personData.add("Kunz");
		
		friendList.setItems(personData);
		
		btnLogout.setOnAction(eve->{logOut();});
		btnExit.setOnAction(eve->{Exit();});
		
		
	}


	
    public void setMainApp(DashboardController mainApp) {
        this.mainApp = mainApp;
        
    }
    public void setUserName(String username) {
    	this.userName=username;
    	userLabel.setText("Hi, "+userName);
    }

    public void logOut() {

    }
    public void Exit() {
    	System.exit(0);
    	
    }

}