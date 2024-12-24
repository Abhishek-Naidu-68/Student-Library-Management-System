package com.avenger.StudentLibraryManagement.Repositories;

import com.avenger.StudentLibraryManagement.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
