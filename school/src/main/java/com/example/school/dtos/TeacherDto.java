package com.example.school.dtos;

import java.time.LocalDateTime;

public record TeacherDto(
		Integer id,
		String firstName,
		String lastName,
		String email,
		String phone,
		String address,
		String city,
		String taxIdCode,
		LocalDateTime dateOfBirth,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

}
