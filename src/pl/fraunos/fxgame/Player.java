package pl.fraunos.fxgame;

import javafx.scene.shape.Circle;

public class Player extends Circle {
	int x, y;
	int speed = 2;

	public void update() {
		if (Main.kp.down)
			y += speed;
		if (Main.kp.up)
			y -= speed;
		if (Main.kp.left)
			x -= speed;
		if (Main.kp.right)
			x += speed;

		this.setCenterX(x);
		this.setCenterY(y);
		// System.out.println(isMoving);
	}

	Player(int x, int y) {
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(10);
	}

}
