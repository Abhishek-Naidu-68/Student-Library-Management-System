package com.avenger.StudentLibraryManagement.Controllers;

import com.avenger.StudentLibraryManagement.DTOs.AuthorEntryDTO;
import com.avenger.StudentLibraryManagement.Models.Author;
import com.avenger.StudentLibraryManagement.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public String addAuthor(@RequestBody AuthorEntryDTO authorEntryDTO){
        return authorService.addAuthor(authorEntryDTO);
    }

}
