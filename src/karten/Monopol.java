package karten;

import main.RohstoffTyp;
import main.Spieler;

public class Monopol extends Karte
{

  public Monopol()
  {
    super(KartenTyp.MONOPOL);
  }

  @Override
  public void ausspielen(Spieler s)
  {
    RohstoffTyp []typen = RohstoffTyp.values();
    int benutzereingabe = s.getSpiel().getBenutzereingabe().getInteger("Auf welchen Rohstoff soll Monopol angemeldet werden(0-4)");
    RohstoffTyp r = typen[benutzereingabe];
    
    for(int i = 0; i < s.getSpiel().getSpielerListe().getSize(); i++)
    {
      if(s.getId() != s.getSpiel().getSpielerListe().getSpieler(i).getId())
      {
        s.getRohstoffe().addRohstoffe(r, s.getSpiel().getSpielerListe().getSpieler(i).getRohstoffe().getUndLoescheAlleRohstoffe(r));
      }
    }
  }


}
