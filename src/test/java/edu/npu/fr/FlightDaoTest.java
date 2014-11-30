package edu.npu.fr;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.fr.dao.FlightDaoI;
import edu.npu.fr.domain.Flight;

@ContextConfiguration("classpath:flightdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightDaoTest{
	
	@Autowired
	@Qualifier("flightDaoJdbc")
	private FlightDaoI dao;

	@Test
	//This method will test for adding flight
	public void testAddFlight(){
		Flight flight = new Flight();
		flight.setDepart(new Date());
		flight.setFrom("USA");
		flight.setTo("India");
		flight.setName("Lufthansa");
		flight.setPrice(1000);
		flight.setNo(200);
		Flight added = dao.addNewFlight(flight);
		Assert.assertEquals(flight, added);
		Assert.assertNotEquals(added.getId(), 0);
	}
	
	@Test
	//This method will test for deleting flight
	public void testDeleteFlight(){
		Flight flight = new Flight();
		flight.setDepart(new Date());
		flight.setFrom("USA");
		flight.setTo("India");
		flight.setName("Lufthansa");
		flight.setPrice(1000);
		flight.setNo(200);
		int deleted = dao.deleteFlight(flight);
		Assert.assertEquals(1, deleted);
	}
	
	@Test
	//This method will test for updating flight
	public void testUpdateFlight(){
		Flight flight = new Flight();
		flight.setDepart(new Date());
		flight.setFrom("South Africa");
		flight.setTo("India");
		flight.setName("Emirates");
		flight.setPrice(5000);
		flight.setNo(450);
		dao.addNewFlight(flight);
		
		flight.setNo(451);
		int updated = dao.updateFlight(flight);
		Assert.assertEquals(updated, 1);
	}

}
