package com.example.school.dtos;

import lombok.Data;

@Data
public class CourseInput {

	private Integer subjectId;
	private Integer teacherId;
	private Boolean isAvailable;
    private Integer maxStudentNumber;

	
	public CourseInput() {
	}

}
