package main;

public enum RohstoffTyp
{
  ENERGIE("Energie", 0), NAHRUNG("Nahrung", 1), ROBOTER("Roboter",2), MINERALIEN("Mineralien", 3), MUNITION("Muniton", 4);

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
  
  public RohstoffTyp getTypByInt(int n)
  {
    switch(n)
    {
      case 0:
      {
        return ENERGIE;
      }
      case 1:
      {
        return NAHRUNG;
      }
      case 2:
      {
        return ROBOTER;
      }
      case 3:
      {
        return MINERALIEN;
      }
      case 4:
      default:
      {
        return MUNITION;
      }
    }
  }
  
  public int getNummer()
  {
    return nummer;
  }  
}
