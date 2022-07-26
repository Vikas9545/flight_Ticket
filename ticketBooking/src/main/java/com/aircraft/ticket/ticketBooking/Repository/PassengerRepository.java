package com.aircraft.ticket.ticketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aircraft.ticket.ticketBooking.Model.PassengerDetails;

public interface PassengerRepository extends JpaRepository<PassengerDetails, Long> {

	
}
