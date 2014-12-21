package edu.npu.fr.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

public class ReservationRowMapper implements RowMapper<Reservation> {
	private List<Passenger> passengers = null;
	public ReservationRowMapper() {
		passengers = new ArrayList<Passenger>();
	}

	public Reservation mapRow(ResultSet resultSet, int row) throws SQLException {
		Reservation reservation = new Reservation();
		
		reservation.setCode(resultSet.getString("Code"));
		
		
		Flight flight = new Flight();
		flight.setFrom(resultSet.getString("Ffrom"));
		flight.setTo(resultSet.getString("Fto"));
		flight.setId(resultSet.getInt("Flight_id"));
		flight.setDepart(resultSet.getDate("Depart").toString());
		flight.setName(resultSet.getString("F.FName"));
		flight.setPrice(resultSet.getFloat("Price"));
		reservation.setFlight(flight);
		
		Passenger p = new Passenger();
		p.setDob(resultSet.getDate("Dob").toString());
		p.setfName(resultSet.getString("P.FName"));
		p.setlName(resultSet.getString("LName"));
		p.setmName(resultSet.getString("MName"));
		p.setGender(resultSet.getString("Gender"));
		p.setId(resultSet.getInt("Passenger_id"));
		passengers.add(p);
		reservation.setPassenger(passengers);
		
		return reservation;
	}

}
