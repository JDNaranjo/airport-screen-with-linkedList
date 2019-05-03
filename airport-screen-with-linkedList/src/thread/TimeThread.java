package thread;

import java.time.LocalDateTime;

import ui.airpotScreenController;

public class TimeThread extends Thread {
	
	airpotScreenController aSC = new airpotScreenController();
	
	public TimeThread(airpotScreenController airportSC) {
		aSC = airportSC;
	}
	
	@Override
	public void run() {
		
			
		try {	
			
			LocalDateTime locaDate = LocalDateTime.now();
			int hours  = locaDate.getHour();
			int minutes = locaDate.getMinute();
			int seconds = locaDate.getSecond();
			String amPm = "";
			String n="";
			String m="";
			if(hours>12) {
				hours = hours-12;
				amPm = " PM";
			}else {
				amPm=" AM";
			}
			if(minutes<10) {
				n="0";
			}
			if(seconds<10) {
				m="0";
			}
			
			String time = "" + hours + ":"+n+ minutes +":"+m+seconds+amPm;
			aSC.setTime(time);
			
			sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

	
