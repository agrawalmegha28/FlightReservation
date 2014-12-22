package edu.npu.fr.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservationValidator implements Validator{
	private PassengerValidator passengerVal = new PassengerValidator();
	
	
	@Override
	public boolean supports(Class<?> targetClass) {
		return targetClass.equals(Reservation.class);
	}

	@Override
	public void validate(Object tstObj, Errors errors) {
		// TODO Auto-generated method stub
		Reservation reservation = (Reservation) tstObj;
		if(reservation.getPassenger().size() == 0){
			errors.rejectValue("passenger", "empty.passengerlist");
			return;
		}
		for(Passenger p : reservation.getPassenger()){
			passengerVal.validate(p, errors);
		}
		
	}

}
