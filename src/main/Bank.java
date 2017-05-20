package main;

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
        s.getRohstoffe().subRohstoffe(verkauf, 3);
        break;
      }
      case SPEZIAL:
      { 
        s.getRohstoffe().subRohstoffe(verkauf, 2);
        break;
      }
      case VIER_ZU_EINS:
      default:
      { 
        s.getRohstoffe().subRohstoffe(verkauf, 4);
        break;
      }
    }
    s.getRohstoffe().addRohstoffe(einkauf, 1);
  }

  public void handelMitSpieler(Spieler verkaufer, Spieler kaeufer, RohstoffTyp verkaufRohstoffTyp, RohstoffTyp einkaufRohstoffTyp, int anzahlEinkauf, int anzahlVerkauf)
  {
    verkaufer.getRohstoffe().subRohstoffe(verkaufRohstoffTyp, anzahlVerkauf);
    kaeufer.getRohstoffe().subRohstoffe(einkaufRohstoffTyp, anzahlEinkauf);
    verkaufer.getRohstoffe().addRohstoffe(einkaufRohstoffTyp, anzahlEinkauf);
    kaeufer.getRohstoffe().addRohstoffe(verkaufRohstoffTyp, anzahlVerkauf);
  }
}
