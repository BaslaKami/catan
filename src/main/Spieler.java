package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import felder.Gebaeude;
import felder.Kolonie;
import felder.Koordinate;
import felder.Metropole;
import felder.Weltraumpirat;
import felder.Wurmloch;
import karten.Karte;

/**
 * Created by Dustin on 17.05.2017.
 */

// TODO: Liste mit den Gebäuden des Spielers einfügen
/*
 * Jeder Spieler besitzt
 * 5 Siedlungen
 * 4 Städte
 * 15 Straßen
 */
public class Spieler
{
  private static int idCounter = 0;
  private int siegpunkte;
  private int id;
  private Farbe farbe;
  private String name;
  private Rohstoffe rohstoffe;
  private List<Wurmloch> wurmlochListe;   //TODO Wird die Liste noch benötigt?
  private List<Metropole> metropolenListe; //TODO Wird die Liste noch benötigt?
  private List<Kolonie> kolonienListe; //TODO Wird die Liste noch benötigt?
  private Spielfeld spielfeld;
  private Spiel spiel;
  private List<Karte> karten;

  public Spieler(Farbe farbe, String name, Spielfeld spielfeld, Spiel spiel)
  {
    siegpunkte = 0;
    id = idCounter++;
    this.farbe = farbe;
    this.name = name;
    this.spielfeld = spielfeld;
    this.spiel = spiel;

    wurmlochListe = new LinkedList<Wurmloch>();
    metropolenListe = new LinkedList<Metropole>();
    kolonienListe = new LinkedList<Kolonie>();
    
    setKarten(new LinkedList<Karte>());

    setRohstoffe(new Rohstoffe());
  }

  public void zug()
  {
    wuerfeln();

  }

  public void baueWurmloch(Koordinate k)
  {
    rohstoffe.subRohstoffe(Wurmloch.getKosten());
    Wurmloch w = new Wurmloch(k, id);
    wurmlochListe.add(w);
    spielfeld.setzeWurmloch(w);
  }

  public void baueKolonie(Koordinate k)
  {
    rohstoffe.subRohstoffe(Kolonie.getKosten());
    Kolonie kolonie = new Kolonie(k, id);
    kolonienListe.add(kolonie);
    spielfeld.setzeKolonie(kolonie);
  }

  public void baueMetropole(Koordinate k)
  {
    // TODO: Es muss noch getestet werden ob das Kolonie-Objekt aus der Liste vom Spieler gelöscht wird.
    rohstoffe.subRohstoffe(Metropole.getKosten());
    Metropole m = new Metropole(k, id);
    metropolenListe.add(m);
    kolonienListe.remove(spielfeld.setzeMetropole(m));
  }

  public void karteSpielen(Karte k)
  {
    k.ausspielen(this);
  }

  public void haelfteDerRohstoffeWerdenEntfernt()
  {
    int anzahlRohstoffe = rohstoffe.anzahlDerGesamtenRohstoffe();

    if (anzahlRohstoffe > 7)
    {
      rohstoffe.entferneZufällig(anzahlRohstoffe / 2);
    }
  }

  public void getAlleRohstoffevonKolonie(Koordinate k)
  {
    rohstoffe.addRohstoffe(spielfeld.getAlleRohstoffevonKolonie(k));
  }

  public String getName()
  {
    return this.name;
  }

  public Farbe getFarbe()
  {
    return this.farbe;
  }

  public int wuerfeln()
  {
    return new Random().nextInt(11) + 2;
  }

  private void setRohstoffe(Rohstoffe rohstoffe)
  {
    this.rohstoffe = rohstoffe;
  }

  public Rohstoffe getRohstoffe()
  {
    return rohstoffe;
  }

  public int getSiegpunkte()
  {
    return siegpunkte;
  }

  public void setSiegpunkte(int siegpunkte)
  {
    this.siegpunkte = siegpunkte;
    // TODO überprüfen ob gewonnen;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }
  
  public Spiel getSpiel()
  {
    return spiel;
  }

  public void bewegeWeltraumpirat(Koordinate koordinate, Weltraumpirat w, SpielerListe spielerListe)
  {
    List<Gebaeude> gebaeudeListe = spielfeld.bewegeWeltraumpirat(koordinate, w);
    for (Gebaeude g : gebaeudeListe)
    {
      if (g.getSpielerId() != getId())
      {
        getRohstoffe().addRohstoffe(spielerListe.getSpieler(g.getSpielerId()).getRohstoffe().entferneZufälligEinenRohstoff(), 1);
      }
    }
  }

  public List<Karte> getKarten()
  {
    return karten;
  }

  public void setKarten(List<Karte> karten)
  {
    this.karten = karten;
  }
}