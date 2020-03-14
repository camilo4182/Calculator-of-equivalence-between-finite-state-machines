package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class EntriesStatesWindowController implements Initializable{
	
	@FXML
	private Button accept_btn;

	@FXML
	private Spinner<Integer> numberEntries_Spinner;
	
	@FXML
	private Spinner<Integer> numberStatesM1_Spinner;
	
	@FXML
	private Spinner<Integer> numberStatesM2_Spinner;
	
	@FXML
	void acceptClicked(ActionEvent event) {
		nextWindow();
	}
	
	private void nextWindow() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/TablesCalculateWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TablesCalculateWindowController controller = loader.getController();
		controller.setWorld("last window connected");
		Scene scene = new Scene(root);
		Stage stage = (Stage)accept_btn.getScene().getWindow(); 
		stage.setScene(scene);
		stage.show();		
	}
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setWorld(String type) {

         System.out.print("data is passing from last window, machine = " + type);
		
	}

}
