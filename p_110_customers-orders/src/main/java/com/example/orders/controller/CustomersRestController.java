package com.example.orders.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.orders.dto.CustomerDto;
import com.example.orders.dto.CustomerInput;
import com.example.orders.service.CustomersService;


@RestController
@RequestMapping("/api/customers")
public class CustomersRestController {
	
	private final CustomersService service;

	public CustomersRestController(CustomersService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<CustomerDto> findAllDto() {
		return service.findAllDto();
	}
	
	@GetMapping("{id}")
	public CustomerDto getCustomerById(@PathVariable Integer id) {
		CustomerDto dto = service.findByIdDto(id);
		return dto;
	}
	
	@GetMapping("search/by-active")
	public List<CustomerDto> searchByActive(@RequestParam Boolean active) {
		return service.searchByActive(active);
	}
	
	@GetMapping("search/by-email")
	public List<CustomerDto> searchByEmail(@RequestParam String email) {
		return service.searchByEmail(email);
	}
	
	@GetMapping("search/by-city")
	public List<CustomerDto> searchByCity(@RequestParam String city) {
		return service.searchByCity(city);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerInput input, UriComponentsBuilder builder) {
		CustomerDto dto = service.create(input);
		URI url = builder.path("/api/customers/{id}").buildAndExpand( dto.id() ).toUri();
		return ResponseEntity.created(url).body(dto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public CustomerDto update(@PathVariable Integer id, @RequestBody CustomerInput input) {
		return service.update(id, input);
	}
}










