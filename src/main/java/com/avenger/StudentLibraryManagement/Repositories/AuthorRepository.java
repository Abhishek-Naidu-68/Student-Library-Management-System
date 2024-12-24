package com.avenger.StudentLibraryManagement.Repositories;

import com.avenger.StudentLibraryManagement.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
