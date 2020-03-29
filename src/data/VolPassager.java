package data;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class VolPassager {

    private int numeroVolPassager;
    private Date date;
    private Timestamp datedepart;
    private int dureeVol;
    private int distanceVol;
    private String aeroportOrigine;
    private String aeroportDestination;
    private int nombreplaceDispoEco;
    private int nombrePlaceDispoAff;
    private int nombreplacedispopre;
    private int numAvionPassager;

    public VolPassager(int numeroVolPassager, String aeroportOrigine, String aeroportDestination) {
        this.numeroVolPassager = numeroVolPassager;
        this.aeroportOrigine = aeroportOrigine;
        this.aeroportDestination = aeroportDestination;
    }

    public VolPassager(int numeroVolPassager, int dureeVol, String aeroportOrigine, String aeroportDestination, int numAvionPassager) {
        this.numeroVolPassager = numeroVolPassager;
        this.dureeVol = dureeVol;
        this.aeroportOrigine = aeroportOrigine;
        this.aeroportDestination = aeroportDestination;
        this.numAvionPassager = numAvionPassager;
    }

    public VolPassager(int numeroVolPassager, Date date, Timestamp datedepart, int dureeVol, int distanceVol, String aeroportOrigine, String aeroportDestination, int nombreplaceDispoEco, int nombrePlaceDispoAff, int nombreplacedispopre, int numAvionPassager) {
        this.numeroVolPassager = numeroVolPassager;
        this.date = date;
        this.datedepart = datedepart;
        this.dureeVol = dureeVol;
        this.distanceVol = distanceVol;
        this.aeroportOrigine = aeroportOrigine;
        this.aeroportDestination = aeroportDestination;
        this.nombreplaceDispoEco = nombreplaceDispoEco;
        this.nombrePlaceDispoAff = nombrePlaceDispoAff;
        this.nombreplacedispopre = nombreplacedispopre;
        this.numAvionPassager = numAvionPassager;
    }

    public VolPassager() {
    }

    public VolPassager(int numeroVolPassager, int dureeVol) {
        this.numeroVolPassager = numeroVolPassager;
        this.dureeVol = dureeVol;
    }

    public static Timestamp stringVersDate(String chaine) throws ParseException {
        // System.err.println("--Date debut reservation-- ");

        String DateDebutString = chaine;
        System.out.println(DateDebutString);
        DateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        java.util.Date DateDebut = formatter.parse(DateDebutString);
        Timestamp ds = new Timestamp(DateDebut.getTime());
        return ds;
    }

    public static void insertVol(Connection conn, int numVol, String origine, String destination, int numAvion,
                                 Timestamp dateDepart, int dureeVol, int distanceVol, int nbrPlaceEco, int nbrPlaceAff, int nbrPlacePre) {
        //Connection conn = BD_Connection.getConnection();
        PreparedStatement ps;
        ResultSet resultats;
        try {

            //String query = "INSERT INTO volpassager values (" + numVol + "," + "TO_DATE('" + formater.format(this.date) + "','YYYY-MM-DD')" + ","+ "TO_DATE('" + formater.format(this.datedepart) + "','YYYY-MM-DD'),"+ "TO_TIMESTAMP('" + formatter.format(this.heureDepart) + "','YYYY-MM-DD HH24:MI:SS.FF')"+ ","+ this.dureeVol + "," + this.distanceVol + ",'" + origine  + "','" + destination + "',"+this.nombreplaceDispoEco + "," + this.nombrePlaceDispoAff + "," + this.nombreplacedispopre +  "," + numAvion + ")";
            String query = "insert into volpassager (NUMVOLPASSAGER, dateEnregistrementVol, DATEDEPART, "
                    + "DUREEVOL, DISTANCEVOL, AEROPORTORIGINE, AEROPORTDESTINATION, NOMBREPLACEDISPOECO, "
                    + "NOMBREPLACEDISPOEAFF, NOMBREPLACEDISPOPRE, NUMAVIONPASSAGER) values"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(query);
            int i = 1;
            ps.setInt(i++, numVol);
            ps.setDate(i++, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            ps.setTimestamp(i++, dateDepart);
            ps.setInt(i++, dureeVol);
            ps.setInt(i++, distanceVol);
            ps.setString(i++, origine);
            ps.setString(i++, destination);
            ps.setInt(i++, nbrPlaceEco);
            ps.setInt(i++, nbrPlaceAff);
            ps.setInt(i++, nbrPlacePre);
            ps.setInt(i++, numAvion);
            ps.execute();

        } catch (Exception e) {
            System.out.println("Echec" + e);
        }

    }

    public Timestamp getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Timestamp datedepart) {
        this.datedepart = datedepart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumeroVolPassager() {
        return numeroVolPassager;
    }

    public void setNumeroVolPassager(int numeroVolPassager) {
        this.numeroVolPassager = numeroVolPassager;
    }

    public int getDureeVol() {
        return dureeVol;
    }

    public void setDureeVol(int dureeVol) {
        this.dureeVol = dureeVol;
    }

    public int getDistanceVol() {
        return distanceVol;
    }

    public void setDistanceVol(int distanceVol) {
        this.distanceVol = distanceVol;
    }

    public String getAeroportOrigine() {
        return aeroportOrigine;
    }

    public void setAeroportOrigine(String aeroportOrigine) {
        this.aeroportOrigine = aeroportOrigine;
    }

    public String getAeroportDestination() {
        return aeroportDestination;
    }

    public void setAeroportDestination(String aeroportDestination) {
        this.aeroportDestination = aeroportDestination;
    }

    public int getNombreplaceDispoEco() {
        return nombreplaceDispoEco;
    }

    public void setNombreplaceDispoEco(int nombreplaceDispoEco) {
        this.nombreplaceDispoEco = nombreplaceDispoEco;
    }

    public int getNombrePlaceDispoAff() {
        return nombrePlaceDispoAff;
    }

    public void setNombrePlaceDispoAff(int nombrePlaceDispoAff) {
        this.nombrePlaceDispoAff = nombrePlaceDispoAff;
    }

    public int getNombreplacedispopre() {
        return nombreplacedispopre;
    }

    public void setNombreplacedispopre(int nombreplacedispopre) {
        this.nombreplacedispopre = nombreplacedispopre;
    }

    public int getNumAvionPassager() {
        return numAvionPassager;
    }

    public List<String> delete(int numVolPassager, Date date) {

        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/yyyy");

        String query1 = "DELETE FROM volpassager where numvolpassager='" + numVolPassager + "'and datevol = TO_DATE('" + formater.format(date) + "','DD/MM/YYYY')";
        String query2 = "DELETE FROM PlaceVolResa where numVolPassager='" + numVolPassager + "'and datevol = TO_DATE('" + formater.format(date) + "','DD/MM/YYYY')";
        String query3 = "DELETE FROM HotesseVolPassager where numVolPassager='" + numVolPassager + "'and datevol = TO_DATE('" + formater.format(date) + "','DD/MM/YYYY')";
        String query4 = "DELETE FROM PiloteVolPassager where numVolPassager='" + numVolPassager + "'and datevol = TO_DATE('" + formater.format(date) + "','DD/MM/YYYY')";

        List<String> listquery = new ArrayList<String>();

        listquery.add(query1);
        listquery.add(query2);
        listquery.add(query3);
        listquery.add(query4);

        return listquery;
    }

    public void setNumAvionPassager(int numAvionPassager) {
        this.numAvionPassager = numAvionPassager;
    }

    public String delete(int numVolPassager) {
        String query = "DELETE FROM volpassager where numvolpassager='" + numVolPassager + "' ";

        return query;
    }

    public String finishVol(int numVolPassager) {
        String query = "UPDATE volpassager SET estTermine=1 where numvolpassager = '" + numVolPassager + "'";

        return query;
    }

    public String getAllVoyageBeforeDate() {
        String query = "SELECT * FROM volpassager WHERE dateVol>sysdate";
        return query;
    }

    public String getNbPlaceDispo(int numVolpassager, Date dateVol) {
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd/MM/yyyy");
        String query = "SELECT nombreplacedispoeco,nombreplacedispoeaff,nombreplacedispopre FROM volpassager WHERE dateVol=TO_DATE('" + formater.format(dateVol) + "','DD/MM/YYYY') AND numvolpassager=" + numVolpassager;
        return query;
    }


}
