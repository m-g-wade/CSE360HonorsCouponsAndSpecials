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
import java.time.LocalDate;
import java.util.*;
import javafx.stage.*;


public class ManagerViewActivePane extends ManagerViewPane{
	
	
	public ManagerViewActivePane()
	{
		super();
		
		for(int i = 0; i < SpecialsAndCouponLog.getActiveCoupons().size(); i++)
			super.leftInScrollVBox.getChildren().add(new OneCouponView(SpecialsAndCouponLog.getActiveCoupons().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getToBeActiveCoupons().size(); i++)
			super.leftInScrollVBox.getChildren().add(new OneCouponView(SpecialsAndCouponLog.getToBeActiveCoupons().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getActiveSpecials().size(); i++)
			super.rightInScrollVBox.getChildren().add(new OneSpecialView(SpecialsAndCouponLog.getActiveSpecials().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getToBeActiveSpecials().size(); i++)
			super.rightInScrollVBox.getChildren().add(new OneSpecialView(SpecialsAndCouponLog.getToBeActiveSpecials().get(i)));
		
	}//ManagerViewActivePane()
	
	

}//ManagerViewActivePane
