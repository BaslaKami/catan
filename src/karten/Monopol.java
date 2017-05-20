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
    // TODO Benutzereingabe zum Festlegen des gewollten Rohstofftyps
    RohstoffTyp r = RohstoffTyp.ENERGIE;
    
    for(int i = 0; i < s.getSpiel().getSpielerListe().getSize(); i++)
    {
      if(s.getId() != s.getSpiel().getSpielerListe().getSpieler(i).getId())
      {
        s.getRohstoffe().addRohstoffe(r, s.getSpiel().getSpielerListe().getSpieler(i).getRohstoffe().getUndLoescheAlleRohstoffe(r));
      }
    }
  }


}
