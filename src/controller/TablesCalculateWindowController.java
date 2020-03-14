package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TablesCalculateWindowController implements Initializable{

    @FXML
    private Button clearT1_btn;

    @FXML
    private Button clearT2_btn;

    @FXML
    private Button calculateEquivalence_btn;

    @FXML
    void calculateEquivalencecClicked(ActionEvent event) {

    }

    @FXML
    void clearT1Clicked(ActionEvent event) {

    }

    @FXML
    void clearT2Clicked(ActionEvent event) {

    }

    public void setWorld(String message) {
    	System.out.print("message received from entries to tables window message = "+message);
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
