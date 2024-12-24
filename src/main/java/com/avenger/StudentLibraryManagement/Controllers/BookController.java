package com.avenger.StudentLibraryManagement.Controllers;

import com.avenger.StudentLibraryManagement.DTOs.BookRequestDTO;
import com.avenger.StudentLibraryManagement.Models.Book;
import com.avenger.StudentLibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public String addBook(@RequestBody BookRequestDTO bookRequestDTO)
    {
        return bookService.addBook(bookRequestDTO);

    }
}
