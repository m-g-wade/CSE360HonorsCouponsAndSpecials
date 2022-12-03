/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Section: 70513-96561
 * Group Project: Group 40
 * 
 * Date File Created: 11/9/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * ChefPane is a pane that contains the Chef Verification as well as the 
 * functions for the chef view: moving pizzas across the columns in order
 * to track the pizza states for the student user.
 */

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChefPane extends BorderPane {

	private ChefLoginPane chefLoginPane;
	private ChefEmployeePortalPane chefPortalPane;
	
	public ChefPane()
	{
		chefLoginPane = new ChefLoginPane();
		chefPortalPane = new ChefEmployeePortalPane();
		this.setCenter(chefLoginPane);
	}
	
	/**
	 * ChefLoginPane provides an interface for the chef to
	 * input their ID and navigate to the chef menu.
	 * @author Ethan
	 *
	 */
	public class ChefLoginPane extends HBox{
		
		private Label enterIDLabel;
		private TextField enterIDTF;
		private Label errorLabel;
		private Button submit;
		private GridPane gridPane;
		
		public ChefLoginPane()
		{
			//setup layout
			enterIDLabel = new Label("Enter employee ID number: ");
			enterIDTF = new TextField();
			errorLabel = new Label("");
			submit = new Button("submit");
			
			errorLabel.setTextFill(Color.RED);
			enterIDTF.setPrefWidth(250);
			enterIDLabel.setFont(new Font("Segoe UI", 14));
			
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
				
				if (Verification.isValidChefID(id))
				{
					Button submitButton = (Button) event.getSource();
					ChefPane root = (ChefPane) submitButton.getParent().getParent().getParent();
					root.setCenter(chefPortalPane);
				}
				else
				{
					errorLabel.setText("This ID is invalid");
				}
					
			}	
		}
	}
	
	/**
	 * ChefEmployeePortalPane is the chef perspective of the pizza
	 * orders, separated into two separate columns: Prepped, and baking.
	 * The chef can move these pizzas among these columns to track
	 * their progress.
	 */
	public static class ChefEmployeePortalPane extends BorderPane
	{
		private ScrollPane prepOrdersScroll;
		private ScrollPane bakingOrdersScroll;
		private static VBox prepOrdersBox;
		private static VBox bakingOrdersBox;
		private Label bottomLabel;
		private Label topLabel;
			
		public ChefEmployeePortalPane() 
		{
			// Instantiate variables
			prepOrdersBox = new VBox();
			bakingOrdersBox = new VBox();
			prepOrdersBox.setStyle("-fx-background-color: #f5f5dc");
			bakingOrdersBox.setStyle("-fx-background-color: #f5f5dc");
			prepOrdersBox.setPrefWidth(275);
			bakingOrdersBox.setPrefWidth(275);

			prepOrdersScroll = new ScrollPane(prepOrdersBox);
			bakingOrdersScroll = new ScrollPane(bakingOrdersBox);
			prepOrdersScroll.setPrefWidth(275);
			bakingOrdersScroll.setPrefWidth(275);
			prepOrdersScroll.setStyle("-fx-background-color: #ffc627");
			bakingOrdersScroll.setStyle("-fx-background-color: #ffc627");
			prepOrdersScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			bakingOrdersScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			
			topLabel = new Label("\t\t\tPizzas to Prepare\t\t\t\t\t\tPizzas Cooking");
			bottomLabel = new Label("");
			topLabel.setStyle("-fx-font: 15px 'Arial'; -fx-font-weight: bold; -fx-text-fill: white");
			bottomLabel.setStyle("-fx-font: 15px 'Arial'; -fx-font-weight: bold; -fx-text-fill: cyan");
			
			this.setStyle("-fx-background-color: #8C1D40");
			
			// Add Elements to ChefEmployeePortalPane
			this.setLeft(prepOrdersScroll);
			this.setRight(bakingOrdersScroll);
			this.setTop(topLabel);
			this.setBottom(bottomLabel);
			Insets spacing = new Insets(12,12,12,12);
			BorderPane.setMargin(prepOrdersScroll, spacing);
			BorderPane.setMargin(bakingOrdersScroll, spacing);
		}
		
		/**
		 * updateDisplay updates the current chef view to accommodate
		 * the new order placed.
		 * @param newOrder The new order placed in the system.
		 */
		public static void updateDisplay()
		{
			// Delete all children from the VBox
			prepOrdersBox.getChildren().clear();
			bakingOrdersBox.getChildren().clear();
			
			//Loop through all of the pizzas from the PrepList
			for (int i = 0; i < OrderLog.getPrepPizzaQuantity(); i++)
			{
				//Add each pizza to a display and add the display
				OnePizzaDisplay display = new OnePizzaDisplay(OrderLog.getPrepPizzaByIndex(i));
				prepOrdersBox.getChildren().add(display);
				VBox.setMargin(display, new Insets(10));
			}
			
			//Loop through all of the pizzas from the PrepList
			for (int i = 0; i < OrderLog.getBakingPizzaQuantity(); i++)
			{
				//Add each pizza to a display and add the display
				OnePizzaDisplay display = new OnePizzaDisplay(OrderLog.getBakingPizzaByIndex(i));
				bakingOrdersBox.getChildren().add(display);
				VBox.setMargin(display, new Insets(10));
			}

		}
		
	}
	
}
