package application;

import java.util.ArrayList;
import application.PlaceOrderPane.OrderSummaryHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderSummaryDisplay extends VBox{
	private ArrayList<Pizza> pizzas;
	private VBox inScrollPizzas;
	private ScrollPane orderSumScroll;
	
	public OrderSummaryDisplay(Order order) {
		
	inScrollPizzas =new VBox();
	pizzas = new ArrayList<Pizza>();
	pizzas = order.getPizzas();
	
	orderSumScroll= new ScrollPane();
	orderSumScroll.setMinHeight(94);
	orderSumScroll.setMaxHeight(94);
	orderSumScroll.setMinWidth(200);
	orderSumScroll.setMaxHeight(200);

	int j=0;
	while(j<pizzas.size()) { //add all pizzas to the scroll pane one by one
		String pizzaString = "Type: " + pizzas.get(j).getType() + "\nToppings: ";
		for(int i = 0; i < pizzas.get(j).getToppings().size(); i++)
			pizzaString += pizzas.get(j).getToppings().get(i) + ", ";
		pizzaString += "\n$" + pizzas.get(j).getPrice();
		
		Label pizzaDisplay = new Label(pizzaString);
		
		pizzaDisplay.setMaxWidth(175);
		
		inScrollPizzas.getChildren().addAll(pizzaDisplay);
		orderSumScroll.setContent(inScrollPizzas);
		j++;

		}//while
		
	this.getChildren().add(orderSumScroll);
		
	}//OrderSummaryDisplay()
}//OrderSummaryDisplay
