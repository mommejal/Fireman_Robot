package carte;

public class Case {
	public int ligne, colonne;
	private NatureTerrain nature = NatureTerrain.TERRAIN_LIBRE;
	protected int nbLignes;
	protected int nbColonnes;

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public NatureTerrain getNature() {
		return nature;
	}

//	public void deplacer(Direction dir) {
//		switch (dir) {
//		// Faut il empecher ici les mouvements en dehors de la carte ?
//		case NORD:
//			ligne--;
//		case SUD:
//			ligne++;
//		case EST:
//			colonne++;
//		case OUEST:
//			colonne--;
//		default:
//		}
//	}

	public boolean voisinExiste(Case src, Direction dir) {
		if (dir == Direction.NORD) {
			return src.ligne != 0;
		}

		else if (dir == Direction.SUD) {
			return src.ligne != (this.nbLignes - 1);
		}

		else if (dir == Direction.EST) {
			return src.colonne != (this.nbColonnes - 1);
		}

		else {
			return src.colonne != 0;
		}
	}

	public Case getVoisin(Case src, Direction dir) {
		if (this.voisinExiste(src, dir)) {

			if (dir == Direction.NORD) {
				return this.getCase(src.ligne - 1, src.colonne);
			}

			else if (dir == Direction.SUD) {
				return this.getCase(src.ligne + 1, src.colonne);
			}

			else if (dir == Direction.EST) {
				return this.getCase(src.ligne, src.colonne + 1);
			}

			else {
				return this.getCase(src.ligne, src.colonne - 1);
			}

		} else {
			return this.getCase(src.ligne, src.colonne);
		}
	}

}
