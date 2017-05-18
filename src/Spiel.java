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

	public Spiel()
	{
		//spielerListe = new SpielerListe();
		//scanner = new Scanner(System.in);
		//erstelleSpieler();
		
		spielfeld = new Spielfeld();
		bank = new Bank();

		// Generiere Karten stack		
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
