package test.user.command;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.user.model.UserModel;
import test.user.service.UserService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserCommand {

	private Long id;

	private final UserService service;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserCommand(UserService service) {
		super();
		this.service = service;
	}

	public UserModel execute() {
		return service.findById(id);
	}
	
	public List<UserModel> executeAll() {
		return service.findAll();
	}

	public List<UserModel> executeAllWithOrders() {
	    return service.findAllWithOrders();
	}
}
