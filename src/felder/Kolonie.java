package felder;

public class Kolonie extends Gebaeude
{
  public Kolonie(Koordinate k, int spieler)
  {
    super(k, FeldTyp.KOLONIE, spieler);
  }

//	public Kolonie(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
//	{
//		super(name, feld, kostenEnergie, kostenRoboter, kostenMineralien, kostenMunition);
//	}

}
