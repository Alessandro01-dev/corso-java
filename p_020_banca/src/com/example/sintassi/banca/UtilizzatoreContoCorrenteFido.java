package com.example.sintassi.banca;

public class UtilizzatoreContoCorrenteFido {

	public static void main(String[] args) {
		// ContoCorrenteFido ccf1 = new ContoCorrenteFido(100);
		ContoCorrente ccf2 = new ContoCorrenteFido(1000, 100);
		System.out.println(ccf2);
		ccf2.stampaSaldo();
		ccf2.versamenti(1000);
		ccf2.versamenti(200);
		ccf2.stampaSaldo();
		ccf2.prelievi(2225.68); // uso del fido
		ccf2.stampaSaldo();
		// ccf2.prelievi(300); per verificare che in caso di eccedenza si comporti come
		// atteso
		ccf2.versamenti(20); // versamento che rimpingua solo il fido
		ccf2.versamenti(10); // versamento che riempie il fido e poi va sul conto

		ccf2.allStorico();
		System.out.println(ccf2);
	}

}
