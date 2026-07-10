package test.user.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.user.model.UserModel;
import test.user.service.UserService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SaveUserCommand {

	private UserModel userInput;

	@Autowired
	private UserService userService;
	
	public void setUtente(UserModel userInput) {
		this.userInput = userInput;
	}

	public String execute() {
		userService.saveUser(userInput);
		return "Utente salvato";
	}
}
