package enicarthage.Projetweb.service;


	import java.util.List;
import java.util.Optional;

import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.entity.Notification;


	public interface EtudiantServicee {


    Optional<Etudiant> authentifierEtudiant(String email, String password) ;

    boolean existeEtudiantByEmail(String email);
   Optional<Etudiant> findEtudiantById(String cin);
    Optional<Etudiant> findEtudiantByIdet(Etudiant etudiant);
    void modifierEtudiant(Etudiant etudiant);

    }
