package com.example.orders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.orders.dto.CustomerDto;
import com.example.orders.dto.CustomerInput;
import com.example.orders.entity.Customer;
import com.example.orders.exception.CustomerNotFoundException;
import com.example.orders.exception.DuplicateEmailException;
import com.example.orders.repository.CustomersRepository;

@Service
public class CustomersService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomersService.class);

	private final CustomersRepository repo;

	public CustomersService(CustomersRepository repo) {
		super();
		this.repo = repo;
	}
	
	private CustomerDto toDto(Customer c) {
		CustomerDto dto = new CustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone(), c.getAddress(), c.getCity(), c.getActive());
		return dto; 
	}
	
	private void applicaVariazioni(Customer c, CustomerInput input) {
		c.setFirstName(input.firstName());
		c.setLastName(input.lastName());
		c.setEmail(input.email());
		c.setPhone(input.phone());
		c.setAddress(input.address());
		c.setCity(input.city());
		c.setActive(input.active());
	}

	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	public List<CustomerDto> findAllDto() {
		List<CustomerDto> dtos = new ArrayList<>();
		List<Customer> customers = repo.findAll();
		for (Customer c : customers) {
			CustomerDto dto = toDto(c);
			dtos.add(dto);
		}
		
		// List<CustomerDto> dtos = customers.stream().map( c -> toDto(c)).toList();
		
		return dtos;
	}

	public CustomerDto findByIdDto(Integer id) {
		Customer c = repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		CustomerDto dto = toDto(c);
		return dto;
	}
	
	public CustomerDto create(CustomerInput input) {
		if (repo.findByEmailIgnoreCase(input.email()).isPresent()) {
			throw new DuplicateEmailException("Email " + input.email() + " già esistente in archivio");
		}
		Customer c = new Customer();
		applicaVariazioni(c, input);
		
		repo.save(c);
		log.info("Inserito cliente ===>>> {}", c);
		return toDto(c);
	}

	@Deprecated
	public CustomerDto createWithException(CustomerInput input) {
		Customer c = new Customer();
		applicaVariazioni(c, input);
		
		try {
			repo.save(c);
		} catch (Exception e) {
			log.warn("Rilevata eccezione", e);
			throw new DuplicateEmailException("Email " + input.email() + " già esistente in archivio", e);
		}
		log.info("Inserito cliente ===>>> {}", c);
		return toDto(c);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
		
	}
	
	public CustomerDto update(Integer id, CustomerInput input) {
		Customer c = repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		
		Optional<Customer> optCustomer = repo.findByEmailIgnoreCase(input.email());
		
		if (!optCustomer.isEmpty() && optCustomer.get().getId() != id) {
			throw new DuplicateEmailException("Email " + input.email() + " già esistente in archivio");
		}
		applicaVariazioni(c, input);
		CustomerDto dto = toDto(repo.save(c));
		return dto;
	}

	public List<CustomerDto> searchByActive(boolean active) {
		List<Customer> customers = repo.findByActive(active);
		List<CustomerDto> dtos = customers.stream()
				.map( c -> toDto(c))
				.toList();
		
		return dtos;
	}

	public List<CustomerDto> searchByEmail(String email) {
		List<Customer> customers = repo.findByEmailLike(email);
		List<CustomerDto> dtos = customers.stream()
				.map( c -> toDto(c))
				.toList();
		
		return dtos;
	}

	public List<CustomerDto> searchByCity(String city) {
		List<Customer> customers = repo.findByCityContainingIgnoreCase(city);
		List<CustomerDto> dtos = customers.stream()
				.map( c -> toDto(c))
				.toList();
		
		return dtos;
	}
	
}




