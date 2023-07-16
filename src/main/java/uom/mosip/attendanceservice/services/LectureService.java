package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Lecture;

import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public ResponseDTO startLecture(long lecture_id) {

        Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

        if (lecture.isPresent()) {
            Lecture validLecture = lecture.get();

            if (validLecture.isStarted()) {
                return new ResponseDTO("INVALID_DATA", "Lecture is already started");
            } else if (validLecture.isEnded()) {
                return new ResponseDTO("INVALID_DATA", "Lecture has already ended");
            } else {
                validLecture.setStarted(true);
                lectureRepository.save(validLecture);
                return new ResponseDTO("OK", "Lecture started successfully", lecture_id);
            }

        } else {
            return new ResponseDTO("INVALID_DATA", "Invalid lecture");
        }
    }

    public ResponseDTO endLecture(long lecture_id) {

        Optional<Lecture> lecture = lectureRepository.findById(lecture_id);

        if (lecture.isPresent()) {
            Lecture validLecture = lecture.get();

            if (!validLecture.isStarted()) {
                return new ResponseDTO("INVALID_DATA", "Lecture is not started");
            } else if (validLecture.isEnded()) {
                return new ResponseDTO("INVALID_DATA", "Lecture has already ended");
            } else {
                validLecture.setEnded(true);
                lectureRepository.save(validLecture);
                return new ResponseDTO("OK", "Lecture finished successfully", lecture_id);
            }

        } else {
            return new ResponseDTO("INVALID_DATA", "Invalid lecture");
        }
    }

}
