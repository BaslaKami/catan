
public class Gebaeude
{
	private String name;
	private int feld;
	private int kostenEnergie;
	private int kostenNahrung;
	private int kostenRoboter;
	private int kostenMineralien;
	private int kostenMunition;

	public Gebaeude(String name, int feld, int kostenEnergie, int kostenRoboter, int kostenMineralien, int kostenMunition)
	{
		this.name = name;
		this.feld = feld;
		this.kostenEnergie = kostenEnergie;
		this.kostenRoboter = kostenRoboter;
		this.kostenMineralien = kostenMineralien;
		this.kostenMunition = kostenMunition;
	}
}
