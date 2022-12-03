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
 * to order from constructed using javafx. Pizza represents a single pizza
 * object in the real world.
 */

package application;

import java.util.*;

/**
 * TODO:Fix description
 * The class Pizza represents a single pizza object in the real world, and so contains the necessary 
 * instance variables used by other classes to calculate prices and display the pizza’s contents. 
 * These variables include the type, toppings, price, status, and pizzaID. There can be many instances of 
 * Pizza created throughout the runtime of the application. All Pizzas are associated with an Order.
 * 
 * 
 */
public class Pizza 
{
	
	private String type;
	private ArrayList<String> toppings;
	private double price;
	private String status; //Not in Order, In Order, Delete, Preparation, Baking, Ready for Pickup
	private int pizzaID;   //The pizza ID is the ID of the pizza's Order plus a unique value 0-9.
	
	
	//Constructors
	public Pizza()
	{
		this.type = "?";
		this.toppings = new ArrayList<String>();
		this.price = -1;
		this.status = "?";
		this.pizzaID = -1;
	}//Pizza()
	
	public Pizza(String type, ArrayList<String> toppings, String status, int pizzaID)
	{
		this.type = type;
		this.toppings = toppings;
		this.price = 0;
		updatePrice();
		this.status = status;
		this.pizzaID = pizzaID;
	}//Pizza(type, toppings, status)
	
	
	//Mutators
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setToppings(ArrayList<String> toppings)
	{
		this.toppings = toppings;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void updatePrice()
	{
		this.price = 8;
		for(int i = 0; i < toppings.size(); i++)
		{
			this.price += 1;
		}
	}
	
	public void addTopping(String topping)
	{
		this.toppings.add(topping);
	}
	
	public void removeTopping(String topping)
	{
		this.toppings.remove(topping);
	}
	
	public void setPizzaID(int pizzaID)
	{
		this.pizzaID = pizzaID;
	}
	
	
	//Accessors
	public String getType()
	{
		return type;
	}
	
	public ArrayList<String> getToppings()
	{
		return toppings;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public int getPizzaID()
	{
		return pizzaID;
	}
	
	public int getOrderNumber()
	{
		return (pizzaID/10)*10;
	}
	
	public boolean equals(Pizza otherPizza)
	{
		if(this.type.equals(otherPizza.type) && this.price == otherPizza.price && this.status.equals(otherPizza.status)
			&& this.toppings.equals(otherPizza.toppings) && this.pizzaID == otherPizza.pizzaID)
				return true;
		else return false;
	}//equals
	
	public String toString()
	{
		String str = "\nPizza #" + pizzaID + ":\n\tType : " + type + "\n\tToppings:";
		for(int i = 0; i < toppings.size(); i++)
		{
			str += "\n\t\t" + toppings.get(i);
		}
		str += "\n\tPrice: " + price + "\n\tStatus: " + status;
		return str;
	}

}//Pizza
