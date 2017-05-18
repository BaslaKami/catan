
public enum RohstoffTyp
{
  ENERGIE("Energie", 1), NAHRUNG("Nahrung", 2), ROBOTER("Roboter",3), MINERALIEN("Mineralien", 4), MUNITION("Muniton", 5);

  private final String rohstoff;
  private final int nummer;

  private RohstoffTyp(String r, int n)
  {
    nummer = n;
    rohstoff = r;
  }

  public String getRohstoff()
  {
    return rohstoff;
  }

  public int getNummer()
  {
    return nummer;
  }  
}
