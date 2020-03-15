package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TablesCalculateWindowController implements Initializable{

	final Button saveButton = new Button("Save");
	private final EventHandler<ActionEvent> saveBtnClicked = new EventHandler<ActionEvent>() {
		
		public void handle(final ActionEvent Event) {
		System.out.print("i was pressed");
		}	
	
	};
	
	@FXML
    private Button fillTables;

    @FXML
    private Button calculateEquivalence_btn;

    @FXML
    void calculateEquivalencecClicked(ActionEvent event) {

    }

    @FXML
    void fillClicked(ActionEvent event) {

    	Stage stage = new Stage();
    	
    try {
			
			final GridPane root = new GridPane();
			final GridPane tittlesGrid = new GridPane();
			final GridPane tablesGrid = new GridPane();
			final GridPane buttonGrid = new GridPane();
			
			final GridPane gridM1 = new GridPane();
			final GridPane gridM2 = new GridPane();		
			
			root.add(tittlesGrid, 0, 0);
			root.add(tablesGrid, 0, 1);
			root.add(buttonGrid, 0, 2);
			
			tittlesGrid.add(new Label("                                           "), 0, 0);
			tittlesGrid.add(new Label("                                                                                                    "), 2, 0);
			tittlesGrid.add(new Label("Machine 1"), 1, 0);
			tittlesGrid.add(new Label("Machine 2"), 3, 0);
			
			tittlesGrid.setPadding(new Insets(5));
			
			tablesGrid.add(gridM1, 0, 0);
			tablesGrid.add(gridM2, 1, 0);
			
			buttonGrid.setAlignment(Pos.CENTER);
			buttonGrid.add(saveButton, 0, 0);
			
			gridM1.setPadding(new Insets(20));
			gridM2.setPadding(new Insets(20));
			
			addThings(gridM1);
			addThings(gridM2);
			
			Scene scene = new Scene(root);
			stage.setTitle("tablas");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }

    

    public void setWorld(String message) {
    	System.out.print("message received from entries to tables window message = "+message);
    }
	
    public void addThings(GridPane g, int states, int entries, String type) {
		
    	int columns=0;
    	int rows=0;
    	
    	if(type == "Mooore") {
    		columns+=entries+1;
    		rows+=states;
    	}
    	else {
    		columns+=entries;
    		rows+=states;
    	}
    	
		for ( int i=0 ;i<columns;i++) {
			
			for (int j=0; j < rows; j++) {
				
				TextField tf = new TextField();	
				
				if(i==0) {
					tf.setMaxWidth(35);
				}
				
				if(i==0 && j==0) {
					tf.setText("S/E");
					tf.setDisable(true);
				}				
				
				g.add(tf, i, j);
			
			}
		}
	
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		saveButton.setOnAction(saveBtnClicked);
	
	}

}
