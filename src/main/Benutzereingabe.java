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
  
  private String getString()
  {
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
   System.out.println(s);
    try
    {
      if (scanner.hasNextInt())
      {
        eingabe = scanner.nextInt();
      }
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
    System.out.println(s);
   
    int zeile = getInteger("Geben Sie die Zeile an: ");
    int spalte = getInteger("Geben Sie die Spalte an: ");
    
    return new Koordinate(zeile,spalte);
  }
}
