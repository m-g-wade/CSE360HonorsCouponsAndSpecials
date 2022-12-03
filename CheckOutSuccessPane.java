/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 11/13/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * 	The class CheckOutSuccessPane is the view of WebsitePane after the user
 *  successfully confirms their order. This pane shows the user a summary of
 *  their order with a thank you message and allows them place a new order or
 *  check their order status directly.
 */

package application;

import java.util.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import java.time.LocalTime;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.util.Random;
import java.util.ArrayList;


public class CheckOutSuccessPane extends HBox{
	
	//right column
	private VBox orderSumVBox;
	
	private Label orderSumLabel;
	private OrderSummaryDisplay orderSumDisplay;
	private Label orderTotalLabel;
	
	//success display
	private VBox successVBox;
	private Label successLabel;
	private Label thankYouLabel;
	private Label successPickUpInfo;
	private Button placeNewOrderButton;
	private Button checkOrderStatusButton;

	private LocalTime pickUpTime;
	private int idNum;
	
	private Pane paneToSend;
	
	
	public CheckOutSuccessPane(LocalTime pickUpTime, int idNum)
	{
		this.pickUpTime = pickUpTime;
		this.idNum = idNum;
		
		placeNewOrderButton = new Button("Place New Order");
		checkOrderStatusButton = new Button("Check Order Status");
		
		//success display
		successVBox = new VBox();
		
		successLabel = new Label("Order Successfully Placed");
		thankYouLabel = new Label("Thank you for choosing Pie in the Sky!");
		successPickUpInfo = new Label("Pick Up Time: " + pickUpTime.getHour() + ":" + String.format("%02d", pickUpTime.getMinute()));
		
		successVBox.getChildren().addAll(successLabel, thankYouLabel, successPickUpInfo, placeNewOrderButton);
		
		//setstyle
		successLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		thankYouLabel.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		successPickUpInfo.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
		
		placeNewOrderButton.setStyle("-fx-border-color: tomato;" +
				"-fx-background-color: lightgray;" +
				"-fx-font: 10px 'Segoe UI Semibold';");
		checkOrderStatusButton.setStyle("-fx-border-color: green;" +
				"-fx-background-color: lightgreen;" +
				"-fx-font: 10px 'Segoe UI Semibold';");
		
		//bind controls to handler
		placeNewOrderButton.setOnAction(new CheckOutSuccessHandler());
		checkOrderStatusButton.setOnAction(new CheckOutSuccessHandler());
		
		//right column
		orderSumVBox = new VBox();
				
		orderSumLabel = new Label("Order Summary");
		orderSumDisplay = new OrderSummaryDisplay(CurrentPizzaLog.getOrder());
		orderTotalLabel = new Label("Order Total: $" + CurrentPizzaLog.getOrder().getOrderTotal());
				
		orderSumVBox.getChildren().addAll(orderSumLabel, orderSumDisplay, orderTotalLabel, checkOrderStatusButton);

		this.getChildren().addAll(successVBox, orderSumVBox);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
		paneToSend = new Pane();
		
	}
	
	/**
	 * The method updateCurrentViewPane sets the currentViewPane
	 * of the WebsitePane to be the page that the user navigates
	 * to using the navigation buttons.
	 */
	public void updateCurrentViewPane()
	{
		((BorderPane)this.getParent()).setCenter(paneToSend);
	}
	
	public class CheckOutSuccessHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			//Place New Order
			if(event.getSource().equals(placeNewOrderButton))
			{
				CurrentPizzaLog.setOrder(new Order());
				paneToSend = new PlaceOrderPane();
				updateCurrentViewPane();
			}
			//Check Order Status Directly
			else if(event.getSource().equals(checkOrderStatusButton))
			{
				CurrentPizzaLog.setOrder(new Order());
				paneToSend = new ViewOrderStatusPane(idNum);
				updateCurrentViewPane();
			}
		}
		
	}//CheckOutSuccessHandler
		
}//CheckOutSuccessPane
