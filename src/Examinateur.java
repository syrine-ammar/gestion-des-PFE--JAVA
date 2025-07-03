package trial;

import java.util.*;
import java.time.LocalDateTime;

public class Examinateur extends Personne {
   

    public Examinateur(String nom, String prenom, int cin, String adresse) {
        super(nom, prenom, cin, adresse);
   
    }

    public Examinateur(Enseignant en) {
        super(en.getNom(), en.getPrenom(), en.getCin(), en.getAdresse());
  
    }

}
