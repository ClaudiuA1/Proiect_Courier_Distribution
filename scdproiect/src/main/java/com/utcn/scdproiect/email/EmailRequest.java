package com.utcn.scdproiect.email;

import java.util.List;

public class EmailRequest {
    private List<String> emails;
    private String message;

    // Getters și Setters
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
