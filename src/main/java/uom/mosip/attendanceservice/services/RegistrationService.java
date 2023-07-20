package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dto.StudentDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    // get details of a single student from registration service
    public StudentDTO getStudentDetails(String student_id) {
        // TODO - call registration service and get the student details

        return getSampleStudentDTO(student_id);
    }

    public List<StudentDTO> getStudentListDetails(List<String> student_id_list) {
        // TODO - call registration service and get the details of all the students
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (String studentId : student_id_list) {
            studentDTOList.add(getSampleStudentDTO(studentId));
        }
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

    private StudentDTO getSampleStudentDTO(String studentId) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentId);
        studentDTO.setIndex_number("190123F");
        studentDTO.setName("Malith Dilshan");
        studentDTO.setPicture("https://ibb.co/sKvy5Tb");
        return studentDTO;
    }

}
