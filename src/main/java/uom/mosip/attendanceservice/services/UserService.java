package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.UserRepository;
import uom.mosip.attendanceservice.dto.UserDTO;
import uom.mosip.attendanceservice.dto.lms.CourseDTO;
import uom.mosip.attendanceservice.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByID(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByMosipID(String mosipID) {
        return userRepository.findByMosipId(mosipID);
    }

    public User saveUser(User user) throws Exception {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            System.out.println(user.getEmail() + " already exist in the database");
            throw new Exception("User with the same email exists");
        }
        if (Objects.equals(user.getMosipId(), "0")) {
            System.out.println("MOSIP ID should be provided");
            throw new Exception("MOSIP ID should be provided");
        }
        Optional<User> existingUserById = userRepository.findByMosipId(user.getMosipId());
        if (existingUserById.isPresent()) {
            System.out.println(user.getId() + " MOSIP ID already exists");
            throw new Exception("User with the same MOSIP ID exists");
        }
        return userRepository.save(user);
    }

    public List<UserDTO> getALlUsers() {
        return userRepository.fetchAllUsers().stream().map(UserDTO::new)
                .toList();
    }

}
