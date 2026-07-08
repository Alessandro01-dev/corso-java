package test.utente.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.utente.model.Utente;
import test.utente.service.UtenteService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SaveUserCommand {

	private Utente utenteInput;

	@Autowired
	private UtenteService utenteService;
	
	public void setUtente(Utente utenteInput) {
		this.utenteInput = utenteInput;
	}

	public String execute() {
		utenteService.saveUser(utenteInput);
		return "OK";
	}
}
