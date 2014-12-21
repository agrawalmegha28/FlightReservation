package edu.npu.fr.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.fr.domain.Flight;
import edu.npu.fr.domain.Passenger;
import edu.npu.fr.domain.Reservation;
import edu.npu.fr.services.FlightReservationServiceI;

@Controller
public class FlightReservationController {
	@Autowired
	FlightReservationServiceI flightReservationService;
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("flight", new Flight());
		return mv;
	}
	
	
	@RequestMapping(value="/reservationcode", method = RequestMethod.GET)
	public String reservationCodeForm(){
		return "findRes";
	}
	
	@RequestMapping(value="/listReservation", method = RequestMethod.POST)
	public ModelAndView listReservation(String reservationCode){
		Reservation res = flightReservationService.searchReservation(reservationCode);
		ModelAndView mv = new ModelAndView("viewReservation");
		mv.addObject("flight", res.getFlight());
		mv.addObject("passengerList", res.getPassenger());
		return mv;
	}
	@RequestMapping(value="/searchFlight", method = RequestMethod.POST)
	public ModelAndView searchFlight(@Valid Flight flight, BindingResult result){
		ModelAndView mv = null;
		if(result.hasErrors()){
			mv = new ModelAndView("home", "flight", flight);
			return mv;
		}
		
		List<Flight> flights = flightReservationService.getFlights(flight.getFrom(), flight.getTo(), null);
		mv = new ModelAndView("viewFlights");
		mv.addObject("flightList", flights);
		return mv;
		
	}
	
	@RequestMapping(value="/book", method=RequestMethod.GET)
	public ModelAndView bookFlight(String flightId){
		int flightIdInt = Integer.parseInt(flightId);		
		Flight flight = flightReservationService.getFlight(flightIdInt);
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		//Add mock passengers
		reservation.getPassenger().add(new Passenger());
		reservation.getPassenger().add(new Passenger());
		reservation.getPassenger().add(new Passenger());
		reservation.getPassenger().add(new Passenger());
		
		ModelAndView mv = new ModelAndView("bookView", "reservation", reservation);
		
		return mv;
	}
	
	@RequestMapping(value="/makeReservation", method=RequestMethod.POST)
	public ModelAndView createReservation(Reservation reservation, BindingResult errors, HttpServletRequest request){
		ModelAndView mv = null;
		System.out.println(reservation);
		if(errors.hasErrors()){
			errors.reject("Some error");
			System.out.println(errors);
			mv = new ModelAndView("bookView", "reservation", reservation);
			return mv;
		}
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date depart = null;
		try {
			depart = format.parse(reservation.getFlight().getDepart());
		} catch (ParseException e) {
			errors.reject("Invalid dob");
			System.out.println(errors);
			mv = new ModelAndView("bookView", "reservation", reservation);
			return mv;

		}
		
		List<Passenger> authentic = new ArrayList<Passenger>();
		for(Passenger p : reservation.getPassenger())
			if(p.isValid())
				authentic.add(p);
		
		Reservation res = flightReservationService.reserveFlight(reservation.getFlight(), depart, authentic);
		
		mv = new ModelAndView("viewReservation");
		mv.addObject("flight", res.getFlight());
		mv.addObject("passengerList", res.getPassenger());
		mv.addObject("code",res.getCode());
		return mv;
	}
	
	
}
