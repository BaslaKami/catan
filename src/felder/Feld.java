package felder;
/**
 * Created by Dustin on 17.05.2017.
 */
public class Feld
{
	private FeldTyp typ;


	public Feld(FeldTyp typ)
	{
	  this.typ = typ;
	}
	
	public FeldTyp getTyp()
	{
	  return typ;
	}
}
