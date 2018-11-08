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

	
	
	public void setDate(long date) {
		this.date = date;
	}

	abstract public long getDuree();

	abstract public void execute();
	
	
}