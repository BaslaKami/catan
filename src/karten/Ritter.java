package karten;

import felder.Koordinate;
import main.Spieler;

public class Ritter extends Karte
{
  boolean ausgespielt;
  Spieler spielerMitGrossterRittermacht; // TODO umbenenen fuer unser Spiel

  public Ritter()
  {
    super(KartenTyp.RITTER);
    ausgespielt = false;
  }

  @Override
  public void ausspielen(Spieler s)
  {
    s.incAnazahlRitter();

    if (spielerMitGrossterRittermacht == null)
    {
      if (s.getAnzahlRitter() >= 3)
      {
        spielerMitGrossterRittermacht = s;
        s.incSiegpunkte(2);
      }
    }
    else
    {
      if (spielerMitGrossterRittermacht.getAnzahlRitter() < s.getAnzahlRitter())
      {
        s.incSiegpunkte(2);
        spielerMitGrossterRittermacht.decSiegpunkte(2);

        spielerMitGrossterRittermacht = s;
      }
    }

    // TODO abfangen wenn angegebene Position nicht müglich
    s.bewegeWeltraumpirat(
        s.getSpiel().getBenutzereingabe().getKoordinate("Gebe die Koordinate fuer den Weltraumpirat an"),
        s.getSpiel().getWeltraumpirat(), s.getSpiel().getSpielerListe());
  }
}
