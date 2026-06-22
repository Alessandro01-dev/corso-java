package com.example.biblioteca;

public class Libro {
	private String titolo;
	private String autore;
	private String isbn;
	
	private boolean disponibile = true;

	public Libro(String titolo, String autore, String isbn) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Libro [titolo=" + titolo + ", autore=" + autore + ", isbn=" + isbn + ", disponibile=" + disponibile
				+ "]";
	}

	public String getTitolo() {
		return titolo;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	
}
