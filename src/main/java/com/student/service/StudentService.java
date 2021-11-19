package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.student.model.Student;
import com.student.repository.StudentRepository;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents(){
		
		List<Student> studentList = studentRepository.findAll();
	
		return studentList;
	}
	
	public Student getStudentById(int id)  {
		
		Student student = null;
		
		try {
			student = studentRepository.getStudentById(id);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	   
		return student;
	}

	public void deleteStudent(int id) {
		
		 studentRepository.deleteById(id);
	}

	public Student addStudent(Student stud) {
		
		Student addStudent = null;
		
	    addStudent =studentRepository.save(stud);
		
	    return addStudent;
	}

	public Student updateStudent(Student student, int id) {
		
		student.setId(id);
		
		Student updatedStudent = studentRepository.save(student);
		
		return updatedStudent;
	}

	public List<Student> findAllStudentsWithPagination(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		
		List<Student> studentList = studentRepository.findAll(pageable).getContent();
		
		return studentList;
		
	}
    
	// sorting the data in ascending order by using Sort class and Direction enum
	public List<Student> getAllStudentsBySorting_Ascending() {
		
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		
		return this.studentRepository.findAll(sort);
	}
	
	 // sorting the data in ascending order by using Sort class and Direction enum
		public List<Student> getAllStudentsBySorting_Descending() {
			
			Sort sort = Sort.by(Sort.Direction.DESC,"firstName");
			
			return this.studentRepository.findAll(sort);
		}
	}
