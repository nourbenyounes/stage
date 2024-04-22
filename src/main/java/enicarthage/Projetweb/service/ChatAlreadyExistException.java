package enicarthage.Projetweb.service;

public class ChatAlreadyExistException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public ChatAlreadyExistException(String chatId) {
        super("Chat with ID " + chatId + " already exists");
    }

}
