package com.example.sintassi.banca;

public abstract class Movimento {

	private String descrizione;
	private double valore;
	
	public String getDescrizione() {
		return descrizione;
	}
	public double getValore() {
		return valore;
	}
	
	public Movimento(String descrizione, double valore) {
		super();
		this.descrizione = descrizione;
		this.valore = valore;
	}
	
	
}
