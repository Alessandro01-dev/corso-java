package com.example.orders.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orders.dto.CustomerDto;
import com.example.orders.entity.Customer;
import com.example.orders.repository.CustomersRepository;

@Service
public class CustomersService {

	private final CustomersRepository repo;

	public CustomersService(CustomersRepository repo) {
		super();
		this.repo = repo;
	}

	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	public List<CustomerDto> findAllDto() {
		List<CustomerDto> dtos = new ArrayList<>();
		List<Customer> customers = repo.findAll();
		for (Customer c : customers) {
			CustomerDto dto = new CustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone(), c.getAddress(), c.getCity());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
}
