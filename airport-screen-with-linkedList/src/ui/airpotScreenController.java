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
import javafx.scene.control.cell.PropertyValueFactory;
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
    	timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    	airlineColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
    	flightColumn.setCellValueFactory(new PropertyValueFactory<>("flight"));
    	toColumn.setCellValueFactory(new PropertyValueFactory<>("arriveCity"));
    	terminalColumn.setCellValueFactory(new PropertyValueFactory<>("terminal"));
    	gateColumn.setCellValueFactory(new PropertyValueFactory<>("gate"));
    	remarksColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));
    }
    
    @FXML
    void generateFlights(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	airport.setFirst(null);
    	data.clear();
		Flight flight = null;
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
	    		flight = airport.getFirst();
	    	}else {
	    			flight.setNext(newFlight);
	    			flight.getNext().setPrev(flight);
	    			flight = flight.getNext();
	    		
	    	}
	    	//data.add(newFlight);
	    	//tableView.getItems().addAll(newFlight);
	    	System.out.println(""+time+" - "+airline+" - "+flightNumber+" - "+cityTo+" - "+terminal+" - "
	    			+gate+" - "+remarks);
    	}
    	airport.orderByTimeLH();
    	tableView.setItems(data);
    	Flight current = airport.getFirst();
    	System.out.println("+++++++++++++++++++");
    	while(current!=null) {
    		data.add(current);
    		System.out.println(""+current.getTime()+" - "+current.getAirline()+" - "+current.getFlight()+" - "+current.getArriveCity()+" - "+current.getTerminal()+" - "
	    			+current.getGate()+" - "+current.getRemarks());
    		current = current.getNext();
    	}
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    public void setTime(String n) {
    	timeLabel.setText(n);
    }
    
    @FXML
    void timeSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByTimeLH();
    	
    	String time = searchTxtFiled.getText();
    	Flight timeFlight = airport.linealSearchByTime(time);
    	
    	if(timeFlight!=null) {
    		data.add(timeFlight);
    		tableView.setItems(data);
    		System.out.println(""+timeFlight.getTime()+" - "+timeFlight.getAirline()+" - "+timeFlight.getFlight()+" - "+timeFlight.getArriveCity()+" - "+timeFlight.getTerminal()+" - "
	    			+timeFlight.getGate()+" - "+timeFlight.getRemarks());
    	}else {
    		canNotLabel.setVisible(true);
    	}
    	
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void airlineSearch(ActionEvent event) {
    	double actualTime = System.currentTimeMillis();
    	canNotLabel.setVisible(false);
    	data.clear();
    	airport.orderByAirlineAZ();
    	
    	String airline = searchTxtFiled.getText();
    	Flight airlineFlight = airport.linealSearchByAirline(airline);
    	
    	if(airlineFlight!=null) {
    		data.add(airlineFlight);
    		tableView.setItems(data);
    		System.out.println(""+airlineFlight.getTime()+" - "+airlineFlight.getAirline()+" - "+airlineFlight.getFlight()+" - "+airlineFlight.getArriveCity()+" - "+airlineFlight.getTerminal()+" - "
	    			+airlineFlight.getGate()+" - "+airlineFlight.getRemarks());
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
    	airport.orderByCityAZ();
    	
    	String city = searchTxtFiled.getText();
    	Flight cityFlight = airport.linealSearchByCity(city);
    	
    	if(cityFlight!=null) {
    		data.add(cityFlight);
    		tableView.setItems(data);
    		System.out.println(""+cityFlight.getTime()+" - "+cityFlight.getAirline()+" - "+cityFlight.getFlight()+" - "+cityFlight.getArriveCity()+" - "+cityFlight.getTerminal()+" - "
	    			+cityFlight.getGate()+" - "+cityFlight.getRemarks());
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
    	airport.orderByFlightAZ();
    	
    	String flight = searchTxtFiled.getText();
    	Flight fFlight = airport.linealSearchByFlight(flight);
    	
    	if(fFlight!=null) {
    		data.add(fFlight);
    		tableView.setItems(data);
    		System.out.println(""+fFlight.getTime()+" - "+fFlight.getAirline()+" - "+fFlight.getFlight()+" - "+fFlight.getArriveCity()+" - "+fFlight.getTerminal()+" - "
	    			+fFlight.getGate()+" - "+fFlight.getRemarks());
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
    	airport.orderByGateLH();
    	
    	String gate = searchTxtFiled.getText();
    	Flight gateFlight = airport.linealSearchByGate(gate);
    	
    	if(gateFlight!=null) {
    		data.add(gateFlight);
    		tableView.setItems(data);
    		System.out.println(""+gateFlight.getTime()+" - "+gateFlight.getAirline()+" - "+gateFlight.getFlight()+" - "+gateFlight.getArriveCity()+" - "+gateFlight.getTerminal()+" - "
	    			+gateFlight.getGate()+" - "+gateFlight.getRemarks());
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
    	airport.orderByTerminalLH();
    	
    	String terminal = searchTxtFiled.getText();
    	Flight terminalFlight = airport.linealSearchByTerminal(terminal);
    	
    	if(terminalFlight!=null) {
    		data.add(terminalFlight);
    		tableView.setItems(data);
    		System.out.println(""+terminalFlight.getTime()+" - "+terminalFlight.getAirline()+" - "+terminalFlight.getFlight()+" - "+terminalFlight.getArriveCity()+" - "+terminalFlight.getTerminal()+" - "
	    			+terminalFlight.getGate()+" - "+terminalFlight.getRemarks());
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
    	airport.orderByTimeLH();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void airlineSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByAirlineAZ();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void flightSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByFlightAZ();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void citySorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByCityAZ();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void terminalSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByTerminalLH();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
    
    @FXML
    void gateSorting(ActionEvent event) {
    	data.clear();
    	double actualTime = System.currentTimeMillis();
    	airport.orderByGateLH();
    	System.out.println("--------------------------------------");
    	Flight temp = airport.getFirst();
    	while(temp!= null) {
    		data.add(temp);
    		System.out.println(""+temp.getTime()+" - "+temp.getAirline()+" - "+temp.getFlight()+" - "+temp.getArriveCity()+" - "+temp.getTerminal()+" - "
	    			+temp.getGate()+" - "+temp.getRemarks());
    		temp = temp.getNext();
    	}
    	tableView.setItems(data);
    	actualTime = System.currentTimeMillis()-actualTime;
    	timeCurrentLabel.setText(""+actualTime);
    }
}
