package model;

/**
 * @author Gus Geurts/Michiel Meurice
 */
public class Vak {

    private final int zijde;
    private StatusVak status;
    private boolean gevaar;

    /**
     * Deze methode geeft alle variabelen een begin/start waarde
     */
    public Vak() {
        zijde = 20;
        status = StatusVak.LEEG;
        gevaar = false;
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
     * deze methode geeft gevaar een nieuwe status
     */
    public void setGevaar(boolean gevaar){
        this.gevaar = gevaar;
    }
    
    /**
     * @return geeft terug of er gevaar heerst in het vak
     */
    public boolean getGevaar(){
        return gevaar;
    }
}
