package edu.npu.fr.dao;

import java.util.Date;
import java.util.List;

import edu.npu.fr.domain.Flight;

public interface FlightDaoI {
	public List<Flight> getFlights(String from, String to, Date depart);
	public Flight addNewFlight(Flight flight);
	public int deleteFlight(Flight flight);
	public int updateFlight(Flight flight);
	public Flight getFlight(int flightId);
}
