package edu.aspire.controllers;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.aspire.services.CustomerService;
import edu.aspire.view.vo.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController{ //Generally controller class handles HTTP requests
	@Autowired
	private CustomerService custService;
	
	@RequestMapping(value="/registration/form", method=RequestMethod.GET)
	public String registerCustomer(Map<String, Object> m){ //endpoint
		m.put("customer", new Customer());
		return "NewCustomer";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String insertCustomer(@Valid Customer customer, Errors errors) throws Exception { //endpoint
		if(errors.hasErrors()){ //In case of validation errors
			return "NewCustomer"; //redirected back to request page
		}

		Integer cid = custService.processCustomer(customer);
		customer.setCid(cid);
		
		return "success";
	}
}
