package enicarthage.Projetweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Administrateur;
import enicarthage.Projetweb.repository.AdministrateurRepository;

import java.util.Optional;

public interface  AdministrateurServicee {

    Optional<Administrateur> authentifierAdministrateur(String email, String password) ;

     boolean existeAdministrateurByEmail(String email) ;
}
