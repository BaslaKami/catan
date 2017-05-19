package karten;

public abstract class Karte
{
	private KartenTyp kartenTyp;
	
	public Karte(KartenTyp kartenTyp)
	{
	  this.setKartenTyp(kartenTyp);
	}

  public KartenTyp getKartenTyp()
  {
    return kartenTyp;
  }

  public void setKartenTyp(KartenTyp kartenTyp)
  {
    this.kartenTyp = kartenTyp;
  }
  
  public abstract void ausspielen();
}
