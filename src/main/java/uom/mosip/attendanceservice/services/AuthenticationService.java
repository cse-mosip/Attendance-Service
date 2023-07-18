package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.StudentDTO;

@Service
public class AuthenticationService {
    // authenticate a student based on biometric data
    public String authenticateStudent(Object fingerprint) {
        // TODO - call authentication service and get the student MOSIP id
        long student_id = 999999;
        return String.valueOf(student_id);
    }

}
