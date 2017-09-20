/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 * 
 * 
 */
	public NameSurferEntry(String line) {
		parse(line);
	}
	
	private void parse(String line) {
		//Gets the name from line
		int nameStart = 0;
		int nameEnd = line.indexOf(" ");
		name = line.substring(nameStart, nameEnd);
		
		//Gets the popularity of the name in each decade
		int numStart = nameEnd + 1;
		nums = line.substring(numStart);
		separateNums(nums);
		
	}
	
	private void separateNums(String nums) {
		//Stores the (NDECADES - 1) with the space into an array
		for(int i = 0; i < NDECADES - 1; i++) {
			decadeArray[i] = Integer.parseInt(nums.substring(0, nums.indexOf(" ")));
			nums = nums.substring(nums.indexOf(" ") + 1);
		}
		
		//Last int in decadeArray shouldn't have a space, so I initialized it's value outside of the loop.
		decadeArray[NDECADES - 1] = Integer.parseInt(nums);
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return decadeArray[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String result = "";
		result += "\"" + name + " [";
		
		for(int i = 0; i < NDECADES - 1; i++) {
			result += decadeArray[i] + " ";
			
		}
		
		result += decadeArray[NDECADES - 1] + "]" + "\"";
		return result;
	}
	
	/*IVARS*/
	private String name;
	private int[] decadeArray = new int[NDECADES];
	private String nums;
	
	
}

