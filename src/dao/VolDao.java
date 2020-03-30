package dao;

import data.EtatVol;
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
        String query = "select NUMAVIONPASSAGER, NUMVOLPASSAGER, AEROPORTORIGINE,AEROPORTDESTINATION, ETATVOL,TERMINER  from VOLPASSAGER" +
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

        if (rs != null) {

            int i = 1;
            int numeroAvion = rs.getInt(i++);
            int numeroVol = rs.getInt(i++);
            String aeroportOrigine = rs.getString(i++);
            String aeroportDestination = rs.getString(i++);
            String etatVol = rs.getString(i++);
            String etatVolTerminer = rs.getString(i++);


            v = new VolPassager(numeroAvion, numeroVol, aeroportOrigine, aeroportDestination, etatVol, etatVolTerminer);

        }

        return v;
    }


    /**
     * Procedure de mise a jour d'un vol
     *
     * @param DATEDEPART
     * @param DUREEVOL
     * @param DISTANCEVOL
     * @param AEROPORTORIGINE
     * @param AEROPORTDESTINATION
     * @param NOMBREPLACEDISPOECO
     * @param NOMBREPLACEDISPOEAFF
     * @param NOMBREPLACEDISPOPRE
     * @param NUMAVIONPASSAGER
     * @param NUMVOLPASSAGER
     * @throws SQLException
     */
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

    public int setEtatVolToSupprimer(int oldNumVol, int newNumVol) throws SQLException {
        int operationResult = -1;
        String query = "UPDATE volpassager " +
                "SET ETATVOL='SUPPRIMER' " +
                "where numvolpassager = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, oldNumVol);
        int rows = ps.executeUpdate();

        if (rows > 0) {
            // reaffecter les passager apres que le vol soit supprimé
            reaffecterPassagers(oldNumVol, newNumVol);
            operationResult = rows;
        }

        return operationResult;
    }

    private void reaffecterPassagers(int oldNumVol, int newNumVol) throws SQLException {
        String pName = "{ call reaffecterPassagers(?, ?) }";
        CallableStatement cstmt = conn.prepareCall(pName);
        cstmt.setInt(1, oldNumVol);
        cstmt.setInt(2, newNumVol);

        cstmt.executeUpdate();
    }

    public VolPassager getByNumVol(int numVol) throws SQLException {
        VolPassager v = null;
        String roquette = "select NUMAVIONPASSAGER, NUMVOLPASSAGER, AEROPORTORIGINE,AEROPORTDESTINATION, ETATVOL,TERMINER" +
                " from VOLPASSAGER where NUMVOLPASSAGER = ?";
        PreparedStatement ps = conn.prepareStatement(roquette);
        ResultSet rs = null;

        ps.setInt(1, numVol);
        rs = ps.executeQuery();

        if (rs.next())
            v = mapResultSetToVol(rs);

        return v;
    }

    public void terminerUsingVolProcedure(int numVol) throws SQLException {
        String pName = "{ call terminerVolPassager(?) }";
        CallableStatement cstmt = conn.prepareCall(pName);
        cstmt.setInt(1, numVol);

        cstmt.executeUpdate();
    }

    public List<VolPassager> getVolByEtat(EtatVol etat) throws SQLException {
        List<VolPassager> volList = new ArrayList<>();
        String rpg = "select NUMAVIONPASSAGER, NUMVOLPASSAGER, AEROPORTORIGINE,AEROPORTDESTINATION, ETATVOL,TERMINER" +
                " from VOLPASSAGER where ETATVOL = ?";
        PreparedStatement ps = conn.prepareStatement(rpg);
        ps.setString(1, etat.getValue());
        ResultSet rs = ps.executeQuery();
        volList = makeVolList(rs);

        return volList;
    }
}
