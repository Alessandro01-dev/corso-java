package com.example.sintassi.contenitori;

public class Coppia<TIPO1, TIPO2> {

	private TIPO1 payload1;
	private TIPO2 payload2;

	public Coppia(TIPO1 intestatario, TIPO2 iban) {
		super();
		this.payload1 = intestatario;
		this.payload2 = iban;
	}

	public TIPO1 getPayload1() {
		return payload1;
	}

	public TIPO2 getPayload2() {
		return payload2;
	}
}
