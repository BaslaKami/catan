
public enum Farbe
{
	BLAU("blue", 0), GELB("green", 1), GRÜN("green", 2), ROT("red", 3);

	private final String farbe;
	private final int wert;

	Farbe(String farbe, int wert)
	{
		this.farbe = farbe;
		this.wert = wert;
	}

	public String getFarbe()
	{
		return farbe;
	}

	public int getWert()
	{
		return wert;
	}
}
