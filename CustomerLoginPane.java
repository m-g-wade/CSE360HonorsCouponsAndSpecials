/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 10/22/2022
 * Date Last Edited: 10/23/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * PizzaApplication is a simulated restaurant web page for ASU students
 * to order from constructed using javafx. WebsitePane is the principal
 * and base element of the UI.
 */

package application;

import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.geometry.*;
import java.time.LocalTime;
import java.util.*;

import application.HomePagePane.HomePageHandler;

public class CustomerLoginPane extends HBox{
	
	private Label enterIDLabel;
	private TextField enterIDTF;
	private Label errorLabel;
	private Button submit;
	private GridPane gridPane;
	
	public CustomerLoginPane()
	{
		//setup layout
		enterIDLabel = new Label("Enter ASU ID to see order status: ");
		enterIDTF = new TextField();
		errorLabel = new Label("");
		submit = new Button("submit");
		
		errorLabel.setTextFill(Color.RED);
		enterIDLabel.setFont(new Font("Segoe UI", 14));
		enterIDTF.setPrefWidth(250);
		
		gridPane = new GridPane();
		gridPane.setMinSize(400,200);
		gridPane.setPadding(new Insets(30, 10, 10, 10)); 
		gridPane.setVgap(5); 
	    gridPane.setHgap(5);    
	    gridPane.add(enterIDLabel, 0, 0); 
	    gridPane.add(enterIDTF, 1, 0);
	    gridPane.add(errorLabel, 1, 1);
	    gridPane.add(submit, 2, 0);
	    
	    //Bind button to the handler
	    submit.setOnAction(new LoginHandler());
		
		this.setAlignment(Pos.TOP_CENTER);
		this.getChildren().addAll(gridPane);
	}
	
	public class LoginHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			String text = enterIDTF.getText();
			int id =Integer.parseInt(text);
			if(Verification.isValidASUID(id))
			{
				Button submit = (Button) event.getSource();
				WebsitePane root = (WebsitePane) submit.getParent().getParent().getParent();
				root.setCenter(new ViewOrderStatusPane(id));
			}
			else 
			{
				errorLabel.setText("No order has been placed with this ASU ID");
			}				
		}
	}
}
