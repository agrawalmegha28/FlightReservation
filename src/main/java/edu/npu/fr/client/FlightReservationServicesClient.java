package edu.npu.fr.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import edu.npu.fr.domain.Reservation;


/* This is code that would appear on the Client side.   Remember that before you can run this, you must
 * have your Rest Server running!
 */
public class FlightReservationServicesClient {
	static private Logger logger = Logger.getLogger(FlightReservationServicesClient.class);
	private static String RESERVATION_SERVICES_URL = "http://localhost:8080/FlightReservation/webservices/flightrestapp/reservation/";
	private static Client authclient=null;  /* Required for JAX-RS authorization processing -- client will add Authorization Header */
	private static Client client=null;  
	
	public static void main(String args[]) {
		testLookupReservation();
    }
	
	
	/* Client that will add an Authorization header */
	private static Client getClientWithAuth() {
		if (authclient == null) {
			authclient = ClientBuilder.newClient();
			/* Dummy user/password that should be overridden in the actual invocations */
			HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic("user", "password");
			authclient.register(authFeature);
		}
		
		return authclient;
	}
	
	/* Client that will not add the authorization header */
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		
		return client;
	}
	
	public static Reservation testLookupReservation() {
		String codeToLookup = "N6TLJK";  /* Just some hardcoded test data */
		int responseCode;
		Reservation res=null;
		
		Client client = getClient();
		
		//Targeting the RESTful Webservice we want to invoke by capturing it in WebTarget instance.
		WebTarget target = client.target(RESERVATION_SERVICES_URL + codeToLookup);
		
		
		//Building the request i.e a GET request to the RESTful Webservice defined
		//by the URI in the WebTarget instance.
		Invocation getAddrEntryInvocation = target.request(MediaType.APPLICATION_XML_TYPE).buildGet();
		Response response = getAddrEntryInvocation.invoke();
		
		responseCode = response.getStatus();
		logger.info("The response code is: " + responseCode);
		if (responseCode == 200) {
			res = response.readEntity(Reservation.class);
			logger.info(res);
		}
		
		return res;
	}

}
