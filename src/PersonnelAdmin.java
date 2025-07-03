package trial;

import java.util.*;
public class PersonnelAdmin extends Personne {

    private List<ProjetPFE> projetsPlanifies;


    public PersonnelAdmin(String nom, String prenom, int cin, String adresse) {
        super(nom, prenom, cin, adresse);
        this.projetsPlanifies = new ArrayList<>();
    }

    public void planifierProjet(ProjetPFE projet) {
        projetsPlanifies.add(projet);}

    public List<ProjetPFE> getProjetsPlanifies() {
        return projetsPlanifies;
    }

}
