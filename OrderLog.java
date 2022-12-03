/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 11/6/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * OrderLog.java is a "static" class that contains communal information about the different
 * lists of orders, as well as the lists of pizzas in the different categories of completion.
 * OrderLog should not be instantiated, but rather statically referenced to access the elements.
 */

package application;

import java.time.LocalTime;
import java.util.ArrayList;

public class OrderLog 
{

	private static ArrayList<Pizza> pizzaListPrep = new ArrayList<Pizza>();
	private static ArrayList<Pizza> pizzaListBaking = new ArrayList<Pizza>();
	private static ArrayList<Pizza> pizzaListDone = new ArrayList<Pizza>();
	private static ArrayList<Order> orderList = new ArrayList<Order>();
	
	/*****Constructor for OrderLog. DO NOT USE. Access references statically.******/
	
	public OrderLog() {}
	
	/***********************************ACCESSORS************************************/
	
	public static int getOrderQuantity()
	{return orderList.size();}
	
	public static int getPrepPizzaQuantity()
	{return pizzaListPrep.size();}

	public static int getBakingPizzaQuantity()
	{return pizzaListBaking.size();}

	public static int getDonePizzaQuantity()
	{return pizzaListDone.size();}
	
	public static Order getOrderByIndex(int index)
	{return orderList.get(index);}
	
	public static Pizza getPrepPizzaByIndex(int index)
	{return pizzaListPrep.get(index);}
	
	public static Pizza getBakingPizzaByIndex(int index)
	{return pizzaListBaking.get(index);}
	
	public static Pizza getDonePizzaByIndex(int index)
	{return pizzaListDone.get(index);}
	
	public static Order getOrderByNumber(int orderNum)
	{	
		// Traverse all orders, matching their order numbers
		for (int i = 0; i < orderList.size(); i++)
		{
			if (orderNum == orderList.get(i).getOrderNumber())
				return orderList.get(i);
		}
		
		System.out.println("No order found with order number " + orderNum);
		return null;
	}
	
	public static Pizza getPizzaByID(int pizzaID)
	{	
		// Traverse all pizza lists, matching their IDs
		for (int i = 0; i < pizzaListPrep.size(); i++)
		{
			if (pizzaID == pizzaListPrep.get(i).getPizzaID())
				return pizzaListPrep.get(i);
		}
		
		for (int i = 0; i < pizzaListBaking.size(); i++)
		{
			if (pizzaID == pizzaListBaking.get(i).getPizzaID())
				return pizzaListBaking.get(i);
		}
		
		for (int i = 0; i < pizzaListDone.size(); i++)
		{
			if (pizzaID == pizzaListDone.get(i).getPizzaID())
				return pizzaListDone.get(i);
		}
		
		System.out.println("No order found with pizza number " + pizzaID);
		return null;
	}
	
	/**
	 * getOrdersByASURITE: returns a list of all orders confirmed
	 * by a specific student's ID number.
	 * @param ASURITE The ASURITE ID of a student
	 * @return An ArrayList of orders.
	 */
	public static ArrayList<Order> getOrdersByASURITE(int ASURITE)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < orderList.size(); i++)
		{
			if (orderList.get(i).getAsuriteID() == ASURITE)
				orders.add(orderList.get(i));
		}
		return orders;
	}
	
	/************************************MUTATORS************************************/
	
	/** addOrder:
	 * Adds an order to the orderList and adds each
	 * of the pizzas in the new order to the pizzaListPrep.
	 * Then, the ChefDisplay is updated to reflect the additions.
	 * @param order	An order to be added
	 * @return A boolean that is true if successful, false otherwise
	 */
	public static boolean addOrder(Order order)
	{
		try
		{
			//Place new order into list by sorting
			order.setOrderNumber(getNewOrderNumber());
			addSorted(order);

			//Add each pizza in the new order to the pizzaListPrep arraylist
			int i = 0;
			for (Pizza pizza : order.getPizzas())
			{
				pizza.setStatus("Preparation");
				pizza.setPizzaID(order.getOrderNumber() + i);
				addSorted(pizza);
				i++;
			}
			
			// Update the Chef view now that new order was added
			ChefPane.ChefEmployeePortalPane.updateDisplay();
			updateOrderStatus(order);
		}
		catch (Exception e)
		{
			System.out.println("ERROR: Cannot add order to OrderLog.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	/** removePizza:
	 * Removes the pizza from the Order in orderList and from the pizzaListPrep in orderList.
	 * Then, updates the Chef view to reflect the changes. Removal should only occur when
	 * pizzas are in the prep List.
	 * @param pizza 
	 */
	public static void removePizza(Pizza pizza)
	{
		// Remove the pizza from the list from prepList
		for (int i = 0; i < pizzaListPrep.size(); i++)
		{
			if (pizza.equals(pizzaListPrep.get(i)))
			{
				pizzaListPrep.remove(i);
				break;
			}
		}
		
		// Remove the pizza from the order. If the order is empty, remove the order
		Order order = getOrderByNumber((pizza.getOrderNumber()));
		order.removePizza(pizza);
		if (order.getPizzas().size() == 0)
		{
			orderList.remove(order);
		}
		
		// Update the display to match the OrderLog
		ChefPane.ChefEmployeePortalPane.updateDisplay();
		updateOrderStatus(getOrderByNumber(pizza.getOrderNumber()));
	}
	
	/**********************************OTHER METHODS*************************************/
	
	/** movePrepToBaking:
	 * Moves a pizza from pizzaListPrep to pizzaListBaking.
	 * @param pizza the specific pizza to be moved
	 * @return A boolean that is true if successful, false otherwise
	 */
	public static boolean movePrepToBaking(Pizza pizza)
	{
		// Iterate through the pizzaListPrep arraylist
		for (int i = 0; i < pizzaListPrep.size(); i++)
		{
			// If the pizza arg is found in the List, move it and return
			if (pizzaListPrep.get(i).equals(pizza))
			{
				Pizza movedPizza = pizzaListPrep.remove(i);
				
				movedPizza.setStatus("Baking");
				pizzaListBaking.add(movedPizza);
				
				ChefPane.ChefEmployeePortalPane.updateDisplay();
				updateOrderStatus(getOrderByNumber(pizza.getOrderNumber()));
				return true;
			}
		}
		
		//Otherwise, pizza was not found in the preplist and cannot be moved
		System.out.println(pizza + " was not found in pizzaListPrep. Cannot be moved.");
		return false;
	}
	
	/** moveBakingToDone:
	 * Moves a pizza from pizzaListBaking to pizzaListDone.
	 * @param pizza the specific pizza to be moved
	 * @return A boolean that is true if successful, false otherwise
	 */
	public static boolean moveBakingToDone(Pizza pizza)
	{
		// Iterate through the pizzaListBaking arraylist
		for (int i = 0; i < pizzaListBaking.size(); i++)
		{
			// If the pizza arg is found in the List, move it and return
			if (pizzaListBaking.get(i).equals(pizza))
			{
				Pizza movedPizza = pizzaListBaking.remove(i);
				
				movedPizza.setStatus("Ready for Pickup");
				pizzaListDone.add(movedPizza);
				
				ChefPane.ChefEmployeePortalPane.updateDisplay();
				updateOrderStatus(getOrderByNumber(pizza.getOrderNumber()));
				return true;
			}
		}
		
		//Otherwise, pizza was not found in the list and cannot be moved
		System.out.println(pizza + " was not found in pizzaListBaking. Cannot be moved.");
		return false;
	}
	
	/** moveBakingToPrep:
	 * Moves a pizza from pizzaListBaking to pizzaListPrep.
	 * @param pizza the specific pizza to be moved
	 * @return A boolean that is true if successful, false otherwise
	 */
	public static boolean moveBakingToPrep(Pizza pizza)
	{
		// Iterate through the pizzaListBaking arraylist
		for (int i = 0; i < pizzaListBaking.size(); i++)
		{
			// If the pizza arg is found in the list, move it and return
			if (pizzaListBaking.get(i).equals(pizza))
			{
				Pizza movedPizza = pizzaListBaking.remove(i);
				
				movedPizza.setStatus("Preparation"); 
				addSorted(movedPizza);
				
				ChefPane.ChefEmployeePortalPane.updateDisplay();
				updateOrderStatus(getOrderByNumber(pizza.getOrderNumber()));
				return true;
			}
		}
		
		//Otherwise, pizza was not found in the list and cannot be moved
		System.out.println(pizza + " was not found in pizzaListBaking. Cannot be moved.");
		return false;
	}

	/***********************************HELPERS************************************/
	
	/** addSorted(order)
	 * Adds an order to the orderList, sorted by pick up time
	 * @param order The order being added to orderList
	 */
	private static void addSorted(Order order)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			// If the order being added has a sooner pick up time, then add before that element
			if (order.getPickupTime().compareTo(orderList.get(i).getPickupTime()) < 0)
			{
				orderList.add(i, order);
				return;
			}
		}
		// The order being added is not less than any pre-existing orders
		orderList.add(order);
	}
	
	
	/**
	 * Adds a pizza to the prepList, sorted by pickup time. 
	 * @param pizza
	 */
	private static void addSorted(Pizza pizza)
	{
		// Get the pick up time of the pizza to add
		LocalTime pizzaPickupTime = getOrderByNumber(pizza.getOrderNumber()).getPickupTime();
		
		for (int i = 0; i < pizzaListPrep.size(); i++)
		{
			// Get the pickup time of a pizza in the PrepList
			LocalTime listPizzaPickupTime = getOrderByNumber(pizzaListPrep.get(i).getOrderNumber()).getPickupTime();
			
			// If the pizza we are adding must be picked up sooner, add in front of other pizza
			if (pizzaPickupTime.compareTo(listPizzaPickupTime) < 0)
			{
				pizzaListPrep.add(i, pizza);
				return;
			}
		}
		
		// Add pizza to the end of the list, latest pick up time
		pizzaListPrep.add(pizza);
	}
	
	private static void updateOrderStatus(Order order)
	{
		//We have a list of pizzas
		boolean isPrepPizza = false;
		boolean isBakingPizza = false;
		for (int i = 0; i < order.getPizzas().size(); i++)
		{
			if (order.getPizzas().get(i).getStatus().equals("Preparation"))
				isPrepPizza = true;
			else if (order.getPizzas().get(i).getStatus().equals("Baking"))
				isBakingPizza = true;
		}
		
		if (isPrepPizza) 					//There's at least one prep pizza, so the order status is "ReadyToCook"
			order.setStatus("ReadyToCook");
		else if (isBakingPizza) 			//No prepped pizzas but at least one still baking, so "Cooking"
			order.setStatus("Cooking");
		else								//Neither prepped nor baking pizzas, so they are all done
			order.setStatus("Ready");		
			
	}
	
	/**
	 * Returns the next valid order number in the system
	 * @return
	 */
	private static int getNewOrderNumber()
	{return (100 + orderList.size())*10;}
	
	/**********************************TO-STRING***********************************/
	
	public String toString() 
	{
		return "Number of Orders: " + orderList.size() + "\n"
				+ "Number of Pizzas: " + (pizzaListPrep.size()+pizzaListBaking.size()+pizzaListDone.size()) + "\n"
				+ "\tPrepped: " + pizzaListPrep.size() + "\n"
				+ "\tBaking: " + pizzaListBaking.size() + "\n"
				+ "\tDone: " + pizzaListDone.size();
	}
	
}
//TODO: Commit Changes
