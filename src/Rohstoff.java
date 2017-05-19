
public class Rohstoff
{
	private RohstoffTyp typ;

	public Rohstoff(RohstoffTyp typ)
	{
		this.setTyp(typ);
	}

  public RohstoffTyp getTyp()
  {
    return typ;
  }

  public void setTyp(RohstoffTyp typ)
  {
    this.typ = typ;
  }

}
