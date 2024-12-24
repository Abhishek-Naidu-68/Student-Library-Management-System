package com.avenger.StudentLibraryManagement.Services;

import com.avenger.StudentLibraryManagement.DTOs.StudentUpdateMobileRequestDTO;
import com.avenger.StudentLibraryManagement.Enums.CardStatus;
import com.avenger.StudentLibraryManagement.Models.Card;
import com.avenger.StudentLibraryManagement.Models.Student;
import com.avenger.StudentLibraryManagement.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);
        return "Student and Card Created Successfully";
    }

    public Student findByEmail(String email){
        return  studentRepository.findByEmail(email);
    }

    public String updateMobileNo(StudentUpdateMobileRequestDTO studentUpdateMobileRequestDTO){

        Student s = studentRepository.findById(studentUpdateMobileRequestDTO.getId()).get();

        s.setMobileNo(studentUpdateMobileRequestDTO.getMobileNo());

        studentRepository.save(s);
        return "Updated Successfully";
    }
}