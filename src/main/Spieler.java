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

/*
 * Jeder Spieler besitzt
 * 5 Siedlungen
 * 4 Staedte
 * 15 Strassen
 */
public class Spieler
{
  private final static int ANZAHL_WURMLOECHER = 15;
  private final static int ANZAHL_KOLONIEN = 5;
  private final static int ANZAHL_METROPOLEN = 4;

  private static int idCounter = 0;
  private int siegpunkte;
  private int id;
  private Farbe farbe;
  private String name;
  private Rohstoffe rohstoffe;
  private List<Wurmloch> wurmlochListe; // TODO Wird die Liste noch benoetigt?
  private List<Metropole> metropolenListe; // TODO Wird die Liste noch benoetigt?
  private List<Kolonie> kolonienListe; // TODO Wird die Liste noch benoetigt?
  private Spielfeld spielfeld;
  private Spiel spiel;
  private List<Karte> karten;
  private int anzahlRitter;
  private int laengsteHandelsstrasse;

  public Spieler(Farbe farbe, String name, Spielfeld spielfeld, Spiel spiel)
  {
    laengsteHandelsstrasse = 0;
    siegpunkte = 0;
    anzahlRitter = 0;
    id = idCounter++;
    this.farbe = farbe;
    this.name = name;
    this.spielfeld = spielfeld;
    this.spiel = spiel;

    wurmlochListe = new LinkedList<Wurmloch>();
    metropolenListe = new LinkedList<Metropole>();
    kolonienListe = new LinkedList<Kolonie>();

    setKarten(new LinkedList<Karte>());

    // setRohstoffe(new Rohstoffe()); //TODO wieder rein
    setRohstoffe(new Rohstoffe(30, 30, 30, 30, 30)); // TODO raus nur f�r tests
  }

  public void zug()
  {
    wuerfeln();
  }

  /*
   * Fuer die init des Spiels, wenn man am anfang zwei Wurmloecher setzen darf und fuer die Ereigniskarte Strassenbau
   */
  public boolean baueWurmlochKostenlos(Koordinate k)
  {
    if (spielfeld.istFrei(k, 'S'))
    {
      if (wurmlochListe.size() < ANZAHL_WURMLOECHER)
      {
        Wurmloch w = new Wurmloch(k, id);
        wurmlochListe.add(w);
        spielfeld.setzeWurmloch(w);
        laengsteHandelsstrasse();
        return true;
      }
      else
      {
        System.out.println("Du hast die maximale Anzahl an Wurmloechern bereits gebaut.");
      }
    }
    else
    {
      System.out.println("Das Wurmloch kann an der angegebenen Stelle nicht gebaut werden.");
    }
    return false;
  }

  private void laengsteHandelsstrasse()
  {
    laengsteHandelsstrasse = spielfeld.getLaengsteStrasse(this);

    if (spiel.getSpielerMitLaengsterHandelsstrasse() != this
        && (spiel.getSpielerMitLaengsterHandelsstrasse() == null || laengsteHandelsstrasse > spiel.getSpielerMitLaengsterHandelsstrasse().getLaengsteHandelsstrasse())
        && laengsteHandelsstrasse > 4)
    {
      if(spiel.getSpielerMitLaengsterHandelsstrasse() != null)
      {
        spiel.getSpielerMitLaengsterHandelsstrasse().decSiegpunkte(2);
      }
      spiel.setSpielerMitLaengsterHandelsstrasse(this);
      incSiegpunkte(2);
    }
  }

  /*
   * Fuer die init des Spiels, wenn man am anfang zwei Wurmloecher setzen darf
   */
  public boolean baueKolonieKostenlos(Koordinate k)
  {
    if (spielfeld.istFrei(k, 'G'))
    {
      if (kolonienListe.size() < ANZAHL_KOLONIEN)
      {
        Kolonie kolonie = new Kolonie(k, id);
        kolonienListe.add(kolonie);
        spielfeld.setzeKolonie(kolonie);
        incSiegpunkte(1);
        return true;
      }
      else
      {
        System.out.println("Du hast die maximale Anzahl an Kolonien bereits gebaut.");
      }
    }
    else
    {
      System.out.println("Das Kolonie kann an der angegebenen Stelle nicht gebaut werden.");
    }
    return false;
  }

  public void baueWurmloch(Koordinate k)
  {
    if (rohstoffe.ausreichendRohstoffeVorhanden(Wurmloch.getKosten()))
    {
      if (baueWurmlochKostenlos(k))
      {
        rohstoffe.subRohstoffe(Wurmloch.getKosten());
      }
    }
    else
    {
      System.out.println("Du besitzt nicht genuegen Rohstoffe.");
    }
  }

  public void baueKolonie(Koordinate k)
  {
    if (rohstoffe.ausreichendRohstoffeVorhanden(Wurmloch.getKosten()))
    {
      if (baueKolonieKostenlos(k))
      {
        rohstoffe.subRohstoffe(Kolonie.getKosten());
      }
    }
    else
    {
      System.out.println("Du besitzt nicht genuegen Rohstoffe.");
    }
  }

  public void baueMetropole(Koordinate k)
  {
    // TODO: Es muss noch getestet werden ob das Kolonie-Objekt aus der Liste vom Spieler geloescht wird.
    if (rohstoffe.ausreichendRohstoffeVorhanden(Wurmloch.getKosten()))
    {
      if (spielfeld.kannKolonieAufgewertetWerden(k, this))
      {
        if (metropolenListe.size() < ANZAHL_METROPOLEN)
        {
          rohstoffe.subRohstoffe(Metropole.getKosten());
          Metropole m = new Metropole(k, id);
          metropolenListe.add(m);
          kolonienListe.remove(spielfeld.setzeMetropole(m));
          incSiegpunkte(1);
        }
        else
        {
          System.out.println("Du hast die maximale Anzahl an Metropolen bereits gebaut.");
        }
      }
      else
      {
        System.out
            .println("An der angegebennen Stelle ist es nicht moeglich ein Upgrade zur Metropole durchzufuehren.");
      }
    }
    else
    {
      System.out.println("Du besitzt nicht genuegen Rohstoffe.");
    }
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
      rohstoffe.entferneZufaellig(anzahlRohstoffe / 2);
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

  public void wuerfeln()
  {
    int zahl;
    if (spiel.DEBUG)
    {
      zahl = spiel.getBenutzereingabe().getInteger("Gib Gewuerfelte zahl ein: ");
    }
    else
    {
      zahl = new Random().nextInt(11) + 2;
    }

    System.out.println("Wurf: " + zahl);

    if (zahl == 7)
    {
      for (int i = 0; i < spiel.getSpielerListe().getSize(); i++)
      {
        spiel.getSpielerListe().getSpieler(i).haelfteDerRohstoffeWerdenEntfernt();
      }
      // TODO: Eingabe durch Benutzer von den Koordinaten des Weltraumpiraten

      bewegeWeltraumpirat(new Koordinate(5, 14), spiel.getWeltraumpirat(), spiel.getSpielerListe());
    }
    else
    {
      for (int i = 0; i < spiel.getSpielerListe().getSize(); i++)
      {
        spiel.getSpielerListe().getSpieler(i).getRohstoffe()
            .addRohstoffe(spielfeld.getRohstoffeFuerSpieler(spiel.getSpielerListe().getSpieler(i), zahl));
      }
    }
  }

  public void baueErsteGebaeude()
  {
    // TODO kann an die eingegebene Stelle gebaut werden?
    spielfeld.print();
    baueWurmlochKostenlos(spiel.getBenutzereingabe().getKoordinate("Baue kostenloses Wurmloch"));
    spielfeld.print();
    baueWurmlochKostenlos(spiel.getBenutzereingabe().getKoordinate("Baue kostenloses Wurmloch"));
    spielfeld.print();

    baueKolonieKostenlos(spiel.getBenutzereingabe().getKoordinate("Baue kostenlose Kolonie"));
    spielfeld.print();
    baueKolonieKostenlos(spiel.getBenutzereingabe().getKoordinate("Baue kostenlose Kolonie"));
    spielfeld.print();

    getAlleRohstoffevonKolonie(spiel.getBenutzereingabe().getKoordinate("Waehle Kolonie fuer die ersten Rohstoffe"));
  }

  public void baueGebaeude()
  {
    int eingabe;
    boolean bauenBeenden = false;
    do
    {
      eingabe = spiel.getBenutzereingabe()
          .getInteger("Waehle welches Gebaeude du bauen moechtest\n" + "1 --> Wurmloch\n" + "2 --> Kolonie\n"
              + "3 --> Metropole\n" + "4 --> Spielfeld anzeigen\n" + "5 --> Rohstoffe Anzeigen\n"
              + "6 --> Karte spielen\n" + "7 --> L�ngste Stra�e\n" + "8 --> Bauen Beenden");
      switch (eingabe)
      {
        case 1:
        {
          baueWurmloch(spiel.getBenutzereingabe().getKoordinate("Baue Wurmloch"));
          spielfeld.print();
          break;
        }
        case 2:
        {
          baueKolonie(spiel.getBenutzereingabe().getKoordinate("Baue Kolonie"));
          spielfeld.print();
          break;
        }
        case 3:
        {
          baueMetropole(spiel.getBenutzereingabe().getKoordinate("Baue Metropole"));
          spielfeld.print();
          break;
        }
        case 4:
        {
          spielfeld.print();
          break;
        }
        case 5:
        {
          printRohstoffe(true);
          break;
        }
        case 6:
        {
          // TODO Karte spielen
          break;
        }
        case 7:
        {
          System.out.println("Laengste Stra�e: " + spielfeld.getLaengsteStrasse(this));
          break;
        }
        default:
        {
          bauenBeenden = true;
          break;
        }
      }
    } while (bauenBeenden == false);
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

  public void incSiegpunkte(int menge)
  {
    setSiegpunkte(getSiegpunkte() + menge);
  }

  public void decSiegpunkte(int menge)
  {
    setSiegpunkte(getSiegpunkte() - menge);
  }

  public void setSiegpunkte(int siegpunkte)
  {
    this.siegpunkte = siegpunkte;

    if (siegpunkte >= 10) // TODO Variable f�r die ben�tigten Siegpunkte anlegen
    {
      System.out.println(name + " hat das Spiel gewonnen.");
    }
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

  public void zieheKarte()
  {
    rohstoffe.subRohstoffe(Karte.getKosten());
    karten.add(spiel.karteZiehen());
  }

  public void bewegeWeltraumpirat(Koordinate koordinate, Weltraumpirat w, SpielerListe spielerListe)
  {
    List<Gebaeude> gebaeudeListe = spielfeld.bewegeWeltraumpirat(koordinate, w);
    for (Gebaeude g : gebaeudeListe)
    {
      if (g.getSpielerId() != getId())
      {
        getRohstoffe()
            .addRohstoffe(spielerListe.getSpieler(g.getSpielerId()).getRohstoffe().entferneZufaelligEinenRohstoff(), 1);
      }
    }
  }

  public void printRohstoffe(boolean kopfZeile)
  {
    if (kopfZeile)
    {
      System.out.printf("%c %c %c %c %c %c\n", 'S', RohstoffTyp.ENERGIE.getRohstoff().charAt(1),
          RohstoffTyp.NAHRUNG.getRohstoff().charAt(1), RohstoffTyp.ROBOTER.getRohstoff().charAt(1),
          RohstoffTyp.MINERALIEN.getRohstoff().charAt(1), RohstoffTyp.MUNITION.getRohstoff().charAt(1));
    }
    System.out.printf("%d %d %d %d %d %d\n", getId(), rohstoffe.getRohstoffe(RohstoffTyp.ENERGIE),
        rohstoffe.getRohstoffe(RohstoffTyp.NAHRUNG), rohstoffe.getRohstoffe(RohstoffTyp.ROBOTER),
        rohstoffe.getRohstoffe(RohstoffTyp.MINERALIEN), rohstoffe.getRohstoffe(RohstoffTyp.MUNITION));
  }

  public List<Karte> getKarten()
  {
    return karten;
  }

  public void setKarten(List<Karte> karten)
  {
    this.karten = karten;
  }

  public int getAnzahlRitter()
  {
    return anzahlRitter;
  }

  public int getLaengsteHandelsstrasse()
  {
    return laengsteHandelsstrasse;
  }

  public void setAnzahlRitter(int anzahlRitter)
  {
    this.anzahlRitter = anzahlRitter;
  }

  public void incAnazahlRitter()
  {
    setAnzahlRitter(getAnzahlRitter() + 1);
  }
}