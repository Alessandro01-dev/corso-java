package com.example.school.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;
	
	@Column(name = "email", length = 255, nullable = false)
	private String email;
	
	@Column(name = "phone", length = 50, nullable = true)
	private String phone;
	
	@Column(name = "address", length = 255, nullable = true)
	private String address;
	
	@Column(name = "city", length = 100, nullable = true)
	private String city;
	
	@Column(name = "tax_id_code", length = 16, nullable = false)
	private String taxIdCode;
	
	@Column(name = "date_of_birth", nullable = false)
	private LocalDateTime dateOfBirth;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PostPersist
	private void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	public Person() {
	}

}
