package com.student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;
	
	private static  List<Student> addStudents = new ArrayList<Student>();
	
	//To get All Students from the Database
	
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		
		 addStudents = studentService.getAllStudents();
		 
		 if(addStudents.size()<=0) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
		 else {
			 return ResponseEntity.of(Optional.of(addStudents));
		 }
	}
	
	//To get Students based on the id
	
	@GetMapping("/getStudents/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id)  {
		
		Student student = null;
		student = studentService.getStudentById(id);
		
		if(student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else {
			return ResponseEntity.of(Optional.of(student));
		}
		
	}
	
	//To add a Student into the Database
	
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addBook(@RequestBody Student stud) {
		
		Student addStudent = null;
		
		try {
			addStudent = studentService.addStudent(stud);
			System.out.println(addStudent);
		    return ResponseEntity.status(HttpStatus.CREATED).build();	
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//To delete a student from the database
	
	@DeleteMapping("/getStudents/{id}")
	public  ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
		
		try {
			studentService.deleteStudent(id);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	// To update a student from the database
	
	@PutMapping("/updateStudents/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
		
		try {
		    Student updatedStudent = studentService.updateStudent(student,id);
		    System.out.println(updatedStudent);
		    
		    return ResponseEntity.ok().body(student);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//To get the records based on the name
	
	@GetMapping("/getStudentsByPagination")
	public List<Student> getAllStudentsByPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
		
		List<Student> studentList = studentService.findAllStudentsWithPagination(pageNo, pageSize);
		
	//	System.out.println(studentList);
		
		return studentList;
	}
	
	//To get all All students by sorting in ascending order
	
	@GetMapping("/getStudentsBySorting")
	public List<Student> getAllStudentsBySorting_Ascending() {
		
		List<Student> studentList = studentService.getAllStudentsBySorting_Ascending();
		System.out.println(studentList);
			return studentList;
	}
	
	//To get all All students by sorting in descending orders
	
	@GetMapping("/getStudentsBySortingDesc")
	public List<Student> getAllStudentsBySorting_Descending() {
		
		List<Student> studentList = studentService.getAllStudentsBySorting_Descending();
		System.out.println(studentList);
		return studentList;
	}
}	
