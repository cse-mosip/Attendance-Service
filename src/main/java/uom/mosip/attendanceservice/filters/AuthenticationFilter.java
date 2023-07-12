package uom.mosip.attendanceservice.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uom.mosip.attendanceservice.dto.auth.TokenDTO;
import uom.mosip.attendanceservice.dto.auth.UserDetails;
import uom.mosip.attendanceservice.services.TokenService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Access-Token");
        if (accessToken == null) {
            rejectRequest(response, "NO_ACCESS_TOKEN", "Access Token is not found in the headers. " +
                    "Please set the Access-Token header to contain the token.");
            return;
        }

        // Decode the Access Token
        TokenDTO accessTokenDTO;
        try {
            accessTokenDTO = tokenService.decodeJWTToken(accessToken);
        } catch (ExpiredJwtException e) {
            rejectRequest(response, "TOKEN_EXPIRED", "The Access Token is expired.");
            e.printStackTrace();
            return;
        } catch (MalformedJwtException e) {
            rejectRequest(response, "TOKEN_MALFORMED", "The Access Token is malformed.");
            e.printStackTrace();
            return;
        } catch (SignatureException e) {
            rejectRequest(response, "TOKEN_SIGNATURE_MISMATCH", "The Access Token does not match with he signature.");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            rejectRequest(response, "TOKEN_ERROR", "The Access Token cannot be decoded.");
            e.printStackTrace();
            return;
        }

        if (accessTokenDTO == null) {
            rejectRequest(response, "TOKEN_ERROR", "The Access Token cannot be decoded.");
            return;
        }

        // Create UserDetails object & store it in Request Context
        UserDetails userDetails = new UserDetails(Long.parseLong(accessTokenDTO.getUsername()),
                Integer.parseInt(accessTokenDTO.getUserType()));
        storeUserDetailsInRequestContext(userDetails);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/admin/login");
    }

    private void rejectRequest(HttpServletResponse response, String status, String message) throws IOException {
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("status", status);
        responseBody.put("message", message);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }

    private void storeUserDetailsInRequestContext(UserDetails userDetails) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute("authenticatedUserDetails", userDetails, RequestAttributes.SCOPE_REQUEST);
    }
}
