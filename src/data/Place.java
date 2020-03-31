package data;

import java.sql.Timestamp;

public class Place {

	private int numPlace;
	private String position;
	private String typeClasse;
	private int prixPlace;
	private Timestamp dateChangementPlace;
	private int nummodel;
	private int numAvion;
	private int numVol;
	private int placeDispoPre;
	private int placeDispoEco;
	private int placeDispoAff;

	public Place(int numPlace, int numAvion, int numVol, int placeDispoPre, int placeDispoEco, int placeDispoAff, String position, String typeClasse, int prixPlace) {
		this.position = position;
		this.typeClasse = typeClasse;
		this.prixPlace = prixPlace;
		this.numAvion = numAvion;
		this.numVol = numVol;
		this.placeDispoPre = placeDispoPre;
		this.placeDispoEco = placeDispoEco;
		this.placeDispoAff = placeDispoAff;
		this.numPlace = numPlace;
	}

	public Place(int numPlace, String position, String typeClasse, int prixPlace, Timestamp dateChangementPlace, int nummodel) {
		this.numPlace = numPlace;
		this.position = position;
		this.typeClasse = typeClasse;
		this.prixPlace = prixPlace;
		this.dateChangementPlace = dateChangementPlace;
		this.nummodel = nummodel;
	}

	public int getNumPlace() {
		return numPlace;
	}

	public void setNumPlace(int numPlace) {
		this.numPlace = numPlace;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(String typeClasse) {
		this.typeClasse = typeClasse;
	}

	public int getPrixPlace() {
		return prixPlace;
	}

	public void setPrixPlace(int prixPlace) {
		this.prixPlace = prixPlace;
	}

	public Timestamp getDateChangementPlace() {
		return dateChangementPlace;
	}

	public void setDateChangementPlace(Timestamp dateChangementPlace) {
		this.dateChangementPlace = dateChangementPlace;
	}

	public int getNummodel() {
		return nummodel;
	}

	public void setNummodel(int nummodel) {
		this.nummodel = nummodel;
	}

	@Override
	public String toString() {
		return "Place{" +
				"position='" + position + '\'' +
				", typeClasse='" + typeClasse + '\'' +
				", prixPlace=" + prixPlace +
				", numAvion=" + numAvion +
				", numVol=" + numVol +
				", placeDispoPre=" + placeDispoPre +
				", placeDispoEco=" + placeDispoEco +
				", placeDispoAff=" + placeDispoAff +
				'}';
	}
}
