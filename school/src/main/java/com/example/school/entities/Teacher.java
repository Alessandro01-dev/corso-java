package com.example.school.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
	
	public Teacher() {
	}

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Course> courses = new ArrayList<>();
	
}
