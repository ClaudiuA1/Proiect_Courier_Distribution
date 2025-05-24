package com.utcn.scdproiect.courier.config;


import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    // stocare simplă in-memory cu TTL
    private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public String generateOtp(String email) {
        String code = String.format("%06d", random.nextInt(1_000_000));
        // expiră în 5 minute
        otpStore.put(email, new OtpEntry(code, Instant.now().plusSeconds(300)));
        return code;
    }

    public boolean validateOtp(String email, String code) {
        OtpEntry entry = otpStore.get(email);
        if (entry == null || Instant.now().isAfter(entry.expiresAt)) {
            otpStore.remove(email);
            return false;
        }
        boolean ok = entry.code.equals(code);
        if (ok) otpStore.remove(email);
        return ok;
    }

    private static class OtpEntry {
        final String code;
        final Instant expiresAt;
        OtpEntry(String code, Instant expiresAt) {
            this.code = code;
            this.expiresAt = expiresAt;
        }
    }
}

