package dao;

import data.Pilote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiloteDao {
    private static PiloteDao piloteDao = null;
    private Connection conn;

    private PiloteDao(Connection conn) {
        this.conn = conn;
    }

    public static PiloteDao getInstance(Connection conn) {
        if (piloteDao == null) {
            piloteDao = new PiloteDao(conn);
        }

        return piloteDao;
    }

    public List<Pilote> getAll() throws SQLException {
        String query = "select * from PILOTE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return makePiloteList(rs);
    }

    public Pilote getById(int numpilote) throws SQLException {
        String query = "Select * from pilote where NUMPILOTE = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, numpilote);

        ps.executeQuery();
        rs = ps.getResultSet();

        return mapResultSetToPilote(rs);
    }

    private Pilote mapResultSetToPilote(ResultSet rs) throws SQLException {
        Pilote p = null;

        if (rs.next()) {
            int i = 1;
            int numPilote = rs.getInt(i++);
            String nomPersonnelPilote = rs.getString(i++);
            String prenomPersonnelPilote = rs.getString(i++);
            String ruePersonnelPilote = rs.getString(i++);
            String paysPersonnelPilote = rs.getString(i++);
            String localisationActuellePilote = rs.getString(i++);

            p = new Pilote(numPilote, nomPersonnelPilote,
                    prenomPersonnelPilote, ruePersonnelPilote,
                    paysPersonnelPilote, localisationActuellePilote);
        }

        return p;
    }

    private List<Pilote> makePiloteList(ResultSet rs) throws SQLException {
        List<Pilote> pl = new ArrayList<>();
        Pilote p = null;

        while (rs.next()) {
            p = mapResultSetToPilote(rs);
            pl.add(p);
        }
        return pl;
    }

    public void updateUsingProcedure(String nomPersonnelPilote,
                                     String prenomPersonnelPilote,
                                     String ruePersonnelPilote,
                                     String paysPersonnelPilote,
                                     String localisationActuellePilote,
                                     int numPilote) throws SQLException {
        String pName = "{ call updatePilote(?, ?, ?, ?, ?, ?) }";
        CallableStatement cstmt = conn.prepareCall(pName);
        int i = 1;
        cstmt.setString(i++, nomPersonnelPilote);
        cstmt.setString(i++, prenomPersonnelPilote);
        cstmt.setString(i++, ruePersonnelPilote);
        cstmt.setString(i++, paysPersonnelPilote);
        cstmt.setString(i++, localisationActuellePilote);
        cstmt.setInt(i++, numPilote);

        cstmt.executeUpdate();
    }


}
