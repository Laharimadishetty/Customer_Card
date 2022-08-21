package com.customer.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.demo.dto.CardDTO;
import com.customer.demo.dto.CustomerDTO;
import com.customer.demo.entity.Customer;
import com.customer.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer/{custId}")
	public CustomerDTO getCustomerDetails(@PathVariable Integer custId) {
		return customerService.getCustomerDetails(custId);
	}
	
	@PostMapping("/customer")
	public Integer addCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.addCustomer(customerDTO);
	}
	
	@PostMapping("/card/{custId}")
	public void issueCardToExistingCustomer(@PathVariable Integer custId, @RequestBody CardDTO cardDTO) {
		customerService.addCardToExistingCustomer(custId,cardDTO);
	}
	
	@DeleteMapping("/customer/{customerId}")
	public void deleteCustomer(@PathVariable Integer customerId) {
		customerService.deleteCustomer(customerId);
	}
	
	@DeleteMapping("/existingCustomer/{customerId}")
	public void deleteCardOfExistingCustomer(@PathVariable Integer customerId, @RequestBody List<Integer> cardIdsToDelete) {
		customerService.deleteCardOfExistingCustomer(customerId, cardIdsToDelete);
	}
	
	

}
