package test.order.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.order.service.OrderService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DeleteOrderCommand {

	private Long id;
	
	@Autowired
	private OrderService orderService;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String execute() {
		orderService.deleteOrder(id);
		return "Ordine eliminato";
	}
	
}
