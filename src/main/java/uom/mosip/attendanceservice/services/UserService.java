package uom.mosip.attendanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.UserRepository;
import uom.mosip.attendanceservice.models.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
  
    public User saveUser(User user) throws Exception {
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            System.out.println(user.getEmail()+" already exist in the database");
            throw new Exception("User with the same email exists");
        }
        if (user.getId() == 0) {
            System.out.println("ID should be provided");
            throw new Exception("ID should be provided");
        }
        Optional<User> existingUserById = userRepository.findById(user.getId());
        if (existingUserById.isPresent()) {
            System.out.println(user.getId()+" user already exists");
            throw new Exception("User with the same ID exists");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
