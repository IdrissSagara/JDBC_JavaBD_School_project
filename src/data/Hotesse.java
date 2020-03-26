package data;

public class Hotesse {
	
	private int numHotesse;
	private String nomPersonnelHotesse;
	private String prenomPersonnelHotesse;
	private String langueMaternelle;
	private String deuxiemeLangue;
	private String troisiemeLangue;
	private int nbHeureTotal;
	private int numModel;
	private int numVolPassager;
	private String localisationActuelleHotesse;
	
	
	
	public int getNumHotesse() {
		return numHotesse;
	}
	public void setNumHotesse(int numHotesse) {
		this.numHotesse = numHotesse;
	}
	public String getNomPersonnelHotesse() {
		return nomPersonnelHotesse;
	}
	public void setNomPersonnelHotesse(String nomPersonnelHotesse) {
		this.nomPersonnelHotesse = nomPersonnelHotesse;
	}
	public String getPrenomPersonnelHotesse() {
		return prenomPersonnelHotesse;
	}
	public void setPrenomPersonnelHotesse(String prenomPersonnelHotesse) {
		this.prenomPersonnelHotesse = prenomPersonnelHotesse;
	}
	public String getLangueMaternelle() {
		return langueMaternelle;
	}
	public void setLangueMaternelle(String langueMaternelle) {
		this.langueMaternelle = langueMaternelle;
	}
	public String getDeuxiemeLangue() {
		return deuxiemeLangue;
	}
	public void setDeuxiemeLangue(String deuxiemeLangue) {
		this.deuxiemeLangue = deuxiemeLangue;
	}
	public String getTroisiemeLangue() {
		return troisiemeLangue;
	}
	public void setTroisiemeLangue(String troisiemeLangue) {
		this.troisiemeLangue = troisiemeLangue;
	}
	public int getNbHeureHotesse() {
		return nbHeureTotal;
	}
	public void setNbHeureHotesse(int nbHeureHotesse) {
		this.nbHeureTotal = nbHeureHotesse;
	}
	public int getNumModel() {
		return numModel;
	}
	public void setNumModel(int numModel) {
		this.numModel = numModel;
	}
	public int getNumVolPassager() {
		return numVolPassager;
	}
	public void setNumVolPassager(int numVolPassager) {
		this.numVolPassager = numVolPassager;
	}
	
	public String insertHotesse(int numHotesse, int numVol) {

        String query = "INSERT INTO hotesse values('" + numHotesse + "','" + numVol + "')";
        return query;
    }


}
