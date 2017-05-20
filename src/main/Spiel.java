package main;

import java.util.Scanner;

import felder.Koordinate;
import felder.Weltraumpirat;
import karten.Karte;
import karten.Kartenstack;

public class Spiel
{
  public static final boolean DEBUG = true;
  private SpielerListe spielerListe;
  private Spielfeld spielfeld;
  private Bank bank;
  private Scanner scanner;
  private Kartenstack kartenstack;
  private Weltraumpirat weltraumpirat;
  private Benutzereingabe benutzereingabe;

  public static void main(String[] args)
  {
    new Spiel();
  }

  /*
   * 1. Abfrage Anzahl Spieler
   * 2. Spiel erzeugen
   * a. Spielfeld erzeugen
   * b. Spieler erzeugen
   * c. Kartenstack erzeugen
   * d. Weltraumpiraten erzeugen
   * e. Bank erzeugen
   * 3. Spieler 1 platziert zwei Stra�en und zwei Siedlungen
   * 4. Spieler 2 platziert zwei Stra�en und zwei Siedlungen
   * 5. Spieler 3 platziert zwei Stra�en und zwei Siedlungen
   * 6. Spieler 4 platziert zwei Stra�en und zwei Siedlungen (wenn existent)
   * 7. Spieler 1 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt
   * 8. Spieler 2 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt
   * 9. Spieler 3 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt
   * 10. Spieler 4 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt (wenn existent)
   *
   */
  public Spiel()
  {
    benutzereingabe = new Benutzereingabe();

    /** 1. Abfrage Anzahl Spieler */
    /** 2. Spiel erzeugen */
    /** a. Spielfeld erzeugen */
    spielfeld = new Spielfeld();

    /** b. Spieler erzeugen */
    spielerListe = new SpielerListe();
    scanner = new Scanner(System.in);
    erstelleSpieler();

    /** c. Kartenstack erzeugen */
    setKartenstack(new Kartenstack());

    /** d. Weltraumpiraten erzeugen */
    weltraumpirat = new Weltraumpirat();
    weltraumpirat.printPos();

    /** e. Bank erzeugen */
    bank = new Bank();

    spielerSetztenErsteGebaeude();

    printRohstoffeDerSpieler();

    System.out.println("Zug 1 Spieler 1\n");

    rundeStarten();
    //zug(spielerListe.getSpieler(0));
  }

  private void rundeStarten()
  {
    do
    {
      for (int i = 0; i < spielerListe.getSize(); i++)
      {
        zug(spielerListe.getSpieler(i));
      }
    } while (true); //TODO Abbruchbediengung durch sieg darstellen
  }

  private void spielerSetztenErsteGebaeude()
  {
    if (DEBUG)
    {
      spielerBauenErsteGebaeudeDEBUG();
    }
    else
    {
      for (int i = 0; i < spielerListe.getSize(); i++)
      {
        System.out.println(spielerListe.getSpieler(i).getName());
        spielerListe.getSpieler(i).baueErsteGebaeude();
      }
    }
  }

  public void spielerBauenErsteGebaeudeDEBUG()
  {
    /** 3. Spieler 1 platziert zwei Stra�en und zwei Siedlungen */
    spielerListe.getSpieler(0).baueWurmlochKostenlos(new Koordinate(4, 3));
    spielerListe.getSpieler(0).baueWurmlochKostenlos(new Koordinate(4, 5));

    spielerListe.getSpieler(0).baueKolonieKostenlos(new Koordinate(4, 2));
    spielerListe.getSpieler(0).baueKolonieKostenlos(new Koordinate(4, 6));

    /** 4. Spieler 2 platziert zwei Stra�en und zwei Siedlungen */
    spielerListe.getSpieler(1).baueWurmlochKostenlos(new Koordinate(8, 13));
    spielerListe.getSpieler(1).baueWurmlochKostenlos(new Koordinate(9, 12));

    spielerListe.getSpieler(1).baueKolonieKostenlos(new Koordinate(8, 12));
    spielerListe.getSpieler(1).baueKolonieKostenlos(new Koordinate(8, 14));

    /** 5. Spieler 3 platziert zwei Stra�en und zwei Siedlungen */
    spielerListe.getSpieler(2).baueWurmlochKostenlos(new Koordinate(4, 19));
    spielerListe.getSpieler(2).baueWurmlochKostenlos(new Koordinate(5, 20));

    spielerListe.getSpieler(2).baueKolonieKostenlos(new Koordinate(4, 18));
    spielerListe.getSpieler(2).baueKolonieKostenlos(new Koordinate(4, 20));
    spielfeld.print();

    /** 7. Spieler 1 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt */
    spielerListe.getSpieler(0).getAlleRohstoffevonKolonie(new Koordinate(4, 2));

    /** 8. Spieler 1 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt */
    spielerListe.getSpieler(1).getAlleRohstoffevonKolonie(new Koordinate(8, 14));

    /** 9. Spieler 1 bekommt die Rohstoffe f�r eine Siedlung ausgezahlt */
    spielerListe.getSpieler(2).getAlleRohstoffevonKolonie(new Koordinate(4, 20));
  }

  /*
   * 1. Spieler w�rfelt Rohstoffertr�ge aus
   * a. Die gew�rfelte Zahl legt fest welches Feld Rohstoffe gibt. Jedes Dorf an diesem Feld bekommt einen Rohstoff
   * dieser Art, jede Stadt zwei. Es gibt keine Rohstoffe von dem Feld auf dem der R�uber steht.
   * b. Falls die 7 Gew�rfelt wird
   * c. gibt jeder Spieler mit mehr als 7 Rohstoffen die H�lfte (abgerundet) ab.
   * d. Spieler stellt den R�uber um (aktuelle Feld ist nicht zul�ssig)
   * e. Der Spieler bekommt von den Spielern die auf dem neuen Feld des R�ubers eine Siedlung oder Stadt haben eine
   * zuf�llige Rohstoffkarte
   * 2. Spieler handelt
   * a. Spieler darf beliebig oft handeln
   * 3. Geb�ude bauen, Entwicklungskarten kaufen
   * a. Entwicklungskarte kann zu jeder Zeit des Zuges (1-3) ausgespielt werden
   * b. Es kann pro Zug immer nur genau eine Entwicklungskarte ausgespielt werden
   * c. Die Entwicklungskarte welche ausgespielt wird darf nicht w�hrend des aktuellen Zuges gekauft worden sein
   * 4. Zug beenden/n�chster Spieler beginnt mit Punkt 1
   */
  private void zug(Spieler s)
  {
    /*
     * 1. Spieler w�rfelt Rohstoffertr�ge aus
     * a. Die gew�rfelte Zahl legt fest welches Feld Rohstoffe gibt. Jedes Dorf an diesem Feld bekommt einen Rohstoff
     * dieser Art, jede Stadt zwei. Es gibt keine Rohstoffe von dem Feld auf dem der R�uber steht.
     * b. Falls die 7 Gew�rfelt wird
     * c. gibt jeder Spieler mit mehr als 7 Rohstoffen die H�lfte (abgerundet) ab.
     * d. Spieler stellt den R�uber um (aktuelle Feld ist nicht zul�ssig)
     * e. Der Spieler bekommt von den Spielern die auf dem neuen Feld des R�ubers eine Siedlung oder Stadt haben eine
     * zuf�llige Rohstoffkarte
     */
    s.wuerfeln();
    printRohstoffeDerSpieler();
    /*
     * 2. Spieler handelt
     * a. Spieler darf beliebig oft handeln
     */
    handeln(s);

    /*
     * 3. Geb�ude bauen, Entwicklungskarten kaufen
     * a. Entwicklungskarte kann zu jeder Zeit des Zuges (1-3) ausgespielt werden
     * b. Es kann pro Zug immer nur genau eine Entwicklungskarte ausgespielt werden
     * c. Die Entwicklungskarte welche ausgespielt wird darf nicht w�hrend des aktuellen Zuges gekauft worden sein
     */

    s.baueGebaeude();
  }

  private void handeln(Spieler s)
  {
    // TODO: Handeln
  }

  // private void w�rfeln(Spieler s)
  // {
  // int zahl = s.w�rfeln();
  // System.out.println("Wurf: " + zahl);
  //
  // if (zahl == 7)
  // {
  // for (int i = 0; i < spielerListe.getSize(); i++)
  // {
  // spielerListe.getSpieler(i).haelfteDerRohstoffeWerdenEntfernt();
  // }
  // // TODO: Eingabe durch Benutzer von den Koordinaten des Weltraumpiraten
  //
  // s.bewegeWeltraumpirat(new Koordinate(5, 14), weltraumpirat, spielerListe);
  // }
  // else
  // {
  // for (int i = 0; i < spielerListe.getSize(); i++)
  // {
  // spielerListe.getSpieler(i).getRohstoffe()
  // .addRohstoffe(spielfeld.getRohstoffeFuerSpieler(spielerListe.getSpieler(i), zahl));
  // //spielerListe.getSpieler(i).getRohstoffe().print();
  // }
  //
  // }
  // }

  private void printRohstoffeDerSpieler()
  {
    // System.out.printf("%c %c %c %c %c %c\n", 'S', RohstoffTyp.ENERGIE.getRohstoff().charAt(1),
    // RohstoffTyp.NAHRUNG.getRohstoff().charAt(1), RohstoffTyp.ROBOTER.getRohstoff().charAt(1),
    // RohstoffTyp.MINERALIEN.getRohstoff().charAt(1), RohstoffTyp.MUNITION.getRohstoff().charAt(1));
    spielerListe.getSpieler(0).printRohstoffe(true);
    for (int i = 1; i < spielerListe.getSize(); i++)
    {
      spielerListe.getSpieler(i).printRohstoffe(false);
    }
    System.out.println();
  }

  private void erstelleSpieler()
  {
    System.out.println("***Spiel wird erstellt***");

    if (DEBUG)
    {
      Spieler s = new Spieler(Farbe.BLAU, "eins", spielfeld, this);
      spielerListe.hinzufuegen(s);
      s = new Spieler(Farbe.GELB, "zwei", spielfeld, this);
      spielerListe.hinzufuegen(s);
      s = new Spieler(Farbe.GRUEN, "drei", spielfeld, this);
      spielerListe.hinzufuegen(s);
      // erstelleSpieler(3);
    }
    else
    {
      erstelleSpieler(anzahlSpieler());
    }

    getSpielerListe().ausgeben();

  }

  private void erstelleSpieler(int anzahl)
  {
    String name = null;
    Farbe farbe = Farbe.BLAU;

    Farbe[] f = Farbe.values();

    for (int i = 0; i < anzahl; i++)
    {
      try
      {
        System.out.println("Spieler" + " " + i + " " + "Name eingeben");

        if (scanner.hasNext())
        {
          name = scanner.next();

          if (!name.isEmpty())
          {
            farbe = f[i];

            Spieler s = new Spieler(farbe, name, spielfeld, this);
            spielerListe.hinzufuegen(s);
          }

        }

      }
      catch (Exception e)
      {
        System.out.println(e.toString());
      }
    }
  }

  private int anzahlSpieler()
  {
    int anzahl = 0;
    do
    {
      System.out.println("Geben Sie die Spieleranzahl(3-4) ein:");
      anzahl = scanner.nextInt();

    } while (anzahl != 3 && anzahl != 4);

    return anzahl;

  }

  public SpielerListe getSpielerListe()
  {
    return this.spielerListe;
  }

  public void finalize()
  {
    scanner.close();
  }

  public Weltraumpirat getWeltraumpirat()
  {
    return weltraumpirat;
  }

  private void setKartenstack(Kartenstack kartenstack)
  {
    this.kartenstack = kartenstack;
  }

  public Karte karteZiehen()
  {
    return (kartenstack.ziehen());
  }

  public void karteZuruecklegen(Karte k)
  {
    kartenstack.zuruecklegen(k);
  }

  public Benutzereingabe getBenutzereingabe()
  {
    return benutzereingabe;
  }

  public void setBenutzereingabe(Benutzereingabe benutzereingabe)
  {
    this.benutzereingabe = benutzereingabe;
  }
}
