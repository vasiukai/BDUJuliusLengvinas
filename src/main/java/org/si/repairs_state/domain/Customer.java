package org.si.repairs_state.domain;

public class Customer {


    private String Busena, Komentaras, Prietaisas, Modelis, RMA, TelNr, Laikas;





    public Customer( String Busena, String Komentaras, String Prietaisas, String Modelis, String RMA, String TelNr, String Laikas) {

        this.Busena = Busena;
        this.Komentaras = Komentaras;
        this.Prietaisas = Prietaisas;
        this.Modelis = Modelis;
        this.RMA = RMA;
        this.TelNr = TelNr;
        this.Laikas = Laikas;
    }

    public String getBusena() {
        return Busena;
    }

    public void setBusena(String Busena) {
        this.Busena = Busena;
    }

    public String getKomentaras() {
        return Komentaras;
    }

    public void setKomentaras(String Komentaras) {
        this.Komentaras = Komentaras;
    }

    public String getPrietaisas() {
        return Prietaisas;
    }

    public void setPrietaisas(String Prietaisas) {
        this.Prietaisas = Prietaisas;
    }

    public String getModelis() {
        return Modelis;
    }

    public void setModelis(String Modelis) {
        this.Modelis = Modelis;
    }

    public String getRMA() {
        return RMA;
    }

    public void setRMA(String RMA) {
        this.RMA = RMA;
    }

    public String getTelNr() {
        return TelNr;
    }

    public void setTelNr(String TelNr) {
        this.TelNr = TelNr;
    }

    public String getLaikas() {
        return Laikas;
    }

    public void setLaikas(String Laikas) {
        this.Laikas = Laikas;
    }
}
