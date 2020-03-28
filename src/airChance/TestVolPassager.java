package airChance;

import connection.BD_Connection;
import dao.HotesseDao;

import java.sql.Connection;
import java.sql.SQLException;

public class TestVolPassager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = BD_Connection.getConnection();

		//VolPassager.insertVol(conn, 77, "Faso", "Waliso", 72, new Timestamp(System.currentTimeMillis()), 7, 7, 7, 7, 7);
		//Pilote.getAllPilote(conn);
		/*PiloteVolDao piloteVolDao = PiloteVolDao.getInstance(conn);
		try {
			int v = piloteVolDao.insert(10, 145);
			System.out.println("valeur returnee " + v);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/

		HotesseDao hotesseDao = HotesseDao.getInstance(conn);
		/*try {
			List<Hotesse> hl = hotesseDao.getAll();
			for (Hotesse h: hl) {
				System.out.println(h.getNumHotesse() + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		HotesseVolDao hotesseVolDao = HotesseVolDao.getInstance(conn);
		try {
			int id = hotesseVolDao.insert(150, 29);
			System.out.println("valeur de id retournée" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
