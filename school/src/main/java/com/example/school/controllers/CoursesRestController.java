package com.example.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.dtos.CourseDto;
import com.example.school.dtos.CourseInput;
import com.example.school.services.CoursesService;

@RestController
@RequestMapping("/api/courses")
public class CoursesRestController {

	@Autowired
	CoursesService service;
	
	public CoursesRestController() {
	}
	
	@GetMapping
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courses = service.findAll();
		return courses;
	}
	
	@GetMapping("/{id}")
	public CourseDto getCourse(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	public CourseDto createCourse(@RequestBody CourseInput input) {
		return service.save(input);
	}
	
}
