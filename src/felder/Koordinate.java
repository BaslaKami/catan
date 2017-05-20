package felder;

public class Koordinate
{
  private int x;
  private int y;
  
  public Koordinate(int posX, int posY)
  {
    setPos(posX, posY);
  }
  
  public void setPos(int posX, int posY)
  {
    setPosX(posX);
    setPosY(posY);
  }

  public int getPosX()
  {
    return x;
  }

  public void setPosX(int posX)
  {
    this.x = posX;
  }

  public int getPosY()
  {
    return y;
  }

  public void setPosY(int posY)
  {
    this.y = posY;
  }
  
  public void print()
  {
    System.out.println(x + ";" + y);
  }
}
