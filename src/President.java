package trial;

import java.util.*;
import java.time.LocalDateTime;

public class President extends Personne {


    public President(String nom, String prenom, int cin, String adresse) {
        super(nom, prenom, cin, adresse);

    }

    public President(Enseignant en) {
        super(en.getNom(), en.getPrenom(), en.getCin(), en.getAdresse());

    }


}

