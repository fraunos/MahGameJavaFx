package pl.fraunos.fxgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	double x, y;
	int speed = 2;
	double direction = 0;
	boolean isMoving = false;
	int currentState = 1;
	Image image = SpriteLoader.getSprite(currentState, 0);

	public void update(double mouseX, double mouseY) {
		if (isMoving) {
			if (Main.gameTime % 10 == 0) {
				currentState++;
				currentState %= 2;
			}
			if (Main.kp.up) {
				y -= 2 * speed * Math.sin(Math.toRadians(direction));
				x -= 2 * speed * Math.cos(Math.toRadians(direction));
			}
			if (Main.kp.down) {
				y += speed * Math.sin(Math.toRadians(direction));
				x += speed * Math.cos(Math.toRadians(direction));
			}
			if (Main.kp.left) {
				y += speed * Math.cos(Math.toRadians(direction));
				x -= speed * Math.sin(Math.toRadians(direction));

			}
			if (Main.kp.right) {
				y -= speed * Math.cos(Math.toRadians(direction));
				x += speed * Math.sin(Math.toRadians(direction));
			}
		} else {
			currentState = 4;
		}
		updateSprite();

		setX(x);
		setY(y);
		direction = Math.toDegrees(Math.atan2(mouseY - getCenterY(y), mouseX - getCenterX(x))) + 180;
		setRotate(direction - 90);
	}

	public double getCenterX(double x) {
		return x + 128;

	}

	public double getCenterY(double y) {
		return y + 128;

	}

	Player(int x, int y) {
		setScaleX(SpriteLoader.scale);
		setScaleY(SpriteLoader.scale);
		setImage(image);
		this.x = x;
		this.y = y;
	}

	private void updateSprite() {
		setImage(SpriteLoader.getSprite(currentState, 0));
	}

}
