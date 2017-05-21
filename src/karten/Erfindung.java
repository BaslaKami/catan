package karten;

import main.RohstoffTyp;
import main.Spieler;

public class Erfindung extends Karte
{

  public Erfindung()
  {
    super(KartenTyp.ERFINDUNG);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    /*
     * Der Spieler bekommt zwei Rohstoffkarten seiner Wahl
     */
    
    System.out.println("Wähle welchen Rohstoff du bekommen möchtest");
    
    RohstoffTyp []typen = RohstoffTyp.values();
    
    for(int i = 0; i < 5; i++)
    {
      System.out.println(typen[i].getNummer() + " " + typen[i]);
    }
    
    int benutzereingabe = s.getSpiel().getBenutzereingabe().getInteger("Rohstoff eins");
    s.getRohstoffe().addRohstoffe(typen[benutzereingabe], 1);
    
    benutzereingabe = s.getSpiel().getBenutzereingabe().getInteger("Rohstoff zwei");
    s.getRohstoffe().addRohstoffe(typen[benutzereingabe], 1);
  }

}
