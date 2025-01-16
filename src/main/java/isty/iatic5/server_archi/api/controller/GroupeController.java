package isty.iatic5.server_archi.api.controller;
import isty.iatic5.server_archi.dto.GroupeCreationRequest;


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

    //works
    // Créer un groupe
    @PostMapping("/create")
    public ResponseEntity<String> createGroupe(@RequestBody GroupeCreationRequest groupeCreationRequest) {
        // Extract values from the request object
        String identifiant = groupeCreationRequest.getIdentifiant();
        UniteEnseignement ue = groupeCreationRequest.getUe();
        ArrayList<Eleve> eleves = groupeCreationRequest.getEleves();
        Sujet sujet = groupeCreationRequest.getSujet();

        // Call the service method to create the group
        boolean success = groupeService.createGroupe(identifiant, ue, eleves, sujet);

        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Groupe créé avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la création du groupe");
        }
    }

    //works
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

    //works
    // Lister tous les groupes
    @GetMapping
    public ResponseEntity<List<Groupe>> listGroupes() {
        ArrayList<Groupe> groupes = groupeService.listGroupes();
        return ResponseEntity.ok(groupes);
    }

    //works
    // Créer des groupes aléatoires
    @PostMapping("/create-random")
    public ResponseEntity<String> createGroupesAleatoires(
            @RequestBody GroupeCreationRequest groupeCreationRequest) {
        // Extract values from the request object
        ArrayList<UniteEnseignement> ues = groupeCreationRequest.getUes();
        ArrayList<Eleve> eleves = groupeCreationRequest.getEleves();
        ArrayList<Sujet> sujets = groupeCreationRequest.getSujets();
        int nbrePersonneParGroupe = groupeCreationRequest.getNbrePersonneParGroupe();

        // Call the service method to create random groups
        groupeService.createGroupesAleatoires(ues, eleves, sujets, nbrePersonneParGroupe);

        return ResponseEntity.status(HttpStatus.CREATED).body("Groupes aléatoires créés avec succès");
    }


    //works
    // Changer le groupe d'un élève
    @PutMapping("/change")
    public ResponseEntity<String> changerGroupeEleve(
            @RequestBody GroupeCreationRequest groupeCreationRequest) {

        // Extraire les valeurs de l'objet GroupeCreationRequest
        String nouvelIdentifiant = groupeCreationRequest.getIdentifiant();
        Eleve eleve = groupeCreationRequest.getEleve();
        Sujet sujet = groupeCreationRequest.getSujet();
        UniteEnseignement ue = groupeCreationRequest.getUe();

        // Appeler la méthode de service pour changer le groupe de l'élève
        boolean success = groupeService.changerGroupeEleve(eleve, nouvelIdentifiant, sujet, ue);

        // Retourner la réponse appropriée en fonction du résultat
        if (success) {
            return ResponseEntity.ok("Changement de groupe effectué avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec du changement de groupe");
        }
    }

}
