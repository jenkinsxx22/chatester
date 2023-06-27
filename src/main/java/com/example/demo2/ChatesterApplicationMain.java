package com.example.demo2;


import java.io.IOException;
import java.util.prefs.Preferences;

import com.example.demo2.models.User;
import com.example.demo2.services.UserService;
import com.example.demo2.view.DashboardController;
import com.example.demo2.view.LoginController;
import com.example.demo2.view.RegisterController;
import com.example.demo2.view.interneterrorController;
import com.example.demo2.view.waitingbarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatesterApplicationMain extends Application {
	private Stage primaryStage;
	
	private User user = new User();
	private UserService userservice = new UserService();
	public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Chatester App");
        this.primaryStage.getIcons().add(new Image("file:images/chat-icon.png"));
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
/*
        if(checkConnection()==true)
        {
	        if (getUserName().equals("")) {	        	
	        	showLogin();
	        }else {
	        	if(validateUser()==true) {showHome();}
	        	else {showLogin();}
	        }
        }        
  */
		showLogin();
}

   public boolean checkConnection() 
    {
    	boolean checkConn=false;
    	
    	
    		
    		checkConn=userservice.Connect();
    		if(checkConn==false) {
    		  try {
    		        // Load the fxml file and create a new stage for the popup dialog.
    		        FXMLLoader loader = new FXMLLoader();
    		        loader.setLocation(getClass().getResource("view/interneterror.fxml"));
    		        AnchorPane page = (AnchorPane) loader.load();

    		        // Create the dialog Stage.
    		        Stage dialogStage = new Stage();
    		        dialogStage.initStyle(StageStyle.UNDECORATED);
    		        dialogStage.initOwner(primaryStage);
    		        dialogStage.getIcons().add(new Image("file:resources/images/chat-icon.png"));
    		        Scene scene = new Scene(page);
    		        dialogStage.setScene(scene);

    		        // Set the person into the controller.
    		        interneterrorController controller = loader.getController();
    		        controller.setDialogStage(dialogStage);
    		        controller.setMainApp(this);
    		        // Show the dialog and wait until the user closes it
    		        dialogStage.showAndWait();

    		     
    		        
    		    } catch (IOException e) {
    		        e.printStackTrace();
    		 
    		    }
    		 
    	}
    		 return checkConn;
    }
    
//	public void showHome() {
//    	showWaitingBar("Show");
//
//		try {
//		FXMLLoader loader =new FXMLLoader(getClass().getResource("view/Home.fxml"));			
//		Parent fxml = (Parent) loader.load();
//        primaryStage.setScene(new Scene(fxml, 1000, 650));
//        primaryStage.show();
//    	//	controller.setDialogStage(primaryStage);
//
//        HomeController controller = (HomeController) loader.getController();
//		controller.setMainApp(this);
//		controller.setUserName(user.getFullName());
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	public void showHome() {
    	showWaitingBar("Show");

		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("view/Dashboard.fxml"));			
		Parent fxml = (Parent) loader.load();
        primaryStage.setScene(new Scene(fxml, 1000, 650));
        primaryStage.show();
        DashboardController controller = (DashboardController) loader.getController();
		controller.setMainApp(this);
		controller.setDialogStage(primaryStage);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
	public void showLogin() {
    	showWaitingBar("Show");

		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("view/Login.fxml"));			
		Parent fxml = (Parent) loader.load();        
        primaryStage.setScene(new Scene(fxml, 1150, 600));
        primaryStage.show();
    	

        LoginController controller = (LoginController) loader.getController();
		controller.setMainApp(this);
		controller.setDialogStage(primaryStage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showRegister() {
    	showWaitingBar("Show");
		try {
		FXMLLoader loader =new FXMLLoader(getClass().getResource("view/Register.fxml"));			
		Parent fxml = (Parent) loader.load();
        primaryStage.setScene(new Scene(fxml, 800, 600));
        primaryStage.show();
        RegisterController controller = (RegisterController) loader.getController();
        controller.setDialogStage(primaryStage);
		controller.setMainApp(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
	public void setUserLogin(String username, String password) {
	    Preferences prefs = Preferences.userNodeForPackage(ChatesterApplicationMain.class);
        prefs.put("userName",username);
        prefs.put("userPassword",password);
	}
	
	public String getUserName() {		
	    Preferences prefs = Preferences.userNodeForPackage(ChatesterApplicationMain.class);
	    String username = prefs.get("userName",null);
        return username;
	}
	public String getUserPassword() {		
	    Preferences prefs = Preferences.userNodeForPackage(ChatesterApplicationMain.class);
	    String userpassword = prefs.get("userPassword",null);
        return userpassword;
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setUser(User user) {
		this.user=user;
	}
	public User getUser() {
		return this.user;
	}
	public void showWaitingBar(String cmdStr){}
	public void showWaitingBar2(String cmdStr) {
		Stage waitingStage = new Stage();
		waitingbarController controller = new waitingbarController();
		waitingStage.setTitle("Chatester App");
		waitingStage.getIcons().add(new Image("file:resources/images/chat-icon.png"));
		waitingStage.initStyle(StageStyle.UNDECORATED);
		if (cmdStr=="Show") {		
			try {
				FXMLLoader loader =new FXMLLoader(getClass().getResource("view/waitingbar.fxml"));			
				Parent fxml = (Parent) loader.load();
				waitingStage.setScene(new Scene(fxml, 600, 150));
				waitingStage.show();
				controller = (waitingbarController) loader.getController();
				controller.setDialogStage(waitingStage);
				controller.setMainApp(this);
				controller.runProgressBar();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(cmdStr=="Hide") {
			waitingStage.close();
			//				FXMLLoader loader =new FXMLLoader(getClass().getResource("view/waitingbar.fxml"));			
//				controller = (waitingbarController) loader.getController();
//				controller.setDialogStage(waitingStage);
//				controller.setMainApp(this);
//				controller.hideDialog();
		}
	} 
	
	public boolean validateUser() {
		boolean result=false;
		
		User user = new User();
		user=userservice.findUserByEmail(getUserName());
		if(user!=null) {
			if(user.getPassword().equals(getUserPassword()))
			{
				setUser(user);
				result=true;			
			}
				else {
					result=false;
		            
		        }
		}
			else {
				result=false;	            
	        }
	return result;	
	}
	public void uploadProfile(byte[] imgBytes) {
		user.setProfilepic(imgBytes);
		userservice.uploadProfilePic(user);
	}
	public void showLoginError() {
        // Show the error message.
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(primaryStage);
        alert.setTitle("Invalid Username or Password");
        alert.setHeaderText("Invalid Username or Password");
        alert.setContentText("entered details are not correct!");            
        alert.showAndWait();
	}
}
