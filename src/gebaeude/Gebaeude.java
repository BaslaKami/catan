package gebaeude;

public  class Gebaeude
{
	//private String name;
	//private int feld;
  private Koordinate pos;
  private GebaeudeTyp typ;
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
	  this(k, null);
  }
	
	public Gebaeude(Koordinate k, GebaeudeTyp typ)
  {
    pos = k;
    setTyp(typ);
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

  public GebaeudeTyp getTyp()
  {
    return typ;
  }

  public void setTyp(GebaeudeTyp typ)
  {
    this.typ = typ;
  }
	
//	public Gebaeude(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
//	{
//		this.name = name;
//		this.feld = feld;
//		this.kostenEnergie = kostenEnergie;
//		this.kostenRoboter = kostenRoboter;
//		this.kostenMineralien = kostenMineralien;
//		this.kostenMunition = kostenMunition;
//	}
}
