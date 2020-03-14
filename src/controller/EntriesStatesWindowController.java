package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

public class EntriesStatesWindowController implements Initializable{

	 @FXML
	    private Button accept_btn;

	    @FXML
	    private Spinner<?> numberEntries_Spinner;

	    @FXML
	    private Spinner<?> numberStatesM1_Spinner;

	    @FXML
	    private Spinner<?> numberStatesM2_Spinner;

	    @FXML
	    void acceptClicked(ActionEvent event) {

	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setWorld(String type) {

         System.out.print("data is passing from last window, machine = " + type);
		
	}

}
