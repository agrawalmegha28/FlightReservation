package edu.npu.fr.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

@Service
public class FlightReservationService implements FlightReservationServiceI{
	@Autowired
	@Qualifier("reservationDaoJdbc")
	private ReservationDaoI dao;

	@Override
	public List<Flight> getFlights(String from, String to, Date depart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation reserveFlight(Flight flight, Date depart,
			List<Passenger> passengers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation searchReservation(String code) {
		return dao.searchReservation(code);
	}

	@Override
	public void deleteReservation(String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReservation(String code, Reservation updated) {
		// TODO Auto-generated method stub
		
	}

}
