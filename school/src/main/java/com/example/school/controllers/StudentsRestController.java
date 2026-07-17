package com.example.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.dtos.StudentDto;
import com.example.school.dtos.StudentInput;
import com.example.school.services.StudentsService;

@RestController
@RequestMapping("/api/students")
public class StudentsRestController {

	@Autowired
	StudentsService service;
	
	public StudentsRestController() {
	}
	
	@GetMapping
	public List<StudentDto> getAllStudents() {
		List<StudentDto> students = service.findAll();
		for (StudentDto student : students) {
			System.out.println(student.toString());			
		}
		return students;
	}
	
	@GetMapping("/{id}")
	public StudentDto getStudent(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<StudentDto> createStudent(@RequestBody StudentInput input) {
		StudentDto student = service.create(input);
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/enrollment/{studentId}/{courseId}")
	public void studentEnrollment(@PathVariable Integer studentId, @PathVariable Integer courseId) {
		service.courseEnrollment(studentId, courseId);
	}
	
}
