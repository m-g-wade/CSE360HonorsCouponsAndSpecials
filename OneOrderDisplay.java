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

public class OneOrderDisplay extends HBox{
	

/*	private Label orderNumber;
	private Label accepted;
	private Label cook;
	private Label preparing;
	private Label ready; */
	
	private RadioButton acceptedStatus;
	private RadioButton cookStatus;
	private RadioButton preparingStatus;
	private RadioButton readyStatus;

	private BorderPane orderDetails;
	private VBox orderStatusVBox;
	private VBox orderSumVBox;
	private VBox numAndTimeVBox;		
	
	public OneOrderDisplay(Order order) {
		
		this.setSpacing(10);
		
	    acceptedStatus=new RadioButton("Order is Accepted!");
		cookStatus=new RadioButton("The chef is ready to cook");
		preparingStatus=new RadioButton("Food is preparing");
		readyStatus=new RadioButton("Food is Ready!");  
		
		orderDetails = new BorderPane();
		orderStatusVBox = new VBox();
		orderSumVBox = new VBox();
		numAndTimeVBox =new VBox();
		
		orderStatusVBox.getChildren().addAll(acceptedStatus,cookStatus,preparingStatus,readyStatus);
		orderSumVBox.getChildren().addAll(new OrderSummaryDisplay(order));
		
		if(order.getStatus().equals("Approved")) {
			acceptedStatus.setSelected(true);
		}
		else if(order.getStatus().equals("ReadyToCook")) {
			acceptedStatus.setSelected(true);
			cookStatus.setSelected(true);
		}
		else if(order.getStatus().equals("Cooking")) {
			acceptedStatus.setSelected(true);
			cookStatus.setSelected(true);
			preparingStatus.setSelected(true);
			
		}
		else if(order.getStatus().equals("Ready")) {
			acceptedStatus.setSelected(true);
			cookStatus.setSelected(true);
			preparingStatus.setSelected(true);
			readyStatus.setSelected(true);
		}
		
		String OrderString = "Order Number:"+ order.getOrderNumber()+ "\n Estimated Time:"+ order.getPickupTime().toString();
		Label numAndTime = new Label(OrderString);
		numAndTime.setMaxWidth(175);
		numAndTimeVBox.getChildren().addAll(numAndTime);
		
		orderDetails.setCenter(orderSumVBox);
		orderDetails.setLeft(orderStatusVBox);
		orderDetails.setRight(numAndTimeVBox);
		BorderPane.setMargin(orderSumVBox, new Insets(10));
		BorderPane.setMargin(orderStatusVBox, new Insets(10));
		BorderPane.setMargin(numAndTimeVBox, new Insets(10));

		this.getChildren().add(orderDetails);
	}//OneOrderDisplay()
}//OneOrderDisplay
