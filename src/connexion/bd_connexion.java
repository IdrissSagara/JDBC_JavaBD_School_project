package connexion;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class bd_connexion {
    private static Connection conn;
    private static String USER = "";
    private static String PASSWD = "";

    // static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
    public bd_connexion() throws SQLException {
    }

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "sagarai", "Sagara1992");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}
