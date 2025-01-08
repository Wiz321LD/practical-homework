package org.example.hw5.util;

public class StudentErrorResponse {


    private String errorMessage;


    public StudentErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
