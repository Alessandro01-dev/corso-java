package test.user.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import test.order.model.OrderModel;

@Getter
@Setter
public class UserModel {

	private Long id;
	private String nome;
	private String cognome;
	private String cf;
	private List<OrderModel> orders;
	
}