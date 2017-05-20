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

// TODO: Liste mit den Geb�uden des Spielers einf�gen
/*
 * Jeder Spieler besitzt
 * 5 Siedlungen
 * 4 St�dte
 * 15 Stra�en
 */
public class Spieler
{
  private static int idCounter = 0;
  private int siegpunkte;
  private int id;
  private Farbe farbe;
  private String name;
  private Rohstoffe rohstoffe;
  private List<Wurmloch> wurmlochListe; // TODO Wird die Liste noch ben�tigt?
  private List<Metropole> metropolenListe; // TODO Wird die Liste noch ben�tigt?
  private List<Kolonie> kolonienListe; // TODO Wird die Liste noch ben�tigt?
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

  /*
   * F�r die init des Spiels, wenn man am anfang zwei Wurml�cher setzen darf und f�r die Ereigniskarte Stra�enbau
   */
  public boolean baueWurmlochKostenlos(Koordinate k)
  {
    if (spielfeld.kannGebaeudeGebautWerden(k, 'S'))
    {
      Wurmloch w = new Wurmloch(k, id);
      wurmlochListe.add(w);
      spielfeld.setzeWurmloch(w);
      return true;
    }
    else
    {
      System.out.println("Das Wurmloch kann an der angegebenen Stelle nicht gebaut werden.");
      return false;
    }
  }

  /*
   * F�r die init des Spiels, wenn man am anfang zwei Wurml�cher setzen darf
   */
  public boolean baueKolonieKostenlos(Koordinate k)
  {
    if (spielfeld.kannGebaeudeGebautWerden(k, 'G'))
    {
      Kolonie kolonie = new Kolonie(k, id);
      kolonienListe.add(kolonie);
      spielfeld.setzeKolonie(kolonie);
      return true;
    }
    else
    {
      System.out.println("Das Kolonie kann an der angegebenen Stelle nicht gebaut werden.");
      return false;
    }
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
    // TODO: Es muss noch getestet werden ob das Kolonie-Objekt aus der Liste vom Spieler gel�scht wird.
    if (rohstoffe.ausreichendRohstoffeVorhanden(Wurmloch.getKosten()))
    {
      if (spielfeld.kannKolonieAufgewertetWerden(k, this))
      {
        rohstoffe.subRohstoffe(Metropole.getKosten());
        Metropole m = new Metropole(k, id);
        metropolenListe.add(m);
        kolonienListe.remove(spielfeld.setzeMetropole(m));
      }
      else
      {
        System.out.println("An der angegebennen Stelle ist es nicht moeglich ein Upgrade zur Metropole durchzufuehren.");
      }
    }
    else
    {
      System.out.println("Du besitzt nicht genuegen Rohstoffe.");
    }
  }

  // TODO Auswertung der Siegespunkte

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
              + "6 --> Karte spielen\n" + "7 --> Bauen Beenden");
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

  public void setSiegpunkte(int siegpunkte)
  {
    this.siegpunkte = siegpunkte;
    // TODO �berpr�fen ob gewonnen;
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
}