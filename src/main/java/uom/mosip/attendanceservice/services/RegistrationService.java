package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.StudentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    // get details of a single student from registration service
    public StudentDTO getStudentDetails(String student_id) {
        // TODO - call registration service and get the student details
        StudentDTO studentDTO = new StudentDTO();
        return studentDTO;
    }

    public List<StudentDTO> getStudentListDetails(List<String> student_id_list) {
        // TODO - call registration service and get the details of all the students
        List<StudentDTO> studentDTOList = null;
        return studentDTOList;
    }

    public Map<String, StudentDTO> getStudentDetailsMap(List<String> student_id_list) {
        List<StudentDTO> studentDTOList = getStudentListDetails(student_id_list);
        Map<String, StudentDTO> studentDTOMap = new HashMap<>();

        // Loop and map
        for (StudentDTO studentDTO : studentDTOList) {
            studentDTOMap.put(studentDTO.getId(), studentDTO);
        }

        return studentDTOMap;
    }
}
