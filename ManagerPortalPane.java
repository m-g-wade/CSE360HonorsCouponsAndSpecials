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
import java.time.LocalTime;
import java.util.*;


public class ManagerPortalPane extends BorderPane
{
	//Top UI Elements and Controls
		private VBox websiteTop;
		private Image logo;
		private ImageView logoView;
		
		private HBox menuBar;
		private Button addCouponButton;
		private Button addSpecialButton;
		private Button viewActiveButton;
		private Button viewEndedButton;
		
		//Center UI Elements
		private HBox currentViewPane;
		
		//Opening and Closing LocalTimes
		private LocalTime openingTime;
		private LocalTime closingTime;
		
		//Temporary ID Number, Pizza, and Order for use between classes
		private int currentIDNum;
		private Pizza currentPizza;
		private Order currentOrder;
		
		//ArrayLists of approved/validated ID Numbers
		private int[] employeeIDNums = {1918870234, 1268441865, 1661701324, 1768872264, 1207957309};
		private int[] studentIDNums = {1430868335, 1813959949, 1418938200, 1350969234, 1752057811};
		
		
		//Constructor
		public ManagerPortalPane()
		{
			//Setup Layout
			//logo = new Image("Clouds_and_trees_2.jpg");
			logoView = new ImageView();
			//logoView.setImage(logo);
			
			addCouponButton = new Button("Add Coupon Code");
			addSpecialButton = new Button("Add Special");
			viewActiveButton = new Button("View Active and to be Active");
			viewEndedButton = new Button("View Canceled and Ended");
			
			menuBar = new HBox();
			menuBar.getChildren().addAll(addCouponButton, addSpecialButton, viewActiveButton, viewEndedButton);
			menuBar.setAlignment(Pos.CENTER);
			menuBar.setSpacing(20);
			
			websiteTop = new VBox();
			websiteTop.getChildren().addAll(logoView, menuBar);
			websiteTop.setSpacing(10);
			
			currentViewPane = new ManagerAddCouponPane();
			
			this.setTop(websiteTop);
			this.setCenter(currentViewPane);
			
			//Bind buttons to Handler

			addCouponButton.setOnAction(new NavigationButtonHandler());
			addSpecialButton.setOnAction(new NavigationButtonHandler());
			viewActiveButton.setOnAction(new NavigationButtonHandler());
			viewEndedButton.setOnAction(new NavigationButtonHandler());
			
		}//ManagerPortalPane()
		
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
				if(event.getSource().equals(addCouponButton))
				{
					currentViewPane = new ManagerAddCouponPane();
					updateCurrentViewPane(currentViewPane);
				}
				
				else if(event.getSource().equals(addSpecialButton))
				{
					currentViewPane = new ManagerAddSpecialPane();
					updateCurrentViewPane(currentViewPane);
				}
				
				else if(event.getSource().equals(viewActiveButton))
				{
					currentViewPane = new ManagerViewActivePane();
					updateCurrentViewPane(currentViewPane);
				}
				
				else if(event.getSource().equals(viewEndedButton))
				{
					currentViewPane = new ManagerViewEndedPane();
					updateCurrentViewPane(currentViewPane);
				}

			}//handle(ActionEvent)
			
		}//NavigationButtonHandler
}//ManagerPortalPane
