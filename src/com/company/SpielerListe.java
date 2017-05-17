package com.company;

/**
 * Created by Dustin on 17.05.2017.
 */
public class SpielerListe {
    private Spieler[] spieler;
    private static int spieleranzahl;

    public SpielerListe(int spieleranzahl) {
        this.spieleranzahl = spieleranzahl;

        spieler = new Spieler[spieleranzahl];
        erstelleListe();

        System.out.println("\n***SpielerListe:");
        for(int i = 0; i < spieleranzahl; i++) {
            spieler[i].printSpieler();
        }
    }

    public void erstelleListe() {
        System.out.println("***" + spieleranzahl + " Spieler werden erstellt***");
        for(int i = 0; i < spieleranzahl; i++) {
            spieler[i] = new Spieler(i);
        }
    }

    public Spieler getSpielerByNummer(int nummer) {
        return spieler[nummer];
    }

    public int getSpieleranzahl() {
        return spieleranzahl;
    }
}
