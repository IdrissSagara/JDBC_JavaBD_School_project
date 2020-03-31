package airChance;

import connection.BD_Connection;
import dao.ReservationDao;
import data.ReservationClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestVolPassager {

	public static void main(String[] args) {

        Connection conn = BD_Connection.getConnection();

		/*VolDao volDao = VolDao.getInstance(conn);

		List<VolPassager> volPassagerList = new ArrayList<>();

		System.out.println("\t\t\t\t !!!! Attention la base es sensible à la casse . format à respecter: ex: Mali - France");
		String depart = DemandeSaisie.saisirString(" Saisissez votre ville de depart ", 1, 30);
		String destination = DemandeSaisie.saisirString(" Saisissez votre ville d'arrivée ", 1, 30);

*//**
         * selection de la destination et du depart
         *//*

		System.out.println("Les vols disponible et en service pour la destination: " + depart + " - " + destination + ":\n");

		try {
			volPassagerList = volDao.getVolByDepartDestination(depart, destination);

			if (volPassagerList.size() == 0) {
				System.out.println("\t\t\t Désolé Pas de vol disponible pour cette destination");
			}

*//**
         * selection du vol
         *//*
			int index = 1;
			for (VolPassager vol : volPassagerList) {

				System.out.print(index++ + " - " + vol.toString() + "\n");
			}

			int indexV = DemandeSaisie.saisirInt("\n Saisissez le numéro correspondant au vol ", 1, volPassagerList.size());
			int numVol = volPassagerList.get(indexV - 1).getNumeroVolPassager();

*//**
         * selection de la place
         *//*
			PlaceDao placeDao = PlaceDao.getInstance(conn);
			List<Place> placeList = new ArrayList<>();
			System.out.println("\t\t\t\t\tPlace disponible au avions liée à ce vol\n");

			placeList = placeDao.getPlaceByNumVol(numVol);


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

*//**
         * selection du client
         *//*
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("finally closed connection");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/


        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showResaFromDB(List<ReservationClient> reservationClientList) {
        System.out.println("Liste des reservations");

        int indexR = 1;
        for (ReservationClient rc : reservationClientList) {
            System.out.println(indexR++ + " - " + rc.toString());
        }
    }


}
