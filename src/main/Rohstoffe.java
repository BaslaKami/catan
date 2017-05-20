package main;

import java.util.Random;

import felder.RohstoffTyp;

public class Rohstoffe
{
  private int[] rohstoffe;

  public Rohstoffe()
  {
    rohstoffe = new int[5];
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
    this.rohstoffe[typ.getNummer()] = rohstoffe;
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
    for (int i = 0; i < rohstoffe.length; i++)
    {
      rohstoffe[i] -= r.rohstoffe[i];
    }
  }

  public void subRohstoffe(RohstoffTyp typ, int anzahl)
  {
    setRohstoffe(typ, getRohstoffe(typ) - anzahl);
  }

  // TODO: Exception handling wenn mehr abgezogen wird als möglich
  public void addRohstoffe(RohstoffTyp typ, int anzahl)
  {
    setRohstoffe(typ, getRohstoffe(typ) + anzahl);
  }

  public void entferneZufällig(int anzahl)
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

  public RohstoffTyp entferneZufälligEinenRohstoff()
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
  
  public int getUndLöscheAlleRohstoffe(RohstoffTyp typ)
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
