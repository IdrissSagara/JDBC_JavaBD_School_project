package data;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VolPassager {

	private int numeroVolPassager;
	private Date date = new Date();
	private int heureDepart;
	private int dureeVol;
	private int distanceVol;	
	private String aeroportOrigine;
	private String aeroportDestination;
	private int nombreplaceDispoEco;
	private int nombrePlaceDispoAff;
	private int nombreplacedispopre;
	private int numAvionPassager;
	
	
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
	public int getHeureDepart() {
		return heureDepart;
	}
	public void setHeureDepart(int heureDepart) {
		this.heureDepart = heureDepart;
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
	public void setNumAvionPassager(int numAvionPassager) {
		this.numAvionPassager = numAvionPassager;
	}
	
	public VolPassager() {
	    }
	
	public VolPassager(int numeroVolPassager, int heureDepart, int dureeVol, int distanceVol, String aeroportOrigine,
			String aeroportDestination, int nombreplaceDispoEco, int nombrePlaceDispoAff, int nombreplacedispopre,
			int numAvionPassager) {
		this.numeroVolPassager = numeroVolPassager;
		this.heureDepart = heureDepart;
		this.dureeVol = dureeVol;
		this.distanceVol = distanceVol;
		this.aeroportOrigine = aeroportOrigine;
		this.aeroportDestination = aeroportDestination;
		this.nombreplaceDispoEco = nombreplaceDispoEco;
		this.nombrePlaceDispoAff = nombrePlaceDispoAff;
		this.nombreplacedispopre = nombreplacedispopre;
		this.numAvionPassager = numAvionPassager;
	}
	public VolPassager(int numeroVolPassager, int dureeVol) {
		this.numeroVolPassager = numeroVolPassager;
		this.dureeVol = dureeVol;
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
    
    public String insertVol(int numVol, String origine, String destination, int numAvion) {

        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yyyy-mm-dd");
        String query = "INSERT INTO volpassager values (" + numVol + "','" + this.heureDepart + ",TO_DATE('" + formater.format(this.date) + "','yyyy-mm-dd'),'" +  "','" + this.dureeVol + "','" + this.distanceVol + "','" + origine  + "','" + destination + "','"  +this.nombreplaceDispoEco + "','" + this.nombrePlaceDispoAff + "','" + this.nombreplacedispopre +  "','" + numAvion + "',)";

      
        return query;
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
