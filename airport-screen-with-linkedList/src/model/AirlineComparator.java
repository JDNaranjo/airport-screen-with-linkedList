package model;

import java.util.Comparator;

public class AirlineComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		
		int r = 0;
		String f1Airline = f1.getAirline();
		String f2Airline = f2.getAirline();
		
		if(f1Airline.compareTo(f2Airline)>0) {
			r=1;
		}else if(f1Airline.compareTo(f2Airline)<0) {
			r=-1;
		}
		
		return r;
	}

}
