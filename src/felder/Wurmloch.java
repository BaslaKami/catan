package felder;

public class Wurmloch extends Gebaeude
{
  private static final int kostenEnergie = 0;
  private static final int kostenNahrung = 0;
  private static final int kostenRoboter = 0;
  private static final int kostenMineralien = 0;
  private static final int kostenMunition = 0;
  
  public Wurmloch(Koordinate k, int spieler)
  {
    super(k, FeldTyp.WURMLOCH, spieler);
  }
//  
//	public Wurmloch(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
//	{
//		super(name, feld, kostenEnergie, kostenRoboter, kostenMineralien, kostenMunition);
//	}
	
}
