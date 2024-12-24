package com.avenger.StudentLibraryManagement.Services;

import com.avenger.StudentLibraryManagement.DTOs.BookRequestDTO;
import com.avenger.StudentLibraryManagement.Models.Author;
import com.avenger.StudentLibraryManagement.Models.Book;
import com.avenger.StudentLibraryManagement.Repositories.AuthorRepository;
import com.avenger.StudentLibraryManagement.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDTO bookRequestDTO){

        int authorId = bookRequestDTO.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();

        book.setName(bookRequestDTO.getName());
        book.setGenre(bookRequestDTO.getGenre());
        book.setIssued(false);
        book.setPages(bookRequestDTO.getPages());

        book.setAuthor(author);

        List<Book> currentBooks = author.getBooksWritten();
        currentBooks.add(book);


        authorRepository.save(author);

        return "Book Created Successfully";
    }
}
