import java.util.Random;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Planet extends Feld
{
	private int ertragsnummer;
	//private boolean ertrag;
	//private PlanetTyp typ;
	private RohstoffTyp rohstoffTyp;
	
	public Planet(RohstoffTyp rohstoffTyp)
	{
	  this.setRohstoffTyp(rohstoffTyp);
	  Random zufallsgenerator = new Random();
	  do
	  {
	    ertragsnummer = zufallsgenerator.nextInt(11) + 2;
	    
	  }while(ertragsnummer == 7);
	 
	}

  public RohstoffTyp getRohstoffTyp()
  {
    return rohstoffTyp;
  }

  private void setRohstoffTyp(RohstoffTyp rohstoffTyp)
  {
    this.rohstoffTyp = rohstoffTyp;
  }

}
