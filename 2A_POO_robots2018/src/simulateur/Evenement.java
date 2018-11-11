package simulateur;

import robot.Robot;

public abstract class Evenement {

	private long date;
	protected Robot robot;
	private long duree;

	public Evenement(Robot robot) {
		this.robot = robot;
	}

	public long getDate() {
		return date;
	}

	
	
	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	abstract public long getDuree();

	abstract public void execute();
}