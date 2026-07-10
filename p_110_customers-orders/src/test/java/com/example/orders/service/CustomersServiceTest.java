package com.example.orders.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.orders.dto.CustomerDto;

@SpringBootTest
class CustomersServiceTest {
	
	@Autowired
	CustomersService service;
	
	private static final Logger log = LoggerFactory.getLogger(CustomersService.class);
	
	@Test
	void testFindAll() {
		List<CustomerDto> clienti = service.findAllDto();
		assert(clienti.size() > 0);

		for(CustomerDto c : clienti) {
			log.info("*****{}", c);
		}

	}

}
