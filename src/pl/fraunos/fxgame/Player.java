package pl.fraunos.fxgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Player extends ImageView {
	double x, y;
	double tempX, tempY;
	double deltaX, deltaY;
	int speed = 2;
	double direction = 0;
	boolean isMoving = false;
	int currentState = 1;
	Image image = SpriteLoader.getSprite(currentState, 0);
	AudioClip[] ac = { new AudioClip(Main.class.getResource("res/kroki.mp3").toString()), new AudioClip(Main.class.getResource("res/kroki-2.mp3").toString()) };

	public void update(double mouseX, double mouseY) {

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

		if (isMoving && Main.kp.up) {
			if (Main.gameTime % 10 == 0) {
				currentState++;
				currentState %= 2;
				playSound(currentState);
			}
		} else if (isMoving) {
			if (Main.gameTime % 20 == 0) {
				currentState++;
				currentState %= 2;
				playSound(currentState);
			}
		} else {
			currentState = 4;
		}
		checkMovement();
		updateSprite();

		// setX(x);
		// setY(y);
		direction = Math.toDegrees(Math.atan2(mouseY - Main.sizeY / 2, mouseX - Main.sizeX / 2)) + 180;
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
		setX(x - 128);
		setY(y - 128);
		setImage(image);
		this.x = x;
		this.y = y;
	}

	private void updateSprite() {
		// changeSprite();
		setImage(SpriteLoader.getSprite(currentState, 0));
	}

	// private void changeSprite() {
	// if (Main.gameTime % 10 == 0) {
	// currentState++;
	// currentState %= 2;
	// }
	// }

	private void checkMovement() {
		if (x == tempX && y == tempY) {
			isMoving = false;
		} else {
			isMoving = true;
		}
		deltaX = x - tempX;
		deltaY = y - tempY;
		tempX = x;
		tempY = y;
	}

	private void playSound(int x) {
		ac[x % 2].play();

	}

}
