package enicarthage.Projetweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.EtudiantRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService implements EtudiantServicee {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Optional<Etudiant> authentifierEtudiant(String email, String password) {
        return etudiantRepository.findByEmailAndPassword(email, password);
    }
    @Override
    public boolean existeEtudiantByEmail(String email) {
        return etudiantRepository.existsByEmail(email);
    }
    @Override
    public Optional<Etudiant> findEtudiantById(String cin) {
        return etudiantRepository.findByCin(cin);
    }
    @Override
    public Optional<Etudiant> findEtudiantByIdet(Etudiant etudiant) {
        return etudiantRepository.findByCin(etudiant.getCin());
    }
    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    
   
    
    

}