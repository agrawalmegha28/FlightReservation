package edu.npu.fr.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.fr.domain.Flight;


public class FlightRowMapper implements RowMapper<Flight> {    
		 public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {    
		  Flight flight = new Flight();    
		  flight.setId(rs.getInt("id"));    
		  flight.setFrom(rs.getString("Ffrom"));    
		  flight.setTo(rs.getString("Fto"));    
		  flight.setName(rs.getString("FName"));   
		  flight.setPrice(rs.getFloat("Price"));
		  flight.setDepart(rs.getDate("Depart").toString());
		  flight.setNo(rs.getInt("FNo"));
		  return flight;    
		 }    
		   
}
