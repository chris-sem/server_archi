package isty.iatic5.server_archi.api.controller;

import isty.iatic5.server_archi.api.model.Eleve;
import isty.iatic5.server_archi.service.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/eleves")
public class EleveController {

    private final EleveService eleveService;

    @Autowired
    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    // Créer un nouvel élève
    @PostMapping("/create")
    public ResponseEntity<String> createEleve(@RequestParam String nom, @RequestParam String prenom) {
        boolean success = eleveService.createEleve(nom, prenom);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Élève créé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création de l'élève");
        }
    }

    // Supprimer un élève par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEleve(@PathVariable int id) {
        boolean success = eleveService.deleteEleve(id);
        if (success) {
            return ResponseEntity.ok("Élève supprimé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Élève non trouvé");
        }
    }

    // Récupérer la liste des élèves
    @GetMapping
    public ResponseEntity<ArrayList<Eleve>> getAllEleves() {
        ArrayList<Eleve> eleves = eleveService.eleves();
        return ResponseEntity.ok(eleves);
    }
}
