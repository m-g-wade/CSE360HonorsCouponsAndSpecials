/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Group Project: Group 40
 * 
 * Date File Created: 11/9/2022
 * Date Last Edited: 11/12/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * CurrentPizzaLog.java is a "static" class that contains communal information about the current
 * Pizza object or Order object that may need to be preserved between the switching of panes.
 * After the Order is created in PlaceOrderPane, it is saved here to be accessed by the CheckOutPane.
 */

package application;

import java.time.LocalTime;

public class CurrentPizzaLog {

		//Opening and Closing LocalTimes
		private static LocalTime openingTime;
		private static LocalTime closingTime;
		
		//Temporary Pizza and Order for use between classes
		private static Pizza currentPizza = new Pizza();
		private static Order currentOrder = new Order();
		
		
	public CurrentPizzaLog() {}//unused constructor
	
	//Accessors
	public static Pizza getPizza()
	{
		return currentPizza;
	}
	
	public static Order getOrder()
	{
		return currentOrder;
	}
	
	//Mutators
	public static void setPizza(Pizza pizza)
	{
		currentPizza = pizza;
	}
	
	public static void setOrder(Order order)
	{
		currentOrder = order;
	}
	
	//Apply Coupon
	public static void applyCoupon(CouponCode coupon)
	{
		if(coupon.getType().equals("Apply to whole order"))
			currentOrder.setOrderTotal((int)((double)currentOrder.getOrderTotal()*((double)coupon.getDiscountPercentage()/100.0)));
		else
		{
			int max = 0;
			for(int i = 0; i < currentOrder.getPizzas().size(); i++)
			{
				if(currentOrder.getPizzas().get(i).getPrice() > currentOrder.getPizzas().get(max).getPrice())
					max = i;
			}
			
			currentOrder.getPizzas().get(max).setPrice(currentOrder.getPizzas().get(max).getPrice() * (((double)coupon.getDiscountPercentage()/100.0)));
			currentOrder.updateOrderTotal();
		}
	}
	
}//CurrentPizzaLog
