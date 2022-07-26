package com.aircraft.ticket.ticketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aircraft.ticket.ticketBooking.Model.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>{

	CustomerDetails findBycustfirstname(String username);
}
