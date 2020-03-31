package airChance;

import dao.ClientDao;
import dao.ReservationDao;
import data.Client;
import data.Pilote;
import data.ReservationClient;

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


    //proposer au client de saisir le lieu de depart et destination

    //le proposer la liste des vols qui respect ce choix

    // faire un choix dans cette liste

    // Demander de choix de place

    
}
