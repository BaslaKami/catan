package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import felder.Feld;
import felder.FeldTyp;
import felder.Gebaeude;
import felder.Kolonie;
import felder.Koordinate;
import felder.Metropole;
import felder.Planet;
import felder.Weltraumpirat;
import felder.Wurmloch;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Spielfeld
{
  // private List<Feld> felder;
  private char[][] feldTyp;
  private Feld[][] felder;
  private static final int HOEHE = 11;
  private static final int BREITE = 21;

  public Spielfeld()
  {
    feldTyp = new char[HOEHE][BREITE];
    felder = new Feld[HOEHE][BREITE];
    erstelleFeldTypTabelle();
    erstelleSpielfeld();
    print();

    // Generiere 18 Planeten
    // 9 Enklaven
    // 10 Weltraum
  }

  public void erstelleSpielfeld()
  {
    System.out.println("\n***Spielfeld wird erstellt");
    List<RohstoffTyp> rohstoffTypListe = new LinkedList<RohstoffTyp>();
    Random zufallsgenerator = new Random();
    int zufallszahl;

    for (int i = 0; i < 4; i++)
    {
      rohstoffTypListe.add(RohstoffTyp.ENERGIE);
      rohstoffTypListe.add(RohstoffTyp.MINERALIEN);
      rohstoffTypListe.add(RohstoffTyp.NAHRUNG);
    }

    for (int i = 0; i < 3; i++)
    {
      rohstoffTypListe.add(RohstoffTyp.MUNITION);
      rohstoffTypListe.add(RohstoffTyp.ROBOTER);
    }

    for (int zeile = 1; zeile < HOEHE; zeile += 2)
    {
      for (int spalte = 2; spalte < 19; spalte++)
      {
        if (feldTyp[zeile][spalte] == 'P')
        {
          zufallszahl = zufallsgenerator.nextInt(rohstoffTypListe.size());
          felder[zeile][spalte] = new Planet(rohstoffTypListe.get(zufallszahl));
          rohstoffTypListe.remove(zufallszahl);
        }
      }
    }
  }

  public void erstelleFeldTypTabelle()
  {
    for (int zeile = 0; zeile < HOEHE; zeile++)
    {
      for (int spalte = 0; spalte < BREITE; spalte++)
      {
        feldTyp[zeile][spalte] = ' ';
      }
    }

    // Stra�en und Geb�ude Zeilen einf�gen
    for (int zeile = 0; zeile < HOEHE; zeile += 2)
    {
      for (int spalte = 0; spalte < BREITE - 1; spalte += 2)
      {
        feldTyp[zeile][spalte] = 'G';
        feldTyp[zeile][spalte + 1] = 'S';
      }
      feldTyp[zeile][feldTyp[zeile].length - 1] = 'G';
    }

    // Stra�en und Planeten einf�gen
    for (int zeile = 1; zeile < HOEHE; zeile += 4)
    {
      // ungerade Planetenzeilen
      for (int spalte = 0; spalte < BREITE - 2; spalte += 4)
      {
        feldTyp[zeile][spalte] = 'S';
        feldTyp[zeile][spalte + 2] = 'P';
      }
      // gerade Planetenzeilen
      for (int spalte = 0; spalte < BREITE - 2 && zeile <= 5; spalte += 4)
      {
        feldTyp[zeile + 2][spalte] = 'P';
        feldTyp[zeile + 2][spalte + 2] = 'S';
      }

      feldTyp[zeile][feldTyp[zeile].length - 1] = 'S';
    }

    // Eintr�ge l�schen
    for (int zeile = 0; zeile < HOEHE; zeile += 2)
    {
      for (int spalte = 0; spalte < 4 - zeile; spalte++)
      {
        // Links oben
        feldTyp[zeile][spalte] = ' ';
        feldTyp[zeile + 1][spalte] = ' ';

        // rechts oben
        feldTyp[zeile][BREITE - spalte - 1] = ' ';
        feldTyp[zeile + 1][BREITE - spalte - 1] = ' ';

        // Links unten
        feldTyp[11 - zeile - 1][spalte] = ' ';
        feldTyp[11 - zeile - 2][spalte] = ' ';

        // rechts unten
        feldTyp[11 - zeile - 1][BREITE - spalte - 1] = ' ';
        feldTyp[11 - zeile - 2][BREITE - spalte - 1] = ' ';
      }
    }
    // Toten Planeten aus der mitte entfernen
    feldTyp[feldTyp.length / 2][feldTyp[feldTyp.length / 2].length / 2] = ' ';

    //TODO Enklaven wieder einkommentieren oder Handel zwischen zwei spielern realisieren
    //erstelleEnklaven();
  }

  private void erstelleEnklaven()
  {
    // TODO: Enklaven m�ssen so verteilt werden das 9 Planeten Anschluss dazu
    // haben
    feldTyp[0][4] = 'E';
    feldTyp[0][6] = 'E';

    feldTyp[0][16] = 'E';
    feldTyp[2][16] = 'E';

    feldTyp[6][20] = 'E';
    feldTyp[8][16] = 'E';

    feldTyp[10][8] = 'E';
    feldTyp[10][6] = 'E';

    feldTyp[6][2] = 'E';
  }

  public void print()
  {
    System.out.print("                        1                   2");
    System.out.print("                            1                   2");
    System.out.println("                            1                   2");
    System.out.print("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0");
    System.out.print("        0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0");
    System.out.println("        0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0");
    System.out.println();

    for (int zeile = 0; zeile < HOEHE; zeile++)
    {
      printReferenzFeld(zeile);
      printPlanetZufallszahlen(zeile);
      printPlanetTypen(zeile);
      System.out.println();
    }
    System.out.println();
    
    // System.out.println("\033[0m BLACK");
    // System.out.println("\033[31m RED");
    // System.out.println("\033[32m GREEN");
    // System.out.println("\033[33m YELLOW");
    // System.out.println("\033[34m BLUE");
    // System.out.println("\033[35m MAGENTA");
    // System.out.println("\033[36m CYAN");
    // System.out.println("\033[37m WHITE");
    // System.out.println("Hello \u001b[1;31m"+ "red" + "\u001b[0m world!");
  }
  
  private void printReferenzFeld(int zeile)
  {
    System.out.printf("%02d  ", zeile);
    for (int spalte = 0; spalte < BREITE; spalte++)
    {

      switch (feldTyp[zeile][spalte])
      {
        case 'G':
        {
          System.out.print("\033[36m" + feldTyp[zeile][spalte]);
          break;
        }
        case 'E':
        {
          System.out.print("\033[1;31m" + feldTyp[zeile][spalte]);
          break;
        }
        case 'P':
        {
          System.out.print("\033[1;34m" + feldTyp[zeile][spalte]);
          break;
        }
        default:
        {
          System.out.print(feldTyp[zeile][spalte]);
          break;
        }
      }
      System.out.print("\033[0m" + " ");

    }
  }
  
  private void printPlanetZufallszahlen(int zeile)
  {
    System.out.print("   ");

    System.out.printf("%02d  ", zeile);

    // Ausgabe von felder
    for (int spalte = 0; spalte < BREITE; spalte++)
    {
      if (felder[zeile][spalte] != null)
      {
        switch (felder[zeile][spalte].getTyp())
        {
          case PLANET:
          {
            // System.out.print("P ");
            System.out.printf("\033[1;31m%x \033[0m", ((Planet) felder[zeile][spalte]).getErtragsnummer());
            break;
          }
          case WURMLOCH:
          {
            //System.out.print("W ");
            printGebaeude('W', ((Gebaeude)felder[zeile][spalte]).getSpielerId());
            break;
          }
          case METROPOLE:
          {
            //System.out.print("M ");
            printGebaeude('M', ((Gebaeude)felder[zeile][spalte]).getSpielerId());
            break;
          }
          case KOLONIE:
          {
            //System.out.print("K ");
            printGebaeude('K', ((Gebaeude)felder[zeile][spalte]).getSpielerId());
            break;
          }
          default:
          {
            System.out.print(". ");
            break;
          }

        }
      }
      else
      {
        System.out.print(". ");
      }
    }
  }
  
  private void printGebaeude(char gebaeudeKuerzel, int spielerId)
  {
    String farbe;
    switch(spielerId)
    {
      case 0:
      {
        farbe = "\033[1;34m";
        break;
      }
      case 1:
      {
        farbe = "\033[2;33m";
        break;
      }
      case 2:
      {
        farbe = "\033[2;32m";
        break;
      }
      default:
      {
        farbe = "\033[2;31m";
        break;
      }
    }
    
    System.out.print(farbe + gebaeudeKuerzel + " " + "\u001b[0m");
  }
  
  private void printPlanetTypen(int zeile)
  {
    System.out.print("   ");

    System.out.printf("%02d  ", zeile);

    // Ausgabe der PlanetenRohstoffTypen
    for (int spalte = 0; spalte < BREITE; spalte++)
    {
      if (felder[zeile][spalte] != null)
      {
        switch (felder[zeile][spalte].getTyp())
        {
          case PLANET:
          {
            // System.out.print("P ");
            System.out.printf("\033[1;31m%c \033[0m",
                ((Planet) felder[zeile][spalte]).getRohstoff().getRohstoff().charAt(1));
            break;
          }
          case WURMLOCH:
          {
            System.out.print("W ");
            break;
          }
          case METROPOLE:
          {
            System.out.print("M ");
            break;
          }
          case KOLONIE:
          {
            System.out.print("K ");
            break;
          }
          default:
          {
            System.out.print(". ");
            break;
          }
        }
      }
      else
      {
        System.out.print(". ");
      }
    }
  }

  public static int getBreite()
  {
    return BREITE;
  }

  public static int getHoehe()
  {
    return HOEHE;
  }
  
  public boolean kannGebaeudeGebautWerden(Koordinate k, char gebaeudeTyp)
  {
    return feldTyp[k.getPosX()][k.getPosY()] == gebaeudeTyp && felder[k.getPosX()][k.getPosY()] == null;
  }
  
  public boolean kannKolonieAufgewertetWerden(Koordinate k, Spieler s)
  {
    return feldTyp[k.getPosX()][k.getPosY()] == 'G' && felder[k.getPosX()][k.getPosY()] != null && ((Gebaeude)felder[k.getPosX()][k.getPosY()]).getSpielerId() == s.getId();
  }

  public void setzeWurmloch(Wurmloch w)
  {
    felder[w.getPosX()][w.getPosY()] = w;
  }

  public void setzeKolonie(Kolonie k)
  {
    felder[k.getPosX()][k.getPosY()] = k;
  }

  public Kolonie setzeMetropole(Metropole m)
  {
    Kolonie k = (Kolonie) felder[m.getPosX()][m.getPosY()];
    felder[m.getPosX()][m.getPosY()] = m;
    return k;
  }

  public Rohstoffe getAlleRohstoffevonKolonie(Koordinate k)
  {
    Rohstoffe rohstoffe = new Rohstoffe();

    // direkt �ber dem Geb�ude
    if (k.getPosX() > 0 && feldTyp[k.getPosX() - 1][k.getPosY()] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() - 1][k.getPosY()]).getRohstoff(), 1);
    }

    // unter dem Geb�ude rechts
    if (k.getPosX() + 1 < HOEHE && k.getPosY() + 2 < BREITE && feldTyp[k.getPosX() - 1][k.getPosY() + 2] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() - 1][k.getPosY() + 2]).getRohstoff(), 1);
    }

    // unter dem Geb�ude links
    if (k.getPosX() + 1 < HOEHE && k.getPosY() > 1 && feldTyp[k.getPosX() - 1][k.getPosY() - 2] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() - 1][k.getPosY() - 2]).getRohstoff(), 1);
    }

    // direkt unter dem Geb�ude
    if (k.getPosX() < HOEHE - 1 && feldTyp[k.getPosX() + 1][k.getPosY()] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() + 1][k.getPosY()]).getRohstoff(), 1);
    }

    // �ber dem Geb�ude rechts
    if (k.getPosX() > 0 && k.getPosY() + 2 < BREITE && feldTyp[k.getPosX() + 1][k.getPosY() + 2] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() + 1][k.getPosY() + 2]).getRohstoff(), 1);
    }

    // �ber dem Geb�ude links
    if (k.getPosX() > 0 && k.getPosY() > 1 && feldTyp[k.getPosX() + 1][k.getPosY() - 2] == 'P')
    {
      rohstoffe.addRohstoffe(((Planet) felder[k.getPosX() + 1][k.getPosY() - 2]).getRohstoff(), 1);
    }

    return rohstoffe;
  }

  public Rohstoffe getRohstoffeFuerSpieler(Spieler spieler, int zahl)
  {
    Rohstoffe rohstoffe = new Rohstoffe();

    // TODO: Mit getAngrenzendeGebaeude(Koordinate k) Funktion �bersichtlicher gestalten

    // Finde Planten mit der gew�rfelten Zahl
    for (int zeile = 0; zeile < HOEHE; zeile++)
    {
      for (int spalte = 0; spalte < BREITE; spalte++)
      {
        if (feldTyp[zeile][spalte] == 'P' && ((Planet) felder[zeile][spalte]).getErtragsnummer() == zahl
            && ((Planet) felder[zeile][spalte]).getWeltraumpirat() == null)
        {
          // Planet hat die gew�rfelte Nummer

          // Geb�ude direkt �ber dem Planeten
          if (zeile > 0 && felder[zeile - 1][spalte] != null
              && ((Gebaeude) felder[zeile - 1][spalte]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile - 1][spalte]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

          // Geb�ude direkt unter dem Planeten
          if (zeile < HOEHE - 1 && felder[zeile + 1][spalte] != null
              && ((Gebaeude) felder[zeile + 1][spalte]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile + 1][spalte]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

          // Geb�ude links �ber dem Planeten
          if (zeile > 0 && spalte > 1 && felder[zeile - 1][spalte - 2] != null
              && ((Gebaeude) felder[zeile - 1][spalte - 2]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile - 1][spalte - 2]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

          // Geb�ude rechts �ber dem Planeten
          if (zeile > 0 && spalte < BREITE - 2 && felder[zeile - 1][spalte + 2] != null
              && ((Gebaeude) felder[zeile - 1][spalte + 2]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile - 1][spalte + 2]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

          // Geb�ude links unter dem Planeten
          if (zeile < HOEHE - 1 && spalte > 1 && felder[zeile + 1][spalte - 2] != null
              && ((Gebaeude) felder[zeile + 1][spalte - 2]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile + 1][spalte - 2]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

          // Geb�ude rechts unter dem Planeten
          if (zeile < HOEHE - 1 && spalte < BREITE - 2 && felder[zeile + 1][spalte + 2] != null
              && ((Gebaeude) felder[zeile + 1][spalte + 2]).getSpielerId() == spieler.getId())
          {
            // unterscheidung metropole zwei rohstoffe oder kolonie einen
            if (((Gebaeude) felder[zeile + 1][spalte + 2]).getTyp() == FeldTyp.METROPOLE)
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 2);
            }
            else
            {
              rohstoffe.addRohstoffe(((Planet) felder[zeile][spalte]).getRohstoff(), 1);
            }
          }

        }
      }
    }
    return rohstoffe;
  }

  public List<Gebaeude> bewegeWeltraumpirat(Koordinate k, Weltraumpirat w)
  {
    // List<Integer> spielerIds = new LinkedList<Integer>();

    ((Planet) felder[w.getPosition().getPosX()][w.getPosition().getPosY()]).setWeltraumpirat(null);
    w.bewegen(k);
    ((Planet) felder[k.getPosX()][k.getPosY()]).setWeltraumpirat(w);

    return getAngrenzendeGebaeude(k);
  }

  public List<Gebaeude> getAngrenzendeGebaeude(Koordinate k)
  {
    List<Gebaeude> gebaeudeListe = new LinkedList<Gebaeude>();

    // Geb�ude direkt �ber dem Planeten
    if (k.getPosX() > 0 && felder[k.getPosX() - 1][k.getPosY()] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() - 1][k.getPosY()]);
    }

    // Geb�ude direkt unter dem Planeten
    if (k.getPosX() < HOEHE - 1 && felder[k.getPosX() + 1][k.getPosY()] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() + 1][k.getPosY()]);
    }

    // Geb�ude links �ber dem Planeten
    if (k.getPosX() > 0 && k.getPosY() > 1 && felder[k.getPosX() - 1][k.getPosY() - 2] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() - 1][k.getPosY() - 2]);
    }

    // Geb�ude rechts �ber dem Planeten
    if (k.getPosX() > 0 && k.getPosY() < BREITE - 2 && felder[k.getPosX() - 1][k.getPosY() + 2] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() - 1][k.getPosY() + 2]);
    }

    // Geb�ude links unter dem Planeten
    if (k.getPosX() < HOEHE - 1 && k.getPosY() > 1 && felder[k.getPosX() + 1][k.getPosY() - 2] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() + 1][k.getPosY() - 2]);
    }

    // Geb�ude rechts unter dem Planeten
    if (k.getPosX() < HOEHE - 1 && k.getPosY() < BREITE - 2 && felder[k.getPosX() + 1][k.getPosY() + 2] != null)
    {
      gebaeudeListe.add((Gebaeude) felder[k.getPosX() + 1][k.getPosY() + 2]);
    }

    return gebaeudeListe;
  }
}
