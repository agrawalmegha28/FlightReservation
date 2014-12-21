package edu.npu.fr.dao.jdbc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;

import edu.npu.fr.dao.FlightDaoI;
import edu.npu.fr.dao.PassengerDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;


@Repository("passengerDaoJdbc")
@Transactional
public class PassengerDaoJdbc implements PassengerDaoI {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private PassengerRowMapper passengerRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		passengerRowMapper = new PassengerRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Passenger")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("FName", "MName", "LName", "Gender", "Dob");
		
	}
	
	

	private MapSqlParameterSource getPassengerParamMap(Passenger passenger) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("FName", passenger.getfName());
		params.addValue("MName", passenger.getmName());
		params.addValue("LName", passenger.getlName());
		params.addValue("Gender", passenger.getGender());
		Date dob = null;
		DateFormat format = new SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH);
		try {
			dob = format.parse(passenger.getDob());
		} catch (ParseException e) {
				System.out.println("DOB is " + passenger.getDob());
		}
		params.addValue("Dob", dob);
		return params;
	}
	
	
	@Override
	@Transactional(readOnly=false)
	public Passenger addNewPassenger(Passenger passenger) {
		MapSqlParameterSource params = getPassengerParamMap(passenger);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    passenger.setId(newId.longValue());
	    return passenger;
	}



	@Override
	public int deletePassenger(Passenger passenger) {
		MapSqlParameterSource params =  getPassengerParamMap(passenger);
		String sql = "DELETE FROM Passenger WHERE id =: id";
		return dbTemplate.update(sql, params);
	}

}
