package dao;

import data.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao {

    private static PlaceDao placeDao = null;
    private Connection conn;

    private PlaceDao(Connection conn) {
        this.conn = conn;
    }

    public static PlaceDao getInstance(Connection conn) {
        if (placeDao == null) {
            placeDao = new PlaceDao(conn);
        }

        return placeDao;
    }

    public List<Place> getPlaceByNumVol(int numVol) throws Exception {
        List<Place> placeList = null;
        String ar15 = "select NUMPLACE, NUMAVIONPASSAGER, NUMVOLPASSAGER, NOMBREPLACEDISPOPRE,NOMBREPLACEDISPOECO,NOMBREPLACEDISPOEAFF,\n" +
                "       POSITION,TYPECLASSE,PRIXPLACE\n" +
                "from VOLPASSAGER natural join\n" +
                "    AVIONPASSAGER natural join\n" +
                "    MODELE natural join\n" +
                "    PLACE where NUMVOLPASSAGER = ?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(ar15);

        ps.setInt(1, numVol);
        ps.executeQuery();
        rs = ps.getResultSet();
        placeList = makeReservationList(rs);

        return placeList;
    }

    private Place mapResultSetToPlace(ResultSet rs) throws Exception {
        Place v = null;

        if (rs != null) {

            int i = 1;
            int numplace = rs.getInt(i++);
            int numAvion = rs.getInt(i++);
            int numVol = rs.getInt(i++);
            int nbPlacePre = rs.getInt(i++);
            int nbPlaceEco = rs.getInt(i++);
            int nbPlaceAff = rs.getInt(i++);
            String position = rs.getString(i++);
            String typeClasse = rs.getString(i++);
            int prix = rs.getInt(i++);

            v = new Place(numplace, numAvion, numVol, nbPlacePre, nbPlaceEco, nbPlaceAff, position, typeClasse, prix);
        }

        return v;
    }

    private List<Place> makeReservationList(ResultSet rs) throws Exception {
        List<Place> pl = new ArrayList<>();
        Place p = null;

        while (rs.next()) {
            p = mapResultSetToPlace(rs);
            pl.add(p);
        }
        return pl;
    }


}
