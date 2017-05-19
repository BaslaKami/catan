import java.util.Random;

/**
 * Created by Dustin on 17.05.2017.
 */
public class Planet extends Feld
{
  private int ertragsnummer;
  private Rohstoff rohstoff;

  public Planet(RohstoffTyp rohstoffTyp)
  {
    setRohstoff(new Rohstoff(rohstoffTyp));
    /* TODO: gleiche Ertragsnummern dürfen nicht angrenzen. Es gibt die
     folgenden Ertragsnummern 5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5,
     6, 3 und 11 */
    Random zufallsgenerator = new Random();
    do
    {
      ertragsnummer = zufallsgenerator.nextInt(11) + 2;

    } while (ertragsnummer == 7);

  }

  public Rohstoff getRohstoff()
  {
    return rohstoff;
  }

  public void setRohstoff(Rohstoff rohstoff)
  {
    this.rohstoff = rohstoff;
  }
}
