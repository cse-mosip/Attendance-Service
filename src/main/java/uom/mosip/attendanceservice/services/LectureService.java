package uom.mosip.attendanceservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.controllers.LectureController;
import uom.mosip.attendanceservice.dao.LectureRepository;
import uom.mosip.attendanceservice.dto.ResponseDTO;
import uom.mosip.attendanceservice.models.Lecture;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;
    private static final Logger logger = LoggerFactory.getLogger(LectureService.class);

    //Create lecture

    //Get all lectures
    public ResponseDTO getAllLectures() {
        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        logger.info(lectureList.toString());
        return new ResponseDTO("OK", "All lectures fetched successfully", lectureList);
    }

    //Update lecture

    //Delete lecture

    //Get lecture By ID

}
