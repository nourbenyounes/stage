package enicarthage.Projetweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Personne;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, String> {
    
    Optional<Personne> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    
    Optional<Personne> findByCin(String cin); // Correction du nom de la méthode
    List<Personne> findAll();

}
