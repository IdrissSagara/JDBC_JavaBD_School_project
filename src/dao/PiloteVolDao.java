package dao;

import java.sql.*;

public class PiloteVolDao {
    private static PiloteVolDao piloteVol = null;
    private Connection conn;

    private PiloteVolDao(Connection conn) {
        this.conn = conn;
    }

    public static PiloteVolDao getInstance(Connection conn) {
        if (piloteVol == null) {
            piloteVol = new PiloteVolDao(conn);
        }

        return piloteVol;
    }

    public int insert(int numPilote, int numVol) throws SQLException {
        String query = "insert into pilote_Vol (NUMPILOTE, NUMVOLPASSAGER) values (?, ?)";
        ResultSet rs;

        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        ps.setInt(i++, numPilote);
        ps.setInt(i++, numVol);

        ps.executeUpdate();

        ps = conn.prepareStatement("select PILVOL_SEQ.currval from dual");
        rs = ps.executeQuery();
        int ret = -1;
        if (rs.next()) {
            ret = (int) rs.getLong(1);
        }

        return ret;
    }
}
