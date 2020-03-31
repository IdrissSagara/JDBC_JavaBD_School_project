package airChance;

import dao.ClientDao;
import dao.PlaceDao;
import dao.ReservationDao;
import dao.VolDao;
import data.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionReservationAirChance {

    public static void ConsultationCommandeClients(Connection conn) throws SQLException {

        ClientDao clientDaoDao = ClientDao.getInstance(conn);

        List<Client> clientList = new ArrayList<>();
        ;
        System.out.println("Liste des clients:\n");

        try {
            clientList = clientDaoDao.getAllClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int index = 1;
        for (Client clt : clientList) {
            System.out.print(index++ + " - " + clt.toString() + "\n");
        }

        int indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro correspondant au client  pour voir les commandes ", 1, clientList.size());
        int numclient = clientList.get(indexV - 1).getNumclient();

        ReservationDao reservationDao = ReservationDao.getInstance(conn);

        List<ReservationClient> reservationClientList = new ArrayList<>();

        System.out.println("Liste des comandes du client : " + numclient + "\n");
        List<Pilote> piloteList = new ArrayList<>();
        try {
            reservationClientList = reservationDao.getReservationsByNumClient(numclient);

            int indexR = 1;
            for (ReservationClient rc : reservationClientList) {
                System.out.println(indexR++ + " - " + rc.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void Reserver(Connection conn) throws SQLException {

        VolDao volDao = VolDao.getInstance(conn);

        List<VolPassager> volPassagerList = new ArrayList<>();

        System.out.println("\t\t\t\t !!!! Attention la base es sensible à la casse . format à respecter: ex: Mali - France");
        String depart = DemandeSaisie.saisirString(" Saisissez votre ville de depart ", 1, 30);
        String destination = DemandeSaisie.saisirString(" Saisissez votre ville d'arrivée ", 1, 30);

/**
 * selection de la destination et du depart
 */

        System.out.println("Les vols disponible et en service pour la destination: " + depart + " - " + destination + ":\n");

        volPassagerList = volDao.getVolByDepartDestination(depart, destination);

        if (volPassagerList.size() == 0) {
            System.out.println("\t\t\t Désolé Pas de vol disponible pour cette destination");
        }

/**
 * selection du vol
 */
        int index = 1;
        for (VolPassager vol : volPassagerList) {

            System.out.print(index++ + " - " + vol.toString() + "\n");
        }

        int indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro correspondant au vol ", 1, volPassagerList.size());
        int numVol = volPassagerList.get(indexV - 1).getNumeroVolPassager();

/**
 * selection de la place
 */
        PlaceDao placeDao = PlaceDao.getInstance(conn);
        List<Place> placeList = new ArrayList<>();
        System.out.println("\t\t\t\t\tPlace disponible au avions liée à ce vol\n");

        try {
            placeList = placeDao.getPlaceByNumVol(numVol);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (placeList.size() == 0) {
            System.out.println("\t\t\t Désolé Pas de places disponible pour ce vol");
        }


        index = 1;
        for (Place pl : placeList) {
            System.out.print(index++ + " - " + pl.toString() + "\n");
        }

        indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro de ligne correspondant à la place ", 1, placeList.size());
        int numeroPlace = placeList.get(indexV - 1).getNumPlace();

        System.out.println("num place" + numeroPlace);

/**
 * selection du client
 */
        ClientDao clientDao = ClientDao.getInstance(conn);
        List<Client> cLientList = new ArrayList<>();

        cLientList = clientDao.getAllClient();

        index = 1;
        for (Client pl : cLientList) {
            System.out.print(index++ + " - " + pl.toString() + "\n");
        }
        indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro de ligne correspondant au client ", 1, cLientList.size());
        int numeroClient = cLientList.get(indexV - 1).getNumclient();

        ReservationDao reservationDao = ReservationDao.getInstance(conn);

        int res = reservationDao.save(numVol, numeroClient, numeroPlace);
        System.out.println("Reservation inserted " + res);
    }

    public static void supprimerResa(Connection conn) throws Exception {
        ReservationDao reservationDao = ReservationDao.getInstance(conn);
        List<ReservationClient> reservationClientList = new ArrayList<>();
        reservationClientList = reservationDao.getAll();
        showResaFromDB(reservationClientList);
        int indexR = 0;

        indexR = DemandeSaisie.saisirInt("Saisissez le numéro de la ligne correspondant à la reservation", 1, reservationClientList.size());
        int numResa = reservationClientList.get(indexR).getNumRes();
        System.out.println("numero de reservation " + numResa);
        indexR = reservationDao.supprimer(numResa);
        System.out.println("resa supprimé " + indexR);

        reservationClientList = reservationDao.getAll();
        showResaFromDB(reservationClientList);
    }

    private static void showResaFromDB(List<ReservationClient> reservationClientList) {
        System.out.println("Liste des reservations");

        int indexR = 1;
        for (ReservationClient rc : reservationClientList) {
            System.out.println(indexR++ + " - " + rc.toString());
        }
    }

}
