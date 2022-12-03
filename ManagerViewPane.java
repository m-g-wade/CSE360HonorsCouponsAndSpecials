/*
 * Manager ViewPane is the parent of both ManagerViewActivePane
 * and ManagerViewEndedPane since the only change in structure is whic
 * list they source the elements of the lists from.
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


public class ManagerViewPane extends HBox{

	//Left Side
	VBox leftVBox;
	Label leftLabel;
	
	VBox leftInScrollVBox;
	ScrollPane leftScroll;
	Button leftDeleteButton;
	
	
	//Right Side
	VBox rightVBox;
	Label rightLabel;
	
	VBox rightInScrollVBox;
	ScrollPane rightScroll;
	Button rightDeleteButton;
	
	
	ManagerViewPane()
	{
		//Left Side
		leftVBox = new VBox();
		leftLabel = new Label("Coupon Codes");
		
	    leftInScrollVBox = new VBox();
	    leftScroll = new ScrollPane();
	    
	    leftScroll.setContent(leftInScrollVBox);
	    
	    leftVBox.getChildren().addAll(leftLabel, leftScroll);
	    
	    //Right Side
	    rightVBox = new VBox();
	  	rightLabel = new Label("Specials");
	  		
	  	rightInScrollVBox = new VBox();
	  	rightScroll = new ScrollPane();
	  	
	  	rightScroll.setContent(rightInScrollVBox);
		
	  	rightVBox.getChildren().addAll(rightLabel, rightScroll);
	  	
	  	this.getChildren().addAll(leftVBox, rightVBox);
	  	this.setSpacing(20);	
		this.setAlignment(Pos.CENTER);
		
	}//ManagerViewPane()
	
	
	
	
}//ManagerViewPane
