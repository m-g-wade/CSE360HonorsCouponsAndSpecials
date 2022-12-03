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

public class ManagerViewEndedPane extends ManagerViewPane{

	public ManagerViewEndedPane()
	{
		super();
		
		for(int i = 0; i < SpecialsAndCouponLog.getEndedCoupons().size(); i++)
			super.leftInScrollVBox.getChildren().add(new OneCouponView(SpecialsAndCouponLog.getEndedCoupons().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getCanceledCoupons().size(); i++)
			super.leftInScrollVBox.getChildren().add(new OneCouponView(SpecialsAndCouponLog.getCanceledCoupons().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getEndedSpecials().size(); i++)
			super.rightInScrollVBox.getChildren().add(new OneSpecialView(SpecialsAndCouponLog.getEndedSpecials().get(i)));
		
		for(int i = 0; i < SpecialsAndCouponLog.getCanceledSpecials().size(); i++)
			super.rightInScrollVBox.getChildren().add(new OneSpecialView(SpecialsAndCouponLog.getCanceledSpecials().get(i)));
		
	}//ManagerViewActivePane()
	
	
}
