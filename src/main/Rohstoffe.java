package main;

import java.util.Random;

public class Rohstoffe
{
  private int[] rohstoffe;

  public Rohstoffe()
  {
    rohstoffe = new int[5];
    for (int i = 0; i < rohstoffe.length; i++)
    {
      rohstoffe[i] = 0;
    }
  }

  public Rohstoffe(int energie, int nahrung, int roboter, int mineralien, int munition)
  {
    this();
    rohstoffe[RohstoffTyp.ENERGIE.getNummer()] = energie;
    rohstoffe[RohstoffTyp.NAHRUNG.getNummer()] = nahrung;
    rohstoffe[RohstoffTyp.ROBOTER.getNummer()] = roboter;
    rohstoffe[RohstoffTyp.MINERALIEN.getNummer()] = mineralien;
    rohstoffe[RohstoffTyp.MUNITION.getNummer()] = munition;
  }

  public int getRohstoffe(RohstoffTyp typ)
  {
    return rohstoffe[typ.getNummer()];
  }

  public void setRohstoffe(RohstoffTyp typ, int rohstoffe)
  {
    if (rohstoffe >= 0)
    {
      this.rohstoffe[typ.getNummer()] = rohstoffe;
    }
    else
    {
      // TODO: Exception handling
      System.out.println("Fehler in Rohstoffe: subRohstoffe bekommt negativen Wert");
    }
  }

  public void addRohstoffe(Rohstoffe r)
  {
    for (int i = 0; i < rohstoffe.length; i++)
    {
      rohstoffe[i] += r.rohstoffe[i];
    }
  }

  public void subRohstoffe(Rohstoffe r)
  {
    if (ausreichendRohstoffeVorhanden(r))
    {
      for (int i = 0; i < rohstoffe.length; i++)
      {
        rohstoffe[i] -= r.rohstoffe[i];
      }
    }
    else
    {
      System.out.println("Fehler in Rohstoffe: subRohstoffe(Rohstoffe r) bekommt negativen Wert");
    }
  }

  public void subRohstoffe(RohstoffTyp typ, int anzahl)
  {
    if (getRohstoffe(typ) - anzahl >= 0)
    {
      setRohstoffe(typ, getRohstoffe(typ) - anzahl);
    }
    else
    {
      // TODO: Exception handling
      System.out.println("Fehler in Rohstoffe: subRohstoffe(RohstoffTyp typ, int anzahl) bekommt negativen Wert");
    }
  }

  public boolean ausreichendRohstoffeVorhanden(Rohstoffe rohstoffe)
  {
    for (int i = 0; i < this.rohstoffe.length; i++)
    {
      if (this.rohstoffe[i] < rohstoffe.rohstoffe[i])
      {
        return false;
      }
    }
    return true;
  }

  public void addRohstoffe(RohstoffTyp typ, int anzahl)
  {
    if (anzahl >= 0)
    {
      setRohstoffe(typ, getRohstoffe(typ) + anzahl);
    }
    else
    {
      // TODO: Exception handling
      System.out.println("Fehler in Rohstoffe: addRohstoffe bekommt negativen Wert");
    }
  }

  public void entferneZufaellig(int anzahl)
  {
    Random zufallsgenerator = new Random();
    int typ;

    for (int i = 0; i < anzahl; i++)
    {
      do
      {
        typ = zufallsgenerator.nextInt(rohstoffe.length);
      } while (rohstoffe[typ] == 0);
      rohstoffe[typ]--;
    }
  }

  public RohstoffTyp entferneZufaelligEinenRohstoff()
  {
    Random zufallsgenerator = new Random();
    int typ;
    do
    {
      typ = zufallsgenerator.nextInt(rohstoffe.length);
    } while (rohstoffe[typ] == 0);
    rohstoffe[typ]--;

    RohstoffTyp[] rT = RohstoffTyp.values();
    return rT[typ];
  }

  public int getUndLoescheAlleRohstoffe(RohstoffTyp typ)
  {
    int anzahl = getRohstoffe(typ);
    setRohstoffe(typ, 0);
    return anzahl;
  }

  public int anzahlDerGesamtenRohstoffe()
  {
    int anzahl = 0;

    for (int i : rohstoffe)
    {
      anzahl += i;
    }
    return anzahl;
  }

  public void print()
  {
    RohstoffTyp[] r = RohstoffTyp.values();
    for (int i = 0; i < rohstoffe.length; i++)
    {
      System.out.println(r[i] + " " + rohstoffe[i]);
    }
    System.out.println();
  }
}
