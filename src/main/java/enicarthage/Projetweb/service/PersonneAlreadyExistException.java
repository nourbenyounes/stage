package enicarthage.Projetweb.service;

public class PersonneAlreadyExistException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public PersonneAlreadyExistException(String cin) {
        super("Personne with username " + cin + " already exists");
    }

}
