package enicarthage.Projetweb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
public interface DocumentServicee {
void saveDocument(String titre, String chemin, byte[] fileContent,String destinataire, String source) ;
       
    public List<Document> getDocumentsForAdministrateur() ;
	public Optional<Document> getDocumentById(Long id);
	public List<Document> getDocumentsForEtudiant() ;
}
