package com.avenger.StudentLibraryManagement.DTOs;

public class StudentUpdateMobileRequestDTO {

    private int id;
    private String mobileNo;

    public StudentUpdateMobileRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
