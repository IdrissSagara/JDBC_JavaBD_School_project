package dao;

import data.Hotesse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotesseDao {
    private static HotesseDao hotesseDao = null;
    private Connection conn;

    private HotesseDao(Connection conn) {
        this.conn = conn;
    }

    public static HotesseDao getInstance(Connection conn) {
        if (hotesseDao == null) {
            hotesseDao = new HotesseDao(conn);
        }
        return hotesseDao;
    }

    public List<Hotesse> getAll() throws SQLException {
        String query = "select * from hotesse";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return makeHotesseList(rs);
    }

    /**
     * Transforme un resultSet en Objet java Hotesse
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Hotesse mapResultSetToHotesse(ResultSet rs) throws SQLException {
        Hotesse h = null;

        if (rs != null) {
            int i = 1;
            int numHotesse = rs.getInt(i++);
            String nomPersonnelHotesse = rs.getString(i++);
            String prenomPersonnelHotesse = rs.getString(i++);
            String langueMaternelle = rs.getString(i++);
            String deuxiemeLangue = rs.getString(i++);
            String troisiemeLangue = rs.getString(i++);
            String localisationActuelleHotesse = rs.getString(i++);
            int nbHeureTotal = rs.getInt(i++);

            h = new Hotesse(numHotesse, nomPersonnelHotesse, prenomPersonnelHotesse, langueMaternelle,
                    deuxiemeLangue, troisiemeLangue, nbHeureTotal, localisationActuelleHotesse);
        }

        return h;
    }

    /**
     * Trasforme un ResultSet de GetAll en list d'Hotesse
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Hotesse> makeHotesseList(ResultSet rs) throws SQLException {
        List<Hotesse> hl = new ArrayList<>();
        Hotesse h = null;

        while (rs.next()) {
            h = mapResultSetToHotesse(rs);
            hl.add(h);
        }
        return hl;
    }

}
