package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Airport;
import model.Flight;
import thread.TimeThread;

public class airpotScreenController {

    @FXML
    private Label timeLabel;

    @FXML
    private Button generateFlights;

    @FXML
    private TextField textFieldFlights;

    @FXML
    private TableView<Flight> tableView;
    
    @FXML
    private TableColumn<Flight, String> timeColumn;

    @FXML
    private TableColumn<Flight, String> airlineColumn;

    @FXML
    private TableColumn<Flight, String> flightColumn;

    @FXML
    private TableColumn<Flight, String> toColumn;

    @FXML
    private TableColumn<Flight, String> terminalColumn;

    @FXML
    private TableColumn<Flight, String> gateColumn;

    @FXML
    private TableColumn<Flight, String> remarksColumn;
    
    @FXML
    private TextField searchTxtFiled;

    @FXML
    private Button timeSearchButton;

    @FXML
    private Button airlineSearchButton;

    @FXML
    private Button flightSearchButton;

    @FXML
    private Button citySearchButton;

    @FXML
    private Button terminalSearchButton;

    @FXML
    private Button gateSearchButton;
    
    @FXML
    private Label canNotLabel;
    
    @FXML
    private Button timeSortingButton;

    @FXML
    private Button airlineSortingButton;

    @FXML
    private Button flightSortingButton;

    @FXML
    private Button citySortingButton;

    @FXML
    private Button terminalSortingButton;

    @FXML
    private Button gateSortingButton;
    
    @FXML
    private Label timeCurrentLabel;
    
    private Airport airport;
    
    ObservableList<Flight> data = FXCollections.observableArrayList();

    @FXML
    void initialize() throws InterruptedException {
    	canNotLabel.setVisible(false);
    	airport = new Airport();
    	TimeThread tt = new TimeThread(this);
    	tt.start();
    }
    
    @FXML
    void generateFlights(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
			
	    	String time = airport.timeRandom();
	    	String airline = airport.airlineRandom();
	    	String flightNumber = airport.flightRandom(airline);
	    	String cityTo = airport.toRandom();
	    	String terminal = airport.terminalRandom();
	    	String gate = airport.gateRandom();
	    	String remarks = airport.remarksRandom();
	    	
	    	Flight newFlight = new Flight(time, airline, flightNumber, cityTo, terminal, gate, remarks);
	    	if(airport.getFirst()==null) {
	    		airport.setFirst(newFlight);
	    	}else {
	    		Flight flight = airport.getFirst();
	    		while(flight.getNext()!=null) {
	    			flight = flight.getNext();
	    		}
	    		flight.setNext(newFlight);
	    	}
	    	data.add(newFlight);
	    	System.out.println(""+time+" - "+airline+" - "+flightNumber+" - "+cityTo+" - "+terminal+" - "
	    			+gate+" - "+remarks+" - ");
    	}
    	//airport.orderByTimeLH();
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    public void setTime(String n) {
    	timeLabel.setText(n);
    }
    
    @FXML
    void airlineSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	//airport.orderByAirlineAZ(airport.getFlights());
    	
    	String airline = searchTxtFiled.getText();
    	int pos = airport.linealSearchByAirline(airline);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }

    @FXML
    void citySearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByCityAZ(airport.getFlights());
    	
    	String city = searchTxtFiled.getText();
    	int pos = airport.binarySearchByCity(city);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }

    @FXML
    void flightSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByFlightAZ(airport.getFlights());
    	
    	String flight = searchTxtFiled.getText();
    	int pos = airport.binarySearchByFlight(flight);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }

    @FXML
    void gateSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByGateLH(airport.getFlights());
    	
    	String gate = searchTxtFiled.getText();
    	int pos = airport.binarySearchByGate(gate);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void terminalSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByTerminalLH(airport.getFlights());
    	
    	String terminal = searchTxtFiled.getText();
    	int pos = airport.binarySearchByTerminal(terminal);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }

    @FXML
    void timeSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByTimeLH(airport.getFlights());
    	
    	String time = searchTxtFiled.getText();
    	int pos = airport.binarySearchByTime(time);
    	
    	if(pos!=-1) {
    		data.add(airport.getFlights().get(pos));
    		tableView.setItems(data);
    		System.out.println(""+airport.getFlights().get(pos).getTime()+" - "+airport.getFlights().get(pos).getAirline()+" - "+airport.getFlights().get(pos).getFlight()+" - "+airport.getFlights().get(pos).getArriveCity()+" - "+airport.getFlights().get(pos).getTerminal()+" - "
	    			+airport.getFlights().get(pos).getGate()+" - "+airport.getFlights().get(pos).getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void timeSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByTimeLH(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void airlineSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByAirlineAZ(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void flightSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByFlightAZ(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void citySorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByCityAZ(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void terminalSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByTerminalLH(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void gateSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByGateLH(airport.getFlights());
    	System.out.println("--------------------------------------");
    	for (int i = 0; i < Integer.parseInt(textFieldFlights.getText()); i++) {
    		data.add(airport.getFlights().get(i));
    		System.out.println(""+airport.getFlights().get(i).getTime()+" - "+airport.getFlights().get(i).getAirline()+" - "+airport.getFlights().get(i).getFlight()+" - "+airport.getFlights().get(i).getArriveCity()+" - "+airport.getFlights().get(i).getTerminal()+" - "
	    			+airport.getFlights().get(i).getGate()+" - "+airport.getFlights().get(i).getRemarks());
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
}
