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

public class Special {
	
	LocalDate startDate;
	LocalDate endDate;
	String specialName;
	String advertisingMessage;
	//Image advertisingImage;
	Order order;
	
	String status;
	
	//Constructors
	public Special()
	{
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		specialName = "";
		advertisingMessage = "";
		order = new Order();
		status = "To Be Active";
	}//Special()
	
	public Special(LocalDate startDate, LocalDate endDate, String specialName, String advertisingMessage, Order order)
	{	
		this.startDate = startDate;
		this.endDate = endDate;
		this.specialName = specialName;
		this.advertisingMessage = advertisingMessage;
		this.order = order;
		this.status = "To Be Active";
	}//Special(All)
	
	
	//Accessors
	public LocalDate getStartDate()
	{
		return startDate;
	}
	
	public LocalDate getEndDate()
	{
		return endDate;
	}
	
	public String getSpecialName()
	{
		return specialName;
	}
	
	public String getMessage()
	{
		return advertisingMessage;
	}
	
	public Order getOrder()
	{
		return order;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	//Mutator
	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}
	
	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}
	
	public void setSpecialName(String specialName)
	{
		this.specialName = specialName;
	}
	
	public void setMessage(String advertisingMessage)
	{
		this.advertisingMessage = advertisingMessage;
	}
	
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	//toString()
	public String toString()
	{
		return "Start Date: " + this.getStartDate() +
				"\nEndDate: " + this.getEndDate() + 
				"\nSpecial Name: " + this.getSpecialName() + 
				"\nMessage: " + this.getMessage() + 
				"\nOrder: " + this.getOrder().toString() + 
				"\nStatus: " + this.getStatus();
	}

}//Special
