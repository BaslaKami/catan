package felder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.RohstoffTyp;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Planet extends Feld
{
  private final static int []MOEGLICHE_ERTRAEGE = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
  private int ertragsnummer;
  private RohstoffTyp rohstoff;
  private Weltraumpirat weltraumpirat;

  public Planet(RohstoffTyp rohstoffTyp, int ertragsIndex)
  {
    super(FeldTyp.PLANET);

    setRohstoff(rohstoffTyp);
    
    ertragsnummer = MOEGLICHE_ERTRAEGE[ertragsIndex];

  }

  public RohstoffTyp getRohstoff()
  {
    return rohstoff;
  }

  public void setRohstoff(RohstoffTyp rohstoff)
  {
    this.rohstoff = rohstoff;
  }
  
  public int getErtragsnummer()
  {
    return ertragsnummer;
  }

  public Weltraumpirat getWeltraumpirat()
  {
    return weltraumpirat;
  }

  public void setWeltraumpirat(Weltraumpirat weltraumpirat)
  {
    this.weltraumpirat = weltraumpirat;
  }
}
