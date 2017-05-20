package felder;

import main.Rohstoffe;

public class Kolonie extends Gebaeude
{
  private static final int kostenEnergie = 1;
  private static final int kostenNahrung = 1;
  private static final int kostenRoboter = 1;
  private static final int kostenMineralien = 1;
  private static final int kostenMunition = 0;
  
  
  public Kolonie(Koordinate k, int spieler)
  {
    super(k, FeldTyp.KOLONIE, spieler);
  }

//	public Kolonie(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
//	{
//		super(name, feld, kostenEnergie, kostenRoboter, kostenMineralien, kostenMunition);
//	}

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
  public static Rohstoffe getKosten()
  {
    return new Rohstoffe(kostenEnergie, kostenNahrung, kostenRoboter, kostenMineralien, kostenMunition);
  }
  
}
