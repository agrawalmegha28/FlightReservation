package edu.npu.fr.services;

import java.util.Date;
import java.util.List;

import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

public interface FlightReservationServiceI {
	public List<Flight> getFlights(String from, String to, Date depart);
	public Reservation reserveFlight(Flight flight, Date depart, List<Passenger> passengers);
	public Reservation searchReservation(String code);
	public void deleteReservation(String code);
	public void updateReservation(String code, Reservation updated);
	public Flight getFlight(int flightId);

}
