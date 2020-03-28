package data;

public class PiloteVol {
    private int numPiloteVol;
    private int numvolpassager;
    private int numpilote;

    public PiloteVol(int numPiloteVol, int numvolpassager, int numpilote) {
        this.numPiloteVol = numPiloteVol;
        this.numvolpassager = numvolpassager;
        this.numpilote = numpilote;
    }

    public int getNumPiloteVol() {
        return numPiloteVol;
    }

    public void setNumPiloteVol(int numPiloteVol) {
        this.numPiloteVol = numPiloteVol;
    }

    public int getNumvolpassager() {
        return numvolpassager;
    }

    public void setNumvolpassager(int numvolpassager) {
        this.numvolpassager = numvolpassager;
    }

    public int getNumpilote() {
        return numpilote;
    }

    public void setNumpilote(int numpilote) {
        this.numpilote = numpilote;
    }
}
