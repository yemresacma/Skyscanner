package com.finartz.skyscanner.service;

import com.finartz.skyscanner.exception.FlightNotFoundException;
import com.finartz.skyscanner.exception.NotEnoughBalanceException;
import com.finartz.skyscanner.exception.TicketNotFoundException;
import com.finartz.skyscanner.model.Flight;
import com.finartz.skyscanner.model.Ticket;
import com.finartz.skyscanner.repository.FlightRepository;
import com.finartz.skyscanner.repository.TicketRepository;
import com.finartz.skyscanner.utility.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    FlightRepository flightRepository;

    ReentrantLock buyReturnLock = new ReentrantLock();

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        ticketRepository.findAll().forEach(ticket -> tickets.add(ticket));
        return tickets;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException());
    }

    public void saveOrUpdate(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    /**
     *
     * @param ticketRequestObject is json object with given structure below
     * "flightInfoId" -> flight id
     * "creditCardNumber" -> card number
     * @return saved ticket in case of success
     * @throws Exception in case of invalid flight or credit card info
     */
    public Ticket buyTicket(Object ticketRequestObject) throws Exception {
        // parse json object and extract credit card number and flight Id
        LinkedHashMap ticketRequest = ((LinkedHashMap) ticketRequestObject);
        Long flightInfoId = Long.parseLong(ticketRequest.get("flightInfoId").toString());
        String creditCardNumber = ((LinkedHashMap) ticketRequest).get("creditCardNumber").toString();

        buyReturnLock.lock();

        // throw an exception in case of flight does not exist
        Flight flightInfo = flightRepository.findById(flightInfoId)
                .orElseThrow(() -> new FlightNotFoundException("id"));

        LocalDate date = flightInfo.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (date.isBefore(LocalDate.now())) {
            buyReturnLock.unlock();
            throw new Exception("Flight time is in the past");
        }

        // check there is enought seat
        if (flightInfo.getSoldTicketNumber() >= flightInfo.getTotalSeat()) {
            buyReturnLock.unlock();
            throw new Exception("No seats left for this flight");
        }

        creditCardNumber = PaymentUtil.validateAndMaskCardNumber(creditCardNumber);

        float soldTotalRatio = (float) (flightInfo.getSoldTicketNumber()) / flightInfo.getTotalSeat();
        int price = PaymentUtil.calculateTicketPrice(flightInfo.getInitialTicketPrice(), soldTotalRatio);
        Ticket newTicket;
        if (PaymentUtil.getPayment(creditCardNumber, price)) {
            flightInfo.setSoldTicketNumber(flightInfo.getSoldTicketNumber() + 1);
            flightRepository.save(flightInfo);

            newTicket = new Ticket();
            newTicket.setFlightInfo(flightInfo);
            newTicket.setTicketPrice(price);
            newTicket.setCreditCardInfo(creditCardNumber);
            ticketRepository.save(newTicket);
        } else {
            buyReturnLock.unlock();
            throw new NotEnoughBalanceException();
        }

        buyReturnLock.unlock();
        return newTicket;
    }

    /**
     * This method is used to return ticket
     * @param ticketDeleteRequest is is json object with given structe below
     * "ticketNumber" -> ticket number(id)
     */
    public void returnTicket(Object ticketDeleteRequest) {
        // parse json object and extract ticket number
        LinkedHashMap deletionRequest = ((LinkedHashMap) ticketDeleteRequest);
        Long ticketNumber = Long.parseLong(deletionRequest.get("ticketNumber").toString());

        buyReturnLock.lock();

        Ticket ticket = ticketRepository.findById(ticketNumber)
                .orElseThrow(() -> {
                    buyReturnLock.unlock();
                    return new TicketNotFoundException();
                });
        Flight flight = ticket.getFlightInfo();

        PaymentUtil.returnPayment(ticket.getTicketPrice(), ticket.getCreditCardInfo());

        flight.setSoldTicketNumber(flight.getSoldTicketNumber() - 1);
        ticketRepository.delete(ticket);
        flightRepository.save(flight);

        buyReturnLock.unlock();
    }
}
