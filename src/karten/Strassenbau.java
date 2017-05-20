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
    //TODO zwei eingabeaufforderungen für die Koordinaten
    s.baueWurmloch(new Koordinate(8,3));
    s.baueWurmloch(new Koordinate(4,7));
  }

}
