package test.order.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.order.model.OrderModel;
import test.order.service.OrderService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UpdateOrderCommand {

	private Long id;
	private OrderModel orderInput;
	
	@Autowired
	private OrderService orderService;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setOrder(OrderModel orderInput) {
		this.orderInput = orderInput;
	}
	
	public String execute() {
		orderService.updateOrder(orderInput, id);
		return "Ordine modificato";
	}
	
}
