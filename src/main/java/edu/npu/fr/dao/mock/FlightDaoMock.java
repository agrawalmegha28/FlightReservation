package edu.npu.fr.dao.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import edu.npu.fr.dao.FlightDaoI;
import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

public class FlightDaoMock implements FlightDaoI {
	public FlightDaoMock() {
		super();
	}
	@Override
	public List<Flight> getFlights(String from, String to, Date depart) {
		List<Flight> flights = new ArrayList<Flight>();
		for (int i = 0; i < 10; i++ ){
			Flight f = new Flight();
			f.setName("A" + i);
			f.setDepart(depart);
			f.setFrom(from);
			f.setPrice(10000);
			f.setTo(to);
			flights.add(f);
		}
		return flights;
	}
	@Override
	public Flight addNewFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deleteFlight(Flight flight) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		return 0;
	}
}
