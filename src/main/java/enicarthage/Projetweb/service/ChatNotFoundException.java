package enicarthage.Projetweb.service;

public class ChatNotFoundException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public ChatNotFoundException(String chatId) {
        super("Chat with ID " + chatId + " not found");
    }

}
