/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Section: 70513-96561
 * Group Project: Group 40
 * 
 * Date File Created: 11/13/2022
 * Date Last Edited: 11/13/2022
 * 
 * Anant Rastogi
 * Ethan Rich
 * Pei-En Pan
 * Margaret Wade
 * Rini Jain
 * 
 * Verification is a static class that allows another class to
 * compare ID numbers and determine whether a certain ID is
 * valid or not.
 */

package application;

public class Verification {

	private static int[] ASURITEIDs = {1220000000, 1221111111, 1222222222, 1223333333};
	private static int[] ChefIDs = {1009999999, 1008888888, 1007777777, 1006666666};
	
	// Default constructor, do not use
	public Verification() {}
	
	/**
	 * isValidASUID checks the user's ID and returns whether their
	 * ID is valid.
	 * @param id A 10-digit number
	 * @return True if the ID matches an ID in the system; false otherwise.
	 */
	public static boolean isValidASUID(int id)
	{
		for (int i = 0; i < ASURITEIDs.length; i++)
		{
			if (id == ASURITEIDs[i])
				return true;
		}
		return false;
	}
	
	/**
	 * isValidChefID checks the user's ID and returns whether their
	 * ID is valid.
	 * @param id A 10-digit number
	 * @return True if the ID matches an ID in the system; false otherwise.
	 */
	public static boolean isValidChefID(int id)
	{
		for (int i = 0; i < ChefIDs.length; i++)
		{
			if (id == ChefIDs[i])
				return true;
		}
		return false;
	}
}
