package com.aircraft.ticket.ticketBooking.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name="location_details")
@Entity
public class TicketLoationDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column
	private String location_A;
	@Column
	private String location_B;
	
	@Column
	private Long price;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocation_A() {
		return location_A;
	}
	public void setLocation_A(String location_A) {
		this.location_A = location_A;
	}
	public String getLocation_B() {
		return location_B;
	}
	public void setLocation_B(String location_B) {
		this.location_B = location_B;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}

	
	
	

}
