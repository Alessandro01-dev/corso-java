package com.example.sintassi.banca;

public class ContoCorrente {

	private double saldo;
	
	// default di double è 0.0d
	public ContoCorrente() {
		System.out.println("Il conto è stato aperto con saldo " + saldo);
	}
	

	public ContoCorrente(double saldoConto) {
		if (saldoConto >= 0) {
			saldo = saldoConto;
			System.out.println("Il conto è stato aperto con saldo " + saldo);
		} else {
			throw new IllegalArgumentException("Valore di setup del conto corrente non valido");
		}
	}

	public void prelievi(double prelievo) {
		if (prelievo < saldo) {
			System.out.println("Il tuo saldo prima del prelievo è: " + saldo);
			saldo -= prelievo;
			System.out.println("Il tuo saldo dopo il prelievo è: " + saldo);
		} else {
			throw new IllegalArgumentException("Non è possibile prelevare un importo maggiore di " + saldo);
		}
	}

	public void versamenti(double versamento) {
		if (versamento > 0) {
			System.out.print("Il saldo prima del versamento è: ");
			stampaSaldo();
			saldo += versamento;
			System.out.print("Il saldo dopo il versamento è: ");
			stampaSaldo();
		} else {
			System.out.println("Non è possibile versare un importo inferiore a 0");
			throw new IllegalArgumentException("Non è possibile versare un importo < 0");
		}
	}

	public void stampaSaldo() {

		System.out.println(saldo);
	}
}