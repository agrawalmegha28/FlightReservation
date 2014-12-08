package edu.npu.fr.resthandlers;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.fr.domain.Reservation;
import edu.npu.fr.exceptions.UnknownResourceException;
import edu.npu.fr.services.FlightReservationServiceI;



@Path("/flightrestapp")
public class FlightRestHandler {
	@Autowired
	private FlightReservationServiceI flightResService;
	private Logger logger = Logger.getLogger(FlightRestHandler.class);
	
	/* Test Url:
	 * http://localhost:8080/zuniversity/webservices/studrestapp/student/100
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/reservation/{code}")
	@Produces("application/xml, application/json")
	public Reservation searchReservation(@PathParam("code") String code) {
		Reservation res = null;
		res = flightResService.searchReservation(code);
		logger.debug("Fetched Reservation :" + res);
		if (res == null) {
			throw new UnknownResourceException(" code: " + code + " is invalid");
		}
		return res;
	}
	
	/* Test Url:  Use HTTP Delete command
	 * http://localhost:8080/zuniversity/webservices/studrestapp/student/100
	 */
	@DELETE
	@Path("/reservation/{code}")
	public Response deleteReservation(@PathParam("code") String code) {
		ResponseBuilder respBuilder;
		flightResService.deleteReservation(code);
		respBuilder = Response.ok();
		logger.debug("Deleted Reservation with code" + code);
		return respBuilder.build();
	}
	
	@POST
	@Path("/reservation")
	public Response createReservation(Reservation reservation) {
		ResponseBuilder respBuilder;
		
		Reservation r = flightResService.reserveFlight(reservation.getFlight(), 
				reservation.getDepart(), reservation.getPassenger());
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(r);
		return respBuilder.build();
	}
	
}
