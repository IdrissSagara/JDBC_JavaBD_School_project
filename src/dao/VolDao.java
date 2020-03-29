package dao;

import data.VolPassager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VolDao {
    private static VolDao volDao = null;
    private Connection conn;

    private VolDao(Connection conn) {
        this.conn = conn;
    }

    public static VolDao getInstance(Connection conn) {
        if (volDao == null) {
            volDao = new VolDao(conn);
        }

        return volDao;
    }

    // recuperation de tous les vols et l'ajouter dans une liste
    public List<VolPassager> getAllVol() throws SQLException {
        String query = "select NUMAVIONPASSAGER, AEROPORTDESTINATION, AEROPORTORIGINE  from VOLPASSAGER" +
                "    natural join AVIONPASSAGER" +
                "    natural join VOLPASSAGER ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return makeVolList(rs);
    }

    private List<VolPassager> makeVolList(ResultSet rs) throws SQLException {
        List<VolPassager> volPassagerList = new ArrayList<>();
        VolPassager v = null;
        while (rs.next()) {
            v = mapResultSetToVol(rs);
            volPassagerList.add(v);
        }
        return volPassagerList;
    }

    private VolPassager mapResultSetToVol(ResultSet rs) throws SQLException {
        VolPassager v = null;

        if (rs.next()) {

            int i = 1;
            int numeroVol = rs.getInt(i++);
            String aeroportOrigine = rs.getString(i++);
            String aeroportDestination = rs.getString(i++);

            v = new VolPassager(numeroVol, aeroportOrigine, aeroportDestination);

        }

        return v;
    }


    public void updateUsingVolProcedure(Timestamp DATEDEPART,
                                        int DUREEVOL,
                                        int DISTANCEVOL,
                                        String AEROPORTORIGINE,
                                        String AEROPORTDESTINATION,
                                        int NOMBREPLACEDISPOECO,
                                        int NOMBREPLACEDISPOEAFF,
                                        int NOMBREPLACEDISPOPRE,
                                        int NUMAVIONPASSAGER,
                                        int NUMVOLPASSAGER) throws SQLException {
        String pName = "{ call updateVol(?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?) }";
        CallableStatement cstmt = conn.prepareCall(pName);
        Date DATEENREGISTREMENTVOL = new Date(Calendar.getInstance().getTime().getTime());
        int i = 1;
        cstmt.setDate(i++, DATEENREGISTREMENTVOL);
        cstmt.setTimestamp(i++, DATEDEPART);
        cstmt.setInt(i++, DUREEVOL);
        cstmt.setInt(i++, DISTANCEVOL);
        cstmt.setString(i++, AEROPORTORIGINE);
        cstmt.setString(i++, AEROPORTDESTINATION);
        cstmt.setInt(i++, NOMBREPLACEDISPOECO);
        cstmt.setInt(i++, NOMBREPLACEDISPOEAFF);
        cstmt.setInt(i++, NOMBREPLACEDISPOPRE);
        cstmt.setInt(i++, NUMAVIONPASSAGER);
        cstmt.setInt(i++, NUMVOLPASSAGER);

        cstmt.executeUpdate();
    }


}
