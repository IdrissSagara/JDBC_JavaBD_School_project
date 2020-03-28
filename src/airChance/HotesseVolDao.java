package airChance;

import java.sql.*;

public class HotesseVolDao {

    private static HotesseVolDao hotessevoldao = null;
    private Connection conn;

    private HotesseVolDao(Connection conn) {
        this.conn = conn;
    }

    public static HotesseVolDao getInstance(Connection conn) {
        if (hotessevoldao == null) {
            hotessevoldao = new HotesseVolDao(conn);
        }
        return hotessevoldao;
    }

    public int insert(int numVol, int numHotesse) throws SQLException {
        String roquette = "insert into HOTESSE_VOL (NUMVOLPASSAGER, NUMHOTESSE) " +
                "values (?, ?)";
        ResultSet rs;

        PreparedStatement ps = conn.prepareStatement(roquette, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        ps.setInt(i++, numVol);
        ps.setInt(i++, numHotesse);
        ps.executeUpdate();

        ps = conn.prepareStatement("select HOT_VOL.currval from dual");
        rs = ps.executeQuery();

        int ret = -1;
        if (rs.next()) {
            ret = (int) rs.getLong(1);
        }

        return ret;
    }
}
