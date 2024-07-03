package model;

import java.util.Comparator;

import api.jaws.Jaws;
import api.jaws.Shark;

public class DistanceComparator implements Comparator{

	private Jaws jaws;
	
	/**
	 * A distance comparator used to compare two sharks by their distance to the user.
	 * @param jaws.
	 */
	public DistanceComparator(Jaws j){
		jaws = j;
	}
	
	/**
	 * Compare two sharks shark1 and shark2. If shark1 is closer to the user, return -1.
	 * If they are equally close, return 0. Otherwise return 1.
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Shark shark1 = (Shark) o1;
		Shark shark2 = (Shark) o2;
		SharkModel model1 = new SharkModel(shark1,jaws);
		SharkModel model2 = new SharkModel(shark2,jaws);
		double distance1 = model1.getDistanceFromKings();
		double distance2 = model2.getDistanceFromKings();
		if(distance1 == distance2){
			return 0;
		}
		else if(distance1 < distance2){
			return -1;
		}
		else if(distance1 > distance2){
			return 1;
		}
		return 0;
	}
}
