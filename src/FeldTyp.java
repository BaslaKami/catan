
public enum FeldTyp
{
  PLANET("Planet", 1), WURMLOCH("Wurmloch", 2), KOLONIE("Kolonie",3), METROPOLE("Metropole", 4);
  
  private final String typ;
  private final int nummer;

  private FeldTyp(String typ, int n)
  {
    nummer = n;
    this.typ = typ;
  }

  public String getRohstoff()
  {
    return typ;
  }

  public int getNummer()
  {
    return nummer;
  } 
}
