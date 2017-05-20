package karten;

import main.Spieler;

public class Siegpunkt extends Karte
{

  public Siegpunkt()
  {
    super(KartenTyp.SIEGPUNKT);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    s.setSiegpunkte(s.getSiegpunkte() + 1);
  }

}
