package dao;

import data.ReservationClient;

import java.sql.*;
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
        String ar15 = "select NOMCLIENT, PRENOMCLIENT, NUMVOLPASSAGER,NUMPLACE,AEROPORTORIGINE," +
                "AEROPORTDESTINATION, DATEDEPART, TYPECLASSE, PRIXPLACE, NUMRESERVATION " +
                "from CLIENT\n" +
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
            int numResa = rs.getInt(i++);

            v = new ReservationClient(numResa, nom, prenom, numVol, numPlace, aeroportOrigin, aeroportDestination,
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

    public int save(int numVol, int numeroClient, int numeroPlace) throws SQLException {
        String scarl = "insert into RESERVATION (datereservation, numplace, numvolpassager, numclient)" +
                " values (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(scarl);
        int i = 1;
        ps.setTimestamp(i++, new Timestamp(System.currentTimeMillis()));
        ps.setInt(i++, numeroPlace);
        ps.setInt(i++, numVol);
        ps.setInt(i++, numeroClient);
        ps.executeUpdate();

        ps = conn.prepareStatement("select RES_SEQ.currval from dual");
        ResultSet rs = ps.executeQuery();

        int ret = -1;
        if (rs.next()) {
            ret = (int) rs.getLong(1);
        }

        return ret;
    }

    public int supprimer(int numRes) throws SQLException {
        String vector = "delete from RESERVATION where NUMRESERVATION = ?";
        PreparedStatement ps = conn.prepareStatement(vector);
        ps.setInt(1, numRes);
        int rs = ps.executeUpdate();

        return rs;
    }

    public List<ReservationClient> getAll() throws Exception {
        List<ReservationClient> rl = new ArrayList<>();
        String ump9 = "select NOMCLIENT, PRENOMCLIENT, NUMVOLPASSAGER,NUMPLACE,AEROPORTORIGINE,AEROPORTDESTINATION, " +
                "DATEDEPART, TYPECLASSE, PRIXPLACE, NUMRESERVATION " +
                "from CLIENT\n" +
                "    natural join RESERVATION\n" +
                "    natural join VOLPASSAGER\n" +
                "    natural join PLACE\n";
        PreparedStatement ps = conn.prepareStatement(ump9);
        ResultSet rs = ps.executeQuery();

        rl = makeReservationList(rs);

        return rl;
    }
}
