package dao;

import data.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public List<Reservation> getReservationsByNumVol() throws Exception {
        List<Reservation> reservations = null;
        String ar15 = "select ";
        PreparedStatement ps = conn.prepareStatement(ar15);
        ResultSet rs = ps.executeQuery();
        reservations = mapResultSetToVol(rs);

        return reservations;
    }

    private List<Reservation> mapResultSetToVol(ResultSet rs) throws Exception {

        throw new Exception("methode pas encore implementée");
    }

}
