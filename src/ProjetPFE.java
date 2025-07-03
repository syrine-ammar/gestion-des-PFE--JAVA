package trial;
import java.util.*;

public class ProjetPFE {

    private Date dateProjet;
    private int heure;
    private String type;
    private String titre;
    private boolean valide;
    private List<Personne> invites; 
    private PersonnelAdmin planificateur;
    private boolean binome;

    public ProjetPFE(Date dateProjet, int heure, String type, String titre, boolean valide, PersonnelAdmin planificateur, boolean binome) {
        this.dateProjet = dateProjet;
        this.heure = heure;
        this.type = type;
        this.titre = titre;
        this.valide = valide;
        this.invites = new ArrayList<>();
        this.planificateur = planificateur;
        this.binome = binome;
    }
    public void ajouterInvite(Personne personne) {
        invites.add(personne);
    }

    public void retirerInvite(Personne personne) {
        invites.remove(personne);
    }

    public List<Personne> getInvites() {
        return invites;
    }

    public PersonnelAdmin getPlanificateur() {
        return planificateur;
    }

    public void setPlanificateur(PersonnelAdmin planificateur) {
        this.planificateur = planificateur;
    }

    // Method to check if it's a binome project
    public boolean isBinome() {
        return binome;
    }

    // Method to display project information
    public void afficherProjet() {
        System.out.println("Titre du projet: " + titre);
        System.out.println("Date du projet: " + dateProjet);
        System.out.println("Heure du projet: " + heure);
        System.out.println("Type du projet: " + type);
        System.out.println("Valide: " + (valide ? "Oui" : "Non"));
        System.out.println("Invites: ");
        for (Personne personne : invites) {
            System.out.println("- " + personne.getNom() + " " + personne.getPrenom());
        }
        System.out.println("Planificateur: " + planificateur.getNom() + " " + planificateur.getPrenom());
        System.out.println("Type de projet: " + (binome ? "Binome" : "Solo"));
    }
	public String getTitre() {
		
		return this.titre;
	}
	public void setValide(boolean b) {
		this.valide=b;
	}
}
