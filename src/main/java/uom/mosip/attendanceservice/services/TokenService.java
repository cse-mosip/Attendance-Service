package uom.mosip.attendanceservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.auth.AccessTokenDTO;
import uom.mosip.attendanceservice.dto.auth.TokenDTO;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${custom.jwt.secret}")
    private String jwtSecret;
    @Value("${custom.jwt.expiry-time}")
    private int jwtExpiryTimeout;

    public AccessTokenDTO generateJWTToken(String username, String userType) {
        Date currentTime = new Date();
        Date expirationTime = DateUtils.addSeconds(currentTime, jwtExpiryTimeout);
        Date notBeforeTime = DateUtils.addSeconds(currentTime, -1);

        String accessToken = Jwts.builder().setSubject(username)
                .setIssuer("AttendanceService")
                .setAudience("AttendanceFrontends")
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setNotBefore(notBeforeTime)
                .claim("user_type", userType)
                .signWith(getJWTSigningKey())
                .compact();

        return new AccessTokenDTO(accessToken, jwtExpiryTimeout);
    }

    public TokenDTO decodeJWTToken(String token) {
        Claims tokenBody = Jwts.parserBuilder().setSigningKey(getJWTSigningKey()).build().parseClaimsJws(token).getBody();
        String userType = tokenBody.get("user_type", String.class);

        if (tokenBody.getExpiration() == null || tokenBody.getIssuedAt() == null || tokenBody.getNotBefore() == null ||
            tokenBody.getSubject() == null) {
            throw new MalformedJwtException("Required fields are null in the JWT Token.");
        }

        return new TokenDTO(tokenBody.getExpiration(), tokenBody.getIssuedAt(), tokenBody.getNotBefore(),
                tokenBody.getSubject(), userType);
    }

    private Key getJWTSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
