package dao;

import data.Pilote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
