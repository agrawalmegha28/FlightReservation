package edu.npu.fr.dao.jdbc;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.fr.dao.FlightDaoI;
import edu.npu.fr.domain.Flight;


@Repository("flightDaoJdbc")
@Transactional
public class FlightDaoJdbc implements FlightDaoI {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private ReservationRowMapper studentRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		studentRowMapper = new ReservationRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Flight")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("FNo", "FName", "Ffrom", "Fto", "Depart", "Price");
		
	}
	
	
//This method populates column value for a particular table
	private MapSqlParameterSource getFlightParamMap(Flight flight) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", flight.getId());
		params.addValue("FNo", flight.getNo());
		params.addValue("FName", flight.getName());
		params.addValue("Ffrom", flight.getFrom());
		params.addValue("Fto", flight.getTo());
		params.addValue("Depart", flight.getDepart());
		params.addValue("Price", flight.getPrice());
		return params;
	}
	
	@Override
	//This method will insert new flight
	public Flight addNewFlight(Flight flight) {
		MapSqlParameterSource params = getFlightParamMap(flight);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    flight.setId(newId.longValue());
	    return flight;
	}



	@Override
	//This method will read flights
	public List<Flight> getFlights(String from, String to, Date depart) {
		String sql = "SELECT * FROM Flight WHERE Ffrom = :Ffrom AND Fto = :Fto";
		Flight flight = new Flight();
		flight.setFrom(from);
		flight.setTo(to);
		flight.setDepart(depart.toString());
		System.out.println(flight);
		MapSqlParameterSource params = getFlightParamMap(flight);
		List<Flight> flights = dbTemplate.query(sql, params, new FlightRowMapper());
		return flights;  
	}



	@Override
	//This method will delete flights
	public int deleteFlight(Flight flight) {
		MapSqlParameterSource params = getFlightParamMap(flight);
		String sql = "DELETE FROM Flight WHERE FNo = :FNo AND FName = :FName";
		return dbTemplate.update(sql, params);
		
	}



	@Override
	//This method will update flights
	public int updateFlight(Flight flight) {
		MapSqlParameterSource params = getFlightParamMap(flight);
		String sql = "UPDATE FLIGHT SET Ffrom = :FNo, Depart = :Depart, FNo = :FNo, FName = :FName, Price = :Price "
				+ "WHERE id = :id";
		return dbTemplate.update(sql, params);
		
	}


	
	public Flight getFlight(int flightId) {
		String sql = "SELECT * FROM Flight WHERE id = :id";
		Flight flight = new Flight();
		flight.setId(flightId);
		MapSqlParameterSource params = getFlightParamMap(flight);
		List<Flight> flights = dbTemplate.query(sql, params, new FlightRowMapper());
		return flights.get(0);  
	}

}
