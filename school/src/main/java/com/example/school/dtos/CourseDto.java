package com.example.school.dtos;

import java.time.LocalDateTime;

import com.example.school.entities.Subject;
import com.example.school.entities.Teacher;

public record CourseDto(
		Integer id,
		Subject subject,
		Teacher teacher,
		Boolean isAvailable,
		Integer maxStudentNumber,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
}
