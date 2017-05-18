import java.util.Random;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Spieler
{
	private Farbe farbe;
	private String name;

	public Spieler(Farbe farbe, String name)
	{
		this.farbe = farbe;
		this.name = name;
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
}