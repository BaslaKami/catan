package main;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dustin on 17.05.2017.
 */
public class SpielerListe
{
	private List<Spieler> spieler;
	private int anzahl = 0;

	public SpielerListe()
	{
		spieler = new ArrayList<Spieler>();
	}

	public void hinzufügen(Spieler s)
	{
		spieler.add(s);
		this.anzahl++;
	}

	public void ausgeben()
	{	
		for(Spieler s : spieler)
		{
			System.out.println(s.getName() + " " + s.getFarbe());
		}

	}
	
	public Spieler getSpieler(int nummer)
	{
		return spieler.get(nummer);
	}
	
	public int getSize()
	{
	  return spieler.size();
	}
}
