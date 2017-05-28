package karten;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Kartenstack
{
  List<Karte> kartenstack;
  
  /** 25 Entwicklungskarten (14 x Ritter, 6 x Fortschritt und 5 x Siegpunkte) */
  public Kartenstack(int ritterKarten, int fortschrittskarten, int siegpunktKarten)
  {
    kartenstack = new LinkedList<Karte>();
    
    //Ritterkarten generieren
    for(int i = 0; i < ritterKarten; i++)
    {
      kartenstack.add(new Ritter());
    }
    
    //Fortschrittskarten(Strassenbau, Monopol, Erfindung)  generieren
    for(int i = 0; i < fortschrittskarten; i++)
    {
      kartenstack.add(new Wurmlochbau());
      kartenstack.add(new Monopol());
      kartenstack.add(new Subvention());
    }
    
    //Siegpunktkarten generieren
    for(int i = 0; i < siegpunktKarten; i++)
    {
      kartenstack.add(new Siegpunkt());
    }
  }
  
  public Kartenstack()
  {
    this(14,2,5);
  }
  
  public Karte ziehen()
  {
    int zufallszahl = new Random().nextInt(kartenstack.size());
    Karte karte = kartenstack.get(zufallszahl);
    kartenstack.remove(zufallszahl);
    
    return karte;
  }
  
  public void zuruecklegen(Karte karte)
  {
    kartenstack.add(karte);
  }
}
