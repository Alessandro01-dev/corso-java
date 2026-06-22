package com.example.sintassi.banca;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ContoCorrenteTest {

	private static final Logger log = LoggerFactory.getLogger(ContoCorrenteTest.class);

	private ContoCorrente cc;
	
	@Test
	void testContoCorrente() {
		double saldo = 100.00;
		ContoCorrente cc2 = new ContoCorrente(saldo);
		assertEquals(cc2.saldo(), saldo);
	}
	
	@Test
	void testPrelievi() {
		double saldo = cc.saldo();
		double importo = 400.00;
		cc.prelievi(importo);
		double saldoFinale = cc.saldo();

		assertEquals(saldo-importo, saldoFinale);
		assertEquals(saldo-importo, cc.saldo());
		assertNotEquals(saldo, cc.saldo());
		assertNotEquals(saldo, saldoFinale);

		log.info("metodo prelievi ok: saldo-pre={}, importo={}, saldo-post={}", saldo, importo, saldoFinale);
	}

	@BeforeEach
	void setUpCC() {
		log.trace("Creazione nuovo ContoCorrente");
		cc = new ContoCorrente();
	}

	@Test
	void testVersamenti() {
		double saldo = cc.saldo();
		double importo = 100.00;
		cc.versamenti(importo);
		double saldoFinale = cc.saldo();

		assertEquals(saldoFinale-saldo, importo);
		assertEquals(importo+saldo, saldoFinale);
		assertEquals(importo+saldo, cc.saldo());

		log.info("metodo versamento ok: saldo-pre={}, importo={}, saldo-post={}", saldo, importo, saldoFinale);
	}

}
