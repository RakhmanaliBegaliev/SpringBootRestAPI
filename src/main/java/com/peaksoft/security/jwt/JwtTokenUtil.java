package com.peaksoft.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenUtil {
    @Value("java-moscow-4")
    private String jwtSecret;
    private final Long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000L; // 1 WEEK
}
