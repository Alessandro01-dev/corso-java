package test.utente.model;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
public class Utente {

	private Long id;
	private String nome;
	private String cognome;
	private String cf;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
}
