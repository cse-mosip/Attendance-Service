package uom.mosip.attendanceservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.as.ASResponseDTO;
import uom.mosip.attendanceservice.dto.http.HttpResponseDTO;

import java.util.ArrayList;

@Service
public class AuthenticationService {
    @Value("${custom.as.url}")
    private String asURL;

    private final HttpService httpService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthenticationService(HttpService httpService) {
        this.httpService = httpService;
    }

    // authenticate a student based on biometric data
    public String authenticate(Object fingerprint) {
        // TODO - call authentication service and get the student MOSIP id
        String url = asURL + "/upload";

        ArrayList<Object> fingerprintList = new ArrayList<>();
        fingerprintList.add(fingerprint);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", fingerprintList);

        HttpResponseDTO httpResponseDTO = httpService.post(url, jsonObject.toString());

        if (httpResponseDTO.isExceptionOccurred()) {
            return null;
        }

        if (!httpResponseDTO.isSuccessful()) {
            return null;
        }

        if (httpResponseDTO.getRequestBody() == null) {
            return null;
        }

        try {
            ASResponseDTO asResponseDTO = objectMapper.readValue(httpResponseDTO.getRequestBody(), ASResponseDTO.class);
            return asResponseDTO.message;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

}
