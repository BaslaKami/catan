package felder;
import java.util.Random;

import main.RohstoffTyp;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Planet extends Feld
{
  private int ertragsnummer;
  private RohstoffTyp rohstoff;
  private Weltraumpirat weltraumpirat;

  public Planet(RohstoffTyp rohstoffTyp)
  {
    super(FeldTyp.PLANET);
    setRohstoff(rohstoffTyp);
    /* TODO: gleiche Ertragsnummern dürfen nicht angrenzen. Es gibt die
     folgenden Ertragsnummern 5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5,
     6, 3 und 11 */
    Random zufallsgenerator = new Random();
    do
    {
      ertragsnummer = zufallsgenerator.nextInt(11) + 2;

    } while (ertragsnummer == 7);

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
