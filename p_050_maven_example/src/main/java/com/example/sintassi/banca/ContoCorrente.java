package com.example.sintassi.banca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContoCorrente {

	private static final Logger log = LoggerFactory.getLogger(ContoCorrente.class);

	private double saldo = 1000.0;

	public ContoCorrente() {
		log.info("Conto corrente creato con saldo: {}", saldo);
	}

	public ContoCorrente(double saldo_conto) {

		log.info("saldo iniziale {) ", saldo_conto);
		saldo = saldo_conto;
		// if
		// System.exit(1);
	}

	public void prelievi(double prelievo) {

		log.trace("Il tuo saldo iniziale è: {}", saldo);
		saldo -= prelievo;
		log.trace("Il tuo saldo attuale è: {}", saldo);
	}

	public void versamenti(double versamento) {

		if (versamento <= 0) {
			IllegalArgumentException iae = new IllegalArgumentException("Importo versamento <= 0 " + versamento);
			log.error("Importo versamento <= 0 ", iae);
			log.error("{}", iae.getMessage(), iae);
			throw iae;
		}

		log.trace("Il saldo iniziale è: {}", saldo);
		saldo += versamento;
		log.trace("Il saldo attuale è: {}", saldo);
	}

	public void stampaSaldo() {

		log.info("{}", saldo);
	}

	public double saldo() {
		return saldo;
	}
}