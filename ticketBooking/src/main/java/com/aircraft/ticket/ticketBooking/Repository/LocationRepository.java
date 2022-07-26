package com.aircraft.ticket.ticketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aircraft.ticket.ticketBooking.Model.TicketLoationDetails;

public interface LocationRepository extends JpaRepository<TicketLoationDetails,Long> {

}
