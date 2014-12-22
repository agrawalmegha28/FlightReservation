package edu.npu.fr.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PassengerValidator implements Validator{

	@Override
	public boolean supports(Class<?> targetClass) {
		return targetClass.equals(Passenger.class);
	}

	@Override
	public void validate(Object tstObj, Errors errors) {
		// TODO Auto-generated method stub
		Passenger passenger = (Passenger) tstObj;
		if(passenger.getfName() == null || passenger.getfName().trim().equals(""))
			errors.rejectValue("passenger", "empty.fname");
		if(passenger.getlName() == null || passenger.getlName().trim().equals(""))
			errors.rejectValue("passenger", "empty.lname");
		if(passenger.getDob() == null || passenger.getDob().trim().equals(""))
			errors.rejectValue("passenger", "empty.dob");
		else {
			Date date = null;
			DateFormat format = new SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH);
			try {
				date = format.parse(passenger.getDob().trim());
			} catch (ParseException e) {
				errors.rejectValue("passenger", "invalid.dob");
			}
			passenger.setDob(passenger.getDob().trim());
			
		}
		if(passenger.getGender() == null || passenger.getGender().trim().equals(""))
			errors.rejectValue("passenger", "empty.gender");
		else{
			String gender = passenger.getGender().trim();
			if(!gender.equals("M") && !gender.equals("F"))
				errors.rejectValue("passenger", "invalid.gender");
		}
		
	}

}
