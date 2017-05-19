import java.util.Scanner;

public class Spiel
{
	private SpielerListe spielerListe;
	private Spielfeld spielfeld;
	private Bank bank;
	private Scanner scanner;

	public static void main(String[] args)
	{
		Spiel spiel = new Spiel();

		//spiel.erstelleSpiel();
		
		spiel.start();
		
	}

	
	/*
	 *  1.  Abfrage Anzahl Spieler
   *  2.  Spiel erzeugen
   *      a.  Spieler erzeugen
   *      b.  Spielfeld erzeugen
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
	  /**      a.  Spieler erzeugen */
	  spielerListe = new SpielerListe();
    scanner = new Scanner(System.in);
		erstelleSpieler();
		
		/**      b.  Spielfeld erzeugen */
		spielfeld = new Spielfeld();
		
    //TODO:/**      c.  Kartenstack erzeugen */

    //TODO:/**      d.  Weltraumpiraten erzeugen */
		
		/**      e.  Bank erzeugen */
		bank = new Bank();		
		
	//TODO:/** 3.  Spieler 1 platziert zwei Straßen und zwei Siedlungen */
	//TODO:/** 4.  Spieler 2 platziert zwei Straßen und zwei Siedlungen */
	//TODO:/** 5.  Spieler 3 platziert zwei Straßen und zwei Siedlungen */
	//TODO:/** 6.  Spieler 4 platziert zwei Straßen und zwei Siedlungen (wenn existent) */
	//TODO:/** 7.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
	//TODO:/** 8.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
	//TODO:/** 9.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt */
	//TODO:/**10.  Spieler 1 bekommt die Rohstoffe für eine Siedlung ausgezahlt (wenn existent) */
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

						Spieler s = new Spieler(farbe, name);
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
