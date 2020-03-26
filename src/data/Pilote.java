package data;

import java.text.SimpleDateFormat;
import airChance.LectureClavier;

public class Pilote {

	private int numPilote;
	private String nomPersonnelPilote;
	private String prenomPersonnelPilote;
	private String ruePersonnelPilote;
	private String paysPersonnelPilote;
	private String localisationActuellePilote;
	private int nbHeurePilote;
	private int numModel;
	private int numVolPassager;
	
	
	
	public Pilote() {
	}



	public Pilote(int numPilote, String nomPersonnelPilote, String prenomPersonnelPilote, String ruePersonnelPilote,
			String paysPersonnelPilote, String localisationActuellePilote, int nbHeurePilote, int numModel,
			int numVolPassager) {
		this.numPilote = numPilote;
		this.nomPersonnelPilote = nomPersonnelPilote;
		this.prenomPersonnelPilote = prenomPersonnelPilote;
		this.ruePersonnelPilote = ruePersonnelPilote;
		this.paysPersonnelPilote = paysPersonnelPilote;
		this.localisationActuellePilote = localisationActuellePilote;
		this.nbHeurePilote = nbHeurePilote;
		this.numModel = numModel;
		this.numVolPassager = numVolPassager;
	}



	public int getNumPilote() {
		return numPilote;
	}



	public void setNumPilote(int numPilote) {
		this.numPilote = numPilote;
	}



	public String getNomPersonnelPilote() {
		return nomPersonnelPilote;
	}



	public void setNomPersonnelPilote(String nomPersonnelPilote) {
		this.nomPersonnelPilote = nomPersonnelPilote;
	}



	public String getPrenomPersonnelPilote() {
		return prenomPersonnelPilote;
	}



	public void setPrenomPersonnelPilote(String prenomPersonnelPilote) {
		this.prenomPersonnelPilote = prenomPersonnelPilote;
	}



	public String getRuePersonnelPilote() {
		return ruePersonnelPilote;
	}



	public void setRuePersonnelPilote(String ruePersonnelPilote) {
		this.ruePersonnelPilote = ruePersonnelPilote;
	}



	public String getPaysPersonnelPilote() {
		return paysPersonnelPilote;
	}



	public void setPaysPersonnelPilote(String paysPersonnelPilote) {
		this.paysPersonnelPilote = paysPersonnelPilote;
	}



	public String getLocalisationActuellePilote() {
		return localisationActuellePilote;
	}



	public void setLocalisationActuellePilote(String localisationActuellePilote) {
		this.localisationActuellePilote = localisationActuellePilote;
	}



	public int getNbHeurePilote() {
		return nbHeurePilote;
	}



	public void setNbHeurePilote(int nbHeurePilote) {
		this.nbHeurePilote = nbHeurePilote;
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
	

    public String updatePilote() {

        System.out.print("Entrer une nouvelle localisation");
        String newLocalisation = LectureClavier.lireChaine();
        String query = "UPDATE Pilote SET localisationActuel = '" + newLocalisation + "' where numpilote=5";

        return query;
    }
    public String insertPilote(int numPilote, int numVol){
        
        String query ="INSERT INTO pilote values('"+numPilote+"','"+numVol+"'))";
        return query; 
    }
	
	

}
