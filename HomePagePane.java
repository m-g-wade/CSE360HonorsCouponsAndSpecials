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
 * 
 * HomePagePane is a pane that represents the landing page
 * for the user, with buttons that navigate either to the
 * ordering menu or the track status menu.
 */

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
* The class HomePagePane extends HBox and is one of the views of WebsitePane. 
* This is the first view that a user sees upon entering the application. 
* It contains UI elements such as the welcome message, tagline, and additional 
* navigation Buttons like Place an Order and Check Order Status.
*/
public class HomePagePane extends HBox
{
	private VBox homePageVBox;
	private Label welcomeLabel;
	private Label taglineLabel;
	
	private Button homePlaceOrderButton;
	private Button homeOrderStatusButton;
	
	private HBox paneToSend;
	
	public HomePagePane()
	{
		//Setup Layout
		welcomeLabel = new Label("Welcome to Pie in the Sky!");
		taglineLabel = new Label("Your home to quality pizza, right on campus!");
		
		welcomeLabel.setStyle("-fx-font: 30px 'Segoe UI Semibold';");
		taglineLabel.setStyle("-fx-font: 20px 'Segoe UI Semibold';");
		
		homePlaceOrderButton = new Button("Place an Order!");
		homeOrderStatusButton = new Button("Check Order Status");
		
		homePlaceOrderButton.setStyle("-fx-border-color: green;" +
				"-fx-background-color: lightgreen;" +
				"-fx-font: 30px 'Segoe UI Semibold';");
		homeOrderStatusButton.setStyle("-fx-border-color: orange;" +
				"-fx-background-color: navajowhite;" +
				"-fx-font: 15px 'Segoe UI Semibold';");
		
		homePageVBox = new VBox();
		homePageVBox.getChildren().addAll(welcomeLabel, taglineLabel, homePlaceOrderButton, homeOrderStatusButton);
		homePageVBox.setAlignment(Pos.CENTER);
		homePageVBox.setSpacing(40);
		
		//Bind Buttons to their Handler
		homePlaceOrderButton.setOnAction(new HomePageHandler());
		homeOrderStatusButton.setOnAction(new HomePageHandler());
		
		//Set Style
		
		
		this.getChildren().addAll(homePageVBox);
		this.setAlignment(Pos.CENTER);
		
	}//HomePagePane() Constructor
	
	/**
	 * The method updateCurrentViewPane sets the currentViewPane
	 * of the WebsitePane to be the page that the user navigates
	 * to using the navigation buttons of homePlaceOrderButton
	 * and homeOrderStatusButton. This method is called by the HomePageHandler.
	 */
	public void updateCurrentViewPane()
	{
		((BorderPane)this.getParent()).setCenter(paneToSend);
	}
	
	
	/**
	 *  The class HomePageHandler implements EventHandler<ActionEvent>. This handler is responsible for 
	 * providing the responses of all Buttons of HomePagePane that change the value of WebsitePane’s 
	 * currentViewPane, which represents a different page of the web site.
	 */
	public class HomePageHandler implements EventHandler<ActionEvent>
	{
		/**
		 * The method handle responds to the navigation buttons of WebsitePane. On button press, handle instantiates
		 * an appropriate Pane for the current view and calls the method updateCurrentViewPane to set the view
		 * into the WebsitePane.
		 * @param event
		 */
		public void handle(ActionEvent event)
		{
			if(event.getSource().equals(homePlaceOrderButton))
			{
				paneToSend = new PlaceOrderPane();
				updateCurrentViewPane();
			}
			
			else if(event.getSource().equals(homeOrderStatusButton))
			{
				//pane = new CustomerLoginPane();
				//updateCurrentViewPane(currentViewPane);
			}
			
		}//handle
	}//HomePageHandler
	
}//HomePagePane
