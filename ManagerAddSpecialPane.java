/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Honors Project: Coupons and Specials
 * 
 * Date File Created: 11/9/2022
 * Date Last Edited: 11/9/2022
 * 
 * Margaret Wade
 * 
 * TODO: Description
 */

package application;

import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.time.LocalDate;
import java.util.*;
import javafx.stage.*;

public class ManagerAddSpecialPane extends HBox{
	
	private Special special;
	
	//Left Column
	private VBox specialAddVBox;
	
	//Date Specification
	private GridPane dateGridPane;
	
	private Label startDateLabel;
	
	private Label startDayLabel;
	private Label startMonthLabel;
	private Label startYearLabel;
	
	private ComboBox<Integer> startDayCombo;
	private ComboBox<Integer> startMonthCombo;
	private ComboBox<Integer> startYearCombo;
	
	private Label endDateLabel;
	
	private Label endDayLabel;
	private Label endMonthLabel;
	private Label endYearLabel;
	
	private ComboBox<Integer> endDayCombo;
	private ComboBox<Integer> endMonthCombo;
	private ComboBox<Integer> endYearCombo;
	
	private Label dateErrorMessage;

	//Name, Message, And Image
	private Label nameLabel;
	private TextField nameTF;
	
	private Label messageLabel;
	private TextField messageTF; 
	
	private Label imageLabel;
	private FileChooser imageFileChooser;
	private Button imageButton;
	
	//Middle Column
	GridPane placeOrderGridPane;
	
	private Label typeLabel;
	private Label toppingsLabel;
	private Label priceLabel;
	
	private ToggleGroup typeToggleGroup;
	private RadioButton pepperoni;
	private RadioButton vegetable;
	private RadioButton cheese;
	
	private CheckBox mushroomsCheckBox;
	private CheckBox onionCheckBox;
	private CheckBox olivesCheckBox;
	private CheckBox extraCheeseCheckBox;
	
	private TextField priceTextField;
	private Label priceErrorMessage;
	
	private Button addToSpecialButton;
	
	//Right Column
	private VBox rightCol;
	
	private Label specialSumLabel;
	private Label startDateSumLabel;
	
	private Label endDateSumLabel;
	private Label nameSumLabel;
	private Label messageSumLabel;
	
	private ScrollPane sumScroll;
	private VBox sumVBox;
	private Button deleteButton;
	
	private Button confirmButton;
	
	private Pizza currentPizza;
	private Order currentOrder;
	private int count;
	
	
	public ManagerAddSpecialPane()
	{
		special = new Special();
		
		//Left Column-Special Details
		specialAddVBox = new VBox();
		specialAddVBox.setSpacing(5);
		
		//Start Date Select
		startDateLabel = new Label("Start Date: ");
		
		startDayLabel = new Label("Day");
		startMonthLabel = new Label("Month");
		startYearLabel = new Label("Year");
		
		startDayCombo = new ComboBox<Integer>();
		startMonthCombo = new ComboBox<Integer>();
		startYearCombo = new ComboBox<Integer>();
		
		//End Date Select
		endDateLabel = new Label("End Date: ");
		
		endDayLabel = new Label("Day");
		endMonthLabel = new Label("Month");
		endYearLabel = new Label("Year");
		
		endDayCombo = new ComboBox<Integer>();
		endMonthCombo = new ComboBox<Integer>();
		endYearCombo = new ComboBox<Integer>();
		
		for(int i = 1; i <= 31; i++)
		{
			startDayCombo.getItems().add(i);
			endDayCombo.getItems().add(i);
			
			if(i <= 12)
			{
				startMonthCombo.getItems().add(i);
				endMonthCombo.getItems().add(i);
				startYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
				endYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
			}
		}
		
		startDayCombo.getSelectionModel().select(LocalDate.now().getDayOfMonth() - 1);
		startMonthCombo.getSelectionModel().select(LocalDate.now().getMonthValue() - 1);
		startYearCombo.getSelectionModel().select(0);
		
		endDayCombo.getSelectionModel().select(LocalDate.now().getDayOfMonth() - 1);
		endMonthCombo.getSelectionModel().select(LocalDate.now().getMonthValue() - 1);
		endYearCombo.getSelectionModel().select(0);
		
		//Bind Date Combos to Handler
		startDayCombo.setOnAction(new DateHandler());
		startMonthCombo.setOnAction(new DateHandler());
		startYearCombo.setOnAction(new DateHandler());
		
		endDayCombo.setOnAction(new DateHandler());
		endMonthCombo.setOnAction(new DateHandler());
		endYearCombo.setOnAction(new DateHandler());
		
		dateErrorMessage = new Label("");
		
		//Date Grid Pane
		dateGridPane = new GridPane();
		
		dateGridPane.add(startDateLabel, 0, 1);
		dateGridPane.add(startDayLabel, 1, 0);
		dateGridPane.add(startMonthLabel, 2, 0);
		dateGridPane.add(startYearLabel, 3, 0);
		dateGridPane.add(startDayCombo, 1, 1);
		dateGridPane.add(startMonthCombo, 2, 1);
		dateGridPane.add(startYearCombo, 3, 1);
		
		dateGridPane.add(endDateLabel, 0, 3);
		dateGridPane.add(endDayLabel, 1, 2);
		dateGridPane.add(endMonthLabel, 2, 2);
		dateGridPane.add(endYearLabel, 3, 2);
		dateGridPane.add(endDayCombo, 1, 3);
		dateGridPane.add(endMonthCombo, 2, 3);
		dateGridPane.add(endYearCombo, 3, 3);
		
		dateGridPane.setHgap(5);
		dateGridPane.setVgap(5);
		
		special.setStartDate(LocalDate.now());
		special.setEndDate(LocalDate.now());
		
		dateErrorMessage = new Label("");
		
		//Name, Message
		nameLabel = new Label("Special Name:");
		nameTF = new TextField();
		
		nameTF.setOnAction(new ManagerAddSpecialHandler());
		
		messageLabel = new Label("Special Advertising Messaging:");
		messageTF = new TextField(); 
		
		messageTF.setOnAction(new ManagerAddSpecialHandler());
		
		specialAddVBox.getChildren().addAll(dateGridPane, dateErrorMessage, nameLabel, nameTF, messageLabel, messageTF);
		
		//Middle Column-Add Pizza 
		placeOrderGridPane = new GridPane();
		//TODO:Add Border
		
		typeLabel = new Label("Pizza Type:");
		toppingsLabel = new Label("Toppings:");
				
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
		
		priceLabel = new Label("Price: $");
		priceTextField = new TextField();
		priceErrorMessage = new Label("");
		
		priceTextField.setOnAction(new ManagerAddSpecialHandler());
		
		addToSpecialButton = new Button("Add to Special");
		
		//Type Bind to Handler
		pepperoni.setOnAction(new NewPizzaHandler(currentPizza));
		vegetable.setOnAction(new NewPizzaHandler(currentPizza));
		cheese.setOnAction(new NewPizzaHandler(currentPizza));
		
		//Toppings CheckBoxes Bind to Handler
		mushroomsCheckBox.setOnAction(new NewPizzaHandler(currentPizza));
		onionCheckBox.setOnAction(new NewPizzaHandler(currentPizza));
		olivesCheckBox.setOnAction(new NewPizzaHandler(currentPizza));
		extraCheeseCheckBox.setOnAction(new NewPizzaHandler(currentPizza));
		
		//addToSpecialButton Bind to Handler
		addToSpecialButton.setOnAction(new NewPizzaHandler(currentPizza));
		
		//Add elements to GridPane
		placeOrderGridPane.add(typeLabel, 0, 0);
		placeOrderGridPane.add(pepperoni, 1, 1);
		placeOrderGridPane.add(vegetable, 2, 1);
		placeOrderGridPane.add(cheese, 3, 1);
		placeOrderGridPane.add(toppingsLabel, 0, 2);
		placeOrderGridPane.add(mushroomsCheckBox, 1, 3);
		placeOrderGridPane.add(onionCheckBox, 1, 4);
		placeOrderGridPane.add(olivesCheckBox, 1, 5);
		placeOrderGridPane.add(extraCheeseCheckBox, 1, 6);
		placeOrderGridPane.add(priceLabel, 0, 7);
		placeOrderGridPane.add(priceTextField, 1, 7);
		placeOrderGridPane.add(priceErrorMessage, 2, 8);
		placeOrderGridPane.add(addToSpecialButton, 2, 8);
		
		placeOrderGridPane.setVgap(5);
		placeOrderGridPane.setHgap(5);
		
		//Right Column-Special Summary
		rightCol = new VBox();
		
		specialSumLabel = new Label("Special Summary");
		startDateSumLabel = new Label("From: ");
		endDateSumLabel = new Label("To: ");
		nameSumLabel = new Label("");
		messageSumLabel = new Label("");
		
		sumScroll = new ScrollPane();
		sumScroll.setMinHeight(200);
		sumScroll.setMaxHeight(200);
		sumScroll.setMinWidth(150);
		sumScroll.setMaxWidth(150);
		sumVBox = new VBox();
		
		deleteButton = new Button("Delete Selected Items");
		deleteButton.setOnAction(new NewPizzaHandler());
		
		sumScroll.setContent(sumVBox);
		
		confirmButton = new Button("Confirm and Add");
		confirmButton.setOnAction(new ManagerAddSpecialHandler());
		
		rightCol.getChildren().addAll(specialSumLabel, startDateSumLabel, endDateSumLabel, nameSumLabel, messageSumLabel, sumScroll, deleteButton, confirmButton);
		
		this.getChildren().addAll(specialAddVBox, placeOrderGridPane, rightCol);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(15);
		this.setPadding(new Insets(15, 2, 2, 2));
		
		
		//Temporary Variables
		currentPizza = new Pizza();
		currentPizza.setStatus("Not in Order");
		
		currentOrder = new Order();
		count = 0;
		
	}//ManagerAddSpecialPane()
	
	public class ManagerAddSpecialHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			if(event.getSource().equals(nameTF))
			{
				nameSumLabel.setText(nameTF.getText());
				special.setSpecialName(nameTF.getText());
			}
			else if(event.getSource().equals(messageTF))
			{
				messageSumLabel.setText(messageTF.getText());
				special.setMessage(messageTF.getText());
			}
			else if(event.getSource().equals(priceTextField))
			{
				currentPizza.setPrice(Double.parseDouble(priceTextField.getText()));
			}
			else if(event.getSource().equals(confirmButton))
			{
				Special newSpecial  = new Special(special.getStartDate(), special.getEndDate(), special.getSpecialName(), special.getMessage(), special.getOrder());
				newSpecial.setOrder(currentOrder);
				if(newSpecial.getStartDate().equals(LocalDate.now()) || newSpecial.getStartDate().isBefore(LocalDate.now()))
				{
					newSpecial.setStatus("Active");
				}
				SpecialsAndCouponLog.addSpecial(newSpecial);
			}
			
			
		}//handle()
		
	}//ManagerAddSpecialHandler
	
	public class NewPizzaHandler implements EventHandler<ActionEvent>
	{

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
			System.out.println("NewPizzaHandler handle run");
			
			if(!(pizza == null))
				currentPizza = pizza;
			
			if(currentPizza.getStatus().equals("Not in Order") || !(pizza == null)) 
			{
				//Change Pizza type
				if(event.getSource() instanceof RadioButton)
				{
					System.out.println("current order before change pizza type " + currentOrder.toString());
					RadioButton sourceButton = (RadioButton)event.getSource();
					currentPizza.setType(sourceButton.getText());
					System.out.println("current order after change pizza type " + currentOrder.toString());
				}
			
				//Add/Remove Topping from Pizza
				else if(event.getSource() instanceof CheckBox)
				{
					System.out.println("current order before change topping " + currentOrder.toString());
					CheckBox sourceCheckBox = (CheckBox)event.getSource();
					if(sourceCheckBox.isSelected())
					{
						currentPizza.addTopping(sourceCheckBox.getText());
					}
					else
					{
						currentPizza.removeTopping(sourceCheckBox.getText());
					}
					System.out.println("current order after change topping " + currentOrder.toString());
				
				}
			
			    //Add Pizza to Order Button Press TODO: Make cap of 10 pizzas in order
				else if(event.getSource().equals(addToSpecialButton) && count < 10)
				{
					//Create newPizza from currentPizza to add to the Order

					Pizza newPizza = new Pizza();
					newPizza.setStatus("In Order");
					for(int i = 0; i < currentPizza.getToppings().size(); i++)
						newPizza.addTopping(currentPizza.getToppings().get(i));
					newPizza.setType(currentPizza.getType());
					newPizza.setPrice(currentPizza.getPrice());
					newPizza.setPizzaID(count);
					System.out.println("current order before add: " + currentOrder.toString());
					currentOrder.addPizza(newPizza);
					currentOrder.updateOrderTotal();
				    System.out.println("current order after add" + currentOrder.toString());

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
					sumVBox.getChildren().addAll(newPizzaCheckBox);
					sumScroll.setContent(sumVBox);
				
					currentPizza.setStatus("Not in Order");

					
					if(pizza == null)
						count++;
				}//Add Pizza to Order
				

				
				if(!(pizza == null))
					currentPizza = new Pizza("Pepperoni", new ArrayList<String>(), "Not in Order", -1);
			
			}//New pizza (status = "Not in Order")
			
		}//handle(ActionEvent)
		
	}//NewPizzaHandler
	
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

			else if(event.getSource().equals(deleteButton))
			{

				for(int i = 0; i < currentOrder.getPizzas().size(); i++)
				{
					System.out.println(currentOrder);
					if(currentOrder.getPizzas().get(i).getStatus().equals("Delete"))
					{
						currentOrder.removePizza(currentOrder.getPizzas().get(i));
						System.out.println("after delete of " + i + " " + currentOrder.toString());
					    sumVBox.getChildren().remove(i, i + 1);
					    i--;
					    count--;
					}
				}
				System.out.println("\nafter delete: " + currentOrder.toString());
				currentOrder.updateOrderTotal();
			}
			
		}//handle(ActionEvent)
		
	}//OrderSummaryHandler
	
	
	public class DateHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			//StartDate
			if(event.getSource().equals(startYearCombo) || event.getSource().equals(startMonthCombo) || event.getSource().equals(startDayCombo))
			{
				if(LocalDate.of(startYearCombo.getValue(), startMonthCombo.getValue(), startDayCombo.getValue()).compareTo(LocalDate.now()) < 0)
					dateErrorMessage.setText("Start date cannot be before current date");
				else
					special.setStartDate(LocalDate.of(startYearCombo.getValue(), startMonthCombo.getValue(), startDayCombo.getValue()));
				
				startDateSumLabel.setText("From: " + special.getStartDate().toString());
			}
			
			//EndDate
			else if(event.getSource().equals(endYearCombo) || event.getSource().equals(endMonthCombo) || event.getSource().equals(endDayCombo))
			{
				if(LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()).compareTo(special.getStartDate()) < 0)
					dateErrorMessage.setText("End date must be after start date");
				else if (LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()).compareTo(LocalDate.now()) < 0)
					dateErrorMessage.setText("End date cannot be before the current date");
				else
					special.setEndDate(LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()));
				
				endDateSumLabel.setText("To: " + special.getEndDate().toString());
			}
			
	
		}//handle()
		
	}//DateHandler

}//ManagerAddSpecialPane
