package main;

/**
 * Created by Dustin on 29.05.2017.
 */
public class Beobachter extends SpielerZustand
{
  private SpielerZustand aktuellerZustand;

  public Beobachter(Spieler spieler)
  {
    super(spieler);
  }

  @Override
  public String toString() {
    return "Beobachter";
  }

  @Override
  public void zugFestlegen() {
    System.out.print( spieler.getName() + " ist nun Beobachter!");
  }
}
