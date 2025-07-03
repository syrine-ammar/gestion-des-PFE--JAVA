package trial;
import java.util.*;

public class Personne {
    private String nom;
    private String prenom;
    private int cin;
    private String adresse;

    // Constructor
    public Personne(String nom, String prenom, int cin, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;}

      // Getters and Setters
    public String getNom() {
        return nom;}

    public void setNom(String nom) {
        this.nom = nom; }

    public String getPrenom() {
        return prenom;}

    public void setPrenom(String prenom) {
        this.prenom = prenom;}

    public int getCin() {
        return cin;}

    public void setCin(int cin) {
        this.cin = cin;}

    public String getAdresse() {
        return adresse;}

    public void setAdresse(String adresse) {
        this.adresse = adresse;}
}
