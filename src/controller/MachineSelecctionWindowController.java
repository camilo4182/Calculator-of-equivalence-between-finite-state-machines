package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MachineSelecctionWindowController implements Initializable{

	@FXML
    private Button mealeyMachine_btn;

    @FXML
    private Button mooreMachine_btn;

    @FXML
    void mealeyClicked(ActionEvent event) {
       
    	
    	nextWindow("Mealey", event);
    }

    @FXML
    void mooreClicked(ActionEvent event) {

    	
    	nextWindow("Moore", event);
    }

	private void nextWindow(String type, ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/EntriesStatesWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EntriesStatesWindowController controller = loader.getController();
		controller.setWorld(type);
		Scene scene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		//window.setResizable(false);
		window.setScene(scene);
		window.show();
		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
