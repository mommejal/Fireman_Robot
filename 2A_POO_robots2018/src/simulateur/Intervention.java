package simulateur;

import javax.swing.text.html.MinimalHTMLWriter;

import robot.Robot;

public class Intervention extends Evenement {

	public Intervention(Robot robot) {
		super(robot);
	}

	@Override
	public long getDuree() {
		// Renvoie la durée de l'intervention
		return (long)(Math.min(robot.getPosition().getIncendie()/robot.getDebit(), robot.getVolume()/robot.getDebit()));
	}

	@Override
	public void execute() {
		robot.deverserEau(robot.getPosition().getIncendie());
	}

	@Override
	public String toString() {
		return "Intervention [robot=" + robot + "]";
	}

}
