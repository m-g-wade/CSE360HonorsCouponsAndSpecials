package application;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OneSpecialView extends HBox
{
	CheckBox specialCheckBox;
	Label status;
	PromotionalDisplay display;
	
	Special special;
	
	public OneSpecialView()
	{
		specialCheckBox = new CheckBox();
		status = new Label();
		display = new PromotionalDisplay();
		
		special = new Special();
	}//oneSpecialView()
	
	public OneSpecialView(Special special)
	{
		this.special = special;
		
		specialCheckBox = new CheckBox();
		status = new Label();
		display = new PromotionalDisplay();

		specialCheckBox.setText(special.toString());
		
		this.getChildren().addAll(specialCheckBox);
		}//oneSpecialView()
	
}//oneSpecialView