package com.avenger.StudentLibraryManagement.Controllers;

import com.avenger.StudentLibraryManagement.DTOs.StudentUpdateMobileRequestDTO;
import com.avenger.StudentLibraryManagement.Models.Student;
import com.avenger.StudentLibraryManagement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add-student")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/find-by-email/{email}")
    public String findByEmail(@PathVariable("email") String email){
        return studentService.findByEmail(email).getName();
    }

    @PutMapping("/update-student")
    public String updateMobileNo(@RequestBody StudentUpdateMobileRequestDTO studentUpdateMobileRequestDTO){
        return studentService.updateMobileNo(studentUpdateMobileRequestDTO);
    }
}
