package model;

import java.util.Comparator;

public class TimeComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		
		int r = 0;
		
		String f1Time = f1.getTime();
		int f1Hour = Integer.parseInt(""+f1Time.charAt(0)+f1Time.charAt(1));
		int f1Minutes = Integer.parseInt(""+f1Time.charAt(3)+f1Time.charAt(4));
		String f1AmPm = ""+f1Time.charAt(6)+f1Time.charAt(7);
		
		String f2Time = f2.getTime();
		int f2Hour = Integer.parseInt(""+f2Time.charAt(0)+f2Time.charAt(1));
		int f2Minutes = Integer.parseInt(""+f2Time.charAt(3)+f2Time.charAt(4));
		String f2AmPm = ""+f2Time.charAt(6)+f2Time.charAt(7);
		
		if(f1AmPm.equals(f2AmPm)) {
			if(f1Hour==f2Hour) {
				if(f1Minutes==f2Minutes) {
					r=0;
				}else if(f1Minutes>f2Minutes) {
					r=1;
				}else if(f1Minutes<f2Minutes) {
					r=-1;
				}
			}else if(f1Hour>f2Hour) {
				r=1;
			}else if(f1Hour<f2Hour) {
				r=-1;
			}
		}else if(f1AmPm.equals("AM")) {
			r=-1;
		}else if(f1AmPm.equals("PM")) {
			r=1;
		}
		
		return r;
	}

	
	
}
