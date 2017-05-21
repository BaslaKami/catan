package main;

import java.util.List;

public class Bank
{
  private Benutzereingabe benutzereingabe;

  public Bank(Benutzereingabe benutzereingabe)
  {
    this.benutzereingabe = benutzereingabe;
  }

  public void handelMitSpieler (Spieler verkaeufer, List<Spieler> spieler)
  {
    int eingabe, anzahlVerkauf, anzahlEinkauf;
    Spieler kaeufer;
    RohstoffTyp verkaufRohstoffTyp, einkaufRohstoffTyp;

    // Spieler mit dem man handeln möchte
    kaeufer = waehleGegenspieler(spieler, verkaeufer.getId());

    // Verkauf: Typ, Anzahl
    System.out.println("\n\033[32m - Verkauf -\033[0m");
    verkaufRohstoffTyp = waehleRohstoffTyp(verkaeufer.getRohstoffe());
    anzahlVerkauf = waehleRohstoffAnzahl(verkaeufer.getRohstoffe(), verkaufRohstoffTyp);

    // Kauf: Typ, Anzahl
    System.out.println("\n\033[32m - Kauf -\033[0m");
    einkaufRohstoffTyp = waehleRohstoffTyp(kaeufer.getRohstoffe());
    anzahlEinkauf = waehleRohstoffAnzahl(kaeufer.getRohstoffe(), einkaufRohstoffTyp);

    // Möchte der Gegenspieler die Tauschanfrage aktzeptieren?
    System.out.println("Handelsanfrage an " + kaeufer.getName() + ":\n" + verkaeufer.getName() + " bietet " + anzahlVerkauf + verkaufRohstoffTyp.getRohstoff() + " gegen " + anzahlEinkauf + einkaufRohstoffTyp);
    eingabe = benutzereingabe.getInteger("Annehmen?\n1 --> Ja\n2 --> Nein");

    // Handel durchführen
    if (eingabe == 1)
    {
      verkaeufer.getRohstoffe().subRohstoffe(verkaufRohstoffTyp, anzahlVerkauf);
      kaeufer.getRohstoffe().subRohstoffe(einkaufRohstoffTyp, anzahlEinkauf);
      verkaeufer.getRohstoffe().addRohstoffe(einkaufRohstoffTyp, anzahlEinkauf);
      kaeufer.getRohstoffe().addRohstoffe(verkaufRohstoffTyp, anzahlVerkauf);
    }
  }

  public Spieler waehleGegenspieler(List<Spieler> spieler, int verkaeuferId)
  {
    int eingabe, count = 1;
    String auswahlString = "Waehle einen Spieler:\n";
    Spieler ausgewaehlterSpieler = null;
    SpielerListe auswahlListe = new SpielerListe();

    for (Spieler s : spieler)
    {
      if(s.getId() != verkaeuferId)
      {
        auswahlListe.hinzufuegen(s);
        auswahlString += count++ + " --> " + s.getName() + "\n";
      }
    }

    do
    {
      eingabe = benutzereingabe.getInteger(auswahlString);
      if(eingabe > 0 && eingabe <= auswahlListe.getSize())
      {
        ausgewaehlterSpieler = auswahlListe.getSpieler(eingabe-1);
      }
    } while(ausgewaehlterSpieler == null);

    return ausgewaehlterSpieler;
  }

  public RohstoffTyp waehleRohstoffTyp(Rohstoffe vorhandeneRohstoffe)
  {
    int eingabe;
    boolean gueltigerRohstoff = false;
    RohstoffTyp[] rohstoffe = RohstoffTyp.values();
    String auswahlString = "Waehle einen Rohstoff:\n";

    for (RohstoffTyp typ : RohstoffTyp.values())
    {
      auswahlString += typ.getNummer() + 1 + " " + typ.getRohstoff() +"\n";
    }
    do
    {
      eingabe = benutzereingabe.getInteger(auswahlString) - 1;

      if(eingabe >= 0 && eingabe < rohstoffe.length)
      {
        if (vorhandeneRohstoffe.getRohstoffe(rohstoffe[eingabe]) <= 0)
        {
          System.out.println("\n\033[31mRohstoff nicht vorhanden!\033[0m");
        }
        else
        {
          gueltigerRohstoff = true;
        }
      }
    } while(!gueltigerRohstoff);

    return rohstoffe[eingabe];
  }

  public int waehleRohstoffAnzahl(Rohstoffe vorhandeneRohstoffe, RohstoffTyp rohstoffTyp)
  {
    int anzahl;
    boolean ausreichendVorhanden = false;
    do
    {
      anzahl = benutzereingabe.getInteger("Anzahl der " + rohstoffTyp.getRohstoff());
      if (anzahl > 0 && anzahl <= vorhandeneRohstoffe.getRohstoffe(rohstoffTyp))
      {
        ausreichendVorhanden = true;
      }
      else
      {
        System.out.println("\n\033[31mAnzahl nicht vorhanden!\033[0m");
      }
    } while (!ausreichendVorhanden);

    return anzahl;
  }
}
