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
    
    //TODO Rohstoffart vom Benutzer abfragen
    s.getRohstoffe().addRohstoffe(RohstoffTyp.ENERGIE, 1);
    
    //TODO Rohstoffart vom Benutzer abfragen
    s.getRohstoffe().addRohstoffe(RohstoffTyp.MINERALIEN, 1);
  }

}
