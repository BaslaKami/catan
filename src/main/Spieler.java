package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import felder.Gebaeude;
import felder.Kolonie;
import felder.Koordinate;
import felder.Metropole;
import felder.Spielfeld;
import felder.Weltraumpirat;
import felder.Wurmloch;
import karten.Karte;
import karten.KartenTyp;

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
  private List<Wurmloch> wurmlochListe; 
  private List<Metropole> metropolenListe; 
  private List<Kolonie> kolonienListe;
  private Spielfeld spielfeld;
  private Spiel spiel;
  private Benutzereingabe benutzereingabe;
  private List<Karte> karten;
  private int anzahlRitter;
  private int laengsteWurmlochverbindung;

  public Spieler(Farbe farbe, String name, Spielfeld spielfeld, Spiel spiel)
  {
    laengsteWurmlochverbindung = 0;
    siegpunkte = 0;
    anzahlRitter = 0;
    id = idCounter++;
    this.farbe = farbe;
    this.name = name;
    this.spielfeld = spielfeld;
    this.spiel = spiel;
    benutzereingabe = spiel.getBenutzereingabe();

    wurmlochListe = new LinkedList<Wurmloch>();
    metropolenListe = new LinkedList<Metropole>();
    kolonienListe = new LinkedList<Kolonie>();

    setKarten(new LinkedList<Karte>());

    //setRohstoffe(new Rohstoffe());
    setRohstoffe(new Rohstoffe(30, 30, 30, 30, 30));
  }
  
  public void zug()
  {
    alleKartenkoennenGespieltWerden();
    /*
     * 1. Spieler wuerfelt Rohstoffertraege aus
     * a. Die gewuerfelte Zahl legt fest welches Feld Rohstoffe gibt. Jedes Dorf an diesem Feld bekommt einen Rohstoff
     * dieser Art, jede Stadt zwei. Es gibt keine Rohstoffe von dem Feld auf dem der Raeuber steht.
     * b. Falls die 7 Gewuerfelt wird
     * c. gibt jeder Spieler mit mehr als 7 Rohstoffen die Haelfte (abgerundet) ab.
     * d. Spieler stellt den Raeuber um (aktuelle Feld ist nicht zulaessig)
     * e. Der Spieler bekommt von den Spielern die auf dem neuen Feld des Raeubers eine Siedlung oder Stadt haben eine
     * zufaellige Rohstoffkarte
     */
    wuerfeln();
    
    spiel.printRohstoffeDerSpieler();
    /*
     * 2. Spieler handelt
     * a. Spieler darf beliebig oft handeln
     */
    starteHandel();

    /*
     * 3. Gebaeude bauen, Entwicklungskarten kaufen
     * a. Entwicklungskarte kann zu jeder Zeit des Zuges (1-3) ausgespielt werden
     * b. Es kann pro Zug immer nur genau eine Entwicklungskarte ausgespielt werden
     * c. Die Entwicklungskarte welche ausgespielt wird darf nicht waehrend des aktuellen Zuges gekauft worden sein
     */

    baueGebaeude();
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
        laengsteWurmlochverbindung();
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

  private void laengsteWurmlochverbindung()
  {
    laengsteWurmlochverbindung = spielfeld.getLaengsteWurmlochverbindung(this);

    if (spiel.getSpielerMitLaengsterWurmlochVerbindung() != this
        && (spiel.getSpielerMitLaengsterWurmlochVerbindung() == null
            || laengsteWurmlochverbindung > spiel.getSpielerMitLaengsterWurmlochVerbindung().getLaengsteWurmlochverbindung())
        && laengsteWurmlochverbindung > 4)
    {
      if (spiel.getSpielerMitLaengsterWurmlochVerbindung() != null)
      {
        spiel.getSpielerMitLaengsterWurmlochVerbindung().decSiegpunkte(2);
      }
      spiel.setSpielerMitLaengsterWurmlochVerbindung(this);
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
  
  public void alleKartenkoennenGespieltWerden()
  {
    for(Karte k: karten)
    {
      k.setInAktuellerRundeGezogen(false);
    }
  }

  public void wuerfeln()
  {
    int zahl;
    if (Spiel.DEBUG)
    {
      zahl = benutzereingabe.getInteger("Gib Gewuerfelte zahl ein: ");
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

      bewegeWeltraumpirat(benutzereingabe.getKoordinate("Gib die neue Positon des Weltraumpiraten an"), spiel.getWeltraumpirat(), spiel.getSpielerListe());
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
    baueWurmlochKostenlos(benutzereingabe.getKoordinate("Baue kostenloses Wurmloch"));
    spielfeld.print();
    baueWurmlochKostenlos(benutzereingabe.getKoordinate("Baue kostenloses Wurmloch"));
    spielfeld.print();

    baueKolonieKostenlos(benutzereingabe.getKoordinate("Baue kostenlose Kolonie"));
    spielfeld.print();
    baueKolonieKostenlos(benutzereingabe.getKoordinate("Baue kostenlose Kolonie"));
    spielfeld.print();

    getAlleRohstoffevonKolonie(benutzereingabe.getKoordinate("Waehle Kolonie fuer die ersten Rohstoffe"));
  }

  private void printKarten()
  {
    System.out.println("Karten");
    int i = 0;
    for (Karte k : karten)
    {
      System.out.println(i++ + " " + k.getKartenTyp());
    }
    System.out.println();
  }

  public void baueGebaeude()
  {
    int eingabe;
    boolean bauenBeenden = false;
    do
    {
      printKarten();
      System.out.println("Siegespunkte: " + siegpunkte);
      eingabe = benutzereingabe
          .getInteger("Waehle welches Gebaeude du bauen moechtest\n" + "1 --> Wurmloch\n" + "2 --> Kolonie\n"
              + "3 --> Metropole\n" + "4 --> Spielfeld anzeigen\n" + "5 --> Rohstoffe Anzeigen\n"
              + "6 --> Karte ziehen\n" + "7 --> Karte spielen\n" + "8 --> Laengste Wurmlochverbindung\n" + "9 --> Bauen Beenden");
      switch (eingabe)
      {
        case 1:
        {
          baueWurmloch(benutzereingabe.getKoordinate("Baue Wurmloch"));
          spielfeld.print();
          break;
        }
        case 2:
        {
          baueKolonie(benutzereingabe.getKoordinate("Baue Kolonie"));
          spielfeld.print();
          break;
        }
        case 3:
        {
          baueMetropole(benutzereingabe.getKoordinate("Baue Metropole"));
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
          karteZiehen();

          break;
        }
        case 7:
        {
          karteSpielen();
          break;
        }
        case 8:
        {
          System.out.println("Laengste Wurmlochverbindung: " + spielfeld.getLaengsteWurmlochverbindung(this));
          break;
        }
        default:
        {
          bauenBeenden = true;
          break;
        }
      }
    } while (bauenBeenden == false && spiel.isSpielZuEnde() == false);
  }

  private void karteSpielen()
  {
    int kartenNummer = benutzereingabe.getInteger("Welche Karte soll gespielt werden?");
    if(karten.get(kartenNummer).isInAktuellerRundeGezogen() == false)
    {
    karten.get(kartenNummer).ausspielen(this);
    karten.remove(kartenNummer);
    }
    else
    {
      System.out.println("Die Karte kann nicht gespielt werden, da sie erst in dieser Runde gekauft worden ist.");
    }
  }

  private void karteZiehen()
  {
    if (rohstoffe.ausreichendRohstoffeVorhanden(Karte.getKosten()))
    {
      Karte k = spiel.getKartenstack().ziehen();
      if(k.getKartenTyp() == KartenTyp.SIEGPUNKT)
      {
        k.ausspielen(this);
      }
      else
      {
        karten.add(k);
      }
      
      rohstoffe.subRohstoffe(Karte.getKosten());
    }
  }

  public Spieler waehleSpieler()
  {
    int eingabe;
    Spieler ausgewaehlterSpieler = null;
    String auswahlString = "Waehle einen Spieler:\n";
    SpielerListe auswahlListe = new SpielerListe();
    int count = 1;

    for (Spieler s : spiel.getSpielerListe().getListe())
    {
      if (s.getId() != id)
      {
        auswahlListe.hinzufuegen(s);
        auswahlString += count++ + " --> " + s.getName() + "\n";
      }
    }

    do
    {
      eingabe = benutzereingabe.getInteger(auswahlString);
      if (eingabe > 0 && eingabe <= auswahlListe.getSize())
      {
        ausgewaehlterSpieler = auswahlListe.getSpieler(eingabe - 1);
      }
    } while (ausgewaehlterSpieler == null);

    return ausgewaehlterSpieler;
  }

  public RohstoffTyp waehleRohstoffTyp(Spieler s)
  {
    int eingabe;
    boolean gueltigerRohstoff = false;
    RohstoffTyp[] rohstoffe = RohstoffTyp.values();
    String auswahlString = "Waehle einen Rohstoff:\n";

    for (RohstoffTyp typ : RohstoffTyp.values())
    {
      auswahlString += typ.getNummer() + 1 + " " + typ.getRohstoff() + "\n";
    }
    do
    {
      eingabe = benutzereingabe.getInteger(auswahlString) - 1;

      if (eingabe >= 0 && eingabe < rohstoffe.length)
      {
        if (s.rohstoffe.getRohstoffe(rohstoffe[eingabe]) <= 0)
        {
          System.out.println("\n\033[31mRohstoff nicht vorhanden!\033[0m");
        }
        else
        {
          gueltigerRohstoff = true;
        }
      }
    } while (!gueltigerRohstoff);

    return rohstoffe[eingabe];
  }

  public int waehleRohstoffAnzahl(Spieler s, RohstoffTyp rohstoffTyp)
  {
    int anzahl;
    boolean ausreichendVorhanden = false;
    do
    {
      anzahl = benutzereingabe.getInteger("Anzahl der " + rohstoffTyp.getRohstoff());
      if (anzahl > 0 && anzahl <= s.rohstoffe.getRohstoffe(rohstoffTyp))
      {
        ausreichendVorhanden = true;
      }
      else
      {
        System.out.println("\n\033[31mAnzahl nicht vorhanden!\033[0m");
      }
    } while (!ausreichendVorhanden);

    return anzahl;
  }

  public void starteHandel()
  {
    int eingabe;
    boolean handelnBeenden = false;
    do
    {
      // Mit Spieler handeln, case 2 Bank erweiterbar.
      eingabe = benutzereingabe.getInteger("Waehle mit wem du handeln moechtest\n1 --> Spieler\n2 --> Handeln beenden");
      switch (eingabe)
      {
        case 1:
        {
          spiel.spielerHandel(this);
          break;
        }
        default:
        {
          handelnBeenden = true;
          break;
        }
      }
    } while (handelnBeenden == false);
  }

  private void setRohstoffe(Rohstoffe rohstoffe)
  {
    this.rohstoffe = rohstoffe;
  }

  public Rohstoffe getRohstoffe()
  {
    return rohstoffe;
  }

  public int getRohstoffAnzahl(RohstoffTyp typ)
  {
    return rohstoffe.getRohstoffe(typ);
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

    if (this.siegpunkte >= Spiel.getBenoetigteSiegpunkte())
    {
      System.out.println(name + " hat das Spiel gewonnen.");
      spiel.setSpielZuEnde(true);
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
      if (g.getSpielerId() != id)
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
      System.out.printf("%2c %2c %2c %2c %2c %2c\n", 'S', RohstoffTyp.ENERGIE.getRohstoff().charAt(1),
          RohstoffTyp.NAHRUNG.getRohstoff().charAt(1), RohstoffTyp.ROBOTER.getRohstoff().charAt(1),
          RohstoffTyp.MINERALIEN.getRohstoff().charAt(1), RohstoffTyp.MUNITION.getRohstoff().charAt(1));
    }
    System.out.printf("%2d %2d %2d %2d %2d %2d\n", getId(), rohstoffe.getRohstoffe(RohstoffTyp.ENERGIE),
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

  public int getLaengsteWurmlochverbindung()
  {
    return laengsteWurmlochverbindung;
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