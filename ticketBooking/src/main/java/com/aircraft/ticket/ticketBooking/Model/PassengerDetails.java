package com.aircraft.ticket.ticketBooking.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="passenger_details")
public class PassengerDetails {
	
	@Id
	@Column
	private Long passengerIdentityId;

	@Column
	private String passengername;
	
	@Column
	private String passengerAge;
	
	@Column
	private Long ticketId;
	
	public PassengerDetails() {
		// TODO Auto-generated constructor stub
	}

	public Long getPassengerIdentityId() {
		return passengerIdentityId;
	}

	public void setPassengerIdentityId(Long passengerIdentityId) {
		this.passengerIdentityId = passengerIdentityId;
	}

	public String getPassengername() {
		return passengername;
	}

	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}

	public String getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(String passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
	
	
	
}
