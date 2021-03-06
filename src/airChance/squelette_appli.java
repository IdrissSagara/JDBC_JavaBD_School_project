package airChance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class squelette_appli {

    static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

    // Ne pas oublier d'anomymiser le PASSWD avant de rendre votre travail
    static final String USER = "sagarai ";
    static final String PASSWD = "Sagara1404";

    static Connection conn;

    public static void main(String args[]) throws Exception {

		try {

			// Enregistrement du driver Oracle
			System.out.print("Loading Oracle driver... ");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("loaded");

			// Etablissement de la connection
			System.out.print("Connecting to the database... ");
			conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
			System.out.println("connected");

			conn.setAutoCommit(true);

			// code m�tier de la fonctionnalit�
			// Liberation des ressources et fermeture de la connexion...
			// A COMPLETER
			// conn.close();

			System.out.println("bye.");

			// traitement d'exception
		} catch (SQLException e) {
			System.err.println("failed");
			System.out.println("Affichage de la pile d'erreur");
			e.printStackTrace(System.err);
			System.out.println("Affichage du message d'erreur");
			System.out.println(e.getMessage());
			System.out.println("Affichage du code d'erreur");
			System.out.println(e.getErrorCode());

		}
		int choix = 0;
		do {
			System.out.println("************ MENU ************");
			System.out.println("1 - Plannifier un vol");
			System.out.println("2 - Modifier la planification d'un vol");
			System.out.println("3 - Supprimer un vol");
			System.out.println("4 - Terminer un vol");
			System.out.println("5 - Ajouter/Supprimer un personnel-");
			System.out.println("6 - Consulter les commandes d'un client");
			System.out.println("7 - Reservation d'un vol");
			System.out.println("8 - Modifier une reservation");
			System.out.println("9 - Supprimer une reservation");
			System.out.println("11 - Quitter");
			System.out.println("******************************");
			choix = LectureClavier.lireEntier("Votre choix : \n");
			switch (choix) {
				case 1:
					System.out.println("******************************");
					PlanificationVolAirChance.planificationVol(conn);
					break;
				case 2:
					System.out.println("******************************");
					PlanificationVolAirChance.modifierPlanificationVol(conn);
					break;
				case 3:
					System.out.println("******************************");
					PlanificationVolAirChance.suppressionVol(conn);

					break;
				case 4:
					System.out.println("******************************");
					PlanificationVolAirChance.TerminerEtatVol(conn);
					break;
				case 5:
					int sous_choix = 0;
					do {
						System.out.println("******************************");

						System.out.println("\t\t 1 - Ajouter un nouveau pilote");
						System.out.println("\t\t 2 - Supprimer un  pilote");
						System.out.println("\t\t 3 - Ajouter un nouveau hotesse");
						System.out.println("\t\t 4 - Supprimer un Hotesse");
						System.out.println("\t\t 5 - Quitter ---Revenir au menu principal---");
						sous_choix = LectureClavier.lireEntier("Votre choix pour ajouter/supprimer un personnel : \n");
						switch (sous_choix) {
							case 1:
								System.out.println("\n");
								GestionPersonnelAirChance.AjoutPersonnelPilote(conn);
								break;
							case 2:
								System.out.println("\n");
								GestionPersonnelAirChance.SupprimerPersonnelPilote(conn);
								break;
							case 3:
								System.out.println("\n");
								GestionPersonnelAirChance.AjoutPersonnelHotesse(conn);
								break;
							case 4:
								System.out.println("\n");
								GestionPersonnelAirChance.SupprimerPersonnelHotesse(conn);
								break;
							case 5:
								System.out.println("Quitter le sous menu");

						}
					} while (sous_choix != 5);
					break;
				case 6:
					System.out.println("******************************");
					GestionReservationAirChance.ConsultationCommandeClients(conn);
					break;
				case 7:
					System.out.println("******************************");
					GestionReservationAirChance.Reserver(conn);
					break;
				case 8:
					System.out.println("******************************");
					break;
				case 9:
					System.out.println("******************************");
					GestionReservationAirChance.supprimerResa(conn);
					break;
				case 11:
					System.out.println("Deconnexion");
					choix = 11;
			}
		} while (choix != 11);

    }


}
