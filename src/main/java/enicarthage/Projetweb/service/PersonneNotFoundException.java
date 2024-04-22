package enicarthage.Projetweb.service;

public class PersonneNotFoundException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	public PersonneNotFoundException(String cin) {
        super("Personne with cin " + cin + " not found");
    }

}