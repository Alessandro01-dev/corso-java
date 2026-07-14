package com.example.orders.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.orders.dto.CustomerDto;
import com.example.orders.dto.CustomerInput;
import com.example.orders.exception.CustomerNotFoundException;
import com.example.orders.exception.DuplicateEmailException;

@SpringBootTest
class CustomersServiceTest {

	private static final Logger log = LoggerFactory.getLogger(CustomersServiceTest.class);

	private List<CustomerDto> dtosCache = null;
	private Random rnd = new Random();

	private CustomerDto getRandomDto() {
	if (dtosCache == null) {
	dtosCache = service.findAllDto();
	}
	int index = rnd.nextInt(dtosCache.size());
	return dtosCache.get(index);
	}
	
	private void resetDtosCache() {
		dtosCache=null;
	}
	
	@Autowired
	CustomersService service;

	private CustomerInput cInput = null;
	private CustomerDto dto = null;

	@BeforeEach
	void setUp() throws Exception {
		cInput = new CustomerInput(
				"Test name", 
				"Test lastname", 
				"test@example.org", 
				"+39123123123", 
				"Via Roma 123", 
				"Milano",
				true);
	}

	@AfterEach
	void tearDown() throws Exception {
		if (dto != null && dto.id() != null) {
			service.delete(dto.id());
		}
	}

	@Test
	@DisplayName("Find All Customers test")
	void testFindAll() {
		List<CustomerDto> clienti = service.findAllDto();
		assert (clienti.size() > 0);

		for (CustomerDto c : clienti) {
			log.info("*****{}", c);
		}

	}

	@Test
	void testFindById() {
		CustomerDto cliente = getRandomDto();
		assertNotNull(cliente);
		log.info("testFindById ====>>", cliente);
	}

	@Test
	void createTest() {
		dto = service.create(cInput);
		assertNotNull(dto.id());
		log.info("createTest ===>> {}", dto);
	}
	
	@Test
	void createWithSameEmailTest() {
		CustomerDto cliente = getRandomDto();
		cInput = new CustomerInput(
				"Test name", 
				"Test lastname", 
				cliente.email(), 
				"+39123123123", 
				"Via Roma 123", 
				"Milano",
				true);
		assertThrows(DuplicateEmailException.class, () -> service.createWithException(cInput));
	}
	
	@Test
	void createTestWithExceptionTest() {
		CustomerDto cliente = getRandomDto();
		cInput = new CustomerInput(
				"Test name", 
				"Test lastname", 
				cliente.email(), 
				"+39123123123", 
				"Via Roma 123", 
				"Milano",
				true);
		assertThrows(DuplicateEmailException.class, () -> service.createWithException(cInput));
	}
	
	@Test
	void updateTest() {
		CustomerDto cliente = getRandomDto();
		CustomerInput newInput = new CustomerInput(
				cliente.firstName(), 
				cliente.lastName(), 
				cliente.email(), 
				"00000000000", 
				cliente.address(), 
				cliente.city(),
				cliente.active());
		CustomerDto nuovoCliente = service.update(cliente.id(), newInput);
		
		assertEquals("00000000000", nuovoCliente.phone());
		assertNotEquals(cliente.phone(), nuovoCliente.phone());
		assertNotEquals(cliente, nuovoCliente);
	}
	
	@Test
	void updateTestWithDuplicateEmailException() {
		CustomerDto cliente = getRandomDto();
		CustomerDto secondoCliente = getRandomDto();
		while (secondoCliente.id() == cliente.id()) {
			secondoCliente = getRandomDto();
		}
		CustomerInput newInput = new CustomerInput(
				cliente.firstName(), 
				cliente.lastName(), 
				secondoCliente.email(), 
				"00000000000", 
				cliente.address(), 
				cliente.city(),
				cliente.active());
		assertThrows(DuplicateEmailException.class, () -> service.update(cliente.id(), newInput));
	}
	
	@Test
	void updateTestWithCustomerNotFoundException() {
		Integer idInesistente = -999;
		
		assertThrows(CustomerNotFoundException.class, () -> service.update(idInesistente, cInput));
	}
	
}







