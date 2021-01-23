package model;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Vak {

    private final int zijde;
    private StatusVak status;
    private boolean gevaar;
    private boolean check;

    /**
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Vak() {
        zijde = 20;
        status = StatusVak.LEEG;
        gevaar = false;
        check = false;
    }

    /**
     * @return geeft de zijde van het vak terug
     */
    public int getZijde() {
        return zijde;
    }

    /**
     * @return geeft de status van het vak terug
     */
    public StatusVak getStatus() {
        return status;
    }

    /**
     * @param status is de status van het vak 
     * deze methode verandert de status van het vak
     */
    public void setStatus(StatusVak status) {
        this.status = status;
    }

    /**
     * @param gevaar is of er gevaar heerst in een vak 
     * deze methode geeft boolean gevaar een nieuwe status
     */
    public void setGevaar(boolean gevaar) {
        this.gevaar = gevaar;
    }

    /**
     * @return geeft terug of er gevaar heerst in het vak
     */
    public boolean getGevaar() {
        return gevaar;
    }

    /**
     * @return deze methode geeft terug of het vak gecheckt is of niet
     */
    public boolean getCheck() {
        return this.check;
    }

    /**
     * @param check is de parameter die bijhoud of een vak gecheckt is of niet
     * deze methode geeft boolean check een nieuwe status
     */
    public void setCheck(boolean check) {
        this.check = check;
    }
}
