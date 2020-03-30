package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pilote {

    private int numPilote;
    private String nomPersonnelPilote;
    private String prenomPersonnelPilote;
    private String ruePersonnelPilote;
    private String paysPersonnelPilote;
    private String localisationActuellePilote;
    private int nombreHeurePilote;

    public Pilote() {
    }

    public int getNombreHeurePilote() {
        return nombreHeurePilote;
    }

    public void setNombreHeurePilote(int nombreHeurePilote) {
        this.nombreHeurePilote = nombreHeurePilote;
    }


    public Pilote(int numPilote, String nomPersonnelPilote, String prenomPersonnelPilote, String ruePersonnelPilote,
                  String paysPersonnelPilote, String localisationActuellePilote) {
        this.numPilote = numPilote;
        this.nomPersonnelPilote = nomPersonnelPilote;
        this.prenomPersonnelPilote = prenomPersonnelPilote;
        this.ruePersonnelPilote = ruePersonnelPilote;
        this.paysPersonnelPilote = paysPersonnelPilote;
        this.localisationActuellePilote = localisationActuellePilote;
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

    public static ResultSet getPiloteQualifierVol(Connection conn, int numVol) throws SQLException {
        //Connection conn = BD_Connection.getConnection();
        PreparedStatement ps;
        ResultSet resultats = null;

        String query = "select NUMPILOTE, NOMPERSONNELPILOTE, PRENOMPERSONNELPILOTE from volpassager natural join  AVIONPASSAGER" +
                "    natural join MODELE" +
                "    natural join pilote" +
                "    natural join QUALIFICATIONMODELAVION" +
                " where NUMVOLPASSAGER = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, numVol);

        resultats = ps.executeQuery();

        return resultats;
    }

    @Override
    public String toString() {
        return "numero Pilote: " + numPilote +
                ", nom Pilote: '" + nomPersonnelPilote + '\'' +
                ", prenom Pilote: '" + prenomPersonnelPilote + '\'' +
                ", nombre d'heures de vol: '" + nombreHeurePilote + '\'';
    }
}
