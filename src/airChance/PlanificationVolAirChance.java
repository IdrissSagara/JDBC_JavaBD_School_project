package airChance;

import dao.*;
import data.*;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PlanificationVolAirChance {


    /**
     * Afficher toutes les informations sur tous les spectacles.
     *
     * @param conn connexio n � la base de donn � es
     * @throws SQLException en cas d'erreur d'acc � s � la base de donn � es
     */


    public static void planificationVol(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("select NUMAVIONPASSAGER, AEROPORTDESTINATION, AEROPORTORIGINE  from VOLPASSAGER" +
                "    natural join AVIONPASSAGER" +
                "    natural join VOLPASSAGER ");

        List<VolPassager> listVolPassager = new ArrayList<VolPassager>();

        while (rs.next()) {
            VolPassager vp = new VolPassager();
            vp.setNumAvionPassager(rs.getInt(1));
            vp.setAeroportOrigine(rs.getString(2));
            vp.setAeroportDestination(rs.getString(3));
            listVolPassager.add(vp);

        }

        System.out.println("===Planificaiton d'un vol===\n");
        System.out.println("Liste des vols disponibles\n");
        for (VolPassager vp : listVolPassager) {
            System.out.print("Avino N°: " + vp.getNumAvionPassager() + " -" + " Origin :" + vp.getAeroportOrigine() + " -" + " Destination: " + vp.getAeroportDestination() + "\n");
        }

        String origine = DemandeSaisie.saisirString("\ndonner l'aeroport origine: ", 0, 26);
        String destination = DemandeSaisie.saisirString("donner l'aeroport destination: ", 0, 26);


        //int numAvion = LectureClavier.lireEntier("Choisir le numéro de l'avion du vol");
        int numAvion = DemandeSaisie.saisirInt("Choisir le numéro de l'avion qu'on va affecter au Vol", 0, listVolPassager.get(listVolPassager.size() - 1).getNumAvionPassager());

        rs = stmt.executeQuery("select max(numvolpassager) from volpassager");
        int numVol = 0;
        while (rs.next()) {
            numVol = rs.getInt(1);
        }

        numVol++;

        // demander toutes les infos necessaires à enreg un vol
        int dureeVol = DemandeSaisie.saisirInt("Saisissez la duree du vol (Limite fixeé entre 0 et 1000000)", 0, 1000000);
        int distanceVol = DemandeSaisie.saisirInt("Saisissez la distance du vol (Limite fixeé entre 0 et 200000) ", 0, 200000);
        int nbrPlaceAff = DemandeSaisie.saisirInt("Saisissez le nombre de place affaires disponibles (Limite fixeé entre 0 et 500) ", 0, 500);
        int nbrPlaceEco = DemandeSaisie.saisirInt("Saisissez le nombre de place economique disponibles (Limite fixeé entre 0 et 500)", 0, 500);
        int nbrPlacePre = DemandeSaisie.saisirInt("Saisissez le nombre de place en première disponibles (Limite fixeé entre 0 et 500)", 0, 500);
        Timestamp dateDepart;
        try {
            dateDepart = DemandeSaisie.saisirDateTimestamp("Saisissez la date de depart (Année limitée entre 2020 et 2025 )", 2020, 2025);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dateDepart = new Timestamp(System.currentTimeMillis());
        }

        //Creation de vol
        VolPassager.insertVol(conn, numVol, origine, destination, numAvion, dateDepart, dureeVol, distanceVol, nbrPlaceEco, nbrPlaceAff, nbrPlacePre);

        System.out.println("\nA ce stade, nous avons crée notre vol avec les informations necessaire .\n");

        System.out.println("===========================\n===========================\nNous allons désormais affecter du personnel à ce vol\n===========================\n===========================\n");

        //recuperer le nombre de pilotes necessaires pour piloter ce vol
        //proposer ensuite de les selectionner parmis ceux qualifiés pour le faire
        ModelDao modelDao = ModelDao.getInstance(conn);
        int nbPiloteNecessaire = modelDao.getNombrePiloteNecessaire(numVol);

        rs = Pilote.getPiloteQualifierVol(conn, numVol);

        System.out.println("Les pilotes qualifiés pour le vol : " + numVol);
        int maxIdPil = 0;
        String nomPil = "";
        String prenomPil = "";
        while (rs.next()) {
            int idPil = rs.getInt(1);
            if (idPil > maxIdPil) maxIdPil = idPil;
            System.out.print(idPil + "\t");
            nomPil = rs.getString(2);
            prenomPil = rs.getString(3);
            System.out.print(prenomPil + "\t");
            System.out.println(nomPil);
        }

        System.out.println("\n " + nbPiloteNecessaire + " " + " pilotes sont necessaires pour ce vol\n");
        for (int i = 0; i < nbPiloteNecessaire; i++) {

            int numPil = DemandeSaisie.saisirInt("Saisissez le numéro du pilote", 0, maxIdPil);

            //insert valeur dans pilote_vol
            PiloteVolDao piloteVolDao = PiloteVolDao.getInstance(conn);
            int insertedId = -1;
            try {
                insertedId = piloteVolDao.insert(numPil, numVol);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (insertedId == -1) {
                System.out.println("Une erreur fatale s'est produite lors de l'insertion du pilote_vol");
                return;
            }
        }

        //afficcher les hotesses
        System.out.println("Liste des hostesses disponibles \n");
        HotesseDao hotesseDao = HotesseDao.getInstance(conn);
        List<Hotesse> hl = null;
        try {
            hl = hotesseDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (hl == null) {
            System.out.println("Une erreur fatale s'est produite lors de la recuperation des hotesses");
            return;
        }

        int index = 1;
        for (Hotesse h : hl) {
            System.out.println(index++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
        }

        HotesseVolDao hotesseVolDao = HotesseVolDao.getInstance(conn);

        System.out.println("Saisissez 3 hotesses (le nombre 3 par choix de conception)");
        List<Hotesse> listChoixHotesse = new ArrayList<Hotesse>();
        for (int i = 0; i < 3; i++) {
            int choix = DemandeSaisie.saisirInt("Saisissez le numéro de l'hotesse proposer en haut ", 1, hl.size());
            listChoixHotesse.add(hl.get(choix));
            int numHotesse = hl.get(choix - 1).getNumHotesse();
            int res = hotesseVolDao.insert(numVol, numHotesse);

            if (res == -1) {
                return;
            }
        }
        System.out.println("------------------------------|--------------------------");
        System.out.println("Résumé de la planification du vol " + numVol + "\n");
        System.out.println("Détails du vol qu'on à planifier : ");
        System.out.println("Durée: " + dureeVol);
        System.out.println("Distance: " + distanceVol);
        System.out.println("Places affaires: " + nbrPlaceAff);
        System.out.println("Places eco: " + nbrPlaceEco);
        System.out.println("Places prémière: " + nbrPlacePre);
        System.out.println("Départ: " + dateDepart);


        // nous recuperons ici les pilotes selectionner
        rs = Pilote.getPiloteQualifierVol(conn, numVol);
        System.out.println("\nPilotes selectionné pour le vol : " + numVol + "\n");
        int id = 0;
        String nom = "";
        String prenom = "";
        while (rs.next()) {
            int idPil = rs.getInt(1);
            if (idPil > id) id = idPil;
            System.out.print(idPil + "\t");
            nom = rs.getString(2);
            prenom = rs.getString(3);
            System.out.print(nom + "\t");
            System.out.println(prenom);
        }

        System.out.println("Liste des hotesses choisis");
        index = 1;
        for (Hotesse h : listChoixHotesse) {
            System.out.println(index++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
        }

        System.out.println("\n Merci la planification est terminer.\n");
        System.out.println("------------------------------|--------------------------");
        rs.close();
        stmt.close();
    }

    /**
     * Modification de la Planification de vol
     *
     * @param conn
     */
    public static void modifierPlanificationVol(Connection conn) throws SQLException {

        System.out.println("=====Modification de la planification de vol=====\n");
        System.out.println("Liste des vols disponibles \n");

        // recuperation des vols disponibles
        // proposer ensuite de selectionner le vol qu'il veut modifier

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("select NUMAVIONPASSAGER, AEROPORTDESTINATION, AEROPORTORIGINE,NUMVOLPASSAGER  from VOLPASSAGER" +
                "    natural join AVIONPASSAGER");

        List<VolPassager> listVolPassager = new ArrayList<VolPassager>();

        while (rs.next()) {
            VolPassager vp = new VolPassager();
            vp.setNumAvionPassager(rs.getInt(1));
            vp.setAeroportOrigine(rs.getString(2));
            vp.setAeroportDestination(rs.getString(3));
            vp.setNumeroVolPassager(rs.getInt(4));
            listVolPassager.add(vp);

        }
        int index = 1;
        for (VolPassager vp : listVolPassager) {
            System.out.print(index++ + " -" + " Avion n°: " + vp.getNumAvionPassager() + "|" + " Origin :" + vp.getAeroportOrigine() + " |" + " Destination: " + vp.getAeroportDestination() + " | Vol n°: " + vp.getNumeroVolPassager() + "\n");
        }

        int choix = DemandeSaisie.saisirInt("\nChoisir le numero de vol à modifier dans la liste proposer en haut. \n", 1, listVolPassager.size() + 1);
        int numVol = listVolPassager.get(choix - 1).getNumeroVolPassager();

        int dureeVol = DemandeSaisie.saisirInt("Saisissez la duree du vol (Limite fixeé entre 0 et 1000000)", 0, 1000000);
        int distanceVol = DemandeSaisie.saisirInt("Saisissez la distance du vol (Limite fixeé entre 0 et 200000) ", 0, 200000);
        String origine = DemandeSaisie.saisirString("Saisissez l'aeroport origine (ex : France) ", 0, 26);
        String destination = DemandeSaisie.saisirString("Saisissez l'aeroport destination (ex : Mali) ", 0, 26);
        int nbrPlaceAff = DemandeSaisie.saisirInt("Saisissez le nombre de place affaires disponibles (Limite fixeé entre 0 et 500) ", 0, 500);
        int nbrPlaceEco = DemandeSaisie.saisirInt("Saisissez le nombre de place economiques disponibles (Limite fixeé entre 0 et 500)", 0, 500);
        int nbrPlacePre = DemandeSaisie.saisirInt("Saisissez le nombre de place en première disponibles (Limite fixeé entre 0 et 500)", 0, 500);
        int numeroAvion = DemandeSaisie.saisirInt("Saisissez ici le numero de l'Avion dans la liste proposer en haut ", 0, 1002999);
        Timestamp dateDepart;
        try {
            dateDepart = DemandeSaisie.saisirDateTimestamp("Saisissez la date de depart (Année limitée entre 2020 et 2025 )", 2020, 2025);
        } catch (ParseException e) {
            e.printStackTrace();
            dateDepart = new Timestamp(System.currentTimeMillis());
        }
        VolDao volDao = VolDao.getInstance(conn);
        try {
            volDao.updateUsingVolProcedure(dateDepart, dureeVol, distanceVol, origine,
                    destination, nbrPlaceEco, nbrPlaceAff, nbrPlacePre, numeroAvion, numVol);

            System.out.println("---------------Recapitulatif du VOL modifié---------------\n");
            System.out.println("Le vol n°: " + numVol + " a été modifié avec succes avec les valeurs suivantes: ");
            System.out.println("Duree de vol: " + dureeVol);
            System.out.println("Distance de vol: " + distanceVol);
            System.out.println("Aeroport origin: " + origine);
            System.out.println("Aeroport destination: " + destination);
            System.out.println("Nombre de place Eco: " + nbrPlaceEco);
            System.out.println("Nombre de place Affaire: " + nbrPlaceAff);
            System.out.println("Nombre de place Premiere: " + nbrPlacePre);
            System.out.println("Numero Avion attribué au vol: " + numeroAvion);
            System.out.println("\n---------------------------------------");

            // Nous allons modifier le pilote du vol

            System.out.println("\nNous allons prendre en compte la modification des pilotes associé au vol n°: " + numVol);

            PiloteDao piloteDao = PiloteDao.getInstance(conn);
            List<Pilote> piloteList = new ArrayList<>();

            if (piloteList.size() != 0) {

                try {
                    piloteList = piloteDao.getPiloteByNumVol(numVol);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int indexP = 1;
                for (Pilote p : piloteList) {
                    System.out.println(indexP++ + " - " + p.getPrenomPersonnelPilote() + " " + p.getNomPersonnelPilote() + " ");
                }
                System.out.println("\n" + piloteList.size() + " Pilote à été trouvé \n");

                int choixP = DemandeSaisie.saisirInt("Saisissez le numéro du pilote pour le modifier", 1, piloteList.size() + 1);
                int numPilote = piloteList.get(choixP - 1).getNumPilote();

                String nomPersonnelPilote = DemandeSaisie.saisirString("Saisir nom pilote", 0, 30);
                String prenomPersonnelPilote = DemandeSaisie.saisirString("Saisir prenom pilote", 0, 30);
                String ruePersonnelPilote = DemandeSaisie.saisirString("Saisir l'adresse du pilote", 0, 30);
                String paysPersonnelPilote = DemandeSaisie.saisirString("Saisir pays pilote", 0, 30);
                String localisationActuellePilote = DemandeSaisie.saisirString("Saisir localisation actuelle pilote", 0, 30);

                try {
                    piloteDao.updateUsingProcedure(nomPersonnelPilote,
                            prenomPersonnelPilote, ruePersonnelPilote,
                            paysPersonnelPilote, localisationActuellePilote, numPilote);

                    System.out.println("---------------Recapitulatif du PILOTE modifié---------------\n");
                    System.out.println("Le pilote n°" + numPilote + " a été modifié avec les valeurs suivantes: ");
                    System.out.println("Nom: " + nomPersonnelPilote);
                    System.out.println("Prenom: " + prenomPersonnelPilote);
                    System.out.println("Rue: " + ruePersonnelPilote);
                    System.out.println("Pays: " + paysPersonnelPilote);
                    System.out.println("Localisation: " + localisationActuellePilote);
                    System.out.println("\n---------------------------------------");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(piloteList.size() + " Pilote trouvé pour le vol: " + numVol);
            }


            System.out.println("\nNous allons aussi prendre en compte la modification des hotesses associé au vol n°: " + numVol);

            HotesseDao hotesseDao = HotesseDao.getInstance(conn);
            List<Hotesse> hotesseList = new ArrayList<>();

            try {
                hotesseList = hotesseDao.getHotessByNumVol(numVol);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int indexH = 1;
            for (Hotesse h : hotesseList) {
                System.out.println(indexH++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
            }
            System.out.println("\n" + hotesseList.size() + " Hotesse à été trouvé \n");

            int choixH = DemandeSaisie.saisirInt("Saisissez le numéro de l'hotesse pour le modifier", 0, hotesseList.size() + 1);
            int numHotesse = hotesseList.get(choixH - 1).getNumHotesse();

            String nomPersonnelHotesse = DemandeSaisie.saisirString("Saisir nom Hotesse", 0, 30);
            String prenomPersonnelHotesse = DemandeSaisie.saisirString("Saisir prenom Hotesse", 0, 30);
            String langueMaternelle = DemandeSaisie.saisirString("Saisir la langue maternelle de l'hotesse", 0, 30);
            String deuxiemeLangue = DemandeSaisie.saisirString("Saisir la 2 eme langue de l'hotesse", 0, 30);
            String troisiemeLangue = DemandeSaisie.saisirString("Saisir la 3 eme langue de l'hotesse", 0, 30);
            String localisationActuelleHotesse = DemandeSaisie.saisirString("Saisir la localisation actuelle de l'hotesse", 0, 70);
            int nbHeureTotal = DemandeSaisie.saisirInt("Nombre d'heure effectuer", 0, 2000);

            try {
                hotesseDao.updateUsingProcedure(nomPersonnelHotesse, prenomPersonnelHotesse, langueMaternelle, deuxiemeLangue, troisiemeLangue,
                        localisationActuelleHotesse, nbHeureTotal, numHotesse);

                System.out.println("---------------Recapitulatif de l'hotesse modifié---------------\n");
                System.out.println("L'Hotesse n°" + numHotesse + " a été modifié avec les valeurs suivantes: ");
                System.out.println("Nom: " + nomPersonnelHotesse);
                System.out.println("Prenom: " + prenomPersonnelHotesse);
                System.out.println("Langue Maternelle: " + langueMaternelle);
                System.out.println("Deuxieme Langue: " + deuxiemeLangue);
                System.out.println("Troisieme Langue: " + troisiemeLangue);
                System.out.println("La localisation actuel de l'hotesse: " + localisationActuelleHotesse);
                System.out.println("Nombre d'heures total de l'hotesse: " + nbHeureTotal);
                System.out.println("\n---------------------------------------");
                System.out.println("\n---------------------------------------");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void suppressionVol(Connection conn) throws SQLException {
        VolDao volDao = VolDao.getInstance(conn);
        List<VolPassager> volPassagerList = new ArrayList<>();

        try {
            volPassagerList = volDao.getVolByEtat(EtatVol.EN_SERVICE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int index = 1;
        for (VolPassager vl : volPassagerList) {
            System.out.print(index++ + " - " + vl.toString() + "\n\n");
        }

        int numOldVol = 0;
        int numNewVol = 0;
        boolean onError = false;

        do {
            if (onError) {
                System.out.println("Désolé les 2 valeurs ne peuvent pas être les mêmes");
            }
            int indexOldVol = DemandeSaisie.saisirInt("Saisissez le numéro de la ligne correspondante au vol pour le supprimer", 1, volPassagerList.size());
            numOldVol = volPassagerList.get(indexOldVol - 1).getNumeroVolPassager();

            int indexNewVol = DemandeSaisie.saisirInt("Saisissez le numéro de vol qui va recevoir les passageres après suppression", 1, volPassagerList.size());
            numNewVol = volPassagerList.get(indexNewVol - 1).getNumeroVolPassager();

            if (numOldVol == numNewVol)
                onError = true;
            else
                onError = false;

        } while (onError);

        VolPassager volUpdated = null;
        int updatedRows = 0;


        try {
            ShowClientsVol(conn, numOldVol, numNewVol);

            updatedRows = volDao.setEtatVolToSupprimer(numOldVol, numNewVol);
            System.out.println("\nEtat après  avoir supprimer le vol  ");
            ShowClientsVol(conn, numOldVol, numNewVol);

            if (updatedRows > 0) {
                volUpdated = volDao.getByNumVol(numOldVol);
                System.out.println(volUpdated.toString());
            } else {
                System.out.println("pas pu modifer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void ShowClientsVol(Connection conn, int numOldVol, int numNewVol) throws SQLException {
        System.out.println("\n");
        System.out.println("\tLes passagers du vol " + numOldVol + " (ancien)");
        ClientDao clientDao = ClientDao.getInstance(conn);
        List<Client> clients = clientDao.getClientByVolNum(numOldVol);
        int index = 1;
        for (Client cl : clients) {
            System.out.print(index++ + " - " + cl.toString() + "\n");
        }
        System.out.println("\t--Fin de liste--");

        System.out.println("\n");
        System.out.println("\tLes passagers du vol " + numNewVol + " (nouveau)");
        clientDao = ClientDao.getInstance(conn);
        clients = clientDao.getClientByVolNum(numNewVol);
        index = 1;
        for (Client cl : clients) {
            System.out.print(index++ + " - " + cl.toString() + "\n");
        }
        System.out.println("\t--Fin de liste");
    }

    public static void TerminerEtatVol(Connection conn) throws SQLException {

        VolDao volDao = VolDao.getInstance(conn);
        List<VolPassager> volPassagerList = new ArrayList<>();

        try {
            volPassagerList = volDao.getAllVol();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int index = 1;
        for (VolPassager vl : volPassagerList) {
            System.out.print(index++ + " -" + vl.toString() + "\n");
        }

        int indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro correspondant au vol  pour le terminer ", 1, volPassagerList.size());
        int numVol = volPassagerList.get(indexV - 1).getNumeroVolPassager();

        PiloteDao piloteDao = PiloteDao.getInstance(conn);

        try {
            showPilotesByVolNum(numVol, piloteDao);

            volDao.terminerUsingVolProcedure(numVol);

            System.out.println("\nChangement du nombre d'heures de pilote apres  avoir terminer le vole\n");
            showPilotesByVolNum(numVol, piloteDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showPilotesByVolNum(int numVol, PiloteDao piloteDao) throws SQLException {
        int index;
        List<Pilote> piloteList = new ArrayList<>();
        System.out.println("Les pilotes du vol " + numVol);
        piloteList = piloteDao.getPiloteByNumVol(numVol);
        index = 1;
        if (piloteList.size() == 0) {
            System.out.println("Aucun pilote trouvé");
        }
        for (Pilote pl : piloteList) {
            System.out.print(index++ + " - " + pl.toString() + "\n");
        }
        System.out.println("\t--Fin de liste");


    }


}
