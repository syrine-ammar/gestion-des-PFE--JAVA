package trial;

import java.util.*;

public class GrpJury {

    private String specialite;
    private List<ProjetPFE> projetsValides;

    private President president;
    private Rapporteur rapporteur;
    private Examinateur examinateur;
    private List<Personne> encadreurs;


    public GrpJury(String specialite) {
        this.specialite = specialite;
        this.projetsValides = new ArrayList<>();
        this.encadreurs = new ArrayList<>();
    }

    // Method to validate a PFE project and give a grade
    public void validerProjet(ProjetPFE projet, int note) {
        if (note >= 10 && note <= 20) {
            projet.setValide(true);
            projetsValides.add(projet);
        } else {
            System.out.println("projet invalide.");
        }
    }
    public List<ProjetPFE> getProjetsValides() {
        return projetsValides;}

    public void ajouterEncadreur(Enseignant enseignant) {
        encadreurs.add(enseignant);}

    public void retirerEncadreur(Encadreur enseignant) {
        encadreurs.remove(enseignant);
    }

    public List<Personne> getEncadreurs() {
        return encadreurs;
    }

    public void setPresident(Enseignant en) {
        this.president = new President(en);
    }

    public President getPresident() {
        return president;
        }
    public void setRapporteur(Enseignant en) {
        this.rapporteur =new Rapporteur(en);
    }
    public Rapporteur getRapporteur() {
        return rapporteur;
    }

    public void setExaminateur(Enseignant en) {
        examinateur =new Examinateur(en);
    }
    public Examinateur getExaminateur() {
        return examinateur;
    }

    // Method to display jury information
    public void afficherGrpJury() {
        System.out.println("Spécialité: " + specialite);
        System.out.println("Président: " + president.getNom() + " " + president.getPrenom());
        System.out.println("Rapporteur: " + rapporteur.getNom() + " " + rapporteur.getPrenom());
        System.out.println("Examinateur: " + examinateur.getNom() + " " + examinateur.getPrenom());
        System.out.println("Encadreurs: ");
        for (Personne enseignant : encadreurs) {
            System.out.println("- " + enseignant.getNom() + " " + enseignant.getPrenom());
        }
    }
}
