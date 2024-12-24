package com.avenger.StudentLibraryManagement.Repositories;

import com.avenger.StudentLibraryManagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByEmail(String Email);
}
