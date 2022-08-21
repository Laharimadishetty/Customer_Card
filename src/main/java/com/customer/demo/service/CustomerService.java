package com.customer.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.demo.dto.CardDTO;
import com.customer.demo.dto.CustomerDTO;
import com.customer.demo.entity.Card;
import com.customer.demo.entity.Customer;
import com.customer.demo.repository.CardRepository;
import com.customer.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	public CustomerDTO getCustomerDetails(Integer custId) {
		Customer cust = customerRepository.findById(custId).get();
		List<Card> cards = cardRepository.findByCustomer_customerId(custId);
	
		List<CardDTO> cardDTOs = new ArrayList<>();
		cards.forEach(card -> cardDTOs.add(convertCardToCardDTO(card, null)));
		CustomerDTO customerDTO =  convertCustomerToCustomerDTO(cust, cardDTOs);
		return customerDTO;
		
	}
	
	public CustomerDTO convertCustomerToCustomerDTO(Customer customer, List<CardDTO> cardDTOs) {
		if(customer == null) {
			return null;
		}
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getCustomerId());
		customerDTO.setEmail(customer.getEmailid());
		customerDTO.setName(customer.getName());
		customerDTO.setDob(customer.getDateOfBirth());
		customerDTO.setCards(cardDTOs);
		return customerDTO;
	}
	
	public CardDTO convertCardToCardDTO(Card card, Customer customer) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardId(card.getCardId());
		cardDTO.setCardNumber(card.getCardNumber());
		cardDTO.setExpiryDate(card.getExpiryDate());
		cardDTO.setCustomer(convertCustomerToCustomerDTO(customer, null));
		return cardDTO;
	}
	
	public Integer addCustomer(CustomerDTO customerDTO) {
		
		Customer cust = new Customer();
		cust.setCustomerId(customerDTO.getId());
		cust.setDateOfBirth(customerDTO.getDob());
		cust.setEmailid(customerDTO.getEmail());
		cust.setName(customerDTO.getName());
		Customer newCust = customerRepository.save(cust);
		
		List<CardDTO> cardDTOs = customerDTO.getCards();
		if (cardDTOs != null && cardDTOs.size() > 0) {
			cardDTOs.forEach(cardDTO -> cardRepository.save(cardDTOtoCard(newCust, cardDTO)));
		}
		return newCust.getCustomerId();
		
	}
	
	public Card cardDTOtoCard(Customer customer, CardDTO cardDTO) {
		Card card = new Card();
		card.setCardId(cardDTO.getCardId());
		card.setCardNumber(cardDTO.getCardNumber());
		card.setExpiryDate(cardDTO.getExpiryDate());
		card.setCustomer(customer);
		return card;
		
	}
	
	public void addCardToExistingCustomer(Integer custId, CardDTO cardDTO){
		Card card = new Card();
		card.setCardId(cardDTO.getCardId());
		card.setCardNumber(cardDTO.getCardNumber());
		card.setExpiryDate(cardDTO.getExpiryDate());
		card.setCustomer(convertCustomerDTOtoCustomer(cardDTO));
		cardRepository.save(card);
	}
	
	public Customer convertCustomerDTOtoCustomer(CardDTO cardDTO) {
		
		CustomerDTO custDTO = cardDTO.getCustomer();
		Customer cust = new Customer();
		cust.setCustomerId(custDTO.getId());
		cust.setDateOfBirth(custDTO.getDob());
		cust.setEmailid(cust.getEmailid());
		cust.setName(custDTO.getName());
	    return cust;
	}
	
	public void deleteCustomer(Integer customerId) {
		
		cardRepository.deleteByCustomer_customerId(customerId);
		customerRepository.deleteById(customerId);
	}
	
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) {
		cardIdsToDelete.forEach(cardId -> cardRepository.deleteById(cardId));
	}

}
