package isty.iatic5.server_archi.api.controller;

import isty.iatic5.server_archi.api.model.Sujet;
import isty.iatic5.server_archi.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/sujets")
public class SujetController {

    private final SujetService sujetService;

    @Autowired
    public SujetController(SujetService sujetService) {
        this.sujetService = sujetService;
    }

    // Créer un sujet
    @PostMapping("/create")
    public ResponseEntity<String> createSujet(@RequestParam String intitule) {
        boolean success = sujetService.createSujet(intitule);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Sujet créé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création du sujet");
        }
    }

    // Supprimer un sujet par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSujet(@PathVariable int id) {
        boolean success = sujetService.deleteSujet(id);
        if (success) {
            return ResponseEntity.ok("Sujet supprimé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sujet non trouvé");
        }
    }

    // Récupérer la liste des sujets
    @GetMapping
    public ResponseEntity<ArrayList<Sujet>> listSujets() {
        ArrayList<Sujet> sujets = sujetService.listSujets();
        return ResponseEntity.ok(sujets);
    }
}

