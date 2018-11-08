package simulateur;

import javax.swing.text.html.MinimalHTMLWriter;

import robot.Robot;

public class Intervention extends Evenement{
	private Robot robot;

	public Intervention(long date, Robot robot) {
		super(date, robot);
	}
	
	
	
	@Override
	public long getDuree() {
		// Renvoie la durée de l'intervention
		return (long)Math.ceil(Math.min(robot.getPosition().getIncendie()/robot.getDebit(), robot.getVolume()/robot.getDebit()));
	}



	@Override
	public void execute() {
		robot.deverserEau(robot.getPosition().getIncendie());
	}
	
	

}
