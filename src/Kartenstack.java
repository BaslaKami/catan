import java.util.LinkedList;
import java.util.List;

public class Kartenstack
{
  List<Karte> kartenStack;
  
  /** 25 Entwicklungskarten (14 × Ritter, 6 × Fortschritt und 5 × Siegpunkte) */
  public Kartenstack()
  {
    kartenStack = new LinkedList<Karte>();
    
    //Ritterkarten generieren
    for(int i = 0; i < 14; i++)
    {
      kartenStack.add(new Karte("Ritter", ""));
    }
    
    //Fortschrittskarten(Straßenbau, Monopol, Erfindung)  generieren
    for(int i = 0; i < 2; i++)
    {
      kartenStack.add(new Karte("Straßenbau", ""));
      kartenStack.add(new Karte("Monopol", ""));
      kartenStack.add(new Karte("Erfindung", ""));
    }
    
    //Siegpunktkarten generieren
    for(int i = 0; i < 5; i++)
    {
      kartenStack.add(new Karte("Siegpunkt", ""));
    }
  }
}
