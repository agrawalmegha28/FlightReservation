package edu.npu.fr.dao.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

public class ReservationDaoMock implements ReservationDaoI{
	private Map<String, Reservation> reservations = new HashMap<String, Reservation>();
	private int counter = 0;
	
	@Override
	public Reservation reserveFlight(Flight flight, Date depart,List<Passenger> passengers) {
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passengers);
		reservation.setDepart(depart);
		reservation.setCode("ABC" + counter);
		counter++;
		reservations.put(reservation.getCode(), reservation);
		return reservation;
	}


	@Override
	public Reservation searchReservation(String code) {
		if (reservations.containsKey(code)){
			return reservations.get(code);
		}
		return null;
	}


	@Override
	public void deleteReservation(String code) {
		if(reservations.containsKey(code)){
			reservations.remove(code);
		}
	}


	@Override
	public void updateReservation(String code, Reservation updated) {
		if(reservations.containsKey(code)){
			reservations.put(code, updated);
		}
		
	}

}
