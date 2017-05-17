package com.TsuNo;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Planet extends Feld {
    private int ertragsnummer;
    private boolean ertrag;

    private enum PlanetTyp {
        ENERGIE, AGRAR, INDUSTRIE, MINERAL, AUSSENPOSTEN, TOT;
    }
}
