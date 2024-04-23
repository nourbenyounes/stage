package enicarthage.Projetweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService implements DocumentServicee{

    @Autowired
    private DocumentRepository documentRepository;
    @Override

    public void saveDocument(String titre, String chemin, byte[] fileContent,String destinataire, String source,String section) {
        Document document = new Document();
        document.setTitre(titre);
        document.setChemin(chemin);
        int taille=(fileContent.length);
        document.setDestinataire(destinataire);
        document.setSource(source);
        document.setSection(section);



        document.setFileContent(fileContent);
        document.setId((long)30);
        
        documentRepository.save(document);
    }
    @Override

    public List<Document> getDocumentsForAdministrateur() {
        return documentRepository.findByDestinataire("administrateur");
    }
    @Override

	public Optional<Document> getDocumentById(Long id) {
		return documentRepository.findById(id);
	}
    @Override

	public List<Document> getDocumentsForEtudiant() {
        return documentRepository.findByDestinataire("etudiant");

	}
    @Override

	public List<Document> getDocumentsForEnseignant() {
        return documentRepository.findByDestinataire("enseignant");

	}
}
