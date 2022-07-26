package com.aircraft.ticket.ticketBooking.Service;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.FacesWebRequest;
import org.springframework.web.jsf.FacesContextUtils;

import com.aircraft.ticket.ticketBooking.Model.CustomerDetails;
import com.aircraft.ticket.ticketBooking.Model.PassengerDetails;
import com.aircraft.ticket.ticketBooking.Model.TicketLoationDetails;
import com.aircraft.ticket.ticketBooking.Repository.CustomerRepository;
import com.aircraft.ticket.ticketBooking.Repository.LocationRepository;
import com.aircraft.ticket.ticketBooking.Repository.PassengerRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class CustomerService   {


	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	public TicketLoationDetails returnFarePrice(TicketLoationDetails ticketLocationDetails) {
	//System.out.println(ticketLocationDetails.getLocation_A());
	List<TicketLoationDetails> list=locationRepository.findAll();
	
	Long id=null;
	boolean flag=true;
	
	for(int j=0;j<list.size();j++) {
		System.out.println(list.get(j).getLocation_A()+" "+ticketLocationDetails.getLocation_A());
		if((list.get(j).getLocation_A().equalsIgnoreCase((ticketLocationDetails.getLocation_A())))
				&& (list.get(j).getLocation_B().equalsIgnoreCase(ticketLocationDetails.getLocation_B()))){
			flag=false;
			id=list.get(j).getId();
		}
	}
	
	if(flag) {
		int km =0;
		for(int i=0;i<ticketLocationDetails.getLocation_A().length();i++) {
			if(ticketLocationDetails.getLocation_A().charAt(i)!=' ') {
			
			km++;
			}
		}
	//	System.out.println("count"+ticketLocationDetails.getLocation_B());
		for(int i=0;i<ticketLocationDetails.getLocation_B().length();i++) {
			if(ticketLocationDetails.getLocation_B().charAt(i)!=' ') {
				//System.out.println("vikas"+km);
			km++;
			}
		}
		km=km*100;
		
		ticketLocationDetails.setPrice(Long.valueOf(km));
		locationRepository.save(ticketLocationDetails);
	}else {
		
		Optional<TicketLoationDetails> data=locationRepository.findById(id);
		ticketLocationDetails=data.get();
	}
		
		return ticketLocationDetails;
	}
//	
	
	 public void export(HttpServletResponse response,String flightid,String customerid) throws DocumentException, IOException {
	Optional<TicketLoationDetails> ticketDetails= locationRepository.findById(Long.valueOf(flightid));
	Optional<CustomerDetails> customer= customerRepository.findById(Long.valueOf(customerid));
	TicketLoationDetails flightDetails =ticketDetails.get();
	CustomerDetails customerDetails=customer.get();
		 Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	      
	         
	        Paragraph p = new Paragraph("Vikas AirLine", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        p.setSpacingAfter(30f);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(5);
	        table.setWidthPercentage(100f);
	        
	     String age=String.valueOf(customerDetails.getCustomerage());
	     String price=String.valueOf(flightDetails.getPrice());
	     
	     table.addCell("Customer Name");
	        table.addCell("Age");
	        table.addCell("Arrival");
	        table.addCell("Destination");
	        table.addCell("Price");
	         
	     
	        table.addCell(customerDetails.getCustfirstname());
	        table.addCell(age);
	        table.addCell(flightDetails.getLocation_A());
	        table.addCell(flightDetails.getLocation_B());
	        table.addCell(price);
	        
	     document.add(table);
	        document.close();
	}
	
	public List<TicketLoationDetails> retriveData() {
		return locationRepository.findAll();
	}
	
	
	
	public Optional<TicketLoationDetails> findById(Long id){
		return locationRepository.findById(id);
	}
	
	public TicketLoationDetails updateData(TicketLoationDetails ticket) {
		return locationRepository.save(ticket);
	}
	public CustomerDetails saveCustomerDetials(CustomerDetails customer) {
		return customerRepository.save(customer);
	}

	public HashMap login(CustomerDetails customer) {
		customer.getCustfirstname();
		System.out.println("ok ok"+customer.getPassword());
		boolean validate=false;
		HashMap<Boolean, Long> validateLogin=new HashMap<>();
		System.out.println(validateLogin);
		Long id=null;
	List<CustomerDetails> customerDetials=	customerRepository.findAll();
	for(int i=0;i<customerDetials.size()-1;i++) {
		System.out.println("ok i m here"+customerDetials.get(i).getPassword());
	if((customerDetials.get(i).getCustfirstname().equals(customer.getCustfirstname()))
			&& (customerDetials.get(i).getPassword().equals(customer.getPassword()))){
		validateLogin.put(true, customerDetials.get(i).getId());
	
	
	}
	
	}
	return validateLogin;
	}
	
	public List<PassengerDetails> passengerDetails(List<PassengerDetails> passenger,String flight_id) {
		
	passenger.forEach(n->n.setTicketId(Long.valueOf(flight_id)));
		return passengerRepository.saveAll(passenger);
		
	
}
}
