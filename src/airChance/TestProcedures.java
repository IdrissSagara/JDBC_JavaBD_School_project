package airChance;

import connection.BD_Connection;
import dao.PiloteDao;
import dao.VolDao;
import data.Pilote;
import data.VolPassager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestProcedures {
    public static void main(String[] args) {
        Connection conn = BD_Connection.getConnection();

        VolDao volDao = VolDao.getInstance(conn);
        List<VolPassager> volPassagerList = new ArrayList<>();

        try {
            volPassagerList = volDao.getAllVol();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int index = 1;
        for (VolPassager vl : volPassagerList) {
            System.out.print(index++ + " -" + " Avion n°: " + vl.getNumAvionPassager() + "|" + " Origin :" +
                    vl.getAeroportOrigine() + " |" + " Destination: " + vl.getAeroportDestination() + " | Vol n°: " + vl.getNumeroVolPassager() +
                    " | etat Vol: " + vl.getEtatVol() + " | Terminer: " + vl.getVolEtatTerminer() + "\n");
        }

        int indexV = DemandeSaisie.saisirInt("Saisissez le numéro de vol pour le terminer", 1, volPassagerList.size());
        int numVol = volPassagerList.get(indexV - 1).getNumeroVolPassager();
        System.out.println("num vol: " + numVol);
        PiloteDao piloteDao = PiloteDao.getInstance(conn);

        try {
            showPilotesByVolNum(numVol, piloteDao);

            volDao.terminerUsingVolProcedure(numVol);

            System.out.println("\nAprès terminerUsingVolProcedure");
            showPilotesByVolNum(numVol, piloteDao);
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

    private static void showPilotesByVolNum(int numVol, PiloteDao piloteDao) throws SQLException {
        int index;
        List<Pilote> piloteList = new ArrayList<>();
        System.out.println("Les pilotes du vol " + numVol);
        piloteList = piloteDao.getPiloteByNumVol(numVol);
        index = 1;
        for (Pilote pl : piloteList) {
            System.out.print(index++ + " - " + pl.toString() + "\n");
        }
        System.out.println("\t--Fin de liste");
    }
}
