package com.avenger.StudentLibraryManagement.Services;

import com.avenger.StudentLibraryManagement.DTOs.AuthorEntryDTO;
import com.avenger.StudentLibraryManagement.Models.Author;
import com.avenger.StudentLibraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDTO authorEntryDTO){

        Author author  = new Author();

        author.setName(authorEntryDTO.getName());

        author.setAge(authorEntryDTO.getAge());

        author.setCountry(authorEntryDTO.getCountry());

        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);
        return "Author created Successfully";
    }
}