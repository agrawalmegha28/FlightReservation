package edu.npu.fr.domain;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "flight")
public class Flight {
	private long id;
	private String name;
	private String from;
	private String to;
	private Date depart;
	private float price;
	private int no;
	
	
	public Flight() {
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name2) {
		this.name = name2;
	}
	
	public String toString() {
		return "Flight[id: " + id + ", " + name + ", " + from + ", " + to + ", ]";
	}
	
	public boolean hasId(long id) {
		return this.id == id;
	}
	
	public boolean equals(Object tstObj) {
		Flight tstFlight;
		
		if (!(tstObj instanceof Flight)) return false;
		tstFlight = (Flight) tstObj;
		
		if ((tstFlight.no != no) || !(tstFlight.name.equals(name))) {
			return false;
		}
		
		return true;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
