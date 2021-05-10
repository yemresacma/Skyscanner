package com.finartz.skyscanner;

import com.finartz.skyscanner.exception.WrongFlightEntityException;
import com.finartz.skyscanner.exception.WrongRouteEntityException;
import com.finartz.skyscanner.model.*;
import com.finartz.skyscanner.service.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkyscannerApplicationTests {
	@Autowired
	AirportService airportService;
	@Autowired
	CompanyService companyService;
	@Autowired
	RouteService routeService;
	@Autowired
	FlightService flightService;
	@Autowired
	TicketService ticketService;

	Airport airport1;
	Airport airport2;
	Airport airport3;
	Company company1;
	Company company2;
	Company company3;
	Route route1;
	Route route2;
	Flight flight;

	// Airport related tests
	@Test
	public void stage01SaveGetAirportTest() {
		airport1 = new Airport(Constants.TEST_AIRPORT_NAME1);
		airport2 = new Airport(Constants.TEST_AIRPORT_NAME2);
		airport3 = new Airport(Constants.TEST_AIRPORT_NAME2);

		airportService.saveOrUpdate(airport1);
		airportService.saveOrUpdate(airport2);

		// test saving with the same name
		Assertions.assertThrows(DataIntegrityViolationException.class, () ->
				airportService.saveOrUpdate(airport3));

		// get all
		assertThat(airportService.getAllAirports().size() == 2);

		// get by id
		Airport savedAirport = airportService.getAirportByName(Constants.TEST_AIRPORT_NAME1);
		assertThat(savedAirport.getName() == Constants.TEST_AIRPORT_NAME1);

		// get by name
		savedAirport = airportService.getAirportByName(Constants.TEST_AIRPORT_NAME2);
		assertThat(savedAirport.getName() == Constants.TEST_AIRPORT_NAME2);

		Assertions.assertThrows(EntityNotFoundException.class, () ->
				airportService.getAirportByName("invalidName"));

		// invalid ID
		Assertions.assertThrows(EntityNotFoundException.class, () ->
				airportService.getAirportById(-1L));
	}

	@Test
	public void stage02UpdateAirportTest() {
		Airport savedAirport = airportService.getAirportByName(Constants.TEST_AIRPORT_NAME1);
		long previousId = savedAirport.getId();
		savedAirport.setName(Constants.TEST_AIRPORT_NAME3);
		airportService.saveOrUpdate(savedAirport);
		savedAirport = airportService.getAirportByName(Constants.TEST_AIRPORT_NAME3);
		long currentId = savedAirport.getId();

		// Ids are also checked to make sure it is the same entity with different name;
		assertThat(currentId == previousId);
		assertThat(savedAirport.getName() == Constants.TEST_AIRPORT_NAME3);
	}

	@Test
	public void stage03DeleteAirportTest() {
		airportService.deleteById(Constants.TEST_AIRPORT_ID1);
		airportService.deleteById(Constants.TEST_AIRPORT_ID2);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
				airportService.deleteById(Constants.TEST_AIRPORT_ID1));
		assertThat(airportService.getAllAirports().size() == 0);
	}

	// Company related tests
	@Test
	public void stage04GetSaveCompanyTest() {
		company1 = new Company(Constants.TEST_COMPANY_NAME1);
		company2 = new Company(Constants.TEST_COMPANY_NAME2);
		company3 = new Company(Constants.TEST_COMPANY_NAME2);

		companyService.saveOrUpdate(company1);
		companyService.saveOrUpdate(company2);

		// test saving with the same name
		Assertions.assertThrows(DataIntegrityViolationException.class, () ->
				companyService.saveOrUpdate(company3));

		// get all
		assertThat(companyService.getAllCompanies().size() == 2);

		// get by id
		Company savedCompany = companyService.getCompanyByName(Constants.TEST_COMPANY_NAME1);
		assertThat(savedCompany.getName() == Constants.TEST_COMPANY_NAME1);

		// get by name
		savedCompany = companyService.getCompanyByName(Constants.TEST_COMPANY_NAME2);
		assertThat(savedCompany.getName() == Constants.TEST_COMPANY_NAME2);

		Assertions.assertThrows(EntityNotFoundException.class, () ->
				companyService.getCompanyByName("invalidName"));

		// invalid ID
		Assertions.assertThrows(EntityNotFoundException.class, () ->
				companyService.getCompanyById(999L));
	}

	@Test
	public void stage05UpdateCompanyTest() {
		Company savedCompany = companyService.getCompanyByName(Constants.TEST_COMPANY_NAME1);
		long previousId = savedCompany.getId();
		savedCompany.setName(Constants.TEST_COMPANY_NAME3);
		companyService.saveOrUpdate(savedCompany);
		savedCompany = companyService.getCompanyByName(Constants.TEST_COMPANY_NAME3);
		long currentId = savedCompany.getId();

		// Ids are also checked to make sure it is the same entity with different name;
		assertThat(currentId == previousId);
		assertThat(savedCompany.getName() == Constants.TEST_COMPANY_NAME3);
	}

	@Test
	public void stage06DeleteCompanyTest() {
		companyService.deleteById(Constants.TEST_COMPANY_ID1);
		companyService.deleteById(Constants.TEST_COMPANY_ID2);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () ->
				companyService.deleteById(Constants.TEST_COMPANY_ID1));
		assertThat(companyService.getAllCompanies().size() == 0);
	}

	// Route related tests
	@Test
	public void stage07RouteTest() throws WrongRouteEntityException {
		// create and save airports to be able to test Route
		airport1 = new Airport(Constants.TEST_AIRPORT_NAME1);
		Airport newAirport2 = new Airport(Constants.TEST_AIRPORT_NAME2);
		Airport newAirport3 = new Airport(Constants.TEST_AIRPORT_NAME3);
		airportService.saveOrUpdate(airport1);
		airportService.saveOrUpdate(newAirport2);
		airportService.saveOrUpdate(newAirport3);

		route1 = new Route(airport1, newAirport2);
		routeService.saveOrUpdate(route1);

		Route savedRoute = routeService.getRoute(Constants.TEST_AIRPORT_NAME1, Constants.TEST_AIRPORT_NAME2);
		assertThat(savedRoute.getId() == route1.getId());
		assertThat(savedRoute.getDeparturePoint().getId() == airport1.getId());
		assertThat(savedRoute.getArrivalPoint().getId() == newAirport2.getId());

		// test with same arrival&departure points
		route2 = new Route(airport1, airport1);
		Assertions.assertThrows(WrongRouteEntityException.class, () -> routeService.saveOrUpdate(route2));

		route2.setArrivalPoint(newAirport2);
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> routeService.saveOrUpdate(route2));

		route2.setArrivalPoint(newAirport3);
		routeService.saveOrUpdate(route2);
		assertThat(routeService.getAllRoutes().size() == 2);
	}

	@Test
	public void stage08FlightTest() throws Exception {
		LocalDate date = LocalDate.now();
		company1 = new Company(Constants.TEST_COMPANY_NAME1);
		companyService.saveOrUpdate(company1);
		route1 = routeService.getRoute(Constants.TEST_AIRPORT_NAME1, Constants.TEST_AIRPORT_NAME2);

		// test with invalid date
		flight = new Flight(0, 0, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), company1, route1);
		Assertions.assertThrows(WrongFlightEntityException.class, () ->
				flightService.save(flight));

		//  with invalid seat number
		date = date.plusYears(1);
		flight.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assertions.assertThrows(Exception.class, () ->
				flightService.save(flight));

		// test with invalid ticket price
		flight.setTotalSeat(100);
		Assertions.assertThrows(Exception.class, () ->
				flightService.save(flight));

		flight.setInitialTicketPrice(100);
		flightService.save(flight);
		Flight savedFlight = flightService.getFlight(1L);

		assertThat(savedFlight.getCompany().getId() == flight.getId());
		assertThat(savedFlight.getInitialTicketPrice() == flight.getInitialTicketPrice());
		assertThat(savedFlight.getTotalSeat() == flight.getTotalSeat());
		assertThat(savedFlight.getDate() == flight.getDate());
		assertThat(savedFlight.getSoldTicketNumber() == flight.getSoldTicketNumber());
		assertThat(savedFlight.getRoute().getId() == flight.getRoute().getId());
		assertThat(flightService.getFlight("invalidDeparturePoint", "invalidArrivalPoint").size() == 0);
	}

	@Test
	public void stage09TicketBuyTest() throws Exception {
		flight = flightService.getFlight(Constants.TEST_AIRPORT_NAME1, Constants.TEST_AIRPORT_NAME2).get(0);

		// test with invalid id
		LinkedHashMap ticketRequest = new LinkedHashMap();
		ticketRequest.put("creditCardNumber", "123123");
		ticketRequest.put("flightInfoId", -1L);
		Assertions.assertThrows(EntityNotFoundException.class, () ->
				ticketService.buyTicket(ticketRequest));

		// test with invalid credit cards
		ticketRequest.put("flightInfoId", flight.getId());
		Assertions.assertThrows(Exception.class, () ->
				ticketService.buyTicket(ticketRequest));

		ticketRequest.put("creditCardNumber", "112123.123123.231");
		Assertions.assertThrows(Exception.class, () ->
				ticketService.buyTicket(ticketRequest));

		ticketRequest.put("creditCardNumber", "112123.123123.131");
		Assertions.assertThrows(Exception.class, () ->
				ticketService.buyTicket(ticketRequest));

		// check that we get ticket for the correct flight
		int numberOfTicketsToBuy = 20;
		final int numberOfTicketSold = numberOfTicketsToBuy;
		ticketRequest.put("creditCardNumber", "1234-1234-1234-1234");
		Ticket firstTicket = ticketService.buyTicket(ticketRequest);
		numberOfTicketsToBuy--;
		assertThat(flight.getId() == firstTicket.getFlightInfo().getId());

		// check price change

		while (numberOfTicketsToBuy-- > 0) {
			ticketService.buyTicket(ticketRequest);
		}

		// price suppose to raise 2 times with raise rate %10 which means %21
		Ticket lastTicket = ticketService.buyTicket(ticketRequest);
		assertThat(flight.getId() == firstTicket.getFlightInfo().getId());
		assertThat(firstTicket.getTicketPrice() * 121 / 100 == lastTicket.getTicketPrice());
		assertThat(lastTicket.getFlightInfo().getSoldTicketNumber() == numberOfTicketSold);
	}

	@Test
	public void stage10TicketReturnTest() {
		Ticket ticket = ticketService.getTicketById(1L);
		Flight flight = ticket.getFlightInfo();
		int numberOfSoldTickets = flight.getSoldTicketNumber();

		// try to return with invalid number
		LinkedHashMap ticketRequest = new LinkedHashMap();
		ticketRequest.put("ticketNumber", "-1");
		Assertions.assertThrows(EntityNotFoundException.class, () ->
				ticketService.returnTicket(ticketRequest));

		// success case
		ticketRequest.put("ticketNumber", "1");
		ticketService.returnTicket(ticketRequest);
		flight = flightService.getFlight(flight.getId()); // get updated flight
		assertThat(numberOfSoldTickets - 1 == flight.getSoldTicketNumber());
	}
}
