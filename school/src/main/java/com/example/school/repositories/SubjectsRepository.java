package com.example.school.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.school.entities.Subject;

public interface SubjectsRepository extends JpaRepository<Subject, Integer> {
}
