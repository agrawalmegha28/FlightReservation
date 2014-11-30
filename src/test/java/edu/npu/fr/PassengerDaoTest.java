package edu.npu.fr;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.fr.dao.PassengerDaoI;
import edu.npu.fr.domain.Passenger;

@ContextConfiguration("classpath:flightdao-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PassengerDaoTest{
	
	@Autowired
	@Qualifier("passengerDaoJdbc")
	private PassengerDaoI dao;

	@Test
	//This method will test for adding Invalid passenger
	public void testAddInvalidPassenger(){
		Passenger p = new Passenger();
		p.setDob(new Date());
		//database exception will happen for null first name
		p.setfName(null);
		p.setlName("Agrawal");
		p.setGender("F");
		
		try{
			dao.addNewPassenger(p);
		}catch(Exception e){
			Assert.assertEquals(p.getId(), 0);
		}
		
		
	}
	
}
