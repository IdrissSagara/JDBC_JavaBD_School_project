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

    public static void main(String args[]) throws SQLException {

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
				System.out.println("10 - Scenario 1");
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
                        System.out.println("******************************");
						GestionPersonnelAirChance.AjoutPersonnel(conn);
                        break;
                    case 6:
                        System.out.println("******************************");
                        break;
                    case 7:
                        System.out.println("******************************");
                        //requeteAirChancePetit.getAllvoyageAfterDate(conn);
	                    break;
	                case 8:
	                    System.out.println("******************************");
	                    break;
	                case 9:
	                    System.out.println("******************************");
	                    break;
	                case 10:
	                    System.out.println("******************************");
	                    /*RequeteAirChance.updatePilote(conn);
	                    RequeteAirChance.deletePiloteVolPassager(conn);
	                    RequeteAirChance.scenario1(conn, conn2);*/
	                case 11:
	                    System.out.println("Deconnexion");
	                    choix = 11;
	            }
	        } while (choix != 11);

    }


}
