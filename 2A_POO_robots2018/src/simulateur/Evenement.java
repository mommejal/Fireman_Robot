package simulateur;

import robot.Robot;

public abstract class Evenement {

	private long date;
	protected Robot robot;

	public Evenement(long date, Robot robot) {
		this.date = date;
		this.robot = robot;
	}

	public long getDate() {
		return date;
	}

	abstract public void execute();
}