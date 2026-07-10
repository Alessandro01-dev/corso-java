package test.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.user.model.UserModel;
import test.user.service.UserService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DeleteUserCommand {

	private Long id;
	
	@Autowired
	private UserService utenteService;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String execute() {
		utenteService.deleteUser(id);
		return "Utente eliminato";
	}
	
}
