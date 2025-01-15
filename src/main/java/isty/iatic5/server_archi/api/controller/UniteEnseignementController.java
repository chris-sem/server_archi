package isty.iatic5.server_archi.api.controller;

import isty.iatic5.server_archi.api.model.UniteEnseignement;
import isty.iatic5.server_archi.service.UniteEnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/unites")
public class UniteEnseignementController {

    private final UniteEnseignementService ueService;

    @Autowired
    public UniteEnseignementController(UniteEnseignementService ueService) {
        this.ueService = ueService;
    }

    // Créer une unité d'enseignement
    @PostMapping("/create")
    public ResponseEntity<String> createUE(@RequestParam String code, @RequestParam String designation) {
        boolean success = ueService.createUE(code, designation);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Unité d'enseignement créée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création de l'unité d'enseignement");
        }
    }

    // Supprimer une unité d'enseignement par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUE(@PathVariable int id) {
        boolean success = ueService.deleteUE(id);
        if (success) {
            return ResponseEntity.ok("Unité d'enseignement supprimée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unité d'enseignement non trouvée");
        }
    }

    // Récupérer la liste des unités d'enseignement
    @GetMapping
    public ResponseEntity<ArrayList<UniteEnseignement>> listUEs() {
        ArrayList<UniteEnseignement> ues = ueService.listUEs();
        return ResponseEntity.ok(ues);
    }
}

