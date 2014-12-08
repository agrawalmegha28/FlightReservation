package edu.npu.fr.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.fr.dao.PassengerDaoI;
import edu.npu.fr.dao.ReservationDaoI;
import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;

@Repository("reservationDaoJdbc")
@Transactional
public class ReservationDaoJdbc implements ReservationDaoI{
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private ReservationRowMapper rowMapper;
	@Autowired
	private PassengerDaoI passengerDao;
	
	@PostConstruct
	public void setUp(){
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = new ReservationRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
	        .withTableName("Reservation")
	        .usingGeneratedKeyColumns("id")
	        .usingColumns("Flight_id", "Passenger_id", "Code");
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	String randomString( int len ) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	public void deleteReservation(String code){
		String sql = "DELETE FROM RESERVATION WHERE Code = :code";
		MapSqlParameterSource params = new MapSqlParameterSource("code", code);
		PreparedStatementCallback<Object> action = new PreparedStatementCallback<Object>() {
			
			@Override
			public Object doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				return ps.execute();
			}
		};
		dbTemplate.execute(sql, params, action);
	}
	
	private MapSqlParameterSource getReservationParamMap(Reservation reservation){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("Code", reservation.getCode());
		params.addValue("Flight_id", reservation.getFlight().getId());
		return params;
	}

	@Override
	@Transactional(readOnly=false)
	public Reservation reserveFlight(Flight flight, Date depart,
			List<Passenger> passengers) {
		
		Reservation reservation = new Reservation();
		reservation.setPassenger(passengers);
		reservation.setFlight(flight);
		reservation.setCode(randomString(6));
		
		// TODO Auto-generated method stub
		
		for(Passenger passenger : passengers){
			passengerDao.addNewPassenger(passenger);
			MapSqlParameterSource params = getReservationParamMap(reservation);
			params.addValue("Passenger_id", passenger.getId());
			Number key = jdbcInsert.executeAndReturnKey(params);
			reservation.getId().add(key.intValue());
		}
		return reservation;
	}

	@Override
	public Reservation searchReservation(String code) {
		String sql = "SELECT * FROM RESERVATION AS R, "
				+ "Flight AS F, "
				+ "Passenger AS P "
				+ "WHERE Code = :code AND F.id = R.Flight_id "
				+ "AND R.Passenger_id = P.id";
		MapSqlParameterSource params = new MapSqlParameterSource("code", code);
		Reservation reservation = dbTemplate.queryForObject(sql, params, rowMapper);
		return reservation;
	}

	@Override
	public void updateReservation(String code, Reservation updated) {
		deleteReservation(code);
		String sql = "INSERT INTO RESERVATION (id, Flight_id, Passenger_id, Code) " +
					 "VALUES (:code, :flight_id, :passenger_id)";
		
		
	}
	
}
