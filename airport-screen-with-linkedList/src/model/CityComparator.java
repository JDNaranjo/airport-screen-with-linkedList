package model;

import java.util.Comparator;

public class CityComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		
		int r=0;
		
		if(f1.getArriveCity().compareTo(f2.getArriveCity())>0) {
			r=1;
		}else if(f1.getArriveCity().compareTo(f2.getArriveCity())<0) {
			r=-1;
		}
		
		return r;
	}

}
