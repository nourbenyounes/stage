package enicarthage.Projetweb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import enicarthage.Projetweb.entity.Personne;
import enicarthage.Projetweb.service.*;

@Controller
public class pagePersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping("/pagePersonne")
    public String afficherPagePersonne() {
        return "pagePersonne"; 
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Personne>> getall() throws IOException {
        try{
            return new ResponseEntity<List<Personne>>(personneService.getall(), HttpStatus.OK);
        }catch (PersonneNotFoundException e){
            return new ResponseEntity("Person not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Personne> addPersonne(@RequestBody Personne personne) throws IOException {
        try{
            return new ResponseEntity<Personne>(personneService.addPersonne(personne), HttpStatus.OK);
        }catch (PersonneAlreadyExistException e){
            return new ResponseEntity("Person already exists", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbycin/{cin}")
    public ResponseEntity<Personne> getPersonneByCin(@PathVariable String cin) throws IOException {
        try{
            return new ResponseEntity<Personne>(personneService.getPersonneByCin(cin), HttpStatus.OK);
        }catch (PersonneNotFoundException e){
            return new ResponseEntity("Person not Found", HttpStatus.NOT_FOUND);
        }
    }  
}
