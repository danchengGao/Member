package model;

import java.math.BigDecimal;

import api.jaws.Jaws;
import api.jaws.Location;
import api.jaws.Shark;

public class SharkModel {
	
	private Jaws jaws;
	private String name;
    private double distance;
    private Location kingsLocation = new Location(51.5119,0.1161);
    private double earthRadius = 6378.137;
	
	/**
	 * Model a shark to get its distance from king's.
	 * @param shark.
	 * @param jaws.
	 */
    public SharkModel(Shark s,Jaws j) {
		jaws = j;
		name = s.getName();	
		getDistanceFromKings();
	}
	
    /**
     * Calculate the shark's distance from king's.
     * @return double distance.
     */
	public double getDistanceFromKings(){
		Location sharkLocation = jaws.getLastLocation(name);
		double la1 = kingsLocation.getLatitude();
		double la2 = sharkLocation.getLatitude();
		double lo1 = kingsLocation.getLongitude();
		double lo2 = sharkLocation.getLongitude();
		double a = getRad(la1) - getRad(la2);
		double b = getRad(lo1) - getRad(lo2);
		double s = 2*Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2))) + Math.cos(getRad(la1))*Math.cos(getRad(la2))*Math.pow(Math.sin(b/2),2);
		s = s*earthRadius;
		BigDecimal decimal = new BigDecimal(s);  
		distance = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		return distance;
	}
	
	/**
	 * Get radius.
	 * @param double.
	 * @return radius.
	 */
	public static double getRad(double d){
		return d * Math.PI / 180.0;	
    }
	
	/**
	 * When print the model, the shark's name is printed.
	 * @return String name.
	 */
	public String getName(){
		return name;
	}
	
}
