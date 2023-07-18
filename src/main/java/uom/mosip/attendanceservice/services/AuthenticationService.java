package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    // authenticate a student based on biometric data
    public String authenticate(Object fingerprint) {
        // TODO - call authentication service and get the student MOSIP id
        long student_id = 999999;

        // Return null if authentication fails
        return String.valueOf(student_id);
    }

}
