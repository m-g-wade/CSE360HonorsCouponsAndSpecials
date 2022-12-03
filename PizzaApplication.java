/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Section: 70513-96561
 * Group Project: Group 40
 * 
 * Date File Created: 10/22/2022
 * Date Last Edited: 11/12/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * PizzaApplication is a simulated restaurant web page for ASU students
 * to order from constructed using javafx. 
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * 	The class PizzaApplication extends the class Application. This class contains 
 * the start method and creates the stage that the customer sees and uses to interface 
 * with our program. Inside the start method, an instance of the WebsitePane is added 
 * to the Scene, which is displayed on the Stage.
 *
 */
public class PizzaApplication extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			// Create a window for the student view
			WebsitePane root = new WebsitePane();
			Scene scene = new Scene(root, 1000, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			// Create a window for the chef view
			Stage secondaryStage = new Stage();
			ChefPane chefRoot = new ChefPane();
			Scene secondaryScene = new Scene(chefRoot, 600, 600);
			secondaryScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			secondaryStage.setScene(secondaryScene);
			
			// Create a window for the manager view
			Stage tertiaryStage = new Stage();
			ManagerPane managerRoot = new ManagerPane();
			Scene tertiaryScene = new Scene(managerRoot, 1000, 600);
			tertiaryScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			tertiaryStage.setScene(tertiaryScene);
			
			// Display the windows
			tertiaryStage.show();
			//secondaryStage.show();
			primaryStage.show();

		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}//start
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
