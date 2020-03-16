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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EntriesStatesWindowController implements Initializable{
	
	final GridPane gridM1 = new GridPane();
	final GridPane gridM2 = new GridPane();
	private int entries;
	private int statesM1;
	private int statesM2;
	private String machineType;
	
	final Button saveButton = new Button("Calculate Equivalence");
	private final EventHandler<ActionEvent> saveBtnClicked = new EventHandler<ActionEvent>() {
		
		public void handle(final ActionEvent Event) {
		
			if(!checkNulls()) {
				
             // format without nulls
				
			}
			else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Machines not completed");
				alert.showAndWait();
			}
			
			//showResult(true);
			
		}	
	
	};
	
	private void showResult(boolean equivalent) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		if(equivalent==true) {
			alert.setContentText("The machines are equivalent");
		}
		else {
			alert.setContentText("The machines are not equivalent");
		}
    	
    	alert.showAndWait();
	    
	}
	
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
		
		 entries = numberEntries_Spinner.getValue();
		 statesM1 = numberStatesM1_Spinner.getValue();
		 statesM2 = numberStatesM2_Spinner.getValue();
		 
		 openTableWindow();
	}
	
	private void openTableWindow() {
        Stage stage = new Stage();
    	
	    try {
				
				final GridPane root = new GridPane();
				final GridPane tittlesGrid = new GridPane();
				final GridPane tablesGrid = new GridPane();
				final GridPane buttonGrid = new GridPane();
				
				//final GridPane gridM1 = new GridPane();
				//final GridPane gridM2 = new GridPane();		
				
				root.add(tittlesGrid, 0, 0);
				root.add(tablesGrid, 0, 1);
				root.add(buttonGrid, 0, 2);
				
				tittlesGrid.add(new Label("                                           "), 0, 0);
				tittlesGrid.add(new Label("                                                                                                "), 2, 0);
				tittlesGrid.add(new Label("Machine 1"), 1, 0);
				tittlesGrid.add(new Label("Machine 2"), 3, 0);
				
				tittlesGrid.setPadding(new Insets(5));
				
				tablesGrid.add(gridM1, 0, 0);
				tablesGrid.add(gridM2, 1, 0);
				
				buttonGrid.setAlignment(Pos.CENTER);
				
				gridM1.setPadding(new Insets(20));
				gridM2.setPadding(new Insets(20));
				
				addThings(gridM1,statesM1,entries,machineType,"M1");
				addThings(gridM2,statesM2,entries,machineType,"M2");
				
				saveButton.setOnAction(saveBtnClicked);
				buttonGrid.add(saveButton, 0, 0);
				
				Scene scene = new Scene(root);
				stage.setTitle("tablas");
				stage.setResizable(false);
				stage.setScene(scene);
				stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		SpinnerValueFactory<Integer> values1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 2);
		this.numberEntries_Spinner.setValueFactory(values1);
		
		SpinnerValueFactory<Integer> values2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3);
		this.numberStatesM1_Spinner.setValueFactory(values2);
		
		SpinnerValueFactory<Integer> values3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3);
		this.numberStatesM2_Spinner.setValueFactory(values3);
	}
	
   public void addThings(GridPane g, int states, int entries, String type,String id) {
		
    	int columns=entries;
    	int rows=states;
    	
    	if(type == "Moore") {
    		columns+=2;
    		rows+=1;
    	}
    	else {
    		columns+=1;
    		rows+=1;
    	}
    	
		for ( int i=0 ;i<rows;i++) {
			
			for (int j=0; j < columns; j++) {
				
				TextField tf = new TextField();	
				
				if(j==0) {
					tf.setMaxWidth(35);
					tf.setStyle("-fx-border-color: black");
				}
				
				if(i==0) {
					tf.setStyle("-fx-border-color: black");
				}
				
				if(i==0 && j==0) {
					tf.setText("S/E");
					tf.setDisable(true);
				}				
				
				if(type=="Moore") {
					if(i == 0 && j== columns-1) {
						tf.setDisable(true);
					}
				}
			
				tf.setId("text"+id+i+","+j);
				g.add(tf, j, i);
			
			}
		}
	
	}

	public void setWorld(String type) {

         machineType=type;
		
	}

private boolean checkNulls() {
		
		boolean nulls=false;
		
		if(machineType=="Moore") {
			
			for(int i=0;i<statesM1+1 && !nulls;i++) {
				for(int j=0;j<entries+1 && !nulls;j++) {
					
					TextField temp = (TextField) gridM1.lookup("#textM1"+i+","+j);
					
					if(i==0 && j==0 || i==0 && j==entries+1) {
						
					}
					else {
						if(temp==null) {
							nulls=true;
						}
						else {
							if(temp.getText()==null || temp.getText().equals("") || temp.getText().equals(" ")) {
								nulls=true;
							}
						}
					}
					
				}
			}
			
			for(int i=0;i<statesM2+1 && !nulls;i++) {
				for(int j=0;j<entries+1;j++) {
					
                    TextField temp = (TextField) gridM2.lookup("#textM2"+i+","+j);
					
					if(i==0 && j==0 || i==0 && j==entries+1) {
						
					}
					else {
						if(temp==null) {
							nulls=true;
						}
						else {
							if(temp.getText()==null || temp.getText().equals("") || temp.getText().equals(" ")) {
								nulls=true;
							}
						}
					}
					
				}
			}
			
		}
		else {
			
			for(int i=0;i<statesM1+1 && !nulls;i++) {
				for(int j=0;j<entries+1 && !nulls;j++) {
					
					TextField temp = (TextField) gridM1.lookup("#textM1"+i+","+j);
					
					if(i!=0 || j!=0) {
						
						if(temp==null) {
							nulls=true;
						}
						else {		
							//System.out.print("\n m1 i="+i+" j="+j+" == "+temp.getText());
							if(temp.getText()==null || temp.getText().equals("") || temp.getText().equals(" ")) {
								nulls=true;
							}
						}
						
					}
					
				}
			}
			
			for(int i=0;i<statesM2+1 && !nulls;i++) {
				for(int j=0;j<entries+1 && !nulls;j++) {
					
                    TextField temp = (TextField) gridM2.lookup("#textM2"+i+","+j);
					
					if(i!=0 || j!=0) {
						if(temp==null) {
							nulls=true;
						}
						else {
							if(temp.getText()==null || temp.getText().equals("") || temp.getText().equals(" ")) {
								nulls=true;
							}
						}
						
					}
					
				}
			}
			
		}
		
		return nulls;
	}


}