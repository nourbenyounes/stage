package enicarthage.Projetweb.service;

public class NoChatExistsInTheRepository extends Throwable {

	private static final long serialVersionUID = 1L;

	public NoChatExistsInTheRepository() {
        super("No chat exists in the repository");
    }

}
