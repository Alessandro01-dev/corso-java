package test.order.controller;

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

import test.order.command.DeleteOrderCommand;
import test.order.command.OrderCommand;
import test.order.command.SaveOrderCommand;
import test.order.command.UpdateOrderCommand;
import test.order.model.OrderModel;
import test.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final BeanFactory beanFactory;

	public OrderController(BeanFactory beanFactory) {
		super();
		this.beanFactory = beanFactory;
	}

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	// Recupera ordini
	@GetMapping("")
	public ResponseEntity<List<OrderModel>> getOrders() {

		log.info("chiamata getOrders effettuata");

		OrderCommand ordine = beanFactory.getBean(OrderCommand.class);
		return ResponseEntity.ok(ordine.executeAll());
	}

	// Salvataggio ordine
	@PostMapping("/save")
	public ResponseEntity<String> saveOrder(@RequestBody OrderModel orderInput) {

		log.info("chiamata saveOrder effettuata");

		SaveOrderCommand saveOrderCommand = beanFactory.getBean(SaveOrderCommand.class);
		saveOrderCommand.setOrder(orderInput);

		return ResponseEntity.ok(saveOrderCommand.execute());
	}

	// Modifica ordine per id
	@PutMapping("/{id}")
	public ResponseEntity<String> updateOrder(@PathVariable("id") Long id, @RequestBody OrderModel orderInput) {

		log.info("chiamata updateOrder effettuata");

		UpdateOrderCommand updateOrderCommand = beanFactory.getBean(UpdateOrderCommand.class);

		updateOrderCommand.setId(id);
		updateOrderCommand.setOrder(orderInput);

		return ResponseEntity.ok(updateOrderCommand.execute());

	}

	// Elimina ordine per id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {

		log.info("chiamata deleteOrder effettuata");

		DeleteOrderCommand deleteOrderCommand = beanFactory.getBean(DeleteOrderCommand.class);

		deleteOrderCommand.setId(id);

		return ResponseEntity.ok(deleteOrderCommand.execute());
	}

}
