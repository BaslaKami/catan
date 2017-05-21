package karten;

import main.Rohstoffe;
import main.Spieler;

public abstract class Karte
{
  private static final int kostenEnergie = 1;
  private static final int kostenNahrung = 1;
  private static final int kostenRoboter = 0;
  private static final int kostenMineralien = 0;
  private static final int kostenMunition = 1;
  
	private KartenTyp kartenTyp;
	private boolean inAktuellerRundeGezogen = true;
	
	public Karte(KartenTyp kartenTyp)
	{
	  this.setKartenTyp(kartenTyp);
	}

  public KartenTyp getKartenTyp()
  {
    return kartenTyp;
  }

  public void setKartenTyp(KartenTyp kartenTyp)
  {
    this.kartenTyp = kartenTyp;
  }
  
  public static Rohstoffe getKosten()
  {
    return new Rohstoffe(kostenEnergie, kostenNahrung, kostenRoboter, kostenMineralien, kostenMunition);
  }
  
  public static int getKostenenergie()
  {
    return kostenEnergie;
  }

  public static int getKostennahrung()
  {
    return kostenNahrung;
  }

  public static int getKostenroboter()
  {
    return kostenRoboter;
  }

  public static int getKostenmineralien()
  {
    return kostenMineralien;
  }

  public static int getKostenmunition()
  {
    return kostenMunition;
  }
  
  //public abstract void ausspielen();

  public abstract void ausspielen(Spieler s);

  public boolean isInAktuellerRundeGezogen()
  {
    return inAktuellerRundeGezogen;
  }

  public void setInAktuellerRundeGezogen(boolean inAktuellerRundeGezogen)
  {
    this.inAktuellerRundeGezogen = inAktuellerRundeGezogen;
  }
}
