package com.example.school.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.entities.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer> {
	
	Optional<Student> findByEmailIgnoreCase(String email);
	
	Optional<Student> findByTaxIdCodeIgnoreCase(String taxIdCode);
	
}
