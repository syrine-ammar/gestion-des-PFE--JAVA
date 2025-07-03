package trial;
import java.time.LocalDateTime;
import java.util.*;

public class Enseignant extends Personne {
    private String specialite;
    private List<Etudiant> etudiantsEncadres;
    private Map<LocalDateTime, String> schedule;

    public Enseignant(String nom, String prenom, int cin, String adresse, String specialite) {
        super(nom, prenom, cin, adresse);
        this.specialite = specialite;
        this.etudiantsEncadres = new ArrayList<>();
        this.schedule = new HashMap<>();
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void ajouterEtudiantEncadre(Etudiant etudiant) {
        etudiantsEncadres.add(etudiant);
    }

    public void retirerEtudiantEncadre(Etudiant etudiant) {
        etudiantsEncadres.remove(etudiant);
    }

    public List<Etudiant> getEtudiantsEncadres() {
        return etudiantsEncadres;
    }

    public Map<LocalDateTime, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<LocalDateTime, String> schedule) {
        this.schedule = schedule;
    }
}
