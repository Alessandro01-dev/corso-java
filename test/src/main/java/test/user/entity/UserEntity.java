package test.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import test.order.entity.OrderEntity;

@Entity
@Getter
@Setter
@Table(name = "utente")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "COGNOME")
	private String cognome;
	
	@Column(name = "CF")
	private String cf;

	public UserEntity() {
	}

	public UserEntity(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderEntity> orders = new ArrayList<>();

}