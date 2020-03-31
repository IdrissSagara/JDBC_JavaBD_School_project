package airChance;

import dao.HotesseDao;
import dao.PiloteDao;
import data.Hotesse;
import data.Pilote;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonnelAirChance {

    public static void AjoutPersonnelPilote(Connection conn) throws SQLException {

        System.out.println("===Ajouter un nouveaux pilote===\n");

        String nomPilote = DemandeSaisie.saisirString("\nSaisir le nom du pilote: ", 0, 26);
        String prenomPilote = DemandeSaisie.saisirString("\nSaisir le prenom du pilote: ", 0, 26);
        String adressePilote = DemandeSaisie.saisirString("\nSaisir l'adresse du pilote: ", 0, 36);
        String paysPilote = DemandeSaisie.saisirString("\nSaisir le pays du pilote: ", 0, 26);
        String localisationActuelle = DemandeSaisie.saisirString("\nSaisir la localisation actuelle du pilote: ", 0, 26);


        //insert valeur dans pilote_vol
        PiloteDao piloteDao = PiloteDao.getInstance(conn);
        int insertedId = -1;
        List<Pilote> piloteList = new ArrayList<>();
        try {
            insertedId = piloteDao.insertPilote(nomPilote, prenomPilote, adressePilote, paysPilote, localisationActuelle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Liste de tous les pilotes:\n");

        piloteList = piloteDao.getPilote();

        int index = 1;
        for (Pilote pl : piloteList) {
            System.out.print(index++ + " - " + pl.toString() + "\n");
        }


        if (insertedId == -1) {
            System.out.println("Une erreur fatale s'est produite lors de l'insertion du pilote_vol");
            return;
        }
    }

    public static void AjoutPersonnelHotesse(Connection conn) throws SQLException {

        System.out.println("===Ajouter un nouveaux Hotesse===\n");

        String nomHotesse = DemandeSaisie.saisirString("\nSaisir le nom de l'hotesse: ", 0, 26);
        String prenomHotesse = DemandeSaisie.saisirString("\nSaisir le prenom de l'hotesse: ", 0, 26);
        String langueMaternelle = DemandeSaisie.saisirString("\nSaisir la langue maternelle: ", 0, 36);
        String deuxiemeLangue = DemandeSaisie.saisirString("\nSaisir la deuxieme langue: ", 0, 26);
        String troisiemeLangue = DemandeSaisie.saisirString("\nSaisir la troisieme langue: ", 0, 26);
        String localisationActu = DemandeSaisie.saisirString("\nSaisir la localisation actuelle de l'hotesse: ", 0, 26);
        int nbHeureHotesse = DemandeSaisie.saisirInt("\nSaisir le nombre d'heure de l'hotesse: ", 0, 10000);


        //insert valeur dans Hotesse
        HotesseDao hotesseDao = HotesseDao.getInstance(conn);
        int insertId = -1;
        List<Hotesse> hotesseList = new ArrayList<>();
        try {
            insertId = hotesseDao.insertHotesse(nomHotesse, prenomHotesse, langueMaternelle, deuxiemeLangue, troisiemeLangue, localisationActu, nbHeureHotesse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Liste de tous les Hotesse après insertion:\n");

        hotesseList = hotesseDao.getAll();

        int index = 1;
        for (Hotesse hL : hotesseList) {
            System.out.print(index++ + " - " + hL.toString() + "\n");
        }


        if (insertId == -1) {
            System.out.println("Une erreur fatale s'est produite lors de l'insertion du pilote_vol");
            return;
        }
    }
}
