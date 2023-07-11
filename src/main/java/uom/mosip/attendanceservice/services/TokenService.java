package uom.mosip.attendanceservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${custom.jwt.secret}")
    private String jwtSecret;
    @Value("${custom.jwt.expiry-time}")
    private int jwtExpiryTimeout;

    public String generateJWTToken(String username, String userType) {
        Date currentTime = new Date();
        Date expirationTime = DateUtils.addMinutes(currentTime, jwtExpiryTimeout);
        Date notBeforeTime = DateUtils.addSeconds(currentTime, -1);

        return Jwts.builder().setSubject(username)
                .setIssuer("AttendanceService")
                .setAudience("AttendanceFrontends")
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setNotBefore(notBeforeTime)
                .claim("user_type", userType)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
