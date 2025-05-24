package com.utcn.scdproiect.courier.authentif;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utcn.scdproiect.courier.Courier;
import com.utcn.scdproiect.courier.CourierRepository;
import com.utcn.scdproiect.courier.CourierService;
import com.utcn.scdproiect.courier.config.OtpService;
import com.utcn.scdproiect.email.EmailService;



@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final CourierService courierService;
    private final CourierRepository courierRepository;
    private final OtpService otpService;
    private final EmailService emailService;


    public AuthController(CourierService courierService,
                          OtpService otpService,
                          EmailService emailService,
                          CourierRepository courierRepository) {
        this.courierService = courierService;
        this.otpService = otpService;
        this.emailService = emailService;
        this.courierRepository = courierRepository;
    }

    // Pasul 1: trimitem OTP pe email dacă există curierul
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        // verificăm existența curierului
        courierService.findByEmailOrThrow(email);
        String otp = otpService.generateOtp(email);
        emailService.sendEmails(
            List.of(email),
            "Codul tău OTP este: " + otp,
            "OTP CourierApp"
        );
        return ResponseEntity.ok("Am trimis codul OTP pe email");
    }

    // Pasul 2: validăm OTP + parolă
   record AuthResponse(Integer id, String email, String message) {}

@PostMapping("/verify-otp")
public ResponseEntity<AuthResponse> verifyOtp(
  @RequestParam String email,
  @RequestParam String password,
  @RequestParam String otp
) {
  courierService.verifyPasswordOrThrow(email, password);
  if (!otpService.validateOtp(email, otp)) {
    return ResponseEntity.status(401)
      .body(new AuthResponse(null, email, "OTP invalid sau expirat"));
  }
  // găsește curierul ca obiect
  Courier c = courierRepository.findByemail(email);
  return ResponseEntity.ok(new AuthResponse(c.getId(), c.getEmail(), "Autentificare reusita"));
}
}
