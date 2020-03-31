package data;

public class Hotesse {

	private int numHotesse;
	private String nomPersonnelHotesse;
	private String prenomPersonnelHotesse;
	private String langueMaternelle;
	private String deuxiemeLangue;
	private String troisiemeLangue;
	private int nbHeureTotal;
	private String localisationActuelleHotesse;

	public Hotesse(int numHotesse, String nomPersonnelHotesse,
				   String prenomPersonnelHotesse,
				   String langueMaternelle,
				   String deuxiemeLangue,
				   String troisiemeLangue,
				   int nbHeureTotal,
				   String localisationActuelleHotesse) {
		this.numHotesse = numHotesse;
		this.nomPersonnelHotesse = nomPersonnelHotesse;
		this.prenomPersonnelHotesse = prenomPersonnelHotesse;
		this.langueMaternelle = langueMaternelle;
		this.deuxiemeLangue = deuxiemeLangue;
		this.troisiemeLangue = troisiemeLangue;
		this.nbHeureTotal = nbHeureTotal;
		this.localisationActuelleHotesse = localisationActuelleHotesse;
	}

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

	@Override
	public String toString() {
		return "nom: " + getNomPersonnelHotesse() + " | " + " Prenom: " +
				getPrenomPersonnelHotesse() + " | " + " langue Maternelle: " + getLangueMaternelle() +
				" | " + " nombre d'heures : " + getNbHeureHotesse() + " ";
	}
}
