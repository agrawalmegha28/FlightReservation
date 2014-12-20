package edu.npu.fr.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.fr.domain.Flight;
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
	
	
}
