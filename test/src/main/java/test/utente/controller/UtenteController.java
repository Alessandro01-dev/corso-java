package test.utente.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.utente.command.SaveUserCommand;
import test.utente.command.UtenteCommand;
import test.utente.model.Utente;

@RestController
@RequestMapping("/utente")
public class UtenteController {

	private final BeanFactory beanFactory;
	
	public UtenteController(BeanFactory beanFactory) {
		super();
		this.beanFactory = beanFactory;
	}
	
	// Recupero utente per ID
	@GetMapping("/{id}")
	public ResponseEntity<Utente> recuperaUtente(@PathVariable("id") Long id) {
		
		UtenteCommand utente = beanFactory.getBean(UtenteCommand.class);
		utente.setId(id);
		return ResponseEntity.ok(utente.execute());
		
	}
	
	// Recupero utente dal body
	@PostMapping("/salva")
	public ResponseEntity<String> salvaUtente(@RequestBody Utente utenteInput) {
		
		SaveUserCommand saveUserCommand = beanFactory.getBean(SaveUserCommand.class);
		saveUserCommand.setUtente(utenteInput);
		
		return ResponseEntity.ok(saveUserCommand.execute());
	}
}




