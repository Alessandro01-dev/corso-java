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

import com.example.school.dtos.TeacherDto;
import com.example.school.dtos.TeacherInput;
import com.example.school.services.TeachersService;

@RestController
@RequestMapping("/api/teachers")
public class TeachersRestController {

	@Autowired
	TeachersService service;

	public TeachersRestController() {
	}
	
	@GetMapping
	public List<TeacherDto> getAllTeachers() {
		List<TeacherDto> teachers = service.findAll();
		for (TeacherDto teacher : teachers) {
			System.out.println(teacher.toString());			
		}
		return teachers;
	}
	
	@GetMapping("/{id}")
	public TeacherDto getTeacher(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherInput input) {
		TeacherDto teacher = service.create(input);
		return ResponseEntity.ok(teacher);
	}
	
}
