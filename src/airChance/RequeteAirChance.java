package airChance;

import dao.HotesseDao;
import dao.PiloteVolDao;
import data.Hotesse;
import data.Pilote;
import data.VolPassager;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RequeteAirChance {


	/**
	 * Afficher toutes les informations sur tous les spectacles.
	 *
	 * @param conn connexio n � la base de donn � es
	 * @throws SQLException en cas d'erreur d'acc � s � la base de donn � es
	 */


	public static void planificationVol(Connection conn) throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// Execute the query
		System.out.println("===Planificaiton d'un vol===");


		String origine = DemandeSaisie.saisirString("donner l'aeroport origine: ", 0, 26);
		String destination = DemandeSaisie.saisirString("donner l'aeroport destination: ", 0, 26);

		//recherche des avion disponibles
		ResultSet rs = stmt.executeQuery("select a.numavionpassager, nummodel from avionpassager a where a.numavionpassager NOT IN (select v.numavionpassager from volpassager v)");
		while (rs.next()) {
			System.out.print("  Num Avion : " + rs.getInt(1) + " ");
			System.out.println("  Num Modele : " + rs.getInt(2) + " ");
		}

		rs = stmt.executeQuery("select v.numavionpassager, max(v.datedepart) from avionpassager a \n"
				+ "join volpassager v on v.numavionpassager = a.numavionpassager \n"
				+ "group by a.numavionpassager\n"
				+ "order by 1");

		List<VolPassager> listVolPassager = new ArrayList<VolPassager>();

		while (rs.next()) {
			VolPassager vp = new VolPassager();
			vp.setNumAvionPassager(rs.getInt(1));
			vp.setDate(rs.getDate(2));

			listVolPassager.add(vp);

		}
		for (int y = 0; y < listVolPassager.size(); y++) {
			VolPassager vp2 = listVolPassager.get(y);

			ResultSet rs2 = stmt.executeQuery("select distinct v.numavionpassager, av.nummodel from volpassager v join avionpassager av on av.numavionpassager = v.numavionpassager  where v.numavionpassager = " + vp2.getNumAvionPassager());
			while (rs2.next()) {

				if (rs2.getInt(2) == 1) {
					System.out.print("  Num Avion : " + rs2.getInt(1) + " ");
					System.out.println("  Num Modele : " + rs2.getInt(3) + " ");
				}
			}
		}


		System.out.println("Liste des avions disponibles");
		for (VolPassager vp : listVolPassager) {
			System.out.print(vp.getNumAvionPassager() + "\t");
			System.out.println(vp.getNumeroVolPassager());
		}

		//int numAvion = LectureClavier.lireEntier("Choisir le numéro de l'avion du vol");
		int numAvion = DemandeSaisie.saisirInt("Choisir le numéro de l'avion du vol", 0, listVolPassager.get(listVolPassager.size() - 1).getNumAvionPassager());

		rs = stmt.executeQuery("select max(numvolpassager) from volpassager");
		int numVol = 0;
		while (rs.next()) {
			numVol = rs.getInt(1);
		}

		numVol++;

		// demander toutes les infos necessaires à enreg un vol
		/*insertVol(Connection conn, int numVol, String origine, String destination, int numAvion, 
        		Timestamp dateDepart, int dureeVol, int distanceVol, int nbrPlaceEco, int nbrPlaceAff, int nbrPlacePre)*/
		int dureeVol = DemandeSaisie.saisirInt("Saisissez la duree du vol", 0, 10000);
		int distanceVol = DemandeSaisie.saisirInt("Saisissez la distance du vol", 0, 200000);
		int nbrPlaceAff = DemandeSaisie.saisirInt("Saisissez le nombre de place affaires disponibles", 0, 200);
		int nbrPlaceEco = DemandeSaisie.saisirInt("Saisissez le nombre de place economique disponibles", 0, 200);
		int nbrPlacePre = DemandeSaisie.saisirInt("Saisissez le nombre de place en première disponibles", 0, 200);
		Timestamp dateDepart;
		try {
			dateDepart = DemandeSaisie.saisirDateTimestamp("Saisissez la date de depart", 2020, 2023);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dateDepart = new Timestamp(System.currentTimeMillis());
		}

		//Creation de vol
		VolPassager.insertVol(conn, numVol, origine, destination, numAvion, dateDepart, dureeVol, distanceVol, nbrPlaceEco, nbrPlaceAff, nbrPlacePre);

		System.out.println("\nA ce stade, nous avons crée notre vol avec les informations suivantes : Aeroport Origine, Aeroport Destination ....\n\nNous allons désormais affecter du personnel à ce vol");

		System.out.println("===========================\n===========================\n===========================\nA partir d'ici nous allons gérer l'aspect concurentiel\n===========================\n===========================\n===========================\n");

		//recuperer le nombre de pilotes necessaires pour piloter ce vol
		//proposer ensuite de les selectionner parmis ceux qualifiés pour le faire

		rs = Pilote.getPiloteQualifierVol(conn, numVol);

		System.out.println("Les pilotes qualifiés pour le vol " + numVol);
		int maxIdPil = 0;
		String nomPil = "";
		String prenomPil = "";
		while (rs.next()) {
			int idPil = rs.getInt(1);
			if (idPil > maxIdPil) maxIdPil = idPil;
			System.out.print(idPil + "\t");
			nomPil = rs.getString(2);
			prenomPil = rs.getString(3);
			System.out.print(prenomPil + "\t");
			System.out.println(nomPil);
		}

		int numPil = DemandeSaisie.saisirInt("Saisissez le numéro du pilote", 0, maxIdPil);

		//insert valeur dans pilote_vol
		PiloteVolDao piloteVolDao = PiloteVolDao.getInstance(conn);
		int insertedId = -1;
		try {
			insertedId = piloteVolDao.insert(numPil, numVol);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (insertedId == -1) {
			System.out.println("Une erreur fatale s'est produite lors de l'insertion du pilote_vol");
			return;
		}

		//afficcher les hotesses
		System.out.println("Liste des hostesses disponibles");
		HotesseDao hotesseDao = HotesseDao.getInstance(conn);
		List<Hotesse> hl = null;
		try {
			hl = hotesseDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (hl == null) {
			System.out.println("Une erreur fatale s'est produite lors de la recuperation des hotesses");
			return;
		}

		int minId = hl.get(0).getNumHotesse(), maxId = 0;

		int index = 1;
		for (Hotesse h : hl) {
			if (h.getNumHotesse() > maxId) maxId = h.getNumHotesse();
			if (h.getNumHotesse() < minId) minId = h.getNumHotesse();
			System.out.println(index++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
		}

		HotesseVolDao hotesseVolDao = HotesseVolDao.getInstance(conn);

		System.out.println("Saisissez 3 hotesses");
		List<Hotesse> listChoixHotesse = new ArrayList<Hotesse>();
		for (int i = 0; i < 3; i++) {
			int choix = DemandeSaisie.saisirInt("Saisissez le numéro de l'hotesse n°" + i + 1, 1, hl.size());
			listChoixHotesse.add(hl.get(choix));
			int numHotesse = hl.get(choix - 1).getNumHotesse();
			int res = hotesseVolDao.insert(numVol, numHotesse);

			if (res == -1) {
				return;
			}
		}

		System.out.println("Résumé de la planification du vol " + numVol);
		System.out.println("Détails du vol");
		System.out.println("Durée: " + dureeVol);
		System.out.println("Distance: " + distanceVol);
		System.out.println("Places affaires: " + nbrPlaceAff);
		System.out.println("Places eco: " + nbrPlaceEco);
		System.out.println("Places prémière: " + nbrPlacePre);
		System.out.println("Départ: " + dateDepart);

		System.out.println("Pilote selectionné pour le vol");
		System.out.println("Prenom: " + prenomPil);
		System.out.println("Nom: " + nomPil);

		System.out.println("Liste des hotesses choisis");
		index = 1;
		for (Hotesse h : listChoixHotesse) {
			System.out.println(index++ + " - " + h.getPrenomPersonnelHotesse() + " " + h.getNomPersonnelHotesse());
		}

		rs.close();
		stmt.close();
	}

}
