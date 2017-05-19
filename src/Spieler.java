import java.util.Random;

/**
 * Created by Dustin on 17.05.2017.
 */

//TODO: Liste mit den Gebäuden des Spielers einfügen
/* Jeder Spieler besitzt
 *  5 Siedlungen
 *  4 Städte
 * 15 Straßen 
 */
public class Spieler
{
	private Farbe farbe;
	private String name;
	private int []rohstoffe;
	
	public Spieler(Farbe farbe, String name)
	{
		this.farbe = farbe;
		this.name = name;
		rohstoffe = new int[5];
	}

	public String getName()
	{
		return this.name;
	}

	public Farbe getFarbe()
	{
		return this.farbe;
	}
	public int wuerfeln()
	{
		Random zufallsgenerator = new Random();
		
		return zufallsgenerator.nextInt(11) + 2;
	}

  public int getRohstoffe(RohstoffTyp typ)
  {
    return rohstoffe[typ.getNummer()];
  }

  public void setRohstoffe(RohstoffTyp typ, int anzahl)
  {
    rohstoffe[typ.getNummer()] = anzahl;
  }
  
  public void incRohstoffe(RohstoffTyp typ, int anzahl)
  {
    setRohstoffe(typ, getRohstoffe(typ) + anzahl);
  }
  
  public void decRohstoffe(RohstoffTyp typ, int anzahl)
  {
    setRohstoffe(typ, getRohstoffe(typ) - anzahl);
  }
}