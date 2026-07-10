package test.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.user.model.UserModel;
import test.user.service.UserService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UpdateUserCommand {

	private Long id;
	private UserModel utenteInput;
	
	@Autowired
	private UserService utenteService;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUtente(UserModel utenteInput) {
		this.utenteInput = utenteInput;
	}
	
	public String execute() {
		utenteService.updateUser(utenteInput, id);
		return "Utente modificato";
	}
	
}
