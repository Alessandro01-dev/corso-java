drop table if exists course_enrollments;
drop table  if exists courses;
DROP TABLE IF EXISTS subjects;
drop table if exists students;
drop table if exists teachers;

CREATE TABLE students (
    id            INTEGER       PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(100)  NOT NULL,
    last_name     VARCHAR(100)  NOT NULL,
    email         VARCHAR(255)  NOT NULL,
    phone         VARCHAR(50),
    address       VARCHAR(255),
    city          VARCHAR(100),
	tax_id_code   CHAR(16)      NOT NULL,
	date_of_birth DATE          NOT NULL,
    created_at 	  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_students_email UNIQUE (email),
    CONSTRAINT uq_students_tax_id UNIQUE (tax_id_code)
);

CREATE TABLE teachers (
    id            INTEGER       PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(100)  NOT NULL,
    last_name     VARCHAR(100)  NOT NULL,
    email         VARCHAR(255)  NOT NULL,
    phone         VARCHAR(50),
    address       VARCHAR(255),
    city          VARCHAR(100),
    tax_id_code   CHAR(16)      NOT NULL,
	date_of_birth DATE          NOT NULL,
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_teachers_email 		UNIQUE (email),
    CONSTRAINT uq_teachers_tax_id_code  UNIQUE (tax_id_code)
);

/* valutare se creare o no la tabella subjects */
CREATE TABLE subjects (
    id            INTEGER       PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(100)  NOT NULL
);


CREATE TABLE courses (
    id           				INTEGER       PRIMARY KEY AUTO_INCREMENT,
	subject_id             		INTEGER       NOT NULL,
	teacher_id             		INTEGER       NOT NULL,
--  subject		 				VARCHAR(100)  NOT NULL,
    is_available				BOOLEAN       NOT NULL DEFAULT false,
    max_student_number			INTEGER		  NOT NULL,
    created_at   				TIMESTAMP 	  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   				TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_courses_teacher
    	FOREIGN KEY (teacher_id) 
    		REFERENCES teachers(id),
	CONSTRAINT fk_courses_subject 
		FOREIGN KEY (subject_id) 
			REFERENCES subjects(id)
);

CREATE TABLE course_enrollments (
    student_id   INTEGER   NOT NULL,
    course_id    INTEGER   NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_enrollments_student 
    	FOREIGN KEY (student_id) 
    		REFERENCES students(id) 
    			ON DELETE CASCADE,
    CONSTRAINT fk_enrollments_course  
    	FOREIGN KEY (course_id)
    		REFERENCES courses(id) 
    			ON DELETE CASCADE
);




