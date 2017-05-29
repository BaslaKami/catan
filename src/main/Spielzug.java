package main;

/**
 * Created by Dustin on 29.05.2017.
 */
public class Spielzug extends SpielerZustand
{
  public Spielzug(Spieler spieler)
  {
    super(spieler);
  }

  @Override
  public String toString() {
    return "Spielzug";
  }

  @Override
  public void zugFestlegen()
  {
    System.out.print( spieler.getName() + " ist nun am Zug!");
  }
}
