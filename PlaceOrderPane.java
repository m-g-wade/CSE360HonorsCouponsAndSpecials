/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Section: 70513-96561
 * Group Project: Group 40
 * 
 * Date File Created: 10/22/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * PlaceOrderPane allows the user to customize their order of pizzas,
 * adding or removing pizzas to the display. The pane will present
 * options to the user, and the total price tag of the order.
 */

package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The class PlaceOrderPane extends HBox and is one of the views of WebsitePane. 
 * This view is where customers add new Pizzas to their Order. The left side of 
 * the Pane allows the customer to specify the type and toppings of the Pizza 
 * they wish to add. The right side of the Pane contains an Order summary with 
 * the running total and the specific pizza type as well as toppings selected, 
 * where the customer may remove Pizzas from the Order. Finally, the user uses a 
 * Button to proceed to CheckOutPane.
 */
public class PlaceOrderPane extends HBox
{
	private VBox leftCol;
	private Label specialLabel;
	private ScrollPane specialScroll;
	private VBox inSpecialScrollVBox;
	
	private VBox selectionAndWarningVBox;
	private GridPane selectionGridPane;

	private Label typeLabel;
	private Label pizzaCostLabel;
	private Label toppingsLabel;
	private Label toppingsCostLabel;
	private ToggleGroup typeToggleGroup;
	private RadioButton pepperoni;
	private RadioButton vegetable;
	private RadioButton cheese;
	private CheckBox mushroomsCheckBox;
	private CheckBox onionCheckBox;
	private CheckBox olivesCheckBox;
	private CheckBox extraCheeseCheckBox;
	private Label maxPizzaWarning;
	private Button addPizzaToOrderButton;

	private VBox orderSumVBox;
	private VBox orderTotalVBox;
	private VBox sumAndTotalVBox;
	private ScrollPane orderSumScroll;	
	private VBox inScrollPizzas;

	private Label orderSumLabel;
	private Button deletePizzaButton;
	private Label orderTotalLabel;
	private Button goToCheckOutButton;
	
	private Pizza currentPizza;
	private Order currentOrder;
	
	private int count;
	private HBox paneToSend;
	/**
	 * 
	 */
	public PlaceOrderPane()
	{
		//Order A Special Column
		leftCol = new VBox();
		specialLabel = new Label("Order A Special!");
		specialScroll = new ScrollPane();
		inSpecialScrollVBox = new VBox();
		
		for(int i = 0; i < SpecialsAndCouponLog.getActiveSpecials().size(); i++)
		{
			CheckBox oneSpecialCheckBox = new CheckBox();
			oneSpecialCheckBox.setText(SpecialsAndCouponLog.getActiveSpecials().get(i).getSpecialName() + "\n" + 
									   SpecialsAndCouponLog.getActiveSpecials().get(i).getOrder().toString());
			oneSpecialCheckBox.setOnAction(new OrderSpecialHandler(SpecialsAndCouponLog.getActiveSpecials().get(i).getOrder()));
			inSpecialScrollVBox.getChildren().add(oneSpecialCheckBox);
		}
		
		specialScroll.setContent(inSpecialScrollVBox);
		leftCol.getChildren().addAll(specialLabel, specialScroll);
		
		//Selection GridPane/Build-a-Pizza Layout
		typeLabel = new Label("Pizza Type:");
		pizzaCostLabel = new Label("$8");
		toppingsLabel = new Label("Toppings:");
		toppingsCostLabel = new Label("$1 each");
		
		//Type RadioButton ToggleGroup
		typeToggleGroup = new ToggleGroup();
		
		pepperoni = new RadioButton("Pepperoni");
		pepperoni.setToggleGroup(typeToggleGroup);
		pepperoni.setSelected(true);
		
		vegetable = new RadioButton("Vegetable");
		vegetable.setToggleGroup(typeToggleGroup);
		
		cheese = new RadioButton("Cheese");
		cheese.setToggleGroup(typeToggleGroup);
		
		mushroomsCheckBox = new CheckBox("Mushrooms");
		onionCheckBox = new CheckBox("Onion");
		olivesCheckBox = new CheckBox("Olives");
		extraCheeseCheckBox = new CheckBox("Extra Cheese");
		
		maxPizzaWarning = new Label("");
		
		addPizzaToOrderButton = new Button("Add to Order");
		
		//setstyle
		typeLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		pizzaCostLabel.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		toppingsLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		toppingsCostLabel.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		
		pepperoni.setStyle("-fx-font: 15px 'Segoe UI';");
		vegetable.setStyle("-fx-font: 15px 'Segoe UI';");
		cheese.setStyle("-fx-font: 15px 'Segoe UI';");
		
		mushroomsCheckBox.setStyle("-fx-font: 15px 'Segoe UI';");
		onionCheckBox.setStyle("-fx-font: 15px 'Segoe UI';");
		olivesCheckBox.setStyle("-fx-font: 15px 'Segoe UI';");
		extraCheeseCheckBox.setStyle("-fx-font: 15px 'Segoe UI';");
		
		maxPizzaWarning.setStyle("-fx-font: 13px 'Segoe UI';");
		
		addPizzaToOrderButton.setStyle("-fx-border-color: gray;" +
				"-fx-background-color: lightgray;" +
				"-fx-font: 10px 'Segoe UI Semibold';");
		
		//Adding Controls and Labels to selectionGridPane
		selectionGridPane = new GridPane();
		selectionGridPane.add(typeLabel, 0, 0);
		selectionGridPane.add(pizzaCostLabel, 0, 1);
		selectionGridPane.add(toppingsLabel, 0, 2);
		selectionGridPane.add(toppingsCostLabel, 0, 3);
		selectionGridPane.add(pepperoni, 1, 1);
		selectionGridPane.add(vegetable, 2, 1);
		selectionGridPane.add(cheese, 3, 1);
		selectionGridPane.add(mushroomsCheckBox, 1, 3);
		selectionGridPane.add(onionCheckBox, 1, 4);
		selectionGridPane.add(olivesCheckBox, 1, 5);
		selectionGridPane.add(extraCheeseCheckBox, 1, 6);
		selectionGridPane.add(addPizzaToOrderButton, 3, 6);
		
		selectionGridPane.setHgap(10);
		selectionGridPane.setVgap(10);
		
		selectionAndWarningVBox = new VBox();
		selectionAndWarningVBox.setSpacing(20);
		selectionAndWarningVBox.getChildren().addAll(selectionGridPane, maxPizzaWarning);
		
		//OrderSummary VBox Layout
		orderSumLabel = new Label("Order Summary");
		orderSumScroll = new ScrollPane();
		inScrollPizzas = new VBox();
		orderSumScroll.setContent(inScrollPizzas);
		deletePizzaButton = new Button("Delete Selected Items");
		
		orderSumScroll.setMinHeight(183);
		orderSumScroll.setMaxHeight(183);
		orderSumScroll.setMinWidth(200);
		orderSumScroll.setMaxWidth(200);
		
		inScrollPizzas.setSpacing(10);
		
		orderSumVBox = new VBox();
		orderSumVBox.getChildren().addAll(orderSumLabel, orderSumScroll, deletePizzaButton);
		
		orderTotalLabel = new Label("Order Total: ");
		goToCheckOutButton = new Button("Proceed to Checkout");
		
		orderTotalVBox = new VBox();
		orderTotalVBox.getChildren().addAll(orderTotalLabel, goToCheckOutButton);
		
		sumAndTotalVBox = new VBox();
		sumAndTotalVBox.getChildren().addAll(orderSumVBox, orderTotalVBox);
		sumAndTotalVBox.setSpacing(10);
		
		selectionGridPane.setPadding(new Insets(30, 0, 0, 0));
		sumAndTotalVBox.setPadding(new Insets(30, 0, 0, 0));
		
        //setStyle
		orderSumLabel.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		orderTotalLabel.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		
		deletePizzaButton.setStyle("-fx-border-color: tomato;" +
				"-fx-background-color: lightsalmon;" +
				"-fx-font: 10px 'Segoe UI Semibold';");
		goToCheckOutButton.setStyle("-fx-border-color: green;" +
				"-fx-background-color: lightgreen;" +
				"-fx-font: 10px 'Segoe UI Semibold';");
		
		//Add two Selection GridPane and Order Summary VBox to OrderPane
		this.getChildren().addAll(leftCol, selectionAndWarningVBox, sumAndTotalVBox);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		//Bind Selection GridPane Controls to NewPizzaHandler
		pepperoni.setOnAction(new NewPizzaHandler());
		vegetable.setOnAction(new NewPizzaHandler());
		cheese.setOnAction(new NewPizzaHandler());
		
		mushroomsCheckBox.setOnAction(new NewPizzaHandler());
		onionCheckBox.setOnAction(new NewPizzaHandler());
		olivesCheckBox.setOnAction(new NewPizzaHandler());
		extraCheeseCheckBox.setOnAction(new NewPizzaHandler());
		
		
		//set default values of currentPizza
		currentPizza = new Pizza("Pepperoni", new ArrayList<String>(), "Not in Order", -1); //Order number is assigned on successful checkout
		currentOrder = new Order();
		
		//System.out.println(CurrentPizzaLog.getOrder().toString());
		count = CurrentPizzaLog.getOrder().getPizzas().size();
		paneToSend = new HBox();
		
		for(int i = 0; i < count; i++)
		{
			System.out.println("count = " + count);
			addPizzaToOrderButton.setOnAction(new NewPizzaHandler(CurrentPizzaLog.getOrder().getPizzas().get(i)));
			addPizzaToOrderButton.fireEvent(new ActionEvent());
		}
		
		
		addPizzaToOrderButton.setOnAction(new NewPizzaHandler());
		
		//Bind Order Summary VBox Controls to OrderSummaryHandler()
		goToCheckOutButton.setOnAction(new OrderSummaryHandler());
		deletePizzaButton.setOnAction(new OrderSummaryHandler());
		
	}//PlaceOrderPane() Constructor
	
	/**
	 * The method updateCurrentViewPane sets the currentViewPane
	 * of the WebsitePane to be the page that the user navigates
	 * to using the navigation buttons.
	 */
	public void updateCurrentViewPane()
	{
		((BorderPane)this.getParent()).setCenter(paneToSend);
	}
	
	public class OrderSpecialHandler implements EventHandler<ActionEvent>
	{
		Order specialOrder;
		
		public OrderSpecialHandler(Order specialOrder)
		{
			this.specialOrder = specialOrder;
		}
		
		@Override
		public void handle(ActionEvent event) {
			if(((CheckBox)event.getSource()).isSelected())
			{
				CurrentPizzaLog.setOrder(specialOrder);
				paneToSend = new CheckOutPane();
				updateCurrentViewPane();
			}
		}
	}
	
	public class NewPizzaHandler implements EventHandler<ActionEvent>
	{
		/**
		 * TODO: Description
		 */
		Pizza pizza;
		//TODO: goToCheckOutButton
		public NewPizzaHandler() //goToCheckOutButton
		{
			this.pizza = null;
		}
		public NewPizzaHandler(Pizza pizza) //newPizzaCheckBox
		{
			this.pizza = pizza;
		}
		
		
		public void handle(ActionEvent event)
		{
			if(!(pizza == null))
				currentPizza = pizza;
			
			if(currentPizza.getStatus().equals("Not in Order") || !(pizza == null)) 
			{
				//Change Pizza type
				if(event.getSource() instanceof RadioButton)
				{
					//System.out.println("current order before change pizza type " + currentOrder.toString());
					RadioButton sourceButton = (RadioButton)event.getSource();
					currentPizza.setType(sourceButton.getText());
					//System.out.println("current order after change pizza type " + currentOrder.toString());
				}
			
				//Add/Remove Topping from Pizza
				else if(event.getSource() instanceof CheckBox)
				{
					//System.out.println("current order before change topping " + currentOrder.toString());
					CheckBox sourceCheckBox = (CheckBox)event.getSource();
					if(sourceCheckBox.isSelected())
					{
						currentPizza.addTopping(sourceCheckBox.getText());
						currentPizza.updatePrice();
					}
					else
					{
						currentPizza.removeTopping(sourceCheckBox.getText());
						currentPizza.updatePrice();
					}
					//System.out.println("current order after change topping " + currentOrder.toString());
				
				}
			
			    //Add Pizza to Order Button Press TODO: Make cap of 10 pizzas in order
				else if(event.getSource().equals(addPizzaToOrderButton) && count < 10)
				{

					if(currentOrder.getPizzas().size() == 0 || currentPizza.equals(currentOrder.getPizzas().get(currentOrder.getPizzas().size()-1)))
						currentPizza.updatePrice();
					
					//Create newPizza from currentPizza to add to the Order

					Pizza newPizza = new Pizza();
					newPizza.setStatus("In Order");
					for(int i = 0; i < currentPizza.getToppings().size(); i++)
						newPizza.addTopping(currentPizza.getToppings().get(i));
					newPizza.setType(currentPizza.getType());
					newPizza.updatePrice();
					//System.out.println("current order before add: " + currentOrder.toString());
					currentOrder.addPizza(newPizza);
					currentOrder.updateOrderTotal();
					//System.out.println("current order after add" + currentOrder.toString());

					//Add the newPizza's String of information and CheckBox to the ScrollPane orderSumScroll
					String newPizzaString = "Type: " + newPizza.getType() + "\nToppings: ";
					for(int i = 0; i < newPizza.getToppings().size(); i++)
						newPizzaString += newPizza.getToppings().get(i) + ", ";
					newPizzaString += "\n$" + newPizza.getPrice();
				
					CheckBox newPizzaCheckBox = new CheckBox(newPizzaString);
					newPizzaCheckBox.setStyle("-fx-font: 12px 'Segoe UI ';");
					newPizzaCheckBox.setMaxWidth(175);
					newPizzaCheckBox.setWrapText(true);
					newPizzaCheckBox.setPadding(new Insets(0, 0, 0, 3));
					
					newPizzaCheckBox.setOnAction(new OrderSummaryHandler(newPizza));
					inScrollPizzas.getChildren().addAll(newPizzaCheckBox);
					orderSumScroll.setContent(inScrollPizzas);
				
					orderTotalLabel.setText("Order Total: $" + currentOrder.getOrderTotal());
					currentPizza.setStatus("Not in Order");
					maxPizzaWarning.setText("");
					
					if(pizza == null)
						count++;
				}//Add Pizza to Order
				
				if(count >= 10)
				{
					maxPizzaWarning.setText("The maximum number of pizzas per order (10) has been reached");
				}
				
				if(!(pizza == null))
					currentPizza = new Pizza("Pepperoni", new ArrayList<String>(), "Not in Order", -1);
			
			}//New pizza (status = "Not in Order")
			
		}//handle(ActionEvent)
		
	}//NewPizzaHandler
	
	/**
	 * TODO: Description
	 */
	public class OrderSummaryHandler implements EventHandler<ActionEvent>
	{
		Pizza pizza;
		
		public OrderSummaryHandler() //goToCheckOutButton
		{
			
		}
		public OrderSummaryHandler(Pizza pizza) //newPizzaCheckBox
		{
			this.pizza = pizza;
		}
		
		/**
		 * TODO: Description
		 */
		public void handle(ActionEvent event)
		{
			if(event.getSource() instanceof CheckBox)
			{
				//If CheckBox is being selected, change the Pizza's status to "Delete"
				//If CheckBox is being unselected, change the Pizza's status to "In Order"
				CheckBox pizzaCheckBox = (CheckBox)event.getSource();
				
				if(pizzaCheckBox.isSelected())
				{
					pizza.setStatus("Delete");
				}
				else
				{
					pizza.setStatus("In Order");
				}

			}
			else if(event.getSource().equals(goToCheckOutButton))
			{
				if(inScrollPizzas.getChildren().size() != 0)
				{
					CurrentPizzaLog.setOrder(currentOrder);
					paneToSend = new CheckOutPane();
					updateCurrentViewPane();
				}
				else
					maxPizzaWarning.setText("Please add at least one pizza to your order");
				
			}
			else if(event.getSource().equals(deletePizzaButton))
			{

				for(int i = 0; i < currentOrder.getPizzas().size(); i++)
				{
					//System.out.println(currentOrder);
					if(currentOrder.getPizzas().get(i).getStatus().equals("Delete"))
					{
						currentOrder.removePizza(currentOrder.getPizzas().get(i));
						//System.out.println("after delete of " + i + " " + currentOrder.toString());
					    inScrollPizzas.getChildren().remove(i, i + 1);
					    i--;
					    maxPizzaWarning.setText("");
					    count--;
					}
				}
				//System.out.println("\nafter delete: " + currentOrder.toString());
				currentOrder.updateOrderTotal();
				orderTotalLabel.setText("Order Total: $" + currentOrder.getOrderTotal());
			}
			
		}//handle(ActionEvent)
		
	}//OrderSummaryHandler

}//PlaceOrderPane
