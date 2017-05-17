import java.util.Scanner;

public class Spiel {
    private SpielerListe spielerListe;

    public static void main(String[] args) {
	    Spiel spiel = new Spiel();
	    spiel.erstelleSpiel();
    }

    private void erstelleSpiel() {
        System.out.println("***Spiel wird erstellt***");
        spielerListe = new SpielerListe(anzahlSpieler());

        spielerListe.getSpielerByNummer(1);
    }

    private int anzahlSpieler() {
        Scanner scanner = new Scanner(System.in);
        int anzahl = 0;
        while(anzahl != 3 && anzahl!= 4) {
        System.out.print("Spieleranzahl eingeben(3-4): ");
            anzahl= scanner.nextInt();
        }
        return anzahl;
    }
}
