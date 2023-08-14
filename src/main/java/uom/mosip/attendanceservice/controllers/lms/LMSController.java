package uom.mosip.attendanceservice.controllers.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uom.mosip.attendanceservice.services.LMSService;

@RestController
@RequestMapping("/admin/lms")
public class LMSController {

    @Autowired
    private LMSService lmsService;

    @GetMapping("/getAllModules")
    public Object getAllModules() {
        return lmsService.getAllCourses();
    }

    @GetMapping("/getModules/{intake}")
    public Object getCoursesByIntake(@PathVariable("intake") long intake) {
        return lmsService.getCoursesByIntake(intake);
    }

}
