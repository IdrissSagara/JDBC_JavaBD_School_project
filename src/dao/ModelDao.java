package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDao {

    private static ModelDao modelDao = null;
    private Connection conn;

    private ModelDao(Connection conn) {
        this.conn = conn;
    }

    public static ModelDao getInstance(Connection conn) {
        if (modelDao == null) {
            modelDao = new ModelDao(conn);
        }
        return modelDao;
    }

    public int getNombrePiloteNecessaire(int numVol) throws SQLException {
        String roquette = "select NBPILOTENECCESSAIRE from MODELE\n" +
                "    natural join AVIONPASSAGER\n" +
                "    natural join VOLPASSAGER where NUMVOLPASSAGER = ?";
        PreparedStatement ps = conn.prepareStatement(roquette);
        ps.setInt(1, numVol);

        int nb = -1;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        }

        return nb;
    }
}
