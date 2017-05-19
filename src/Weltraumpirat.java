import gebaeude.Koordinate;

public class Weltraumpirat
{
  Koordinate position;
	
  public Weltraumpirat()
	{
		position = new Koordinate(Spielfeld.getHoehe()/2, Spielfeld.getBreite()/2);
	}
  
  public void bewegen(int x, int y)
  {
    position.setPos(x, y);
  }
  
  public void printPos()
  {
    System.out.print("Position Weltraumpirat: ");
    position.print();
  }
}

