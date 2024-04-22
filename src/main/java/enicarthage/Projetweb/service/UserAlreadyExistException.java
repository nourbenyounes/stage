package Exceptions;

public class UserAlreadyExistException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String userName) {
        super("User with username " + userName + " already exists");
    }

}
