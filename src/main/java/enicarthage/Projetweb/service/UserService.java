package Services;

import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Entities.User;

import java.util.List;

public interface UserService {
	
	List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}

