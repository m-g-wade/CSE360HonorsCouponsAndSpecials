/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
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
 * PizzaApplication is a simulated restaurant web page for ASU students
 * to order from constructed using javafx. WebsitePane is the principal
 * and base element of the UI.
 */

package application;

import javafx.event.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.time.LocalTime;
import java.util.*;
import javafx.scene.text.*;


public class WebsitePane extends BorderPane{
	
	//Top UI Elements and Controls
	private VBox websiteTop;
	private Image logo;
	private ImageView logoView;
	
	private HBox menuBar;
	private Button homePageButton;
	private Button websitePlaceOrderButton;
	private Button viewOrderStatusButton;
	private Button chefEmployeePortalButton;
	
	//Center UI Elements
	private HBox currentViewPane;
	
	/**
	 * The layout of WebsitePane specified in the constructor contains the outer 
	 * frame that is constant and a central currentViewPane, which can be a HomePagePane, 
	 * PlaceOrderPane,CheckOutPane, CustomerLoginPane, ViewOrderStatusPane, ChefLoginPane, 
	 * or ChefEmployeePortalPane.
	 */
	public WebsitePane()
	{
		//Setup Layout
		//logo = new Image("Clouds_and_trees_2.jpg");
		logoView = new ImageView();
		//logoView.setImage(logo);
		
		homePageButton = new Button("Home Page");
		websitePlaceOrderButton = new Button("Place an Order");
		viewOrderStatusButton = new Button("View Order Status");
		
		menuBar = new HBox();
		menuBar.getChildren().addAll(homePageButton, websitePlaceOrderButton, viewOrderStatusButton);
		menuBar.setAlignment(Pos.CENTER);
		menuBar.setSpacing(20);
		
		websiteTop = new VBox();
		websiteTop.getChildren().addAll(logoView, menuBar);
		websiteTop.setSpacing(10);
		
		//Set Style
		this.setStyle("-fx-background-color: lightskyblue");
		homePageButton.setStyle("-fx-background-color: whitesmoke;" +
				"-fx-font: 13px 'Segoe UI Semibold';");
		websitePlaceOrderButton.setStyle("-fx-background-color: whitesmoke;" +
				"-fx-font: 13px 'Segoe UI Semibold';");
		viewOrderStatusButton.setStyle("-fx-background-color: whitesmoke;" +
				"-fx-font: 13px 'Segoe UI Semibold';");
		
		currentViewPane = new HomePagePane();
		
		this.setTop(websiteTop);
		this.setCenter(currentViewPane);
		
		
		//Bind Controls with their Handler
		homePageButton.setOnAction(new NavigationButtonHandler());
		websitePlaceOrderButton.setOnAction(new NavigationButtonHandler());
		viewOrderStatusButton.setOnAction(new NavigationButtonHandler());

	}//WebsitePane() Constructor

	/**
	 * The method updateCurrentViewPane sets the center of the WebsitePane to the 
	 * Pane/web page specified in the NavigationButtonHandler.
	 * @param currentViewPane
	 */
	public void updateCurrentViewPane(HBox currentViewPane)
	{
		this.setCenter(currentViewPane);
	}
	
	
	/**
	 * The class NavigationButtonHandler implements EventHandler<ActionEvent>. This handler is responsible for 
	 * providing the responses of all Buttons of WebsitePane that change the value of WebsitePane’s currentViewPane, 
	 * which represents a different page of the web site.
	 */
	public class NavigationButtonHandler implements EventHandler<ActionEvent>
	{
		/**
		 * The method handle responds to the navigation buttons of WebsitePane. On button press, handle instantiates
		 * an appropriate Pane for the current view and calls the method updateCurrentViewPane to set the view
		 * into the WebsitePane.
		 * @param event
		 */
		public void handle(ActionEvent event)
		{
			if(event.getSource().equals(homePageButton))
			{
				currentViewPane = new HomePagePane();
				updateCurrentViewPane(currentViewPane);
			}
			
			else if(event.getSource().equals(websitePlaceOrderButton))
			{
				currentViewPane = new PlaceOrderPane();
				updateCurrentViewPane(currentViewPane);
			}
			
			else if(event.getSource().equals(viewOrderStatusButton))
			{
				currentViewPane = new CustomerLoginPane();
				updateCurrentViewPane(currentViewPane);
			}
			
		}//handle(ActionEvent)
		
	}//NavigationButtonHandler
	
}//WebsitePane
