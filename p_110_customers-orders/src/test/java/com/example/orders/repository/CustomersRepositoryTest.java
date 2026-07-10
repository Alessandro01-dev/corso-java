package com.example.orders.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.orders.entity.Customer;

@SpringBootTest
class CustomersRepositoryTest {

	@Autowired
	CustomersRepository repo;
	
	@Test
	void testFindAll() {
		List<Customer> clienti = repo.findAll();
		assert(clienti.size() > 0);
	}

}
