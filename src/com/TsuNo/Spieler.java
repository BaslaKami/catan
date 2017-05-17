package com.TsuNo;

import java.util.Scanner;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Spieler {
    private Farbe farbe;
    private String name;

    private enum Farbe {
        BLAU(0, "blau"), GELB(1, "gelb"), GRUEN(2, "gruen"), ROT(3, "rot");

        private final int nummer;
        private final String name;

        Farbe(int nummer, String name) {
            this.nummer = nummer;
            this.name = name;
        }

        public int getNummer() {
            return nummer;
        }

        public String getName() {
            return name;
        }
    }

    public Spieler() {

    }

    public Spieler(int i) {
        erstelleSpieler(i);
    }

    private void erstelleSpieler(int i) {
        System.out.print("Spieler " + i + " Name eingeben: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.next();
        if(i == Farbe.BLAU.nummer) {
            farbe = Farbe.BLAU;
        } else if (i == Farbe.GELB.nummer) {
            farbe = Farbe.GELB;
        } else if (i == Farbe.GRUEN.nummer) {
            farbe = Farbe.GRUEN;
        } else if (i == Farbe.ROT.nummer) {
            farbe = Farbe.ROT;
        }
    }

    public void printSpieler() {
        System.out.println("***Spieler " + farbe.getNummer() + ": " + name + ", " + farbe.getName());
    }
}
