package com.avenger.StudentLibraryManagement.Controllers;

import com.avenger.StudentLibraryManagement.DTOs.IssueRequestDTO;
import com.avenger.StudentLibraryManagement.DTOs.ReturnBookReqDTO;
import com.avenger.StudentLibraryManagement.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("issue-book")
    public String issueBook(@RequestBody IssueRequestDTO issueRequestDTO){
        try {
            return transactionsService.issueBook(issueRequestDTO);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("return-book")
    public String returnBook(@RequestBody ReturnBookReqDTO returnBookReqDTO){
        return "";
    }
}
