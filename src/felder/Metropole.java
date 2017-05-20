package felder;

import main.Rohstoffe;

public class Metropole extends Gebaeude
{
  private static final int kostenEnergie = 0;
  private static final int kostenNahrung = 2;
  private static final int kostenRoboter = 0;
  private static final int kostenMineralien = 0;
  private static final int kostenMunition = 3;
  
  public Metropole(Koordinate k, int spieler)
  {
    super(k, FeldTyp.METROPOLE, spieler);
  }

//	public Metropole(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
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
