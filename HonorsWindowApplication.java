/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 10/22/2022
 * Date Last Edited: 10/23/2022
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
import javafx.scene.layout.BorderPane;


public class HonorsWindowApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ManagerLoginPane root = new ManagerLoginPane();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}