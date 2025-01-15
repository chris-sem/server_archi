package isty.iatic5.server_archi.api.controller;

import isty.iatic5.server_archi.api.model.*;
import isty.iatic5.server_archi.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/groupes")
public class GroupeController {

    private final GroupeService groupeService;

    @Autowired
    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    // Créer un groupe
    @PostMapping("/create")
    public ResponseEntity<String> createGroupe(
            @RequestParam String identifiant,
            @RequestBody UniteEnseignement ue,
            @RequestBody ArrayList<Eleve> eleves,
            @RequestBody Sujet sujet) {
        boolean success = groupeService.createGroupe(identifiant, ue, eleves, sujet);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Groupe créé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création du groupe");
        }
    }

    // Supprimer un groupe
    @DeleteMapping("/delete/{identifiant}")
    public ResponseEntity<String> deleteGroupe(@PathVariable String identifiant) {
        boolean success = groupeService.deleteGroupe(identifiant);
        if (success) {
            return ResponseEntity.ok("Groupe supprimé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Groupe non trouvé");
        }
    }

    // Lister tous les groupes
    @GetMapping
    public ResponseEntity<List<Groupe>> listGroupes() {
        ArrayList<Groupe> groupes = groupeService.listGroupes();
        return ResponseEntity.ok(groupes);
    }

    // Créer des groupes aléatoires
    @PostMapping("/create-random")
    public ResponseEntity<String> createGroupesAleatoires(
            @RequestBody ArrayList<UniteEnseignement> ues,
            @RequestBody ArrayList<Eleve> eleves,
            @RequestBody ArrayList<Sujet> sujets,
            @RequestParam int nbrePersonneParGroupe) {
        groupeService.createGroupesAleatoires(ues, eleves, sujets, nbrePersonneParGroupe);
        return ResponseEntity.status(HttpStatus.CREATED).body("Groupes aléatoires créés avec succès");
    }

    // Changer le groupe d'un élève
    @PutMapping("/change")
    public ResponseEntity<String> changerGroupeEleve(
            @RequestBody Eleve eleve,
            @RequestParam String nouvelIdentifiant,
            @RequestBody Sujet sujet,
            @RequestBody UniteEnseignement ue) {
        boolean success = groupeService.changerGroupeEleve(eleve, nouvelIdentifiant, sujet, ue);
        if (success) {
            return ResponseEntity.ok("Changement de groupe effectué avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec du changement de groupe");
        }
    }
}
