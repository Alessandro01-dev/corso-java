package com.example.school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dtos.TeacherDto;
import com.example.school.dtos.TeacherInput;
import com.example.school.entities.Teacher;
import com.example.school.exceptions.PersonNotFoundException;
import com.example.school.repositories.TeachersRepository;

@Service
public class TeachersService {
	
	@Autowired
	private TeachersRepository repo;

	public TeachersService() {
	}
	
	private TeacherDto toDto(Teacher t) {
		TeacherDto dto = new TeacherDto(
				t.getId(),
				t.getFirstName(),
				t.getLastName(),
				t.getEmail(),
				t.getPhone(),
				t.getAddress(),
				t.getCity(),
				t.getTaxIdCode(),
				t.getDateOfBirth(),
				t.getCreatedAt(),
				t.getUpdatedAt());
		return dto;
	}
	
	private Teacher fromInputToEntity(Teacher t, TeacherInput i) {
		t.setFirstName(i.firstName());
		t.setLastName(i.lastName());
		t.setEmail(i.email());
		t.setPhone(i.phone());
		t.setAddress(i.address());
		t.setCity(i.city());
		t.setTaxIdCode(i.taxIdCode());
		t.setDateOfBirth(i.dateOfBirth());
		return t;
	}
	
	public List<TeacherDto> findAll() {
		List<Teacher> teachers = repo.findAll();
		List<TeacherDto> teachersDto = teachers.stream().map(t -> toDto(t)).toList();
		return teachersDto;
	}
	
	public TeacherDto findById(Integer id) {
		Teacher teacher = repo.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
		TeacherDto teacherDto = toDto(teacher);
		return teacherDto;
	}
	
	public TeacherDto create(TeacherInput input) {
		Teacher teacher = new Teacher();
		fromInputToEntity(teacher, input);
		repo.save(teacher);
		TeacherDto teacherDto = toDto(teacher);
		return teacherDto;
	}

}


