package karten;

import felder.Koordinate;
import main.Spieler;

public class Ritter extends Karte
{
  boolean ausgespielt;
  
  public Ritter()
  {
    super(KartenTyp.RITTER);
    ausgespielt = false;
  }

  @Override
  public void ausspielen(Spieler s)
  {
    // TODO: Eingabeaufforderung für die Koordinaten
    s.bewegeWeltraumpirat(new Koordinate(1,6), s.getSpiel().getWeltraumpirat(), s.getSpiel().getSpielerListe());
  }
}
