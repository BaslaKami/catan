package karten;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Kartenstack
{
  List<Karte> kartenstack;
  
  /** 25 Entwicklungskarten (14 � Ritter, 6 � Fortschritt und 5 � Siegpunkte) */
  public Kartenstack()
  {
    kartenstack = new LinkedList<Karte>();
    
    //Ritterkarten generieren
    for(int i = 0; i < 14; i++)
    {
      kartenstack.add(new Ritter());
    }
    
    //Fortschrittskarten(Stra�enbau, Monopol, Erfindung)  generieren
    for(int i = 0; i < 2; i++)
    {
      kartenstack.add(new Strassenbau());
      kartenstack.add(new Monopol());
      kartenstack.add(new Erfindung());
    }
    
    //Siegpunktkarten generieren
    for(int i = 0; i < 5; i++)
    {
      kartenstack.add(new Siegpunkt());
    }
  }
  
  public Karte ziehen()
  {
    int zufallszahl = new Random().nextInt(kartenstack.size());
    Karte karte = kartenstack.get(zufallszahl);
    kartenstack.remove(zufallszahl);
    
    return karte;
  }
  
  public void zur�cklegen(Karte karte)
  {
    kartenstack.add(karte);
  }
}