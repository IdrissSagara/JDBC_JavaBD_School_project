package dao;

import data.Hotesse;

import java.sql.*;
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


    public int insertHotesse(String nomHotesse, String prenomHotesse, String langueMaternelle,
                             String deuxiemeLangue, String troisiemeLangue, String localisationActu, int nbhHotesse) throws SQLException {
        String query = "insert into HOTESSE ( NOMPERSONNELHOTESSE, PRENOMPERSONNELHOTESSE, LANGUEMATERNELLE, DEUXIEMELANGUE, TROISIEMELANGUE, LOCALISATIONACTUELLEHOTESSE, NBHEUREHOTESSE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ResultSet rs;

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        ps.setString(i++, nomHotesse);
        ps.setString(i++, prenomHotesse);
        ps.setString(i++, langueMaternelle);
        ps.setString(i++, deuxiemeLangue);
        ps.setString(i++, troisiemeLangue);
        ps.setString(i++, localisationActu);
        ps.setInt(i++, nbhHotesse);

        ps.executeUpdate();
        ps = conn.prepareStatement("select HOTESSE_SEQ.currval from dual");
        rs = ps.executeQuery();
        int ret = -1;
        if (rs.next()) {
            ret = (int) rs.getLong(1);
        }
        return ret;
    }

    public int supprimerHotesse(int numHotesse) throws SQLException {
        int operationResult = -1;
        String query = "DELETE from HOTESSE where NUMHOTESSE = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, numHotesse);
        int rows = ps.executeUpdate();

        if (rows > 0) {
            operationResult = rows;
        } else {
            System.out.println("erreur de supression");
        }
        return operationResult;
    }


    public List<Hotesse> getAll() throws SQLException {
        String query = "select * from hotesse";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return makeHotesseList(rs);
    }

    public List<Hotesse> getHotessByNumVol(int numeroVol) throws SQLException {
        String query = "Select NUMHOTESSE, NOMPERSONNELHOTESSE, PRENOMPERSONNELHOTESSE," +
                "LANGUEMATERNELLE,DEUXIEMELANGUE,TROISIEMELANGUE," +
                " LOCALISATIONACTUELLEHOTESSE, NBHEUREHOTESSE from HOTESSE_VOL NATURAL JOIN HOTESSE where NUMVOLPASSAGER = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, numeroVol);

        ps.executeQuery();
        rs = ps.getResultSet();

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

    public void updateUsingProcedure(String nomPersonnelHotesse,
                                     String prenomPersonnelHotesse,
                                     String langueMaternelle,
                                     String deuxiemeLangue,
                                     String troisiemeLangue,
                                     String localisationActuelleHotesse,
                                     int nbHeureHotesse,
                                     int numHotesse) throws SQLException {
        String pNameHotesse = "{ call updateHotesse(?, ?, ?, ?, ?, ?,?,?) }";
        CallableStatement cstmt = conn.prepareCall(pNameHotesse);
        int i = 1;
        cstmt.setString(i++, nomPersonnelHotesse);
        cstmt.setString(i++, prenomPersonnelHotesse);
        cstmt.setString(i++, langueMaternelle);
        cstmt.setString(i++, deuxiemeLangue);
        cstmt.setString(i++, troisiemeLangue);
        cstmt.setString(i++, localisationActuelleHotesse);
        cstmt.setInt(i++, nbHeureHotesse);
        cstmt.setInt(i++, numHotesse);

        cstmt.executeUpdate();
    }

}
