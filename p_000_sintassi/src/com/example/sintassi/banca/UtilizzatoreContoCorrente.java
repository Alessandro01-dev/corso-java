package com.example.sintassi.banca;

public class UtilizzatoreContoCorrente {

	public static void main(String[] args) {

		ContoCorrente conto1 = new ContoCorrente();
		ContoCorrente conto2 = new ContoCorrente(-100);
		ContoCorrente conto3 = new ContoCorrente(1000.0);

		conto1.stampaSaldo();
		conto2.stampaSaldo();
		conto3.stampaSaldo();
		conto3.versamenti(-200.0);
		conto3.versamenti(200.0);
		conto3.prelievi(100.50);
		conto3.prelievi(12000.50);
		conto3.stampaSaldo();
	}
}