package test.utente.command;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.utente.model.Utente;
import test.utente.service.UtenteService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UtenteCommand {

	private Long id;

	private final UtenteService service;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public UtenteCommand(UtenteService service) {
		super();
		this.service = service;
	}

	public Utente execute() {
		return service.findById(id);
	}
}
