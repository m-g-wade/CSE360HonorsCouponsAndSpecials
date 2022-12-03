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
 * ViewOrderStatusPane is a pane that allows the user to display the current
 * status of all their orders registered under the ASURITE ID.
 */

package application;

import java.util.*;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewOrderStatusPane extends VBox {
	
	private ScrollPane orderScroll;
	private VBox orderDisplay;
	private Label noOrdersLabel;
	private OneOrderDisplay disOrder;

	public ViewOrderStatusPane(int asuID){
		
		// Set up the Panes
		this.setSpacing(10);
		
		orderScroll = new ScrollPane(); 
		
		orderDisplay = new VBox();
		orderDisplay.setSpacing(10);
		
		
		// Get an arrayList of orders
		ArrayList<Order> orders = OrderLog.getOrdersByASURITE(asuID);
		
		// Label for no Orders
		noOrdersLabel = new Label("There are no orders for this ASU ID."+ asuID);

		if(orders.size() == 0) 
		{
			orderDisplay.getChildren().add(noOrdersLabel);
		}
		else 
		{
			for (int i = 0; i < orders.size(); i++)
			{
				disOrder = new OneOrderDisplay(orders.get(i));
				orderDisplay.getChildren().add(disOrder);
			}
		}
		orderScroll.setContent(orderDisplay);
		this.getChildren().add(orderScroll);
	}
}
