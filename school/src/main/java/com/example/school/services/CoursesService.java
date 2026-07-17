package com.example.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dtos.CourseDto;
import com.example.school.dtos.CourseInput;
import com.example.school.entities.Course;
import com.example.school.entities.Subject;
import com.example.school.entities.Teacher;
import com.example.school.exceptions.CourseNotFoundException;
import com.example.school.exceptions.PersonNotFoundException;
import com.example.school.repositories.CoursesRepository;
import com.example.school.repositories.StudentsRepository;
import com.example.school.repositories.SubjectsRepository;
import com.example.school.repositories.TeachersRepository;

@Service
public class CoursesService {
	
	@Autowired
	private CoursesRepository repo;

	@Autowired
	private SubjectsRepository subjectsRepo;

	@Autowired
	private TeachersRepository teachersRepo;
		
	public CoursesService() {
	}
	
	private CourseDto toDto(Course c) {
		CourseDto dto = new CourseDto(
				c.getId(),
				c.getSubject(),
				c.getTeacher(),
				c.getIsAvailable(),
				c.getMaxStudentNumber(),
				c.getCreatedAt(),
				c.getUpdatedAt());
		return dto;
	}
	
	public List<CourseDto> findAll() {
		List<Course> courses = repo.findAll();
		List<CourseDto> coursesDto = courses.stream().map(c -> toDto(c)).toList();
		return coursesDto;
	}
	
	public CourseDto findById(Integer id) {
		Course course = repo.findById(id)
				.orElseThrow(() -> new CourseNotFoundException(id));
		CourseDto courseDto = toDto(course);
		return courseDto;
	}
	
	public CourseDto save(CourseInput input) {
		Course course = new Course();
		
		Subject subject = subjectsRepo.findById(input.getSubjectId())
				.orElseThrow(() -> new CourseNotFoundException(input.getSubjectId()));

		Teacher teacher = teachersRepo.findById(input.getTeacherId())
				.orElseThrow(() -> new PersonNotFoundException(input.getTeacherId()));
		
		course.setSubject(subject);
		course.setTeacher(teacher);
		course.setIsAvailable(input.getIsAvailable());
		course.setMaxStudentNumber(input.getMaxStudentNumber());
		
		Course savedCourse = repo.save(course);
		CourseDto courseDto = toDto(savedCourse);
		return courseDto;
	}
}







