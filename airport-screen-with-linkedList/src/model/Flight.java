package model;

public class Flight implements Comparable<Flight>{
	
	private String time;
	private String airline;
	private String flight;
	private String arriveCity;
	private String terminal;
	private String gate;
	private String remarks;
	
	private Flight next;
	private Flight prev;
	
	public Flight(String time, String airline, String flight, String arriveCity, String terminal, String gate,
			String remarks) {
		this.time = time;
		this.airline = airline;
		this.flight = flight;
		this.arriveCity = arriveCity;
		this.terminal = terminal;
		this.gate = gate;
		this.remarks = remarks;
	}
	
	public Flight getNext() {
		return next;
	}
	public void setNext(Flight flight) {
		// Hacer Metodo
	}
	
	public Flight getPrev() {
		return prev;
	}
	public void setPrev(Flight flight) {
		// Hacer Metodo
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int compareTo(Flight flight) {
		int r = 0;
		
		String f1Time = time;
		int f1Hour = Integer.parseInt(""+time.charAt(0)+f1Time.charAt(1));
		int f1Minutes = Integer.parseInt(""+time.charAt(3)+f1Time.charAt(4));
		String f1AmPm = ""+f1Time.charAt(6)+time.charAt(7);
		
		String f2Time = flight.getTime();
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
