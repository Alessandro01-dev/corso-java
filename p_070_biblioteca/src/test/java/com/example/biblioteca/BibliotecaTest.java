package com.example.biblioteca;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BibliotecaTest {
	
	private static final Logger log = LoggerFactory.getLogger(BibliotecaTest.class);
	
	Biblioteca b;
	
	@BeforeEach
	void setUpCC() {
		log.trace("Creazione nuova Biblioteca");
		b = new Biblioteca();
	}

	@Test
	void testIngresso() {
		Libro l = new Libro("Divina Commedia", "Dante Alighieri", "ABC1234");
		log.info("libro={}", l);
	}

	@Test
	void testDismissioneLibro() {
		fail("Not yet implemented");
	}

	@Test
	void testDismissioneString() {
		fail("Not yet implemented");
	}

	@Test
	void testPrestito() {
		fail("Not yet implemented");
	}

	@Test
	void testRientro() {
		fail("Not yet implemented");
	}

}
