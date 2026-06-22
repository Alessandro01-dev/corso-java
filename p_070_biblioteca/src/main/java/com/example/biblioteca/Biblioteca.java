package com.example.biblioteca;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Biblioteca {
	
	private static final Logger log = LoggerFactory.getLogger(Biblioteca.class);

	ArrayList<Libro> listaDiLibri = new ArrayList<>();
	
	public void ingresso(Libro libro) {
		listaDiLibri.add(libro);
		log.info("libri registrati = {}", listaDiLibri);
	}
	
	public void dismissione(Libro libro) {
		if (listaDiLibri.contains(libro) && libro.isDisponibile()) {
			listaDiLibri.remove(libro);
		}
		if (listaDiLibri.contains(libro) && !libro.isDisponibile()) {
			System.out.println("libro non disponibile");
		}
	}
	
	public void dismissione(String titolo) {
		
		for(Libro i : listaDiLibri) {
			if (i.getTitolo() == titolo) {
				listaDiLibri.remove(i);
			}
		}
	}
	
	public void prestito(Libro libro) {
		if (listaDiLibri.contains(libro) && libro.isDisponibile()) {
			int i = listaDiLibri.indexOf(libro);
			listaDiLibri.get(i).setDisponibile(false);
		}
	}
	
	public void rientro(Libro libro) {
		if (listaDiLibri.contains(libro) && !libro.isDisponibile()) {
			int i = listaDiLibri.indexOf(libro);
			listaDiLibri.get(i).setDisponibile(true);
		}
	}
}
