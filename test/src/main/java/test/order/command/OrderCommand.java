package test.order.command;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import test.order.model.OrderModel;
import test.order.service.OrderService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderCommand {

	private Long id;
	
	private final OrderService service;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public OrderCommand(OrderService service) {
		super();
		this.service = service;
	}
	
	public List<OrderModel> executeAll() {
		return service.findAll();
	}
	
}