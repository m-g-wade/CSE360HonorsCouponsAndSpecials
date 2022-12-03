/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 11/6/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * 	The class CheckOutPane is the view of WebsitePane where the user selects the 
 *  pickup time, enters their ASU ID and confirms the order. These elements of 
 *  CheckOutPane are bound to CheckOutHandler. On a successful order, a message is
 *  displayed and the user has Button options to place a new order or check order 
 *  status.
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

public class CheckOutPane extends HBox
{
	
	//left column
	private VBox timeVBox;
	
	private Label pickUpLabel;
	private ToggleGroup pickUpToggle;
	private RadioButton asapRadio;
	private Label estTimeLabel;
	private RadioButton chooseTimeRadio;
	private ComboBox<String> timeCombo;
	private Button changeOrderButton;
	
	private LocalTime estTime;
	private LocalTime pickUpTime;
	private LocalTime[] timeOptArray = new LocalTime[5];
	

	//middle column
	private VBox idVBox;
	
	private Label enterIDLabel;
	private Label enterIDSubLabel;
	private TextField enterIDTextField;
	private Label errorLabel;	
	
	private Label enterCouponLabel;
	private TextField enterCouponTF;
	private Label enterCouponSuccessLabel;

	
	//right column
	private VBox orderSumVBox;
	
	private Label orderSumLabel;
	private OrderSummaryDisplay orderSumDisplay;
	private Label orderTotalLabel;
	private Button confirmButton;
	
	private Pane paneToSend;
	
	private boolean couponSuccess;
	
	public CheckOutPane()
	{
		couponSuccess = false;
		
		//left column
		timeVBox = new VBox();
		
		pickUpLabel = new Label("Pick Up Time");
		
		pickUpToggle = new ToggleGroup();
		
		estTime = LocalTime.now().plusMinutes(30);
		pickUpTime = LocalTime.now().plusMinutes(30);
		
		asapRadio = new RadioButton("ASAP");
		estTimeLabel = new Label("Estimated Time: " + estTime.getHour() + ":" + String.format("%02d", estTime.getMinute()));
		asapRadio.setToggleGroup(pickUpToggle);
		asapRadio.setSelected(true);
		
		chooseTimeRadio = new RadioButton("Choose Time: ");
		chooseTimeRadio.setToggleGroup(pickUpToggle);
		
		timeCombo = new ComboBox<String>();
		
		timeOptArray[0] = estTime.plusMinutes(15);
		timeOptArray[1] = estTime.plusMinutes(30);
		timeOptArray[2] = estTime.plusMinutes(45);
		timeOptArray[3] = estTime.plusMinutes(60);
		timeOptArray[4] = estTime.plusMinutes(75);
		
		for(int i = 0; i < timeOptArray.length; i++)
		{
			String timeOptString = new String(timeOptArray[i].getHour() + ":" + String.format("%02d", timeOptArray[i].getMinute()));
			timeCombo.getItems().add(timeOptString);
		}
		
		timeCombo.getSelectionModel().select(0);
		
	    changeOrderButton = new Button("Make Changes to Order");
	    
	    //setStyle
	    pickUpLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
	    asapRadio.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
	    estTimeLabel.setStyle("-fx-font: 15px 'Segoe UI';");
	    chooseTimeRadio.setStyle("-fx-font: 15px 'Segoe UI Semibold';");
	    timeCombo.setStyle("-fx-font: 15px 'Segoe UI';");
		
	    timeVBox.getChildren().addAll(pickUpLabel, asapRadio, estTimeLabel, chooseTimeRadio, timeCombo, changeOrderButton);
	    timeVBox.setSpacing(5);
	    
	    //bind controls to handler
	    asapRadio.setOnAction(new CheckOutHandler());
	    chooseTimeRadio.setOnAction(new CheckOutHandler());
	    timeCombo.setOnAction(new CheckOutHandler());
	    changeOrderButton.setOnAction(new CheckOutHandler());
	    
	    
		//middle column
		idVBox = new VBox();
		
		enterIDLabel = new Label("Enter ASU ID");
		enterIDSubLabel = new Label("Order will be charged to your ASU account");
		enterIDTextField = new TextField();
		errorLabel = new Label("");	
		
		enterCouponLabel = new Label("Enter Coupon Code");
		enterCouponTF = new TextField();
		enterCouponSuccessLabel = new Label("");
		
		//setStyle
		enterIDLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		enterIDSubLabel.setStyle("-fx-font: 15px 'Segoe UI';");
		errorLabel.setStyle("-fx-font: 10px 'Segoe UI Semibold';" +
		"-fx-text-fill: red");
		changeOrderButton.setStyle("-fx-border-color: orange;" +
				"-fx-background-color: navajowhite;" +
				"-fx-font: 15px 'Segoe UI Semibold';");
		
		idVBox.getChildren().addAll(enterIDLabel, enterIDSubLabel, enterIDTextField, errorLabel, enterCouponLabel, enterCouponTF, enterCouponSuccessLabel);
		idVBox.setSpacing(5);
		
		
		
		//bind controls to handler
		enterIDTextField.setOnAction(new CheckOutHandler());
		enterCouponTF.setOnAction(new CheckOutHandler());
		
		//right column
		orderSumVBox = new VBox();
		
		orderSumLabel = new Label("Order Summary");
		orderSumDisplay = new OrderSummaryDisplay(CurrentPizzaLog.getOrder());
		orderTotalLabel = new Label("Order Total: $" + CurrentPizzaLog.getOrder().getOrderTotal());
		confirmButton = new Button("Confirm Order");
		
		//setStyle
		orderSumLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		orderTotalLabel.setStyle("-fx-font: 15px 'Segoe UI';");
		confirmButton.setStyle("-fx-border-color: green;" +
				"-fx-background-color: lightgreen;" +
				"-fx-font: 15px 'Segoe UI Semibold';");
		
		//bind controls to handler
		confirmButton.setOnAction(new CheckOutHandler());
		
		orderSumVBox.getChildren().addAll(orderSumLabel, orderSumDisplay, orderTotalLabel, confirmButton);
		orderSumVBox.setSpacing(5);
		
		//Temporary Panes to send to WebsitePane for navigation/to change the currentViewPane
		paneToSend = new Pane();
		
		timeVBox.setPadding(new Insets(5, 5, 5, 10));
		idVBox.setPadding(new Insets(5, 5, 5, 5));
		orderSumVBox.setPadding(new Insets(5, 10, 5, 5));
		
		this.getChildren().addAll(timeVBox, idVBox, orderSumVBox);
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		
	}//CheckOutPane()
	
	/**
	 * The method updateCurrentViewPane sets the currentViewPane
	 * of the WebsitePane to be the page that the user navigates
	 * to using the navigation buttons.
	 */

	public void updateCurrentViewPane()
	{
		((BorderPane)this.getParent()).setCenter(paneToSend);
	}
	
	public class CheckOutHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			//Make Changes to Order
			if(event.getSource().equals(changeOrderButton))
			{
				paneToSend = new PlaceOrderPane();
				updateCurrentViewPane();
			}
			//Switch Pick Up Time to ASAP
			else if(event.getSource().equals(asapRadio))
			{
				pickUpTime = estTime;
			}
			//Switch Pick Up Time to Chosen Time
			else if(event.getSource().equals(chooseTimeRadio))
			{
				pickUpTime = timeOptArray[timeCombo.getSelectionModel().getSelectedIndex()];
			}
			//Apply Coupon Code
			else if(event.getSource().equals(enterCouponTF))
			{
				for(int i = 0; i < SpecialsAndCouponLog.getActiveCoupons().size(); i++)
				{
					if(enterCouponTF.getText().equals(SpecialsAndCouponLog.getActiveCoupons().get(i).getCouponText()))
					{
						if(couponSuccess == false)
						{
							CurrentPizzaLog.applyCoupon(SpecialsAndCouponLog.getActiveCoupons().get(i));
						
							orderTotalLabel.setText("Order Total: $" + CurrentPizzaLog.getOrder().getOrderTotal());
							enterCouponSuccessLabel.setText("Coupon Code Applied");
							couponSuccess = true;
						}
						else
							enterCouponSuccessLabel.setText("Coupon Code Already Applied to This Order");
					}
				}
			}
			//Confirm the Order
			else if(event.getSource().equals(confirmButton))
			{
				try {
					if(Verification.isValidASUID(Integer.parseInt(enterIDTextField.getText())))
					{
						Order order = CurrentPizzaLog.getOrder();
						order.setPickupTime(pickUpTime);
						order.setAsuriteID(Integer.parseInt(enterIDTextField.getText()));
						
						OrderLog.addOrder(order);
						paneToSend = new CheckOutSuccessPane(pickUpTime, Integer.parseInt(enterIDTextField.getText()));
						CurrentPizzaLog.setOrder(new Order());
						updateCurrentViewPane();
					}
					else
					{
						errorLabel.setText("ASU ID Number not Valid");
					}
				}//try
				catch(NumberFormatException e)
				{
					errorLabel.setText("ASU ID Number not in correct integer format");
				}
			}

		}//handler
		
	}//CheckOutHandler
	
}//CheckOutPane
