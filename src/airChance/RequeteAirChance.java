package airChance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import data.*;
import connection.BD_Connection;

public class RequeteAirChance {

	
	/**
     * Afficher toutes les informations sur tous les spectacles.
     *
     * @param conn connexio n � la base de donn � es
     * @throws SQLException en cas d'erreur d'acc � s � la base de donn � es
     */



    public static void planificationVol(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        // Get a statement from the connection
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Execute the query
        System.out.println("===Planificaiton d'un vol===");

        
        String origine = DemandeSaisie.saisirString("donner l'aeroport origine: ", 0, 26);
        String destination = DemandeSaisie.saisirString("donner l'aeroport oridestinationgine: ", 0, 26);

        //recherche des avion disponibles
        ResultSet rs = stmt.executeQuery("select a.numavionpassager, nummodel from avionpassager a where a.numavionpassager NOT IN (select v.numavionpassager from volpassager v)");
        while (rs.next()) {
            System.out.print("  Num Avion : " + rs.getInt(1) + " ");
            System.out.println("  Num Modele : " + rs.getInt(2) + " ");
        }

        rs = stmt.executeQuery("select v.numavionpassager, max(v.datedepart) from avionpassager a \n"
                + "join volpassager v on v.numavionpassager = a.numavionpassager \n"
                + "group by a.numavionpassager\n"
                + "order by 1");

        List<VolPassager> listVolPassager = new ArrayList<VolPassager>();

        while (rs.next()) {
            VolPassager vp = new VolPassager();
            vp.setNumAvionPassager(rs.getInt(1));
            vp.setDate(rs.getDate(2));
            
            listVolPassager.add(vp);

        }
        for (int y = 0; y < listVolPassager.size(); y++) {
            VolPassager vp2 = listVolPassager.get(y);
            SimpleDateFormat formater = null;
            formater = new SimpleDateFormat("yyyy/mm/dd");

            ResultSet rs2 = stmt.executeQuery("select distinct v.numavionpassager, av.nummodel from volpassager v join avionpassager av on av.numavionpassager = v.numavionpassager  where v.numavionpassager = " + vp2.getNumAvionPassager());
            while (rs2.next()) {

                if (rs2.getInt(2) == 1) {
                    System.out.print("  Num Avion : " + rs2.getInt(1) + " ");
                    System.out.println("  Num Modele : " + rs2.getInt(3) + " ");
                }
            }
        }

        
        for (VolPassager vp: listVolPassager) {
        	System.out.println(vp.getNumAvionPassager());
        	System.out.println(vp.getNumeroVolPassager());
        }

        //int numAvion = LectureClavier.lireEntier("Choisir le numéro de l'avion du vol");
        int numAvion = DemandeSaisie.saisirInt("Choisir le numéro de l'avion du vol", 0, listVolPassager.get(listVolPassager.size() - 1).getNumAvionPassager());

        rs = stmt.executeQuery("select max(numvolpassager) from volpassager");
        int numVol = 0;
        while (rs.next()) {
            numVol = rs.getInt(1);
        }

        numVol++;
        
        // demander toutes les infos necessaires à enreg un vol
        /*insertVol(Connection conn, int numVol, String origine, String destination, int numAvion, 
        		Timestamp dateDepart, int dureeVol, int distanceVol, int nbrPlaceEco, int nbrPlaceAff, int nbrPlacePre)*/
        int dureeVol = DemandeSaisie.saisirInt("Saisissez la duree du vol", 0, 10000);
        int distanceVol = DemandeSaisie.saisirInt("Saisissez la distance du vol", 0, 200000);
        int nbrPlaceAff = DemandeSaisie.saisirInt("Saisissez le nombre de place affaires disponibles", 0, 200);
        int nbrPlaceEco = DemandeSaisie.saisirInt("Saisissez le nombre de place economique disponibles", 0, 200);
        int nbrPlacePre = DemandeSaisie.saisirInt("Saisissez le nombre de place en première disponibles", 0, 200);
        Timestamp dateDepart;
		try {
			dateDepart = DemandeSaisie.saisirDateTimestamp("Saisissez la date de depart", 2020, 2023);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dateDepart = new Timestamp(System.currentTimeMillis());
		}

        VolPassager.insertVol(conn, numVol, origine, destination, numAvion, dateDepart, dureeVol, distanceVol, nbrPlaceEco, nbrPlaceAff, nbrPlacePre);

        System.out.println("\nA ce stade, nous avons crée notre vol avec les informations suivantes : Aeroport Origine, Aeroport Destination ....\n\nNous allons désormais affecter du personnel à ce vol");

        System.out.println("===========================\n===========================\n===========================\nA partir d'ici nous allons gérer l'aspect concurentiel\n===========================\n===========================\n===========================\n");

        rs = stmt.executeQuery("select p.numpilote, nompersonnelPilote FROM VOLPASSAGER NATURAL JOIN PILOTE NATURAL JOIN AVIONPASSAGER where numavion");
        //SELECT * FROM VOLPASSAGER NATURAL JOIN PILOTE NATURAL JOIN AVIONPASSAGER

        while (rs.next()) {
            System.out.print("  Num Pilote : " + rs.getInt(1) + " ");
            System.out.println("  Nom Pilote : " + rs.getString(2) + " ");
        }

        rs = stmt.executeQuery("select p.numpilote, p.nompersonnelPilote , max(v.datedepart) from pilote p \n"
                + "join volpassager v on v.numvolpassager = p.numVolPassager\n"
                + "group by p.numpilote, p.nompersonnel");

        while (rs.next()) {
            System.out.print("  Num Pilote : " + rs.getInt(1) + " ");
            System.out.println("  Nom Pilote : " + rs.getString(2) + " ");
        }

        int numPilote = LectureClavier.lireEntier("Choisir le pilote à affecter à ce vol");

        rs = stmt.executeQuery("select h.numhotesse, nompersonnel from Hotesse");

        while (rs.next()) {
            System.out.print("  Num Hotesse : " + rs.getInt(1) + " ");
            System.out.println("  Nom Hotesse : " + rs.getString(2) + " ");
        }

        rs = stmt.executeQuery("select h.numhotesse, h.nompersonnelHotesse, max(v.datedepart) from volpassager v \n"
                + "join volpassager v on v.numVolPassager = hp.numVolPassager\n"
                + "group by h.numhotesse, h.nompersonnel");
        while (rs.next()) {
            System.out.print("  Num Hotesse : " + rs.getInt(1) + " ");
            System.out.println("  Nom Hotesse : " + rs.getString(2) + " ");
        }
        int numHotesse = LectureClavier.lireEntier("Choisir l'hotesse à affecter à ce vol");

        Pilote p = new Pilote();
        stmt.executeUpdate(p.insertPilote(numPilote, numVol));

        Hotesse h = new Hotesse();
        stmt.executeUpdate(h.insertHotesse(numHotesse, numVol));

        System.out.println("Le vol crée est le suivant :");
        rs = stmt.executeQuery("select aeroportorigine, aeroportdestination, numavionpassager from volpassager where numvolpassager = " + numVol);
        while (rs.next()) {
            System.out.println("  Aeroport Destination : " + rs.getString(1) + " ");
            System.out.println("  Aeroport Origine : " + rs.getString(2) + " ");
            System.out.println("  Numero Avion : " + rs.getInt(3) + " ");
        }

        System.out.println("Le personnel de ce vol est : ");
        rs = stmt.executeQuery("select numpilote from pilote where numvolpassager = " + numVol);
        while (rs.next()) {
            System.out.println("  Numéro Pilote : " + rs.getInt(1) + " ");
        }

        rs = stmt.executeQuery("select numhotesse from hotesse where numvolpassager = " + numVol);
        while (rs.next()) {
            System.out.println("  Numéro Hotesse : " + rs.getInt(1) + " ");
        }

        rs = stmt.executeQuery("Select numpilote, nompersonnel, localisationActuel from pilote where numpilote = " + numPilote);

        while (rs.next()) {
            System.out.print("NumPilote : " + rs.getInt(1) + " ");
            System.out.println("Nom Personnel : " + rs.getString(2) + " ");
            System.out.println("Localisation Actuelle : " + rs.getString(3) + " ");
        }

        // Close the result set, statement and the connection 
        rs.close();
        stmt.close();
    }

}
