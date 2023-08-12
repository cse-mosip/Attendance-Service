package uom.mosip.attendanceservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.json.JSONObject;
import uom.mosip.attendanceservice.dto.auth.RSUser;
import uom.mosip.attendanceservice.dto.http.HttpResponseDTO;

@Service
public class RSAuthService {

    @Value("${custom.mosip.rs.url}")
    private String rsURL;

    @Autowired
    private HttpService httpService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public RSUser authenticateUser(String email, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("password", password);

        String url = rsURL + "/api/public/verify";

        HttpResponseDTO responseDTO = httpService.post(url, requestBody.toString());

        if (responseDTO.isExceptionOccurred())
            throw new RuntimeException("Error when sending request to Registration Service.");

        if (!responseDTO.isSuccessful()) {
            if (responseDTO.getCode() == 400) return null;
            throw new RuntimeException("Unknown error response code occurred.");
        }

        // Decode Request Body
        if (responseDTO.getRequestBody() == null) throw new RuntimeException("Empty response body.");
        try {
            return objectMapper.readValue(responseDTO.getRequestBody(), RSUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
