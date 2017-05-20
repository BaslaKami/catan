import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import felder.Gebaeude;
import felder.Kolonie;
import felder.Koordinate;
import felder.Metropole;
import felder.RohstoffTyp;
import felder.Weltraumpirat;
import felder.Wurmloch;

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
  private static int idCounter = 0;
  private int id;
	private Farbe farbe;
	private String name;
	private Rohstoffe rohstoffe;
	private List<Wurmloch> wurmlochListe;
	private List<Metropole> metropolenListe;
	private List<Kolonie> kolonienListe;
	private Spielfeld spielfeld;
	
	public Spieler(Farbe farbe, String name, Spielfeld spielfeld)
	{
	  id = idCounter++;
		this.farbe = farbe;
		this.name = name;
		this.spielfeld = spielfeld;
		
		wurmlochListe = new LinkedList<Wurmloch>();
		metropolenListe = new LinkedList<Metropole>();
		kolonienListe = new LinkedList<Kolonie>();

		setRohstoffe(new Rohstoffe());
	}


	public void zug()
	{
	  wuerfeln();
	  
	}
	
  public void baueWurmloch(Koordinate k)
	{
	  Wurmloch w = new Wurmloch(k, id);
	  wurmlochListe.add(w);
	  spielfeld.setzeWurmloch(w);
	}
	
	public void baueKolonie(Koordinate k)
  {
    Kolonie kolonie = new Kolonie(k, id);
    kolonienListe.add(kolonie);
    spielfeld.setzeKolonie(kolonie);
  }
	
	public void baueMetropole(Koordinate k)
  {
	  //TODO: Es muss noch getestet werden ob das Kolonie-Objekt aus der Liste vom Spieler gelöscht wird.
    Metropole m = new Metropole(k, id);
    metropolenListe.add(m);
    kolonienListe.remove(spielfeld.setzeMetropole(m));
  }
	
	public void haelfteDerRohstoffeWerdenEntfernt()
	{
	  int anzahlRohstoffe = rohstoffe.anzahlDerGesamtenRohstoffe();
	  
	  if(anzahlRohstoffe > 7)
	  {
	    rohstoffe.entferneZufällig(anzahlRohstoffe/2);
	  }
	}
	
	public void getAlleRohstoffevonKolonie(Koordinate k)
	{
	  rohstoffe.addRohstoffe(spielfeld.getAlleRohstoffevonKolonie(k));
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
		return new Random().nextInt(11) + 2;
	}
	
  private void setRohstoffe(Rohstoffe rohstoffe)
  {
    this.rohstoffe = rohstoffe; 
  }
	
  public Rohstoffe getRohstoffe()
  {
    return rohstoffe;
  }


  public int getId()
  {
    return id;
  }


  public void setId(int id)
  {
    this.id = id;
  }


  public void bewegeWeltraumpirat(Koordinate koordinate, Weltraumpirat w)
  {
    List<Gebaeude> gebaeudeListe = spielfeld.bewegeWeltraumpirat(koordinate, w);
    
    for(Gebaeude g: gebaeudeListe)
    {
      if(g.getSpielerId() != id)
      {
        
      }
    }
  }  
}