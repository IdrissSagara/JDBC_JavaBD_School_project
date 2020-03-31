package data;

public class Client {
    private int numclient;
    private String nomclient;
    private String prenomclient;
    private String rueclient;
    private String cpclient;
    private String villeclient;
    private String paysclient;
    private String numpassport;

    public Client(int numclient, String nomclient, String prenomclient) {
        this.numclient = numclient;
        this.nomclient = nomclient;
        this.prenomclient = prenomclient;
    }

    public int getNumclient() {
        return numclient;
    }


    public void setNumclient(int numclient) {
        this.numclient = numclient;
    }

    public String getNomclient() {
        return nomclient;
    }


    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }


    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }


    public String getRueclient() {
        return rueclient;
    }


    public void setRueclient(String rueclient) {
        this.rueclient = rueclient;
    }


    public String getCpclient() {
        return cpclient;
    }


    public void setCpclient(String cpclient) {
        this.cpclient = cpclient;
    }


    public String getVilleclient() {
        return villeclient;
    }


    public void setVilleclient(String villeclient) {
        this.villeclient = villeclient;
    }


    public String getPaysclient() {
        return paysclient;
    }


    public void setPaysclient(String paysclient) {
        this.paysclient = paysclient;
    }


    public String getNumpassport() {
        return numpassport;
    }


    public void setNumpassport(String numpassport) {
        this.numpassport = numpassport;
    }

    @Override
    public String toString() {
        return "Numero Client: " + numclient +
                " | Nom Client: " + nomclient + " " +
                "| Prenom Client: " + prenomclient + " ";
    }
}
