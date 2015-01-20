import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

public class Player extends Circle {
	int x, y;
	boolean isMoving = false;
	char direction = 0;

	public void update() {
		this.move();
		this.setCenterX(x);
		this.setCenterY(y);
		// System.out.println(isMoving);
	}

	Player(int x, int y) {
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(10);
	}

	public void startMoving(KeyCode kc) {
		isMoving = true;
		switch (kc) {
		case W:
			direction = 'W';
			break;
		case S:
			direction = 'S';
			break;
		case A:
			direction = 'A';
			break;
		case D:
			direction = 'D';
			break;
		default:
			break;
		}

	}

	public void move() {
		if (isMoving) {
			if (direction == 'W')
				y--;
			if (direction == 'S')
				y++;
			if (direction == 'A')
				x--;
			if (direction == 'D')
				x++;
		}
	}

}
