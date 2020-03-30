package data;

public enum EtatVol {
    SUPPRIMER("SUPPRIMER"),
    EN_SERVICE("EN_SERVICE");

    private String value;

    private EtatVol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
