package com.example.sintassi.contenitori;

public class UtilizzatoreContenitore {

	public static void main(String[] args) {

		Coppia<String, String> contoCorrenteCoppia = new Coppia<String, String>("Mario Rossi", "IT123123123123");
	
		System.out.println(contoCorrenteCoppia.getPayload1());
		System.out.println(contoCorrenteCoppia.getPayload2());
	}

}
