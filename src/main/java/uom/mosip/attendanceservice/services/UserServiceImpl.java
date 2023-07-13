package uom.mosip.attendanceservice.services;

import org.springframework.stereotype.Service;
import uom.mosip.attendanceservice.dao.UserRepository;
import uom.mosip.attendanceservice.models.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws Exception {
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail != null) {
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

