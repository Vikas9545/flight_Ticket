package com.aircraft.ticket.ticketBooking.Controller;


import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.PostLoad;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.aircraft.ticket.ticketBooking.Model.CustomerDetails;
import com.aircraft.ticket.ticketBooking.Model.PassengerDetails;
import com.aircraft.ticket.ticketBooking.Model.TicketLoationDetails;
import com.aircraft.ticket.ticketBooking.Service.CustomerService;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.itextpdf.text.DocumentException;

import netscape.javascript.JSObject;

@RestController
public class TicketController {
	
	@Autowired
	CustomerService customeService;

	
	@PostMapping("/locationFare")
	public TicketLoationDetails returnFarePrice(@RequestBody TicketLoationDetails ticketDetials) {
		return customeService.returnFarePrice(ticketDetials);
		 
		
	}
	
	
	@GetMapping(value="/getId/{id}")
	public void getId(@PathVariable String id) {
		System.out.println(id);
	}
	
//	@PostLoad
//	@PatchMapping("getReceipt")
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ "application/pdf" })
//	public byte[] getReceipt(InputStream incomingData) {
//	     return your_pdf_byte_array;
//	}
	

	@GetMapping(value="findAll")
	public List<TicketLoationDetails> returnAll() {
		return customeService.retriveData();
	}
	
	@GetMapping(value="findId/{id}")
	public TicketLoationDetails findbyId(@PathVariable Long id) throws Exception{
		return customeService.findById(id).orElseThrow(()-> new Exception(" No data found"));
		
	}
	
	@PutMapping("/updatedata")
	public TicketLoationDetails updateData(@RequestBody TicketLoationDetails ticketDetials) {
		return customeService.updateData(ticketDetials);
	}
	
	@PutMapping("saveCustomer")
	public CustomerDetails saveCustomer(@RequestBody CustomerDetails customer) {
		
		return customeService.saveCustomerDetials(customer);
		
	}
    
@GetMapping("/Login")
public ResponseEntity<HashMap<Boolean, Long>> login(@RequestBody CustomerDetails customer) {
	System.out.println(customer.getCustfirstname());
	System.out.println(customer.getPassword());
	HashMap<Boolean, Long> validate=customeService.login(customer);
	 if(validate.containsKey(true)) {
		
		 return new ResponseEntity<HashMap<Boolean, Long>>(validate,HttpStatus.OK);
	 }
	 else {
		 System.out.println("vikas");
		 return new ResponseEntity<HashMap<Boolean, Long>>(new HashMap<>(),HttpStatus.OK);
	 }
	
	
}

@GetMapping("/getTicket")
 public ResponseEntity<String> exportToPDF(HttpServletResponse response,@RequestParam String flightid,@RequestParam String Customerid2) throws DocumentException, IOException {
     //System.out.println(id+"vikas "+id2);
     if(!Customerid2.isBlank()) {
    	 
     
	 response.setContentType("application/pdf");
     DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
     String currentDateTime = dateFormatter.format(new Date());
      
     String headerKey = "Content-Disposition";
     String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
     response.setHeader(headerKey, headerValue);
      
    // List<User> listUsers = service.listAll();
      
    // UserPDFExporter exporter = new UserPDFExporter(listUsers);
     customeService.export(response,flightid,Customerid2);
     }else {
    	 return new ResponseEntity<String>("UserNot Logged",HttpStatus.OK);
     }
	return null;
   
//     response.setContentType("/users/export/pdf");
//     response.addHeader("content-disposition", "inline; filename=link_to_pdf.pdf");

      
 }


@PostMapping("savePassenger")
public List<PassengerDetails> getPassengerDetails(@RequestBody List<PassengerDetails> passenger,@RequestParam String flightid) {
	System.out.println(flightid);

	return customeService.passengerDetails(passenger,flightid);

}

}
