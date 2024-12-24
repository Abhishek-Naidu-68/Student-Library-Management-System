package com.avenger.StudentLibraryManagement.DTOs;


public class ReturnBookReqDTO {

    private int bookId;

    private int cardId;

    public ReturnBookReqDTO() {
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
