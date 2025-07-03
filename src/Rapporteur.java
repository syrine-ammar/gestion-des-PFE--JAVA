package trial;

import java.util.*;
import java.time.LocalDateTime;


public class Rapporteur extends Personne {
 

    public Rapporteur(String nom, String prenom, int cin, String adresse) {
        super(nom, prenom, cin, adresse);

    }

    public Rapporteur(Enseignant en) {
        super(en.getNom(), en.getPrenom(), en.getCin(), en.getAdresse());
 
    }

}
