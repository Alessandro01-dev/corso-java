package test.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.order.entity.OrderEntity;
import test.order.model.OrderModel;
import test.order.repository.OrderRepository;
import test.user.entity.UserEntity;
import test.user.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	public List<OrderModel> findAll() {
		
		List<OrderEntity> orders = orderRepository.findAll();
		List<OrderModel> ordersDto = new ArrayList<>();;
		
		for (OrderEntity order : orders) {
			OrderModel o = new OrderModel();
			BeanUtils.copyProperties(order, o);
			
			if (order.getUserEntity() != null) {
	            o.setUserId(order.getUserEntity().getId());
			ordersDto.add(o);
			
			}
		}
				
		return ordersDto;
	}
	
	public void saveOrder(OrderModel order) {
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(order, entity);
		
		Optional<UserEntity> user = userRepository.findById(order.getUserId());
				
		entity.setUserEntity(user.get());
		
		orderRepository.save(entity);
	}
	
	public void updateOrder(OrderModel order, Long id) {
		
		Optional<OrderEntity> ent = orderRepository.findById(id);
		OrderEntity entity = ent.isPresent() ? ent.get() : new OrderEntity();
		BeanUtils.copyProperties(order, entity);

		entity.setId(id);
		
		orderRepository.save(entity);
		
	}
	
	public void deleteOrder(Long id) {
		
		orderRepository.deleteById(id);
		
	}
	
}
