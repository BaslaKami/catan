package felder;

public  class Gebaeude extends Feld
{
  private int spielerId;
  private Koordinate pos;
	private static final int kostenEnergie = 0;
	private static final int kostenNahrung = 0;
	private static final int kostenRoboter = 0;
	private static final int kostenMineralien = 0;
	private static final int kostenMunition = 0;

	public Gebaeude(int x, int y)
	{
	  this(new Koordinate(x,y));
	}
	
	public Gebaeude(Koordinate k)
  {
	  this(k, null, 0);
  }
	
	public Gebaeude(Koordinate k, FeldTyp typ, int spielerId)
  {
	  super(typ);
    pos = k;
    this.spielerId = spielerId;
    //setTyp(typ);
  }
	
	public int getPosX()
	{
	  return pos.getPosX();
	}
	
	public int getPosY()
  {
    return pos.getPosY();
  }

  public static int getKostenenergie()
  {
    return kostenEnergie;
  }

  public static int getKostenmineralien()
  {
    return kostenMineralien;
  }

  public static int getKostenroboter()
  {
    return kostenRoboter;
  }

  public static int getKostennahrung()
  {
    return kostenNahrung;
  }

  public static int getKostenmunition()
  {
    return kostenMunition;
  }

  public int getSpielerId()
  {
    return spielerId;
  }
}
