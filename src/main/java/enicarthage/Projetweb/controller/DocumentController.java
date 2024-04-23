package enicarthage.Projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.service.DocumentService;
import enicarthage.Projetweb.service.EtudiantService;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping("/upload")
    public String uploadDocumentForm(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        System.out.println("upload");

        model.addAttribute("etudiants", etudiants);
        System.out.println(etudiants);
        return "upload"; 
    }


   /* @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("titre") String titre, @RequestParam("file") MultipartFile file,@RequestParam("destinataire") String destinataire,@RequestParam("section") String section,HttpSession session) {
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String chemin = file.getOriginalFilename();
                String source = (String) session.getAttribute("userId");

                documentService.saveDocument(titre, chemin, fileContent,destinataire,source,section);
                System.out.println(source);
                //return "redirect:/welcome";
            } catch (IOException e) {
                //return "redirect:/erreurAuthentification";
                System.out.println("erreur");

            }
        } else {
            //return "redirect:/erreurAuthentification";
            System.out.println("erreur");

        }
    }*/
    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("titre") String titre, 
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("destinataire") String destinataire,
                                 @RequestParam("autreDestinataire") String autreDestinataire,
                                 @RequestParam("section") String section,
                                 HttpSession session) {
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String chemin = file.getOriginalFilename();
                String source = (String) session.getAttribute("userId");

                // Utiliser l'autre destinataire s'il est renseigné, sinon utiliser celui de la liste déroulante
                String destinataireFinal = autreDestinataire.isEmpty() ? destinataire : autreDestinataire;

                documentService.saveDocument(titre, chemin, fileContent, destinataireFinal, source, section);
                System.out.println(source);
                //return "redirect:/welcome";
            } catch (IOException e) {
                //return "redirect:/erreurAuthentification";
                System.out.println("erreur");
            }
        } else {
            //return "redirect:/erreurAuthentification";
            System.out.println("erreur");
        }
    }

    @GetMapping("/liste-document")
    public String listeressources(Model model,HttpSession session) {
        String cin=session.getAttribute("userId").toString();

    	List<Document> documents = documentService.getDocumentsForEtudiant();
        model.addAttribute("documents", documents);
        List<Document> documentss = documentService.getDocumentsForEtudiantid(cin);
        System.out.println(cin);
        System.out.println("documentss"+documentss);
        model.addAttribute("documentss", documentss);
        return"/liste-document";}
    
    
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("id") Long id) {
        Optional<Document> optionalDocument = documentService.getDocumentById(id);
        
        if (!optionalDocument.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Document document = optionalDocument.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", document.getChemin());

        return new ResponseEntity<>(document.getfileContent(), headers, HttpStatus.OK);
    }
    @GetMapping("/ajouter-document")
    public String afficherFormulaireAjoutEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        
        return "upload";
    }
    

    
    @GetMapping("/ajouter-document-etudiant")
    public String uploadDocumentFormEtudiant() {
        return "ajouter-document-etudiant"; 
    }

    @PostMapping("/ajouter-document-etudiant")
    public void handleFileUploadEtudiant(@RequestParam("titre") String titre, @RequestParam("file") MultipartFile file,@RequestParam("destinataire") String destinataire,@RequestParam("section") String section,HttpSession session) {
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String chemin = file.getOriginalFilename();
                String source = (String) session.getAttribute("userId");

                documentService.saveDocument(titre, chemin, fileContent,destinataire,source,section);

                System.out.println(source);
                //return "redirect:/welcome";
            } catch (IOException e) {
                //return "redirect:/erreurAuthentification";
                System.out.println("erreur");

            }
        } else {
            //return "redirect:/erreurAuthentification";
            System.out.println("erreur");

        }
    }
    
    

}
