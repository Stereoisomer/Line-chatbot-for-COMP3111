package skeleton;

import java.util.HashMap;

public class Adapter {
	public static final String[] BEVERAGES = new String[] {
			"1", "Mocha", "Latte", 
			"Cappuccino", "Caramel Macchiato", "Espresso" }; // You can change these

	/**
	* This function compute the edit distance between a string and every 
	* strings in your selected beverage set. The beverage with the minimum 
	* edit distance <= 3 is returned. The use of Wagner_Fischer algorithm
	* is shown in the main function in WagnerFischer.java
	**/
	public String getBeverage(String s){
		// TODO: find the word with minimum edit distance
		int minDist = 4;
		String minDistBev = null;
		for (String bev: BEVERAGES) {
			WagnerFischer wf = new WagnerFischer(bev, s);
			int dist = wf.getDistance();
	        if(dist < minDist) {
	        	minDist = dist;
	        	minDistBev = bev;
	        }
		}
		return minDistBev;
	}
}
