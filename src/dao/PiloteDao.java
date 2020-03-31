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

    public int insertPilote(String nomPilote, String prenomPilote, String adressePilote,
                            String paysPilote, String localisationActuelle) throws SQLException {
        String query = "insert into PILOTE ( NOMPERSONNELPILOTE, PRENOMPERSONNELPILOTE, RUEPERSONNELPILOTE, PAYSPERSONNELPILOTE, LOCALISATIONACTUELLEPILOTE) " +
                "VALUES (?, ?, ?, ?, ?)";
        ResultSet rs;

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        ps.setString(i++, nomPilote);
        ps.setString(i++, prenomPilote);
        ps.setString(i++, adressePilote);
        ps.setString(i++, paysPilote);
        ps.setString(i++, localisationActuelle);

        ps.executeUpdate();
        ps = conn.prepareStatement("select PILOTE_SEQ.currval from dual");
        rs = ps.executeQuery();
        int ret = -1;
        if (rs.next()) {
            ret = (int) rs.getLong(1);
        }
        return ret;
    }


    public List<Pilote> getPilote() throws SQLException {
        String query = "select * from PILOTE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return makePiloteList(rs, false);
    }


    public List<Pilote> getPiloteByNumVol(int numeroVol) throws SQLException {
        String query = "Select NUMPILOTE, NOMPERSONNELPILOTE, PRENOMPERSONNELPILOTE, RUEPERSONNELPILOTE," +
                "PAYSPERSONNELPILOTE,LOCALISATIONACTUELLEPILOTE, NBHEUREPILOTE " +
                "from PILOTE_VOL NATURAL JOIN PILOTE natural join QUALIFICATIONMODELAVION where NUMVOLPASSAGER = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, numeroVol);

        ps.executeQuery();
        rs = ps.getResultSet();

        return makePiloteList(rs, true);
    }

    private Pilote mapResultSetToPilote(ResultSet rs, boolean advanved) throws SQLException {
        Pilote p = null;

        if (rs != null) {
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

            if (advanved) {
                int nbhvol = rs.getInt(i++);
                p.setNombreHeurePilote(nbhvol);
            }
        }

        return p;
    }

    private List<Pilote> makePiloteList(ResultSet rs, boolean advanved) throws SQLException {
        List<Pilote> pl = new ArrayList<>();
        Pilote p = null;

        while (rs.next()) {
            p = mapResultSetToPilote(rs, advanved);
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
