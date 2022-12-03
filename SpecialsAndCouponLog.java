package application;

import java.util.*;
import java.time.LocalDate;

public class SpecialsAndCouponLog {
	
	private static ArrayList<CouponCode> toBeActiveCoupons = new ArrayList<CouponCode>();
	private static ArrayList<CouponCode> activeCoupons = new ArrayList<CouponCode>();
	private static ArrayList<CouponCode> endedCoupons = new ArrayList<CouponCode>();
	private static ArrayList<CouponCode> canceledCoupons = new ArrayList<CouponCode>();
	
	private static ArrayList<Special> toBeActiveSpecials = new ArrayList<Special>();
	private static ArrayList<Special> activeSpecials = new ArrayList<Special>();
	private static ArrayList<Special> endedSpecials = new ArrayList<Special>();
	private static ArrayList<Special> canceledSpecials = new ArrayList<Special>();
	
	
	//Accessors
	public static ArrayList<CouponCode> getToBeActiveCoupons()
	{
		return toBeActiveCoupons;
	}
	
	public static ArrayList<CouponCode> getActiveCoupons()
	{
		return activeCoupons;
	}
	
	public static ArrayList<CouponCode> getCanceledCoupons()
	{
		return canceledCoupons;
	}
	
	public static ArrayList<CouponCode> getEndedCoupons()
	{
		return endedCoupons;
	}
	
	public static ArrayList<Special> getToBeActiveSpecials()
	{
		return toBeActiveSpecials;
	}
	
	public static ArrayList<Special> getActiveSpecials()
	{
		return activeSpecials;
	}
	
	public static ArrayList<Special> getCanceledSpecials()
	{
		return canceledSpecials;
	}
	
	public static ArrayList<Special> getEndedSpecials()
	{
		return endedSpecials;
	}
	
	//Other Methods
	public static void addCoupon(CouponCode coupon)
	{
		if(coupon.getStatus().equals("To Be Active"))
			toBeActiveCoupons.add(coupon);
		else
			activeCoupons.add(coupon);
	}
	
	public static void addSpecial(Special special)
	{
		if(special.getStatus().equals("To Be Active"))
			toBeActiveSpecials.add(special);
		else
			activeSpecials.add(special);
		
	}
	
	public static boolean activateCoupon(CouponCode coupon)
	{
		for(int i = 0; i < toBeActiveCoupons.size(); i++)
		{
			if(coupon.getCouponText().equals(toBeActiveCoupons.get(i).getCouponText()))
			{
				coupon.setStatus("Active");
				toBeActiveCoupons.remove(i);
				activeCoupons.add(coupon);
				return true;
			}
		}
		return false;
	}
	
	public static boolean cancelCoupon(CouponCode coupon)
	{
		for(int i = 0; i < toBeActiveCoupons.size(); i++)
		{
			if(coupon.getCouponText().equals(toBeActiveCoupons.get(i).getCouponText()))
			{
				coupon.setStatus("Cancel");
				toBeActiveCoupons.remove(i);
				canceledCoupons.add(coupon);
				return true;
			}
		}
		
		for(int i = 0; i < activeCoupons.size(); i++)
		{
			if(coupon.getCouponText().equals(activeCoupons.get(i).getCouponText()))
			{
				coupon.setStatus("Cancel");
				activeCoupons.remove(i);
				canceledCoupons.add(coupon);
				return true;
			}
		}
		
		return false;		
		
	}
	
	public static boolean endCoupon(CouponCode coupon)
	{
		for(int i = 0; i < toBeActiveCoupons.size(); i++)
		{
			if(coupon.getCouponText().equals(toBeActiveCoupons.get(i).getCouponText()))
			{
				coupon.setStatus("Ended");
				toBeActiveCoupons.remove(i);
				endedCoupons.add(coupon);
				return true;
			}
		}
		
		for(int i = 0; i < activeCoupons.size(); i++)
		{
			if(coupon.getCouponText().equals(activeCoupons.get(i).getCouponText()))
			{
				coupon.setStatus("Ended");
				activeCoupons.remove(i);
				endedCoupons.add(coupon);
				return true;
			}
		}
		
		return false;		
	}
	
	public static boolean activateSpecial(Special special)
	{
		for(int i = 0; i < toBeActiveSpecials.size(); i++)
		{
			if(special.getSpecialName().equals(toBeActiveSpecials.get(i).getSpecialName()))
			{
				special.setStatus("Active");
				toBeActiveSpecials.remove(i);
				activeSpecials.add(special);
				
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean cancelSpecial(Special special)
	{
		for(int i = 0; i < toBeActiveSpecials.size(); i++)
		{
			if(special.getSpecialName().equals(toBeActiveSpecials.get(i).getSpecialName()))
			{
				special.setStatus("Cancel");
				toBeActiveSpecials.remove(i);
				canceledSpecials.add(special);
				return true;
			}
		}
		
		for(int i = 0; i < activeSpecials.size(); i++)
		{
			if(special.getSpecialName().equals(activeSpecials.get(i).getSpecialName()))
			{
				special.setStatus("Cancel");
				activeSpecials.remove(i);
				canceledSpecials.add(special);
				return true;
			}
		}
		
		return false;		
	}
	
	public static boolean endSpecial(Special special)
	{
		for(int i = 0; i < toBeActiveSpecials.size(); i++)
		{
			if(special.getSpecialName().equals(toBeActiveSpecials.get(i).getSpecialName()))
			{
				special.setStatus("Ended");
				toBeActiveSpecials.remove(i);
				endedSpecials.add(special);
				return true;
			}
		}
		
		for(int i = 0; i < activeSpecials.size(); i++)
		{
			if(special.getSpecialName().equals(activeSpecials.get(i).getSpecialName()))
			{
				special.setStatus("Ended");
				activeSpecials.remove(i);
				endedSpecials.add(special);
				return true;
			}
		}
		
		return false;	
	}
	

}//SpecialsAndCouponLog
