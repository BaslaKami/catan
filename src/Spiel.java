import java.util.Scanner;

import felder.Koordinate;
import felder.Weltraumpirat;
import karten.Kartenstack;

public class Spiel
{
	private SpielerListe spielerListe;
	private Spielfeld spielfeld;
	private Bank bank;
	private Scanner scanner;
	private Kartenstack kartenstack;
	private Weltraumpirat weltraumpirat;

	public static void main(String[] args)
	{
		Spiel spiel = new Spiel();

		//spiel.erstelleSpiel();
		
		spiel.start();
		
	}

	
	/*
	 *  1.  Abfrage Anzahl Spieler
   *  2.  Spiel erzeugen
   *      a.  Spielfeld erzeugen
   *      b.  Spieler erzeugen
   *      c.  Kartenstack erzeugen
   *      d.  Weltraumpiraten erzeugen
   *      e.  Bank erzeugen
   *  3.  Spieler 1 platziert zwei Straßen und zwei Siedlungen
   *  4.  Spieler 2 platziert zwei Straßen und zwei Siedlungen
   *  5.  Spieler 3 platziert zwei Straßen und zwei Siedlungen
   *  6.  Spieler 4 platziert zwei Straßen und zwei Siedlungen (wenn existent)
   *  7.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt
   *  8.  Spieler 2 bekommt die Rohstoffe für eine Siedlung ausgezahlt
   *  9.  Spieler 3 bekommt die Rohstoffe für eine Siedlung ausgezahlt
   *  10. Spieler 4 bekommt die Rohstoffe für eine Siedlung ausgezahlt (wenn existent)
   *
	 */
	public Spiel()
	{
	  /** 1.  Abfrage Anzahl Spieler */
	  /** 2.  Spiel erzeugen */
	  /**      a.  Spielfeld erzeugen */
	  spielfeld = new Spielfeld();
		
		/**      b.  Spieler erzeugen */
    spielerListe = new SpielerListe();
    scanner = new Scanner(System.in);
    erstelleSpieler();
		
    /**      c.  Kartenstack erzeugen */
		kartenstack = new Kartenstack();

    /**      d.  Weltraumpiraten erzeugen */
		weltraumpirat = new Weltraumpirat();
		weltraumpirat.printPos();
		
		/**      e.  Bank erzeugen */
		bank = new Bank();		
		
		//TODO: Per Schleife und Eingabe umsetzen
		/** 3.  Spieler 1 platziert zwei Straßen und zwei Siedlungen */
		spielerListe.getSpieler(0).baueWurmloch(new Koordinate(4,3));
		spielerListe.getSpieler(0).baueWurmloch(new Koordinate(4,5));
		
		spielerListe.getSpieler(0).baueKolonie(new Koordinate(4,2));
    spielerListe.getSpieler(0).baueKolonie(new Koordinate(4,6));
	
		/** 4.  Spieler 2 platziert zwei Straßen und zwei Siedlungen */
    spielerListe.getSpieler(1).baueWurmloch(new Koordinate(8,13));
    spielerListe.getSpieler(1).baueWurmloch(new Koordinate(9,12));
    
    spielerListe.getSpieler(1).baueKolonie(new Koordinate(8,12));
    spielerListe.getSpieler(1).baueKolonie(new Koordinate(8,14));
		
    /** 5.  Spieler 3 platziert zwei Straßen und zwei Siedlungen */
    spielerListe.getSpieler(2).baueWurmloch(new Koordinate(4,19));
    spielerListe.getSpieler(2).baueWurmloch(new Koordinate(5,20));
    
    spielerListe.getSpieler(2).baueKolonie(new Koordinate(4,18));
    spielerListe.getSpieler(2).baueKolonie(new Koordinate(4,20));
    spielfeld.print();
	
    /** 6.  Spieler 4 platziert zwei Straßen und zwei Siedlungen (wenn existent) */
    //existiert aktuell nicht
    
    /** 7.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
    spielerListe.getSpieler(0).getAlleRohstoffevonKolonie(new Koordinate(4,2));
    System.out.println();
    System.out.println("Spieler 0");
    spielerListe.getSpieler(0).getRohstoffe().print();
    
    /** 8.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
    spielerListe.getSpieler(1).getAlleRohstoffevonKolonie(new Koordinate(8,14));
    System.out.println();
    System.out.println("Spieler 1");
    spielerListe.getSpieler(1).getRohstoffe().print();
    
    /** 9.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
    spielerListe.getSpieler(2).getAlleRohstoffevonKolonie(new Koordinate(4,20));
    System.out.println();
    System.out.println("Spieler 2");
    spielerListe.getSpieler(2).getRohstoffe().print();
    
    /**10.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt (wenn existent) */
    
    System.out.println("Zug 1");
    
    zug(spielerListe.getSpieler(0));
    
    //existiert aktuell nicht
	}
	
	 /*
   *  1.  Spieler würfelt Rohstofferträge aus
   *      a.  Die gewürfelte Zahl legt fest welches Feld Rohstoffe gibt. Jedes Dorf an diesem Feld bekommt einen Rohstoff dieser Art, jede Stadt zwei. Es gibt keine Rohstoffe von dem Feld auf dem der Räuber steht.
   *      b.  Falls die 7 Gewürfelt wird 
   *      c.  gibt jeder Spieler mit mehr als 7 Rohstoffen die Hälfte (abgerundet) ab.
   *      d.  Spieler stellt den Räuber um (aktuelle Feld ist nicht zulässig)
   *      e.  Der Spieler bekommt von den Spielern die auf dem neuen Feld des Räubers eine Siedlung oder Stadt haben eine zufällige Rohstoffkarte
   *  2.  Spieler handelt
   *      a.  Spieler darf beliebig oft handeln
   *  3.  Gebäude bauen, Entwicklungskarten kaufen
   *      a.  Entwicklungskarte kann zu jeder Zeit des Zuges (1-3) ausgespielt werden
   *      b.  Es kann pro Zug immer nur genau eine Entwicklungskarte ausgespielt werden
   *      c.  Die Entwicklungskarte welche ausgespielt wird darf nicht während des aktuellen Zuges gekauft worden sein
   *  4.  Zug beenden/nächster Spieler beginnt mit Punkt 1
   */
	private void zug(Spieler s)
	{
	  int zahl = s.wuerfeln();
	  System.out.println("Wurf: " + zahl);
	  
	  if(zahl == 7)
	  {
	    for(int i = 0; i < spielerListe.getSize(); i++)
      {
	      spielerListe.getSpieler(i).haelfteDerRohstoffeWerdenEntfernt();
      }
	    //TODO: Eingabe durch Benutzer von den Koordinaten des Weltraumpiraten
	    s.bewegeWeltraumpirat(new Koordinate(5,14), weltraumpirat);
	  }
	  else
	  {
	    for(int i = 0; i < spielerListe.getSize(); i++)
  	  {
  	    spielerListe.getSpieler(i).getRohstoffe().addRohstoffe(spielfeld.getRohstoffeFuerSpieler(spielerListe.getSpieler(i), zahl));
  	    spielerListe.getSpieler(i).getRohstoffe().print();
  	  }
	  }
	}

	private void erstelleSpieler()
	{
		System.out.println("***Spiel wird erstellt***");

		erstelleSpieler(anzahlSpieler());
		getSpielerListe().ausgeben();
		
	}

	private void erstelleSpieler(int anzahl)
	{
		String name = null;
		Farbe farbe = Farbe.BLAU;

		Farbe[] f = Farbe.values();

		for (int i = 0; i < anzahl; i++)
		{
			try
			{
				System.out.println("Spieler" + " " + i + " " + "Name eingeben");

				if (scanner.hasNext())
				{
					name = scanner.next();

					if (!name.isEmpty())
					{
						farbe = f[i];

						Spieler s = new Spieler(farbe, name, spielfeld);
						spielerListe.hinzufügen(s);
					}

				}

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}

	private int anzahlSpieler()
	{
		int anzahl = 0;
		do
		{
			System.out.println("Geben Sie die Spieleranzahl(3-4) ein:");
			anzahl = scanner.nextInt();

		} while (anzahl != 3 && anzahl != 4);

		return anzahl;

	}

	public void start()
	{
		
	}
	
	public SpielerListe getSpielerListe()
	{
		return this.spielerListe;
	}

	public void finalize()
	{
		scanner.close();
	}

}
