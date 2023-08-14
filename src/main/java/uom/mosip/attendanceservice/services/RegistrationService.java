package uom.mosip.attendanceservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.StudentDTO;
import uom.mosip.attendanceservice.dto.http.HttpResponseDTO;
import uom.mosip.attendanceservice.dto.rs.RSStudentDTO;

import java.util.*;

@Service
public class RegistrationService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${custom.rs.url}")
    private String rsURL;

    @Autowired
    private HttpService httpService;

    // get details of a single student from registration service
    public StudentDTO getStudentDetails(String studentId) {
        List<RSStudentDTO> studentDTOList = getStudentDetailsFromRS(Collections.singletonList(studentId));
        if (studentDTOList.isEmpty()) return getErrorStudentDTO(studentId);
        return new StudentDTO(studentDTOList.get(0));
    }

    public Map<String, StudentDTO> getStudentDetailsMap(List<String> studentIds) {
        List<RSStudentDTO> studentDTOList = getStudentDetailsFromRS(studentIds);
        Map<String, StudentDTO> studentDTOMap = new HashMap<>();

        // Loop and map
        for (RSStudentDTO rsStudentDTO : studentDTOList) {
            studentDTOMap.put(rsStudentDTO.index, new StudentDTO(rsStudentDTO));
        }

        // Recheck for missing values and fill with dummy data if missing
        for (String studentId : studentIds) {
            if (!studentDTOMap.containsKey(studentId)) {
                studentDTOMap.put(studentId, getErrorStudentDTO(studentId));
            }
        }

        return studentDTOMap;
    }

    private List<RSStudentDTO> getStudentDetailsFromRS(List<String> studentIds) {
        String url = rsURL + "/api/public/data";

        JSONObject requestBody = new JSONObject();
        requestBody.put("indices", studentIds);
        requestBody.put("fields", Arrays.asList("index", "email", "firstName", "lastName", "faculty", "department"));

        HttpResponseDTO responseDTO = httpService.post(url, requestBody.toString());

        List<RSStudentDTO> list = new LinkedList<>();

        if (responseDTO.isExceptionOccurred() || !responseDTO.isSuccessful() || responseDTO.getRequestBody() == null) {
            return list;
        }

        try {
            RSStudentDTO[] rsStudentDTOS = objectMapper.readValue(responseDTO.getRequestBody(), RSStudentDTO[].class);
            return Arrays.stream(rsStudentDTOS).toList();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return list;
        }

    }

    private StudentDTO getErrorStudentDTO(String studentId) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIndex(studentId);
        studentDTO.setFirstName("Unknown");
        studentDTO.setLastName("Unknown");
        studentDTO.setEmail("Unknown");
        studentDTO.setFaculty("Unknown");
        studentDTO.setDepartment("Unknown");
        return studentDTO;
    }

}
