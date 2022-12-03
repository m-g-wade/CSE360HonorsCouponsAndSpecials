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
 * to order from constructed using javafx. Order represents one group of
 * Pizzas purchased by a customer in the same transaction.
 */

package application;

import java.util.*;
import java.time.*;

/**
 * TODO: Fix description
 * The class Order represents the group of Pizzas that one customer 
 * purchases in the same transaction. The Order contains the pizzas, 
 * orderTotal, orderNumber, status, pickupTime, and asuriteID. A 
 * single Order associates with one or multiple Pizzas. All Orders 
 * created are saved in the ArrayList instance variable orderList 
 * of the WebsitePane class.
 */
public class Order 
{
	
	private ArrayList<Pizza> pizzas;
	private double orderTotal;
	private int orderNumber;
	private String status;
	private LocalTime pickupTime;
	private int asuriteID;
	
	/**
	 * TODO: Description
	 */
	public Order()
	{
		pizzas = new ArrayList<Pizza>();
		orderTotal = 0;
		orderNumber = -1;
		status = "?";
		pickupTime = LocalTime.MAX;
		asuriteID = -1;		
	}//Order() Default Constructor
	
	/**
	 * TODO: Description
	 */
	public Order(ArrayList<Pizza> pizzas)
	{
		this.pizzas = pizzas;
		this.orderTotal = 0;		
		updateOrderTotal();
		this.orderNumber = -1;
		this.status = "Not Approved";
		this.pickupTime = LocalTime.MAX;
		this.asuriteID = -1;
	}//Order(ArrayList<Pizza> pizzas, String status)
	
	/**
	 * TODO: Description
	 */
	public Order(ArrayList<Pizza> pizzas, int orderNumber, LocalTime pickupTime, int asuriteID)
	{
		this.pizzas = pizzas;
		this.orderTotal = 0;
		updateOrderTotal();
		this.orderNumber = -1;
		this.pickupTime = pickupTime;
		this.asuriteID = asuriteID;
	}//OrderOrder(ArrayList<Pizza> pizzas, int orderNumber, LocalTime pickupTime, int asuriteID)
	
	//Mutators
	public void setPizzas(ArrayList<Pizza> pizzas)
	{
		this.pizzas = pizzas;
	}
	
	public void setOrderNumber(int orderNum)
	{
		this.orderNumber = orderNum;
	}
	
	public void setOrderTotal(int orderTotal)
	{
		this.orderTotal = orderTotal;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setPickupTime(LocalTime pickupTime)
	{
		this.pickupTime = pickupTime;
	}
	
	public void setAsuriteID(int asuriteID)
	{
		this.asuriteID = asuriteID;
	}
	
	public void updateOrderTotal()
	{
		this.orderTotal = 0;
		for(int i = 0; i < pizzas.size(); i++)
		{
			this.orderTotal += pizzas.get(i).getPrice();
		}
	}
	
	//Accessors
	public ArrayList<Pizza> getPizzas()
	{
		return pizzas;
	}
	
	public double getOrderTotal()
	{
		return orderTotal;
	}
	
	public int getOrderNumber()
	{
		return orderNumber;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public LocalTime getPickupTime()
	{
		return pickupTime;
	}
	
	public int getAsuriteID()
	{
		return asuriteID;
	}
	
	public void addPizza(Pizza pizza)
	{
		pizzas.add(pizza);
	}
	
	public void removePizza(Pizza pizza)
	{
		pizzas.remove(pizza);
	}
	
	
	public String toString()
	{
		String str= "\nPizzas: ";
		for(int i = 0; i < pizzas.size(); i++)
		{
			str += "\n" + pizzas.get(i).toString() + "\n";
		}
		str += "Total: " + orderTotal + "\nOrder Number: " + orderNumber
				+ "\nStatus: " + status + "\nPickup Time: " + pickupTime
				+ "\nASURITE ID: " + asuriteID + "\n";
		return str;
	}

}//Order
