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
	
	
	public Pilote() {
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

    public static void getAllPilote(Connection conn) {
        //Connection conn = BD_Connection.getConnection();
        PreparedStatement ps;
        ResultSet resultats;
        try {
            String query = "SELECT * FROM pilote";
            ps = conn.prepareStatement(query);
        } catch (Exception e) {
            System.out.println("Echec" + e);
        }

    }

    public static void updatePilote(Connection conn, String nomPilote, String prenomPilote, String adressePilote,
                                    String paysPilote, int nbHeurePilote, String localisationActuelle, int numModel, int numVol, int numPilote) {
        //Connection conn = BD_Connection.getConnection();
        PreparedStatement ps;
        ResultSet resultats;
        try {
            String query = "UPDATE PILOTE,"
                    + " SET NOMPERSONNELPILOTE= ?, PRENOMPERSONNELPILOTE= ?, RUEPERSONNELPILOTE= ?,"
                    + " PAYSPERSONNELPILOTE=?, NBHEUREPILOTE=0, LOCALISATIONACTUELLEPILOTE=?,NUMMODEL= ?, NUMVOLPASSAGER= ?"
                    + " WHERE NUMPILOTE=0 ";
            ps = conn.prepareStatement(query);
            ps = conn.prepareStatement(query);
            int i = 1;
            ps.setString(i++, nomPilote);
            ps.setString(i++, prenomPilote);
            ps.setString(i++, adressePilote);
            ps.setString(i++, paysPilote);
            ps.setInt(i++, nbHeurePilote);
            ps.setString(i++, localisationActuelle);
            ps.setInt(i++, numModel);
            ps.setInt(i++, numVol);
            ps.setInt(i++, numPilote);
            ps.execute();

        } catch (Exception e) {
            System.out.println("Echec" + e);
        }

    }

    public static void insererPilote(Connection conn, String nomPilote, String prenomPilote, String adressePilote,
                                     String paysPilote, int nbHeurePilote, String localisationActuelle, int numModel, int numVol, int numPilote) {
        //Connection conn = BD_Connection.getConnection();
        PreparedStatement ps;
        ResultSet resultats;
        try {
            String query = "INSERT INTO  PILOTE"
                    + " (NUMPILOTE, NOMPERSONNELPILOTE, PRENOMPERSONNELPILOTE, RUEPERSONNELPILOTE, PAYSPERSONNELPILOTE,"
                    + " LOCALISATIONACTUELLEPILOTE) values"
                    + "(0, '', '', '', '', 0, '', 0, 0) ";
            ps = conn.prepareStatement(query);
            int i = 1;
            ps.setInt(i++, numPilote);
            ps.setString(i++, nomPilote);
            ps.setString(i++, prenomPilote);
            ps.setString(i++, adressePilote);
            ps.setString(i++, paysPilote);
            ps.setInt(i++, nbHeurePilote);
            ps.setString(i++, localisationActuelle);
            ps.setInt(i++, numModel);
            ps.setInt(i++, numVol);
            ps.execute();

        } catch (Exception e) {
            System.out.println("Echec" + e);
        }

    }


}
