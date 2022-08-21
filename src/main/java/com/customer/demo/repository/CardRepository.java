package com.customer.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.demo.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
	List<Card> findByCustomer_customerId(Integer customerId);
	void deleteByCustomer_customerId(Integer customerId);

}
