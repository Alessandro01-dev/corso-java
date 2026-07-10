package com.example.orders.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	private Integer id;

	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@Column(name = "email", length = 255, nullable = false)
	private String email;
	
	@Column(name = "phone", length = 50, nullable = true)
	private String phone;
	
	@Column(name = "address", length = 50, nullable = true)
	private String address;
	
	@Column(name = "city", length = 50, nullable = true)
	private String city;
	
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
	
	@Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
	
	@Column(name = "update_date", nullable = false)
	private LocalDateTime updateDate;

	
}
