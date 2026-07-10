package test.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.order.entity.OrderEntity;
import test.order.model.OrderModel;
import test.user.entity.UserEntity;
import test.user.model.UserModel;
import test.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserModel> findAllWithOrders() {
		List<UserEntity> users = userRepository.findAllWithOrders();
		List<UserModel> usersDto = new ArrayList<>();

		for (UserEntity user : users) {
			UserModel u = new UserModel();
			BeanUtils.copyProperties(user, u);

			List<OrderModel> orderModels = new ArrayList<>();
			if (user.getOrders() != null) {
				for (OrderEntity orderEntity : user.getOrders()) {
					OrderModel orderModel = new OrderModel();
					BeanUtils.copyProperties(orderEntity, orderModel);

					if (orderEntity.getUserEntity() != null) {
						orderModel.setUserId(orderEntity.getUserEntity().getId());
					}

					orderModels.add(orderModel);
				}
			}

			u.setOrders(orderModels);
			usersDto.add(u);
		}

		return usersDto;
	}

	public List<UserModel> findAll() {

		List<UserEntity> users = userRepository.findAll();
		List<UserModel> usersDto = new ArrayList<>();
		;
		for (UserEntity user : users) {
			UserModel u = new UserModel();
			BeanUtils.copyProperties(user, u);
			usersDto.add(u);
		}

		return usersDto;
	}

	public UserModel findById(Long id) {

		Optional<UserEntity> entity = userRepository.findById(id);

		UserEntity ent = entity.isPresent() ? entity.get() : new UserEntity();
		UserModel utente = new UserModel();
		BeanUtils.copyProperties(ent, utente);

		return utente;
	}

	public void saveUser(UserModel utente) {

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(utente, entity);

		userRepository.save(entity);

	}

	public void updateUser(UserModel utente, Long id) {

		Optional<UserEntity> ent = userRepository.findById(id);
		UserEntity entity = ent.isPresent() ? ent.get() : new UserEntity();
		BeanUtils.copyProperties(utente, entity);

		entity.setId(id);

		userRepository.save(entity);

	}

	public void deleteUser(Long id) {

		userRepository.deleteById(id);

	}
}
