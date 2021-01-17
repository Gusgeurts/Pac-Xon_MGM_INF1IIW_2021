/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Gus Geurts
 */
public final class Spoken {

    private ArrayList<Spook> spoken;
    private final int aantalSpoken;
    private final Speelveld vakkenSpeelveld;

    public Spoken(int aantalSpoken, Speelveld vakkenSpeelveld) {
        this.aantalSpoken = aantalSpoken;
        this.vakkenSpeelveld = vakkenSpeelveld;
        maakSpoken();
    }

    public void maakSpoken() {
        spoken = new ArrayList<>();
        for (int i = 0; i < aantalSpoken; i++) {
            spoken.add(new Spook((200 + i * Math.random() * 100), (200 + i * Math.random() * 100)));
        }
    }

    public ArrayList<Spook> getSpoken() {
        return spoken;
    }

    public int getAantalSpoken() {
        return aantalSpoken;
    }

}
