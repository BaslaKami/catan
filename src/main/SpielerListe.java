package main;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dustin on 17.05.2017.
 */
public class SpielerListe
{
	private List<Spieler> spieler;

	public SpielerListe()
	{
		spieler = new ArrayList<Spieler>();
	}

	public void hinzufuegen(Spieler s)
	{
		spieler.add(s);
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

	public List<Spieler> getListe() { return spieler; }
	
	public int getSize()
	{
	  return spieler.size();
	}
}
