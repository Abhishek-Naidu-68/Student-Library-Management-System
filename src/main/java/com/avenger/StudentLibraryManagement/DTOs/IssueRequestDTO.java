package com.avenger.StudentLibraryManagement.DTOs;

public class IssueRequestDTO {

    private int bookId;

    private int cardId;


    public IssueRequestDTO() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
