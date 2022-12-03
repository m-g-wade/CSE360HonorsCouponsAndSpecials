package application;

import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.time.LocalTime;
import java.util.*;

public class ManagerLoginPane extends HBox{
	
	static String[] employeeNums = {"0123456789", "5555555555", "1313131313"};
	
	
	Label enterLabel;
	Label errorLabel;
	TextField enterTF;
	
	VBox rightCol;
	
	BorderPane paneToSend;
	
	//Constructor
	public ManagerLoginPane()
	{
		enterLabel = new Label("Enter Employee ID Number: ");
		
		errorLabel = new Label("");
		errorLabel.setTextFill(Color.RED);
		
		enterTF = new TextField();
		
		rightCol = new VBox();
		rightCol.getChildren().addAll(enterTF, errorLabel);
		
		this.getChildren().addAll(enterLabel, rightCol);
		
		paneToSend = new BorderPane();
		
		enterTF.setOnAction(new ManagerLoginHandler());
		
		
	}//ManagerLoginPortal()
	
	public void updateCurrentViewPane()
	{
		((BorderPane)this.getParent()).setCenter(paneToSend);
	}
	
	public class ManagerLoginHandler implements	EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			String textFieldText = enterTF.getText();
			for(int i = 0; i < 3; i++)
			{
				try 
				{
					if(textFieldText.equals(employeeNums[i]))
					{
						paneToSend = new ManagerPortalPane();
						updateCurrentViewPane();
					}
				}//try
				catch(NumberFormatException e)
				{
					errorLabel.setText("Incorrect input, ID must be numerical");
				}//catch
				
				//System.out.println(enterTF.getText().length());
				
				if(enterTF.getText().length() != 10)
					errorLabel.setText("Please re-enter, ID must be 10 digits");
				else
					errorLabel.setText("Please re-enter, Incorrect Input");
			}
			
			enterTF.setText("");
			
		}//handle()
	}//ManagerLoginHandler

}//ManagerLoginPortal
