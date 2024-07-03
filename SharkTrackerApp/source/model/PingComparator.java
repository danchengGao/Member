package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.jaws.Ping;

public class PingComparator implements Comparator{

	/**
	 * A comparator to compare two sharks by their latest time.
	 */
	public int compare(Object o1, Object o2) {
		String time1 = ((Ping) o1).getTime();
		String time2 = ((Ping) o2).getTime();
		List<String> a = new ArrayList(); 
		List<String> b = new ArrayList(); 
		
		if (time1.equals(time2)){
			return 0;
		}
		
		Pattern pattern = Pattern.compile("(\\d\\d\\d\\d)(\\-)(\\d\\d)(\\-)(\\d\\d)(\\s)(\\d\\d)(\\:)(\\d\\d)(\\:)(\\d\\d)");
		Matcher matcher = pattern.matcher(time1);
		if(matcher.find()){
			a.add(matcher.group(1)); 
			a.add(matcher.group(3)); 
    		a.add(matcher.group(5));
    		a.add(matcher.group(7));
    		a.add(matcher.group(9));
    		a.add(matcher.group(11));
        }
		matcher = pattern.matcher(time2);
		if(matcher.find()){
			b.add(matcher.group(1));
			b.add(matcher.group(3)); 
    		b.add(matcher.group(5));
    		b.add(matcher.group(7));
    		b.add(matcher.group(9));
    		b.add(matcher.group(11));
        }
		
		if(isLarger(a.get(0),b.get(0)) == 1){
			return -1;
		}
		else if(isLarger(a.get(0),b.get(0)) == -1){
			return 1;
		}
		else if(isLarger(a.get(0),b.get(0)) == 0){
			if(isLarger(a.get(1),b.get(1)) == 1){
				return -1;
			}
			else if(isLarger(a.get(1),b.get(1)) == -1){
				return 1;
			}
			else if(isLarger(a.get(1),b.get(1)) == 0){
				if(isLarger(a.get(2),b.get(2)) == 1){
					return -1;
				}
				else if(isLarger(a.get(2),b.get(2)) == -1){
					return 1;
				}
				else if(isLarger(a.get(2),b.get(2)) == 0){
					if(isLarger(a.get(3),b.get(3)) == 1){
						return -1;
					}
					else if(isLarger(a.get(3),b.get(3)) == -1){
						return 1;
					}
					else if(isLarger(a.get(3),b.get(3)) == 0){
						if(isLarger(a.get(4),b.get(4)) == 1){
							return -1;
						}
						else if(isLarger(a.get(4),b.get(4)) == -1){
							return 1;
						}
						else if(isLarger(a.get(4),b.get(4)) == 0){
							if(isLarger(a.get(5),b.get(5)) == 1){
								return -1;
							}
							else if(isLarger(a.get(5),b.get(5)) == -1){
								return 1;
							}
							else if(isLarger(a.get(5),b.get(5)) == 0){
								return 0;
							}
						}
					}
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * Compare two strings that are integers and check whether the first string is larger than the second one.
	 * @param string a
	 * @param string b
	 * @return 1, -1 or 0.
	 */
	public int isLarger(String a, String b){
		if(a.equals(b)){
			return 0;
		}
		List<String> list = new ArrayList();
    	list.add(a);
    	list.add(b);
    	Collections.sort(list);
    	for (String s : list){
    		if (s.equals(a)){
    			return -1;
    		}
    		else if(s.equals(b)){
    			return 1;
    		}
    	}
		return 0;
	}

}
