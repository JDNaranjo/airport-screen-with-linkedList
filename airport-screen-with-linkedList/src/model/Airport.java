package model;

public class Airport {
	
	private Flight first;
	
	public final static String[] AIRLINES = new String[] {"Avianca","Copa Airlines", "EasyFly", "LATAM", "Satena",
			"VivaColombia", "Wingo" }; 
	public final static String[] CITIES = new String[] {"Atlanta","Barranquilla", "Bogota", "Cali", "Cartagena",
			"Medellin", "New York", "Paris", "Madrid", "Berlin", "Amsterdam" }; 

	public Airport() {}
	
	public Flight getFirst() {
		return first;
	}

	public void setFirst(Flight first) {
		this.first = first;
	}
	
	public String timeRandom() {
		
		String time="";
			
		int hourRandom = (int)(Math.random()*12+1);
		int minutesRandom = (int)(Math.random()*60+1);
		String n="";
		String m="";
		String amPm="";
		int hourTime = (int)(Math.random()*2);
		
		if(hourTime == 0) {
			amPm = " AM";
		}else {
			amPm = " PM";
		}
		
		if(minutesRandom<10) {
			n="0";
		}
		
		if(hourRandom<10) {
			m="0";
		}
		
		time = m+hourRandom+":"+n+minutesRandom+amPm;
		
		return time;
	}
	
	public String airlineRandom() {
		
		int airlineNumber = (int)(Math.random()*7-1);
		String airline = AIRLINES[airlineNumber];
		
		return airline;
		
	}
	
	public String flightRandom(String airline) {
		int flightNumber = (int)(Math.random()*1000);
		airline = airline.toUpperCase();
		
		String n="";
		if (flightNumber<10) {
			n="00";
		}else if(flightNumber<100) {
			n="0";
		}
		String flight=""+airline.charAt(0)+airline.charAt(1)+" "+n+flightNumber;
		
		return flight;
	}
	
	public String toRandom() {
		int cityNumber = (int)(Math.random()*12-1);
		String city = CITIES[cityNumber];
		
		return city;
	}
	
	public String terminalRandom() {
		int terminalNumber = (int)(Math.random()*5-1);
		terminalNumber++;
		
		String terminal=""+terminalNumber;
		
		return terminal;
	}
	
	public String gateRandom() {
		int gateNumber=(int)(Math.random()*30-1);
		gateNumber++;
		String gate="";
		if(gateNumber<10) {
			gate="0"+gateNumber;
		}else {
			gate=""+gateNumber;
		}
		
		
		return gate;
	}
	
	public String remarksRandom() {
		int remarksNumber = (int)(Math.random()*3);
		String remarks="";
		if(remarksNumber==0) {
			remarks = "Go to Gate";
		}else if(remarksNumber==1) {
			remarks="Wait on room";
		}else {
			remarks="Delayed";
		}
		
		return remarks;
	}
	
	public void orderByTimeLH() {
		Flight current = first;
		while(current != null) {
			Flight temp = current.getNext();
			Flight min = current;
			while(temp != null) {
				if(temp.compareTo(min) <= 0) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Flight next1 = current.getNext();
				Flight previous1 = current.getPrev();
				
				Flight next2 = min.getNext();
				Flight previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}
			
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
	}
	
	public void orderByTimeHL() {
		Flight current = first;
		while(current != null) {
			Flight temp = current.getNext();
			Flight min = current;
			while(temp != null) {
				if(temp.compareTo(min) >= 0) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Flight next1 = current.getNext();
				Flight previous1 = current.getPrev();
				
				Flight next2 = min.getNext();
				Flight previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}
			
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
	}
	
	public void orderByAirlineAZ() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getFlight().compareTo(temp.getPrev().getFlight())<0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
	}
	

	public void orderByAirlineZA() {
		if(first != null) {
			Flight temp = first;
			int counter = 0;
			
			int size = 0;
			while(temp != null) {
				size++;
				temp = temp.getNext();
			}	
			
			while(temp != null) {
				Flight current = first;
				int counter2 = 0;
				while(current.getNext() != null && counter2 < size-counter) {
					if(current.getAirline().compareTo(current.getNext().getAirline())>0) {
						if(first == current) first = current.getNext();
						Flight next = current.getNext().getNext();
						Flight prev = current.getPrev();
						if(next != null) next.setPrev(current);
						if(prev != null) prev.setNext(current.getNext());
						current.getNext().setNext(current);
						current.getNext().setPrev(prev);
						current.setPrev(current.getNext());
						current.setNext(next);
					} else {
						current = current.getNext();
					}
					counter2++;
				}
				counter++;
				temp = temp.getNext();
			}
		}		
	}
	
	public void orderByFlightAZ() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getFlight().compareTo(temp.getPrev().getFlight())<0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
	}
	
	public void orderByFlightZA() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getFlight().compareTo(temp.getPrev().getFlight())>0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
		}
	

	public void orderByCityAZ() {
		Flight current = first;
		while(current != null) {
			Flight temp = current.getNext();
			Flight min = current;
			while(temp != null) {
				if(temp.getArriveCity().compareTo(min.getArriveCity()) <= 0) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Flight next1 = current.getNext();
				Flight previous1 = current.getPrev();
				
				Flight next2 = min.getNext();
				Flight previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}
			
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
		
	}
	
	public void orderByCityZA() {
		Flight current = first;
		while(current != null) {
			Flight temp = current.getNext();
			Flight min = current;
			while(temp != null) {
				if(temp.getArriveCity().compareTo(min.getArriveCity()) >= 0) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Flight next1 = current.getNext();
				Flight previous1 = current.getPrev();
				
				Flight next2 = min.getNext();
				Flight previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}
			
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
	}

	public void orderByTerminalLH() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getTerminal().compareTo(temp.getPrev().getTerminal())<0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
	}
	
	public void orderByTerminalHL() {
		if(first != null) {
			Flight temp = first;
			int counter = 0;
			
			int size = 0;
			while(temp != null) {
				size++;
				temp = temp.getNext();
			}	
			
			while(temp != null) {
				Flight current = first;
				int counter2 = 0;
				while(current.getNext() != null && counter2 < size-counter) {
					if(current.getTerminal().compareTo(current.getNext().getTerminal())>0) {
						if(first == current) first = current.getNext();
						Flight next = current.getNext().getNext();
						Flight prev = current.getPrev();
						if(next != null) next.setPrev(current);
						if(prev != null) prev.setNext(current.getNext());
						current.getNext().setNext(current);
						current.getNext().setPrev(prev);
						current.setPrev(current.getNext());
						current.setNext(next);
					} else {
						current = current.getNext();
					}
					counter2++;
				}
				counter++;
				temp = temp.getNext();
			}
		}
	}
	
	public void orderByGateLH() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getGate().compareTo(temp.getPrev().getGate())<0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
		
	}
	
	public void orderByGateHL() {
		if(first.getNext() != null) {
			Flight current = first.getNext();
			while(current != null) {
				Flight temp = current;
				while(temp.getPrev() != null) {
					if(temp.getGate().compareTo(temp.getPrev().getGate())>0) {
						if(temp.getPrev() == first) first = temp;
						Flight next = temp.getNext();
						Flight prev = temp.getPrev().getPrev();
						if(next != null) next.setPrev(temp.getPrev());
						if(prev != null) prev.setNext(temp);
						temp.setNext(temp.getPrev());
						temp.getPrev().setPrev(temp);
						temp.getPrev().setNext(next);
						temp.setPrev(prev);
					} else {
						temp = temp.getPrev();
					}
				}
				current = current.getNext();
			}
		}
	}
	
	public void orderByRemarks() {
		Flight current = first;
		while(current != null) {
			Flight temp = current.getNext();
			Flight min = current;
			while(temp != null) {
				if(temp.getRemarks().compareTo(min.getRemarks()) <= 0) {
					min = temp;
				}
				temp = temp.getNext();
			}
			boolean firstIt = false;
			if(min != current) {
				Flight next1 = current.getNext();
				Flight previous1 = current.getPrev();
				
				Flight next2 = min.getNext();
				Flight previous2 = min.getPrev();
				
				if(min == current.getNext()	) {
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					current.setNext(next2);
					current.setPrev(min);
					if(next2 != null) next2.setPrev(current);
					min.setNext(current);
					min.setPrev(previous1);
				} else {
					if(next1 != null) next1.setPrev(min);
					if(previous1 != null) previous1.setNext(min);
					else {
						first = min;
						firstIt = true;
					}
					
					min.setNext(next1);
					min.setPrev(previous1);
					
					if(next2 != null) next2.setPrev(current);
					if(previous2 != null) previous2.setNext(current);
					
					current.setNext(next2);
					current.setPrev(previous2);
				}
				current = min;
			}
			
			if(firstIt) {
				current = first.getNext();
			} else {
				current = current.getNext();
			}
		}
	}

	public Flight linealSearchByTime(String time) {
		Flight current = first;
		while(current!=null) {
			if (current.compareTo(time)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}

	public Flight linealSearchByAirline(String airline) {
		Flight current = first;
		while(current!=null) {
			if (current.getAirline().compareTo(airline)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}

	public Flight linealSearchByFlight(String flight) {
		Flight current = first;
		while(current!=null) {
			if (current.getFlight().compareTo(flight)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}

	public Flight linealSearchByCity(String city) {
		Flight current = first;
		while(current!=null) {
			if (current.getArriveCity().compareTo(city)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}

	public Flight linealSearchByTerminal(String terminal) {
		Flight current = first;
		while(current!=null) {
			if (current.getTerminal().compareTo(terminal)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}

	public Flight linealSearchByGate(String gate) {
		Flight current = first;
		while(current!=null) {
			if (current.getGate().compareTo(gate)==0) {
				return current;
			}else {
				current = current.getNext();
			}
		}
		return null;
	}
	
}
