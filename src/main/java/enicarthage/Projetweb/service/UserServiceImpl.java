package Services;

import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Entities.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;


	@Override
	public List<User> getall() throws UserNotFoundException {
	    List<User> users = userRepository.findAll();
	    if (users.isEmpty()) {
	        throw new UserNotFoundException("No users found");
	    } else {
	        return users;
	    }
	}


	@Override
	public User addUser(User user) throws UserAlreadyExistException {
	    Optional<User> existingUser = userRepository.findById(user.getUserName());

	    if (existingUser.isPresent()) {
	        throw new UserAlreadyExistException("User with username " + user.getUserName() + " already exists");
	    } else {
	        return userRepository.save(user);
	    }
	}


	@Override
	public User getUserByUserName(String username) throws UserNotFoundException {
	    Optional<User> userOptional = userRepository.findById(username);

	    if (userOptional.isPresent()) {
	        return userOptional.get();
	    } else {
	        throw new UserNotFoundException("User with username " + username + " not found");
	    }
	}



}
