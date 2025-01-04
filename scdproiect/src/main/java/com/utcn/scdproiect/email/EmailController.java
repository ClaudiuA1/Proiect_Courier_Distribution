package com.utcn.scdproiect.email;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmails(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmails(emailRequest.getEmails(), emailRequest.getMessage(), emailRequest.getSubject());
        return ResponseEntity.ok("Emails sent successfully!");
    }
}
