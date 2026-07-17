package com.example.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dtos.StudentDto;
import com.example.school.dtos.StudentInput;
import com.example.school.entities.Course;
import com.example.school.entities.Student;
import com.example.school.exceptions.CourseNotFoundException;
import com.example.school.exceptions.DuplicateEmailException;
import com.example.school.exceptions.DuplicateTaxIdCodeException;
import com.example.school.exceptions.MaxEnrolledStudentNumberReached;
import com.example.school.exceptions.PersonNotFoundException;
import com.example.school.repositories.CoursesRepository;
import com.example.school.repositories.StudentsRepository;

@Service
public class StudentsService {

	@Autowired
	private StudentsRepository repo;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	public StudentsService() {
	}
	
	private StudentDto toDto(Student s) {
		StudentDto dto = new StudentDto(
				s.getId(),
				s.getFirstName(),
				s.getLastName(),
				s.getEmail(),
				s.getPhone(),
				s.getAddress(),
				s.getCity(),
				s.getTaxIdCode(),
				s.getDateOfBirth(),
				s.getCreatedAt(),
				s.getUpdatedAt()
				);
		return dto;
	}
	
	private Student fromInputToEntity(Student s, StudentInput i) {
		s.setFirstName(i.firstName());
		s.setLastName(i.lastName());
		s.setEmail(i.email());
		s.setPhone(i.phone());
		s.setAddress(i.address());
		s.setCity(i.city());
		s.setTaxIdCode(i.taxIdCode());
		s.setDateOfBirth(i.dateOfBirth());
		return s;
	}
	
	public List<StudentDto> findAll() {
		List<Student> students = repo.findAll();
		List<StudentDto> studentsDto = students.stream().map( s -> toDto(s)).toList();
		return studentsDto;
	}
	
	public StudentDto findById(Integer id) {
		Student student = repo.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	
		StudentDto studentDto = toDto(student);
		return studentDto;
	}
	
	public StudentDto create(StudentInput input) {
		if (repo.findByEmailIgnoreCase(input.email()).isPresent()) {
			throw new DuplicateEmailException(input.email());
		}
		
		if (repo.findByTaxIdCodeIgnoreCase(input.taxIdCode()).isPresent()) {
			throw new DuplicateTaxIdCodeException(input.taxIdCode());
		}
		
		Student student = new Student();
		fromInputToEntity(student, input);
		repo.save(student);
		StudentDto studentDto = toDto(student);
		return studentDto;
	}
	
	public void courseEnrollment(Integer studentId, Integer courseId) {
		Student student = repo.findById(studentId)
				.orElseThrow(() -> new PersonNotFoundException(studentId));
		
		Course course = coursesRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException(courseId));
		
		if (course.getStudents().size() >= course.getMaxStudentNumber() || student.getCourses().size() > 4) {
			course.setIsAvailable(false);
			throw new MaxEnrolledStudentNumberReached(courseId);
		} else {
			
			student.getCourses().add(course);

			repo.save(student);
		}
	}
}
