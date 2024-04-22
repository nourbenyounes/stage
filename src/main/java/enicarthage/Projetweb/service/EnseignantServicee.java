package enicarthage.Projetweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.repository.EnseignantRepository;

import java.util.Optional;
public interface EnseignantServicee {
 Optional<Enseignant> authentifierEnseignant(String email, String password);

   boolean existeEnseignantByEmail(String email) ;
}
