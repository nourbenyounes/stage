package enicarthage.Projetweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Personne;
import enicarthage.Projetweb.repository.PersonneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public Optional<Personne> authentifier(String email, String password) {
        return personneRepository.findByEmailAndPassword(email, password);
    }

    public boolean existeByEmail(String email) {
        return personneRepository.existsByEmail(email);
    }
    
    
    public List<Personne> getall() throws PersonneNotFoundException {
        List<Personne> personnes = personneRepository.findAll();
        if (personnes.isEmpty()) {
            throw new PersonneNotFoundException("No persons found");
        } else {
            return personnes;
        }
    }
    
    public Personne addPersonne(Personne personne) throws PersonneAlreadyExistException {
        Optional<Personne> existingPersonne = personneRepository.findById(personne.getCin());

        if (existingPersonne.isPresent()) {
            throw new PersonneAlreadyExistException("Person with cin " + personne.getCin() + " already exists");
        } else {
            return personneRepository.save(personne);
        }
    }
    
    public Personne getPersonneByCin(String cin) throws PersonneNotFoundException {
        Optional<Personne> personneOptional = personneRepository.findById(cin);

        if (personneOptional.isPresent()) {
            return personneOptional.get();
        } else {
            throw new PersonneNotFoundException("Person with cin " + cin + " not found");
        }
    }

}
