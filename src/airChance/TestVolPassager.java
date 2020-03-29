package airChance;

import connection.BD_Connection;
import dao.HotesseDao;
import data.Hotesse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		//HotesseDao hotesseDao = HotesseDao.getInstance(conn);
		/*try {
			List<Hotesse> hl = hotesseDao.getAll();
			for (Hotesse h: hl) {
				System.out.println(h.getNumHotesse() + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		/*HotesseVolDao hotesseVolDao = HotesseVolDao.getInstance(conn);
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
		}*/




/*		PiloteDao piloteDao = PiloteDao.getInstance(conn);
		List<Pilote> piloteList = new ArrayList<>();

		try {
			piloteList =  piloteDao.getPiloteByNumVol(144);
			System.out.println("size " + piloteList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		int indexP = 1;
		for (Pilote p : piloteList) {
			System.out.println(indexP++ + " - " + p.getPrenomPersonnelPilote() + " " + p.getNomPersonnelPilote());
		}
	}*/
		HotesseDao hotesseDao = HotesseDao.getInstance(conn);
		List<Hotesse> hotesseList = new ArrayList<>();

		try {
			hotesseList = hotesseDao.getHotessByNumVol(145);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int index = 1;
		for (Hotesse h : hotesseList) {
			System.out.println(index++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
		}

		int indexH = DemandeSaisie.saisirInt("Saisissez le numéro de l'hotesse pour le modifier", 1, hotesseList.size());
		int numHotesse = hotesseList.get(indexH - 1).getNumHotesse();
		System.out.println("num hotesse" + numHotesse);
	}

}
