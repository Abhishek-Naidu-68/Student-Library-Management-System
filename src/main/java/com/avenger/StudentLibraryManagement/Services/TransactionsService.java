package com.avenger.StudentLibraryManagement.Services;


import com.avenger.StudentLibraryManagement.DTOs.BookRequestDTO;
import com.avenger.StudentLibraryManagement.DTOs.IssueRequestDTO;
import com.avenger.StudentLibraryManagement.DTOs.ReturnBookReqDTO;
import com.avenger.StudentLibraryManagement.Enums.CardStatus;
import com.avenger.StudentLibraryManagement.Enums.TransactionStatus;
import com.avenger.StudentLibraryManagement.Models.Book;
import com.avenger.StudentLibraryManagement.Models.Card;
import com.avenger.StudentLibraryManagement.Models.Transactions;
import com.avenger.StudentLibraryManagement.Repositories.BookRepository;
import com.avenger.StudentLibraryManagement.Repositories.CardRepository;
import com.avenger.StudentLibraryManagement.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueRequestDTO issueRequestDTO) throws Exception{

        int bookId = issueRequestDTO.getBookId();
        int cardId = issueRequestDTO.getCardId();

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transactions transaction = new Transactions();

        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssuedOperation(true);

        if(book==null || book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transaction);
            throw new Exception("Books is Not Available");
        }

        if(card==null || !card.getCardStatus().equals(CardStatus.ACTIVATED)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transaction);
            throw new Exception("Card Invalid");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssued(true);
        List<Transactions> listOfTransactionsForBook = book.getListOfTransactions();
        listOfTransactionsForBook.add(transaction);
        book.setListOfTransactions(listOfTransactionsForBook);

        List<Transactions> listOfTransactionsForCard = card.getTransactionsList();
        listOfTransactionsForCard.add(transaction);
        card.setTransactionsList(listOfTransactionsForCard);

        List<Book> booksIssued = card.getBooksIssued();
        booksIssued.add(book);
        card.setBooksIssued(booksIssued);

        cardRepository.save(card);
        return "Book issued";
    }

    public String returnBook(ReturnBookReqDTO returnBookReqDTO) throws Exception{
        Book book = bookRepository.findById(returnBookReqDTO.getBookId()).get();
        Card card = cardRepository.findById(returnBookReqDTO.getCardId()).get();

        Transactions transaction = new Transactions();

        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setIssuedOperation(false);
        transaction.setBook(book);
        transaction.setCard(card);

        if(!card.getCardStatus().equals(CardStatus.ACTIVATED)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transaction);
            throw new Exception("Card Invalid");
        }
        List<Book> booksIssued = card.getBooksIssued();
        if(!booksIssued.contains(book)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transaction);
            throw new Exception("Book Not ISSUED");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        return "";
    }

    public int calculateFine(ReturnBookReqDTO returnBookReqDTO){
        int fine = 0;
        int daysGiven = Transactions.noOfDaysAllowed;

        Date lastTransaction = getLastTransaction(returnBookReqDTO.getBookId(),returnBookReqDTO.getCardId());
        Date today = new Date();
        long LastTransactionMinutes = lastTransaction.getTime();
        long todayMinutes  = today.getTime();
        long timeDifference = Math.abs(todayMinutes-LastTransactionMinutes);
        long daysDifference = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);

        if(daysDifference > daysGiven){
            fine = (int)daysDifference-daysGiven;
        }
        return fine;

    }

    public Date getLastTransaction(int bookId,int cardId){

        List<Transactions> transactionsList = transactionsRepository.getTransactionsForBookAndCard(bookId,cardId);
        Date transactionDate = transactionsList.get(0).getTransactionDate();
        for(Transactions t : transactionsList){
            if(t.getTransactionDate().after(transactionDate) && t.isIssuedOperation()){
                transactionDate = t.getTransactionDate();
            }
        }
        return transactionDate;
    }
}
