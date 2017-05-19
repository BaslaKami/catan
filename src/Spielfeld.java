import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Spielfeld
{
  // private List<Feld> felder;
  private char[][] feldTyp;
  private Object[][] felder;

  public Spielfeld()
  {
    feldTyp = new char[11][21];
    felder = new Object[11][21];
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

    for (int zeile = 1; zeile < 11; zeile += 2)
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
    for (int zeile = 0; zeile < 11; zeile++)
    {
      for (int spalte = 0; spalte < 21; spalte++)
      {
        feldTyp[zeile][spalte] = ' ';
      }
    }

    // Stra�en und Geb�ude Zeilen einf�gen
    for (int zeile = 0; zeile < 11; zeile += 2)
    {
      for (int spalte = 0; spalte < 21 - 1; spalte += 2)
      {
        feldTyp[zeile][spalte] = 'G';
        feldTyp[zeile][spalte + 1] = 'S';
      }
      feldTyp[zeile][feldTyp[zeile].length - 1] = 'G';
    }

    // Stra�en und Planeten einf�gen
    for (int zeile = 1; zeile < 11; zeile += 4)
    {
      // ungerade Planetenzeilen
      for (int spalte = 0; spalte < 21 - 2; spalte += 4)
      {
        feldTyp[zeile][spalte] = 'S';
        feldTyp[zeile][spalte + 2] = 'P';
      }
      // gerade Planetenzeilen
      for (int spalte = 0; spalte < 21 - 2 && zeile <= 5; spalte += 4)
      {
        feldTyp[zeile + 2][spalte] = 'P';
        feldTyp[zeile + 2][spalte + 2] = 'S';
      }

      feldTyp[zeile][feldTyp[zeile].length - 1] = 'S';
    }

    // Eintr�ge l�schen
    for (int zeile = 0; zeile < 11; zeile += 2)
    {
      for (int spalte = 0; spalte < 4 - zeile; spalte++)
      {
        // Links oben
        feldTyp[zeile][spalte] = ' ';
        feldTyp[zeile + 1][spalte] = ' ';

        // rechts oben
        feldTyp[zeile][21 - spalte - 1] = ' ';
        feldTyp[zeile + 1][21 - spalte - 1] = ' ';

        // Links unten
        feldTyp[11 - zeile - 1][spalte] = ' ';
        feldTyp[11 - zeile - 2][spalte] = ' ';

        // rechts unten
        feldTyp[11 - zeile - 1][21 - spalte - 1] = ' ';
        feldTyp[11 - zeile - 2][21 - spalte - 1] = ' ';
      }
    }
    // Toten Planeten aus der mitte entfernen
    feldTyp[feldTyp.length / 2][feldTyp[feldTyp.length / 2].length / 2] = ' ';

    erstelleEnklaven();
  }

  private void erstelleEnklaven()
  {
    //TODO: Enklaven m�ssen so verteilt werden das 9 Planeten Anschluss dazu haben
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
    System.out.println("                        1                   2");
    System.out.println("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0");
    System.out.println();
    for (int zeile = 0; zeile < 11; zeile++)
    {
      System.out.printf("%02d  ", zeile);
      for (int spalte = 0; spalte < 21; spalte++)
      {
        switch(feldTyp[zeile][spalte])
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
      System.out.println();
    }
    
//    System.out.println("\033[0m BLACK");
//    System.out.println("\033[31m RED");
//    System.out.println("\033[32m GREEN");
//    System.out.println("\033[33m YELLOW");
//    System.out.println("\033[34m BLUE");
//    System.out.println("\033[35m MAGENTA");
//    System.out.println("\033[36m CYAN");
//    System.out.println("\033[37m WHITE");
//    System.out.println("Hello \u001b[1;31m"+ "red" + "\u001b[0m world!");
  }
}