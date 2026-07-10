package test.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import test.user.entity.UserEntity;

@Entity
@Getter
@Setter
@Table(name = "ordine")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "PRODOTTO")
	private String productName;
	
	@Column(name = "CODICE_SPEDIZIONE")
	private String shippingCode;
	
	public OrderEntity() {
	}
	
	public OrderEntity(String productName, String shippingCode) {
		this.productName = productName;
		this.shippingCode = shippingCode;
	}
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonBackReference
	private UserEntity userEntity;
	
}
