package isty.iatic5.server_archi.dto;

import java.util.ArrayList;
import isty.iatic5.server_archi.api.model.UniteEnseignement;
import isty.iatic5.server_archi.api.model.Eleve;
import isty.iatic5.server_archi.api.model.Sujet;

public class GroupeCreationRequest {
    private String identifiant;
    private UniteEnseignement ue;
    private ArrayList<UniteEnseignement> ues;
    private ArrayList<Eleve> eleves;
    private Sujet sujet;
    private ArrayList<Sujet> sujets;
    private int nbrePersonneParGroupe;
    private Eleve eleve;


    // Getters et setters

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public ArrayList<UniteEnseignement> getUes() {
        return ues;
    }

    public void setUes(ArrayList<UniteEnseignement> ues) {
        this.ues = ues;
    }

    public ArrayList<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(ArrayList<Sujet> sujets) {
        this.sujets = sujets;
    }

    public int getNbrePersonneParGroupe() {
        return nbrePersonneParGroupe;
    }

    public void setNbrePersonneParGroupe(int nbrePersonneParGroupe) {
        this.nbrePersonneParGroupe = nbrePersonneParGroupe;
    }

    public UniteEnseignement getUe() {
        return ue;
    }

    public void setUe(UniteEnseignement ue) {
        this.ue = ue;
    }

    public ArrayList<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(ArrayList<Eleve> eleves) {
        this.eleves = eleves;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }
}
