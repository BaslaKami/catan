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
    
    for(int i = 0; i < 4; i++)
    {
      rohstoffTypListe.add(RohstoffTyp.ENERGIE);
      rohstoffTypListe.add(RohstoffTyp.MINERALIEN);
      rohstoffTypListe.add(RohstoffTyp.MUNITION);
    }
    
    for(int i = 0; i < 3; i++)
    {
      rohstoffTypListe.add(RohstoffTyp.NAHRUNG);
      rohstoffTypListe.add(RohstoffTyp.ROBOTER);  //TODO: richtige Zuweisung
    }
    
    for (int zeile = 1; zeile < 11; zeile+=2)
    {
      for (int spalte = 2; spalte < 19; spalte++)
      {
        if(feldTyp[zeile][spalte] == 'P')
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
    
    // Straßen und Gebäude Zeilen einfügen
    for (int zeile = 0; zeile < 11; zeile += 2)
    {
      for (int spalte = 0; spalte < 21 - 1; spalte += 2)
      {
        feldTyp[zeile][spalte] = 'G';
        feldTyp[zeile][spalte + 1] = 'S';
      }
      feldTyp[zeile][feldTyp[zeile].length - 1] = 'G';
    }

    // Straßen und Planeten einfügen
    for (int zeile = 1; zeile < 11; zeile += 4)
    {
      //ungerade Planetenzeilen
      for (int spalte = 0; spalte < 21 - 2; spalte += 4)
      {
        feldTyp[zeile][spalte] = 'S';
        feldTyp[zeile][spalte + 2] = 'P';
      }
      //gerade Planetenzeilen
      for (int spalte = 0; spalte < 21 - 2 && zeile<=5; spalte += 4)
      {
        feldTyp[zeile+2][spalte] = 'P';
        feldTyp[zeile+2][spalte + 2] = 'S';
      }
      
      feldTyp[zeile][feldTyp[zeile].length - 1] = 'S';
    }
    
    //Einträge löschen
    for (int zeile = 0; zeile < 11; zeile+=2)
    {
      for (int spalte = 0; spalte < 4-zeile; spalte++)
      {
        //Links oben
        feldTyp[zeile][spalte] = ' ';
        feldTyp[zeile+1][spalte] = ' ';
        
        //rechts oben
        feldTyp[zeile][21-spalte-1] = ' ';
        feldTyp[zeile+1][21-spalte-1] = ' ';
        
        //Links unten
        feldTyp[11-zeile-1][spalte] = ' ';
        feldTyp[11-zeile-2][spalte] = ' ';
        
        //rechts unten
        feldTyp[11-zeile-1][21-spalte-1] = ' ';
        feldTyp[11-zeile-2][21-spalte-1] = ' ';
      }
    }
    //Toten Planeten aus der mitte entfernen
    feldTyp[feldTyp.length/2][feldTyp[feldTyp.length/2].length/2] = ' ';
  }

  public void print()
  {
    for (int zeile = 0; zeile < 11; zeile++)
    {
      for (int spalte = 0; spalte < 21; spalte++)
      {
        System.out.print(feldTyp[zeile][spalte] + " ");
      }
      System.out.println();
    }
    
    for (int zeile = 0; zeile < 11; zeile++)
    {
      for (int spalte = 0; spalte < 21; spalte++)
      {
        if(felder[zeile][spalte] == null)
        {
          System.out.print("  ");
        }
        else
        {
          System.out.print(((Planet)felder[zeile][spalte]).getRohstoffTyp().toString().charAt(1));
        }
      }
      System.out.println();
    }
  }
}
