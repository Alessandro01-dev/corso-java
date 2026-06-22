package com.example.sintassi.banca;

public class UtilizzatoreConto {

	public static void main(String[] args) {

		ContoCorrente conto = new ContoCorrente(1000.0);
		System.out.println(conto);
		conto.stampaSaldo();
		conto.versamenti(200.0);
		conto.prelievi(100.50);

		conto.prelievi(0.5);
		conto.stampaSaldo();

		conto.allStorico();
		System.out.println(conto);
	}
}