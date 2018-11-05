package simulateur;

import carte.Direction;
import robot.Robot;

public class Deplacement extends Evenement {

	Direction dir;

	public Deplacement(long date, Direction dir, Robot robot) {
		super(date,robot);
		this.dir = dir;
	}

	@Override
	public void execute() {
		robot.move(dir);
	}

}
