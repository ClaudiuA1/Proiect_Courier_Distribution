package com.utcn.scdproiect.email;

import java.util.List;

public class EmailRequest {
    private List<String> emails;
    private String message;
    private String subject;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
