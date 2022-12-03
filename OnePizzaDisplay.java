/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
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
 * OnePizzaDisplay is a GridPane that displays all the information about
 * a single pizza for the Chef's perspective. It can be manipulated within
 * the Chef application in multiple columns to track the stage of preparation
 * the pizza is in.
 */

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class OnePizzaDisplay extends GridPane 
{
	private Pizza pizza;
	
	private Label pizzaType;
	private Label pizzaToppings;
	private Label orderNumber;
	private Label pickupTime;
	
	private Button backButton;
	private Button forwardButton; 
	
	private boolean confirmDelete = false;
	
	// Default Constructor
	public OnePizzaDisplay()
	{
		pizzaType = new Label();
		pizzaToppings = new Label();
		orderNumber = new Label();
		pickupTime = new Label();
		backButton = new Button();
		forwardButton = new Button();
	}
	
	// Main Constructor
	public OnePizzaDisplay(Pizza pizza)
	{
		//Instantiate elements
		
		this.pizza = pizza;
		
		pizzaType = new Label("Type: " + pizza.getType());
		
		// Setting up the toppings String
		String toppings = "Toppings: \n\t";
		for (int i = 0; i < pizza.getToppings().size(); i++)
			toppings += pizza.getToppings().get(i) + ",\n\t";
		toppings = toppings.substring(0, toppings.length()-3);
		if (pizza.getToppings().size() == 0)
			toppings += " None";
		pizzaToppings = new Label(toppings);
		
		orderNumber = new Label("Order #" + ((pizza.getPizzaID()/10)*10));
		
		Order order = OrderLog.getOrderByNumber((pizza.getPizzaID()/10)*10);
		pickupTime = new Label("Pickup Time: " + order.getPickupTime());
		
		backButton = new Button("Back");
		backButton.setOnAction(new BackHandler());
		
		forwardButton = new Button("Forward");
		forwardButton.setOnAction(new ForwardHandler());
		
		// Add the elements into OnePizzaDisplay (Element, column, row)
		this.add(pizzaType, 0, 0);
		this.add(pizzaToppings, 0, 1);
		this.add(backButton, 0, 2);
		this.add(orderNumber, 1, 0);
		this.add(pickupTime, 1, 1);
		this.add(forwardButton, 1, 2);
		
		// Setting up the formatting for OnePizzaDisplay
		GridPane.setHalignment(pizzaType, HPos.LEFT);
		GridPane.setHalignment(pizzaToppings, HPos.LEFT);
		GridPane.setHalignment(backButton, HPos.LEFT);
		GridPane.setHalignment(orderNumber, HPos.RIGHT);
		GridPane.setHalignment(pickupTime, HPos.RIGHT);
		GridPane.setHalignment(forwardButton, HPos.RIGHT);
		GridPane.setValignment(pickupTime, VPos.TOP);
		
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		column1.setPercentWidth(50);
		column2.setPercentWidth(50);
		this.getColumnConstraints().addAll(column1, column2);
	}
	
	/**
	 * BackHandler is an action event class that handles what to do
	 * when the back button on OnePizzaDisplay is pressed.
	 * @author Ethan
	 */
	public class BackHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent press) 
		{	
			Button delete = (Button) press.getSource();
			ChefPane root = (ChefPane) delete.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
			Label bottomLabel = (Label) ((BorderPane) root.getCenter()).getBottom();
			
			if (pizza.getStatus().equals("Preparation")) 	// If the "Delete" button is called
			{
				if (confirmDelete) 							// If the chef has been warned about deleting a pizza
				{
					bottomLabel.setText("Deleted Pizza.");
					confirmDelete = false;
					OrderLog.removePizza(pizza);
					return;
				}
				else
				{
					bottomLabel.setText("Are you sure you want to delete?");
					confirmDelete = true;
				}
			}
			else // The "Move back" Button is called
			{
				OrderLog.moveBakingToPrep(pizza);
				bottomLabel.setText("Moved Pizza to Prep.");
				confirmDelete = false;
			}
		}
	}
	
	/**
	 * ForwardHandler is an action event class that handles what to do
	 * when the forward button on OnePizzaDisplay is pressed.
	 * @author Ethan
	 */
	public class ForwardHandler implements EventHandler<ActionEvent>
	{	
		@Override
		public void handle(ActionEvent press) 
		{
			Button delete = (Button) press.getSource();
			ChefPane root = (ChefPane) delete.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
			Label bottomLabel = (Label) ((BorderPane) root.getCenter()).getBottom();
			
			if (pizza.getStatus().equals("Preparation")) // If the "Forward" button is called
			{
				OrderLog.movePrepToBaking(pizza);
				bottomLabel.setText("Moved Pizza to Baking.");
				confirmDelete = false;
			}
			else 											// The "Done" button is called
			{
				OrderLog.moveBakingToDone(pizza);
				bottomLabel.setText("Moved Pizza to Done.");
				confirmDelete = false;
			}
		}
	}

}
