package karten;

import main.Spieler;

public class Wurmlochbau extends Karte
{

  public Wurmlochbau()
  {
    super(KartenTyp.WURMLOCHBAU);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    s.baueWurmlochKostenlos(s.getSpiel().getBenutzereingabe().getKoordinate("Gebe die Koordinaten für das erste Wurmloch an"));
    s.baueWurmlochKostenlos(s.getSpiel().getBenutzereingabe().getKoordinate("Gebe die Koordinaten für das zweite Wurmloch an"));
  }

}
