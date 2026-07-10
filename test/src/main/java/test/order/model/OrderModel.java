package test.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModel {

	private Long id;
	private String productName;
	private String shippingCode;
	private Long userId;
	
}
