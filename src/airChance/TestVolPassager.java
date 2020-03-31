package airChance;

import connection.BD_Connection;

import java.sql.Connection;

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
		/*HotesseDao hotesseDao = HotesseDao.getInstance(conn);
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
		System.out.println("num hotesse" + numHotesse);*/
/*		VolDao volDao = VolDao.getInstance(conn);
		List<VolPassager> volPassagerList = new ArrayList<>();

		try {
			volPassagerList = volDao.getVolByEtat(EtatVol.EN_SERVICE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int index = 1;
		for (VolPassager vl : volPassagerList) {
			System.out.print(index++ + " - " + vl.toString() + "\n");
		}

		int numOldVol = 0;
		int numNewVol = 0;
		boolean onError = false;

		do {
			if (onError) {
				System.out.println("Désolé les 2 valeurs ne peuvent pas être les mêmes");
			}
			int indexOldVol = DemandeSaisie.saisirInt("Saisissez le numéro de vol pour le supprimer", 1, volPassagerList.size());
			numOldVol = volPassagerList.get(indexOldVol - 1).getNumeroVolPassager();

			int indexNewVol = DemandeSaisie.saisirInt("Saisissez le numéro du nouveau vol", 1, volPassagerList.size());
			numNewVol = volPassagerList.get(indexNewVol - 1).getNumeroVolPassager();

			if (numOldVol == numNewVol)
				onError = true;
			else
				onError = false;

		} while (onError);

		System.out.println("num vol: " + numOldVol);
		VolPassager volUpdated = null;
		int updatedRows = 0;


		try {
			ShowClientsVol(conn, numOldVol, numNewVol);

			updatedRows = volDao.setEtatVolToSupprimer(numOldVol, numNewVol);
			System.out.println("Etat après setEtatVolToSupprimer");
			ShowClientsVol(conn, numOldVol, numNewVol);

			if (updatedRows > 0) {
				volUpdated = volDao.getByNumVol(numOldVol);
				System.out.println(volUpdated.toString());
			} else {
				System.out.println("pas pu modifer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("finally closed connection");
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void ShowClientsVol(Connection conn, int numOldVol, int numNewVol) throws SQLException {
		System.out.println("\n\n");
		System.out.println("Les passagers du vol " + numOldVol + " (ancien)");
		ClientDao clientDao = ClientDao.getInstance(conn);
		List<Client> clients = clientDao.getClientByVolNum(numOldVol);
		int index = 1;
		for (Client cl : clients) {
			System.out.print(index++ + " - " + cl.toString() + "\n");
		}
		System.out.println("\t--Fin de liste");

		System.out.println("\n");
		System.out.println("Les passagers du vol " + numNewVol + " (nouveau)");
		clientDao = ClientDao.getInstance(conn);
		clients = clientDao.getClientByVolNum(numNewVol);
		index = 1;
		for (Client cl : clients) {
			System.out.print(index++ + " - " + cl.toString() + "\n");
		}
		System.out.println("\t--Fin de liste");
	}*/
	}


}
