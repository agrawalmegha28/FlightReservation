package edu.npu.fr.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FlightValidator implements Validator{

	@Override
	public boolean supports(Class<?> targetClass) {
		return targetClass.equals(Flight.class);
	}

	@Override
	public void validate(Object tstObj, Errors errors) {
		// TODO Auto-generated method stub
		Flight flight = (Flight) tstObj;
		if(flight.getFrom() == null || flight.getFrom().trim().equals(""))
			errors.rejectValue("from", "empty.flight.from");
		if(flight.getTo() == null || flight.getTo().trim().equals(""))
			errors.rejectValue("to", "empty.flight.to");
		if(flight.getDepart() == null || flight.getDepart().trim().equals(""))
			errors.rejectValue("depart", "empty.flight.depart");
		else {
			Date date = null;
			DateFormat format = new SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH);
			try {
				date = format.parse(flight.getDepart().trim());
			} catch (ParseException e) {
				errors.rejectValue("depart", "invalid.flight.depart");
			}
			flight.setDepart(flight.getDepart().trim());
			
		}
		
	}

}
