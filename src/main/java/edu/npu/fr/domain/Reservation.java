package edu.npu.fr.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reservation")
public class Reservation implements Serializable{
	private List<Integer> id = new ArrayList<Integer>();
	private List<Passenger> passenger = new ArrayList<Passenger>();
	private Flight flight;
	private Date depart;
	private String code;
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public Date getDepart() {
		return depart;
	}
	public void setDepart(Date depart) {
		this.depart = depart;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Integer> getId() {
		return id;
	}
	public void setId(List<Integer> id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		
		return "Flight: " +flight + "Code: " + code + " Passenger: " + passenger;
	}

}
