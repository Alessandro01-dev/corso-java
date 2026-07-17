package com.example.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.entities.Teacher;

@Repository
public interface TeachersRepository extends JpaRepository<Teacher, Integer> {

}
