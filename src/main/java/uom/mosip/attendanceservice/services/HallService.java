package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.HallRepository;
import uom.mosip.attendanceservice.models.Hall;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

//    get all lecture halls
    public Iterable<Hall>  getAllHalls() {
        return hallRepository.findAll();
    }

//    Create lecture hall

//    update lecture hall

//    delete lecture hall
}
