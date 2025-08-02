package org.si.repairs_state.domain;

public class Devices {
    private Long RMA;
    private String  vardas, pavarde, telnr, adresas, pastas, prietaisas, modelis, prietaisoSN, gedimas, laikas;


    public Devices(
            Long RMA,

            String vardas, String pavarde, String telnr, String adresas, String pastas, String prietaisas, String modelis, String prietaisoSN, String gedimas, String laikas) {
       this.RMA = RMA;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.telnr = telnr;
        this.adresas = adresas;
        this.pastas = pastas;
        this.prietaisas = prietaisas;
        this.modelis = modelis;
        this.prietaisoSN = prietaisoSN;
        this.gedimas = gedimas;
        this.laikas = laikas;
    }

    public Devices(


            String vardas, String pavarde, String telnr, String adresas, String pastas, String prietaisas, String modelis, String prietaisoSN, String gedimas, String laikas) {

        this.vardas = vardas;
        this.pavarde = pavarde;
        this.telnr = telnr;
        this.adresas = adresas;
        this.pastas = pastas;
        this.prietaisas = prietaisas;
        this.modelis = modelis;
        this.prietaisoSN = prietaisoSN;
        this.gedimas = gedimas;
        this.laikas = laikas;
    }


    public Long gettRMA() {
        return RMA;
    }

    public void settRMA(Long RMA) {
        this.RMA = RMA;
    }

    public String getvardas() {
        return vardas;
    }

    public void setvardas(String vardas) {
        this.vardas = vardas;
    }

    public String getpavarde() {
        return pavarde;
    }

    public void setpavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public String gettelnr() {
        return telnr;
    }

    public void settelnr(String telnr) {
        this.telnr = telnr;
    }

    public String getadresas() {
        return adresas;
    }

    public void setadresas(String adresas) {
        this.adresas = adresas;
    }

    public String getpastas() {
        return pastas;
    }

    public void setpastas(String pastas)  { this.pastas = pastas; }

    public String getmodelis() { return modelis; }

    public void setmodelis(String modelis) { this.modelis = modelis; }

    public String getprietaisas() { return prietaisas; }

    public void setprietaisas(String prietaisas) { this.prietaisas = prietaisas; }

    public String getprietaisoSN() { return prietaisoSN; }

    public void setprietaisoSN(String prietaisoSN) { this.prietaisoSN = prietaisoSN; }

    public String getgedimas() { return gedimas; }

    public void setgedimas(String gedimas) { this.gedimas = gedimas; }

    public String getlaikas() { return laikas; }

    public void setlaikas(String laikas) { this.laikas = laikas; }
}

