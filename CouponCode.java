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
import java.text.DecimalFormat;

public class CouponCode {

	//Instance Variables
	private String type;
	private LocalDate startDate;
	private LocalDate endDate;
	private int discountPercentage;
	private String couponText;
	
	private String status;
	
	//Constructors
	public CouponCode()
	{
		type = "";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		discountPercentage = 0;
		couponText = "";
		status = "";
	}//CouponCode()
	
	public CouponCode(String type, LocalDate startDate, LocalDate endDate, int discountPercentage, String couponText, String status)
	{
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountPercentage = discountPercentage;
		this.couponText = couponText;
		this.status = status;
	}//CouponCode()
	
	//Accessors
	public String getType()
	{
		return type;
	}
	
	public LocalDate getStartDate()
	{
		return startDate;
	}
	
	public LocalDate getEndDate()
	{
		return endDate;
	}
	
	public int getDiscountPercentage()
	{
		return discountPercentage;
	}
	
	public String getCouponText()
	{
		return couponText;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	//Mutators
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}
	
	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}
	
	public void setDiscountPercentage(int discountPercentage)
	{
		this.discountPercentage = discountPercentage;
	}
	
	public void setCouponText(String couponText)
	{
		this.couponText = couponText;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
	//toString()
	public String toString()
	{
		String couponSum = "";
		DecimalFormat df = new DecimalFormat("0.00");
		couponSum = couponSum + type +
				"\n\nFrom: " + startDate.toString() +
				"\nTo: " + endDate.toString() +
				"\n\nDiscount Percentage: " + discountPercentage + "%" +
				"\n(Ex: $10 becomes $" + df.format(((double)discountPercentage/100.0) * 10.0) + ")" +
				"\n\nCoupon Code: " + couponText;
		
		return couponSum;
	}//toString
	
	
}//CouponCode
