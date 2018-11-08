package simulateur;

import robot.Robot;

public abstract class Evenement {

	private long date;
	protected Robot robot;
	private long duree;

	public Evenement(long date, Robot robot) {
		this.date = date;
		this.robot = robot;
	}

	public long getDate() {
		return date;
	}

	
	
	abstract public long getDuree();

	abstract public void execute();
}