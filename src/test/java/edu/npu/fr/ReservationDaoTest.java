package edu.npu.fr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.fr.dao.FlightDaoI;
import edu.npu.fr.dao.PassengerDaoI;
import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

@ContextConfiguration("classpath:flightdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservationDaoTest {

	@Autowired
	@Qualifier("reservationDaoJdbc")
	private ReservationDaoI dao;
	
	@Autowired
	@Qualifier("flightDaoJdbc")
	private FlightDaoI flightDao;
	
	@Test
//This method will test for the reserved flight
	public void testReserveFlight(){
		Flight flight = new Flight();
		flight.setDepart(new Date());
		flight.setFrom("USA");
		flight.setTo("India");
		flight.setName("Lufthansa");
		flight.setPrice(1000);
		flight.setNo(200);
		flightDao.addNewFlight(flight);
		
		Passenger passenger = new Passenger();
		passenger.setDob(new Date());
		passenger.setfName("Megha");
		passenger.setlName("Agrawal");
		passenger.setGender("F");
		
		
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengers.add(passenger);
		
		
		Reservation reservation = dao.reserveFlight(flight, new Date(), passengers);
		Assert.assertNotEquals(reservation.getId().size(), 0);
	}
	
	@Test
	//This method will test for rollback when there is wrong entry
	public void testReserveFlightRollback(){
		Flight flight = new Flight();
		flight.setDepart(new Date());
		flight.setFrom("France");
		flight.setTo("USA");
		flight.setName("Lufthansa");
		flight.setPrice(5000);
		flight.setNo(400);
		flightDao.addNewFlight(flight);
		
		Passenger passenger = new Passenger();
		passenger.setDob(new Date());
		passenger.setfName("Megha");
		passenger.setlName("Agrawal");
		passenger.setGender("F");
		
		Passenger invalid = new Passenger();
		invalid.setDob(new Date());
		invalid.setfName(null);
		invalid.setlName("Agrawal");
		invalid.setGender("F");
		
		
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengers.add(passenger);
		passengers.add(invalid);
		
		
		passengers.add(passenger);
		Reservation reservation = null;
		try{
				reservation = dao.reserveFlight(flight, new Date(), passengers);
		}catch(Exception e){
			Assert.assertEquals(invalid.getId(), 0);
			Assert.assertNull(reservation);
		}
	}
}
