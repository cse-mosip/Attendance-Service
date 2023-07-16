package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureAttendanceRepository;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.models.Lecture;
import uom.mosip.attendanceservice.models.LectureAttendance;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final LectureAttendanceRepository lectureAttendanceRepository;
    private final LectureRepository lectureRepository;
    @Autowired
    public AdminService(LectureAttendanceRepository lectureAttendanceRepository,LectureRepository lectureRepository) {
        this.lectureAttendanceRepository = lectureAttendanceRepository;
        this.lectureRepository =  lectureRepository;
    }

    public List<LectureAttendance> getStudentAttendance(String studentId){
        return lectureAttendanceRepository.getLectureAttendanceByStudentId(studentId);
    }

      public List<LectureAttendance> getLectureAttendance(long lectureId){
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        if (lecture.isEmpty()){
            return null;
        }
        return lectureAttendanceRepository.getLectureAttendanceByLectureId(lectureId);
    }
}
