package karten;

import felder.Koordinate;
import main.Spieler;

public class Strassenbau extends Karte
{

  public Strassenbau()
  {
    super(KartenTyp.STRASSENBAU);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    s.baueWurmlochKostenlos(s.getSpiel().getBenutzereingabe().getKoordinate("Gebe die Koordinaten f�r das erste Wurmloch an"));
    s.baueWurmlochKostenlos(s.getSpiel().getBenutzereingabe().getKoordinate("Gebe die Koordinaten f�r das zweite Wurmloch an"));
  }

}
