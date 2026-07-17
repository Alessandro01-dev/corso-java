package com.example.school.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	@JsonBackReference
	private Teacher teacher;
	
	@Column(name = "is_available", nullable = false)
	private Boolean isAvailable;
	
	@Column(name = "max_student_number", nullable = false)
	private Integer maxStudentNumber;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	private void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	}


