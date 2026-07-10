package test.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.user.command.DeleteUserCommand;
import test.user.command.SaveUserCommand;
import test.user.command.UpdateUserCommand;
import test.user.command.UserCommand;
import test.user.model.UserModel;
import test.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final BeanFactory beanFactory;

	public UserController(BeanFactory beanFactory) {
		super();
		this.beanFactory = beanFactory;
	}

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	// Recupera utenti con ordini inclusi nel JSON
	@GetMapping("/with-orders")
	public ResponseEntity<List<UserModel>> getUsersWithOrders() {
		log.info("Chiamata getUsersWithOrders effettuata");

		UserCommand utenteCommand = beanFactory.getBean(UserCommand.class);
		return ResponseEntity.ok(utenteCommand.executeAllWithOrders());
	}

	// Recupera utenti
	@GetMapping("")
	public ResponseEntity<List<UserModel>> getUsers() {

		log.info("chiamata getUsers effettuata");

		UserCommand utente = beanFactory.getBean(UserCommand.class);
		return ResponseEntity.ok(utente.executeAll());

	}

	// Recupero utente per ID
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable("id") Long id) {

		log.info("chiamata getUser effettuata con id {}", id);

		UserCommand utente = beanFactory.getBean(UserCommand.class);
		utente.setId(id);
		return ResponseEntity.ok(utente.execute());

	}

	// Salvataggio utente
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody UserModel utenteInput) {

		log.info("chiamata saveUser effettuata");

		SaveUserCommand saveUserCommand = beanFactory.getBean(SaveUserCommand.class);
		saveUserCommand.setUtente(utenteInput);

		return ResponseEntity.ok(saveUserCommand.execute());
	}

	// Modifica utente per id
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserModel utenteInput) {

		log.info("chiamata updateUser effettuata");

		UpdateUserCommand updateUserCommand = beanFactory.getBean(UpdateUserCommand.class);

		updateUserCommand.setId(id);
		updateUserCommand.setUtente(utenteInput);

		return ResponseEntity.ok(updateUserCommand.execute());
	}

	// Elimina utente per id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

		log.info("chiamata deleteUser effettuata");

		DeleteUserCommand deleteUserCommand = beanFactory.getBean(DeleteUserCommand.class);

		deleteUserCommand.setId(id);

		return ResponseEntity.ok(deleteUserCommand.execute());
	}
}
