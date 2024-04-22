package enicarthage.Projetweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Administrateur;
import enicarthage.Projetweb.repository.AdministrateurRepository;

import java.util.Optional;

@Service
public class AdministrateurService implements AdministrateurServicee{

    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Override
    public Optional<Administrateur> authentifierAdministrateur(String email, String password) {
        return administrateurRepository.findByEmailAndPassword(email, password);
    }
    @Override
    public boolean existeAdministrateurByEmail(String email) {
        return administrateurRepository.existsByEmail(email);
    }
}
