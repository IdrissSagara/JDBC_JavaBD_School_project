package data;

import java.sql.Timestamp;

public class ReservationClient {
    private String nom;
    private String prenom;
    private int numVol;
    private int numPlace;
    private String aeroportOrigine;
    private String aeroportDestination;
    private Timestamp dateDepart;
    private String typeClasse;
    private int PrixPlace;

    public ReservationClient(String nom, String prenom, int numVol, int numPlace, String aeroportOrigine, String getAeroportDestination, Timestamp dateDepart, String typeClasse, int prixPlace) {
        this.nom = nom;
        this.prenom = prenom;
        this.numVol = numVol;
        this.numPlace = numPlace;
        this.aeroportOrigine = aeroportOrigine;
        this.aeroportDestination = getAeroportDestination;
        this.dateDepart = dateDepart;
        this.typeClasse = typeClasse;
        PrixPlace = prixPlace;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumVol() {
        return numVol;
    }

    public void setNumVol(int numVol) {
        this.numVol = numVol;
    }

    public int getNumPlace() {
        return numPlace;
    }

    public void setNumPlace(int numPlace) {
        this.numPlace = numPlace;
    }

    public String getAeroportOrigine() {
        return aeroportOrigine;
    }

    public void setAeroportOrigine(String aeroportOrigine) {
        this.aeroportOrigine = aeroportOrigine;
    }

    public String getAeroportDestination() {
        return aeroportDestination;
    }

    public void setAeroportDestination(String aeroportDestination) {
        this.aeroportDestination = aeroportDestination;
    }

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getTypeClasse() {
        return typeClasse;
    }

    public void setTypeClasse(String typeClasse) {
        this.typeClasse = typeClasse;
    }

    public int getPrixPlace() {
        return PrixPlace;
    }

    public void setPrixPlace(int prixPlace) {
        PrixPlace = prixPlace;
    }

    @Override
    public String toString() {
        return "" +
                "nom: " + nom +
                " | prenom: " + prenom +
                " | numero Vol: " + numVol +
                " | numero Place: " + numPlace +
                " | aeroport Origine: " + aeroportOrigine +
                " | aeroport Destination: " + aeroportDestination +
                " | date Depart: " + dateDepart +
                " | Classe: " + typeClasse +
                " | Prix: " + PrixPlace;
    }
}
