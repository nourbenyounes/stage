package Exceptions;

public class UserNotFoundException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userName) {
        super("User with username " + userName + " not found");
    }

}
