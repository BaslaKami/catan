package main;

import java.util.Scanner;

import felder.Koordinate;

public class Benutzereingabe
{
  Scanner scanner;
  
  public Benutzereingabe()
  {
    scanner = new Scanner(System.in);
  }
  
  public String getString(String s)
  {
    System.out.println(s);
    
    String eingabeString = null;
    
    try
    {
      if (scanner.hasNext())
      {
        eingabeString = scanner.next();
    
        if (eingabeString.isEmpty())
        {
          System.out.println("Klasse: Benutzereingabe; Funktion: getEingabe() --> Fehler kein String erhalten");
        }
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
    return eingabeString;
  }
  
  public int getInteger(String s)
  {
   int eingabe = 0;
   System.out.println("\n" + s);
    try
    {
      while (!scanner.hasNextInt()) {
        System.out.println("\n\033[31mZahl eingeben!\033[0m");
        scanner.next(); // Kein int? Dann weg damit und fortfahren.
      }
      eingabe = scanner.nextInt();
    }
    catch (Exception e)
    {
      System.out.println("Klasse: Benutzereingabe; Funktion: getInteger() --> Kein Integer eingegeben");
      System.out.println(e.toString());
    }
    return eingabe;
  }
  
  public Koordinate getKoordinate(String s)
  {
    int zeile;
    int spalte;
    
    System.out.println(s);
    
    do{
    zeile = getInteger("Geben Sie die Zeile an: ");
    spalte = getInteger("Geben Sie die Spalte an: ");
    }while(zeile < 0 || zeile > 10 || spalte < 0 || spalte > 20);
    
    return new Koordinate(zeile,spalte);
  }
  
  public void finalize()
  {
    scanner.close();
  }
}
