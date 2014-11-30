package edu.npu.fr.dao;

import edu.npu.fr.domain.Passenger;

public interface PassengerDaoI {
	public Passenger addNewPassenger(Passenger passenger);
	public int deletePassenger(Passenger passenger);
}
