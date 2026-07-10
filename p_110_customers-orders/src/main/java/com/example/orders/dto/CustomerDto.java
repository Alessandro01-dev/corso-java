package com.example.orders.dto;


public record CustomerDto(

	Integer id,
	String firstName,
	String lastName,
	String email,
	String phone,
	String address,
	String city
	
) {
	
}
