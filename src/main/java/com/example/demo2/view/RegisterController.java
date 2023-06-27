package com.example.demo2.view;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo2.ChatesterApplicationMain;
import com.example.demo2.models.User;
import com.example.demo2.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage; 
public class RegisterController implements Initializable{
	
	private ChatesterApplicationMain mainApp;
	private Stage dialogStage;
	@FXML
	private TextField txtFullName;
	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField pswUserPassword;
	@FXML
	private PasswordField pswUserPassword2;

	@FXML
	private Button btnCancel;
	@FXML
	private Button btnRegister;
	
	private UserService userservice = new UserService();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnRegister.setOnAction(eve->{Register();});
		btnCancel.setOnAction(eve->{Cancel();});
		userservice.Connect();
	}
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	public void Register() {
		String strPassword1 = pswUserPassword.getText();
		String strPassword2 = pswUserPassword2.getText();
		if(txtFullName.getText()!=""&&txtEmail.getText()!=""&&pswUserPassword.getText()!=""&&pswUserPassword2.getText()!="") {

			String regex = "^(.+)@(.+)$"; 
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(txtEmail.getText()); 
			if(matcher.matches()==false) {
				txtEmail.setStyle("-fx-background-color:RED");
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Email Problem");
	            alert.setHeaderText("Invalid Email Address");
	            alert.setContentText("You have entered an invalid email! try again");            
	            alert.showAndWait();	
			}
			if(strPassword1.equals(strPassword2)) {
				
				User user = new User();
				
				user.setFullName(txtFullName.getText());
				user.setEmail(txtEmail.getText());
				user.setPassword(strPassword1);
				userservice.SaveUser(user);
				
				System.out.print("Registered!");
				mainApp.setUserLogin(txtEmail.getText(), strPassword1);
				mainApp.showHome();
			}
			else {
				pswUserPassword.setStyle("-fx-background-color:RED");
				pswUserPassword2.setStyle("-fx-background-color:RED");
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Password Problem");
	            alert.setHeaderText("Mismatched");
	            alert.setContentText("Password does not matched with entered one! try again");            
	            alert.showAndWait();	            
	        }
		}
		else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Fill the fields!");            
            alert.showAndWait();
            
        }
	}
	public void Cancel() {
		mainApp.showLogin();
	}
	
    public void setMainApp(ChatesterApplicationMain mainApp) {
        this.mainApp = mainApp;
   	 mainApp.showWaitingBar("Hide");

    }


}

