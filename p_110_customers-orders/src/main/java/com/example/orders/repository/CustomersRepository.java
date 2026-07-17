package com.example.orders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.orders.entity.Customer;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByActive(Boolean value);

	List<Customer> findByEmailContainingIgnoreCase(String email);
	
	@Query("SELECT u FROM Customer u WHERE u.lastName LIKE CONCAT( '%',?1, '%')")
	List<Customer> findByEmailLike(String pattern);

	@Query("SELECT c FROM Customer c WHERE UPPER(c.city) LIKE UPPER(:city) ESCAPE '\\'")
	List<Customer> findByCityContainingIgnoreCase(String city);
	
	Optional<Customer> findByEmailIgnoreCase(String email);
}
