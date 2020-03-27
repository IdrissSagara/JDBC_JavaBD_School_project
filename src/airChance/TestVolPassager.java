package airChance;

import java.sql.Connection;
import java.sql.Timestamp;

import connection.BD_Connection;
import data.VolPassager;

public class TestVolPassager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn =BD_Connection.getConnection();
		
		VolPassager.insertVol(conn, 77, "Faso", "Waliso", 72, new Timestamp(System.currentTimeMillis()), 7, 7, 7, 7, 7);
	}

}
