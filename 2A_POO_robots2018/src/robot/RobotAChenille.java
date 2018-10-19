package robot;

public class RobotAChenille extends Robot {
	
	int reservoirmax = 2000;
	int debit = 100/5;
	double vitesse = 60;
	
	
	@Override
	public void deverserEau(int vol) {
		if (volume>debit) {
			volume -= debit;
		}
		
	}

	@Override
	public void remplirReservoir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVitesse(double vitesse) {
		// TODO Auto-generated method stub
		super.setVitesse(vitesse);
	}
	
}
