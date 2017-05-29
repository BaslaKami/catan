package karten;

import main.RohstoffTyp;
import main.Spieler;

public class Subvention extends Karte
{

  public Subvention()
  {
    super(KartenTyp.SUBVENTION);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    /*
     * Der Spieler bekommt zwei Rohstoffkarten seiner Wahl
     */
    
    System.out.println("Waehle welchen Rohstoff du bekommen moechtest");
    
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
