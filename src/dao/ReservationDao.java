package dao;

import data.ReservationClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    private static ReservationDao reservationDao = null;
    private Connection conn;

    private ReservationDao(Connection conn) {
        this.conn = conn;
    }

    public static ReservationDao getInstance(Connection conn) {
        if (reservationDao == null) {
            reservationDao = new ReservationDao(conn);
        }

        return reservationDao;
    }

    public List<ReservationClient> getReservationsByNumClient(int numClient) throws Exception {
        List<ReservationClient> reservations = null;
        String ar15 = "select NOMCLIENT, PRENOMCLIENT, NUMVOLPASSAGER,NUMPLACE,AEROPORTORIGINE,AEROPORTDESTINATION, DATEDEPART, TYPECLASSE, PRIXPLACE from CLIENT\n" +
                "    natural join RESERVATION\n" +
                "    natural join VOLPASSAGER\n" +
                "    natural join PLACE\n" +
                "where NUMCLIENT = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(ar15);

        ps.setInt(1, numClient);
        ps.executeQuery();
        rs = ps.getResultSet();
        reservations = makeReservationList(rs);

        return reservations;
    }

    private ReservationClient mapResultSetToReservation(ResultSet rs) throws Exception {
        ReservationClient v = null;

        if (rs != null) {

            int i = 1;
            String nom = rs.getString(i++);
            String prenom = rs.getString(i++);
            int numVol = rs.getInt(i++);
            int numPlace = rs.getInt(i++);
            String aeroportOrigin = rs.getString(i++);
            String aeroportDestination = rs.getString(i++);
            Timestamp dateDepart = rs.getTimestamp(i++);
            String typeClasse = rs.getString(i++);
            int prixPlace = rs.getInt(i++);

            v = new ReservationClient(nom, prenom, numVol, numPlace, aeroportOrigin, aeroportDestination,
                    dateDepart, typeClasse, prixPlace);
        }

        return v;
    }

    private List<ReservationClient> makeReservationList(ResultSet rs) throws Exception {
        List<ReservationClient> pl = new ArrayList<>();
        ReservationClient p = null;

        while (rs.next()) {
            p = mapResultSetToReservation(rs);
            pl.add(p);
        }
        return pl;
    }

}
