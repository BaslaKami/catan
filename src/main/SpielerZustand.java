package main;

/**
 * Created by Dustin on 29.05.2017.
 */
public abstract class SpielerZustand
{
  protected  Spieler spieler;

  public  SpielerZustand(Spieler spieler)
  {
    this.spieler = spieler;
  }

  public abstract void zugFestlegen();
}
