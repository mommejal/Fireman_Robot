package carte;

public class Case{
	public int ligne, colonne;
	private NatureTerrain nature = NatureTerrain.TERRAIN_LIBRE;
	
	
	public int getLigne() {
		return ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public NatureTerrain getNature() {
		return nature;
	}	
}



