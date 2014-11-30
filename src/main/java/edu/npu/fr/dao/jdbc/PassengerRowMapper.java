package edu.npu.fr.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;

public class PassengerRowMapper implements RowMapper<Passenger> {    
	
	
		 public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {    
		  Passenger passenger = new Passenger();    
		  passenger.setId(rs.getInt("id"));    
		  passenger.setfName(rs.getString("FName"));    
		  passenger.setmName(rs.getString("MName"));    
		  passenger.setlName(rs.getString("LName"));   
		  passenger.setGender(rs.getString("Gender"));
		  passenger.setDob(rs.getDate("Dob"));
		  
		  return passenger;    
		 }    
		   
}
