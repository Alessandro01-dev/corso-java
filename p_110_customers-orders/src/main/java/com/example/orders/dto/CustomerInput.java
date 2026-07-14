package com.example.orders.dto;

public record CustomerInput(
		
		String firstName,
		String lastName,
		String email,
		String phone,
		String address,
		String city,
		Boolean active){

}
