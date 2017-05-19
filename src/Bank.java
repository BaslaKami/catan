
public class Bank
{

  public Bank()
  {
    // TODO Auto-generated constructor stub
  }

  public void handelMitEnklave(Spieler s, RohstoffTyp verkauf, RohstoffTyp einkauf, HandelTyp handelTyp)
  {
    switch (handelTyp)
    {
      case DREI_ZU_EINS:
      {
        s.decRohstoffe(verkauf, 3);
        break;
      }
      case SPEZIAL:
      { 
        s.decRohstoffe(verkauf, 2);
        break;
      }
      case VIER_ZU_EINS:
      default:
      { 
        s.decRohstoffe(verkauf, 4);
        break;
      }
    }
    s.incRohstoffe(einkauf, 1);
  }
  
  public void handelMitSpieler(Spieler kaufer, Spieler verkaufer, RohstoffTyp verkauf, RohstoffTyp einkauf, int anzahlEinkauf, int anzahlVerkauf)
  {
    verkaufer.decRohstoffe(einkauf, anzahlEinkauf);
    kaufer.decRohstoffe(verkauf, anzahlVerkauf);
    verkaufer.incRohstoffe(verkauf, anzahlVerkauf);
    kaufer.incRohstoffe(einkauf, anzahlEinkauf);
  }
}
